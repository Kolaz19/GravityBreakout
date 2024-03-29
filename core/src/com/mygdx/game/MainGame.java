package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainGame extends ApplicationAdapter implements ResizableScreen {
	private StateManager stateManager;
	private SpriteBatch batch;
	private World world;
	private Matrix4 debugMatrix;
	private OrthographicCamera cam;
	private Texture backgroundTexture;
	private Platform platform;
	private Ball ball;
	private TilesInLevel tiles;
	private Listener listener;
	private int backgroundWidth, backgroundHeight;
	private Stage stage;
	private ScoreLabel scoreLabel;
	private Score score;
	private AirScoreLinesRenderer lineRenderer;
	private AirScoreFlame airScoreFlame;
	private boolean stop, gameOver;
	private PauseMenu pauseMenu;
	private int level;
	private HighscoreLabel highscoreLabel;
	private WallSpawner wallSpawner;

	static final float PIXELS_TO_METERS = 7f;

	public MainGame(StateManager manager) {
		stateManager = manager;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,-10),true);
		//Textures
		backgroundTexture = new Texture(Gdx.files.internal("background.png"));

		backgroundHeight = backgroundTexture.getHeight();
		backgroundWidth = backgroundTexture.getWidth();
		cam = new OrthographicCamera(backgroundWidth, backgroundHeight);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);

		platform = new Platform(world,backgroundWidth);
		wallSpawner = new WallSpawner(world,backgroundWidth,backgroundHeight);
		wallSpawner.createLeftWall();
		wallSpawner.createUpperWall();
		wallSpawner.createRightWall();
		tiles = new TilesInLevel(this.world);
		listener = new Listener(ball,platform,tiles);
		world.setContactListener(listener);
		lineRenderer = new AirScoreLinesRenderer(cam);

		stage = new Stage(new FitViewport(backgroundWidth *PIXELS_TO_METERS,backgroundHeight* PIXELS_TO_METERS));
		scoreLabel = new ScoreLabel();

		stage.addActor(scoreLabel);
		score = new Score();
		highscoreLabel = new HighscoreLabel(score);
		stage.addActor(highscoreLabel);
		airScoreFlame = new AirScoreFlame(this.score);
		this.stop = this.gameOver = false;
		pauseMenu = new PauseMenu(batch, cam, backgroundWidth, backgroundHeight, stateManager);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		MouseCoordinates.update(cam);

		if (this.stop) {
			pauseMenu.act();
			checkForPause();
		} else if (this.gameOver) {
			gameOverLogic();
		} else {
			processGameLogic();
		}

		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		batch.end();

		if (this.stop) {
			drawPauseMenu();
		} else if (this.gameOver) {
			drawGame();
		} else {
			drawGame();
		}
	}

	private void drawPauseMenu() {
		batch.begin();
		batch.end();
		pauseMenu.draw();
	}

	private void processGameLogic() {
		MouseCoordinates.correctMouseOutOfBounds(cam);
		world.step(1f / 60f, 6, 2);
		batch.setProjectionMatrix(cam.combined);
		lineRenderer.setProjectionMatrix(cam);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

		platform.update();
		ball.update(platform.getOriginX());
		setTilesToDynamic();
		tiles.disposeTilesOutOfBounds();
		tiles.update();
		updateScore();
		scoreLabel.setScore(score.getScore());
		airScoreFlame.update(tiles.getAmountOfTilesInAir(2), tiles.getAmountOfTilesInAir(3), tiles.getAmountOfTilesInAir(4));
		airScoreFlame.act(Gdx.graphics.getDeltaTime());
		checkForGameOver();
		checkForPause();
	}

	private void gameOverLogic() {
		if (scoreLabel.isScreenFinished()) {
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				SaveGame.saveHighscore(this.level, score.getScore());
				Gdx.input.setCursorCatched(false);
				stateManager.changeState(StateManager.State.LEVELSELECT);
			}
		}
		stage.act();
	}

	private void drawGame() {
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(2), Color.YELLOW);
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(3), Color.BLUE);
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(4), Color.PURPLE);
		batch.begin();
		ball.render(batch);
		platform.render(batch);
		renderTiles();
		if (!gameOver) {
			airScoreFlame.draw(batch, 0);
		}
		batch.end();
		stage.draw();
	}

	private void checkForGameOver() {
		if (tiles.size() == 0 || ball.getPositionY() < -1) {
			this.gameOver = true;
			airScoreFlame.stopFlameSound();
		}
	}

	public void resize() {
		Gdx.graphics.setWindowedMode(1100, 860);
	}

	@Override
	public void dispose () {
		tiles.dispose();
		wallSpawner.dispose();
		platform.dispose();
		if (ball != null) {
			ball.dispose();
		}
		world.dispose();
		backgroundTexture.dispose();
		highscoreLabel.dispose();
		listener.dispose();
		scoreLabel.dispose();
		airScoreFlame.dispose();
		stage.dispose();
		pauseMenu.dispose();
		batch.dispose();
	}

	public void setLevel(int level) {
		setNewBall(new Ball(world, platform.getOriginX(), LevelTemplate.getSpeedForLevel(level), LevelTemplate.getSpeedIncreaseForLevel(level)));
		tiles.setTilesForLevel(LevelTemplate.getLevelTemplate(level));
		listener.updateEffectVolume();
		this.score.resetScore();
		stop = gameOver = false;
		this.level = level;
		this.scoreLabel.setLevel(level);
		this.scoreLabel.updateEffectVolume();
		this.highscoreLabel.setLevel(level);
		this.airScoreFlame.updateEffectVolume();
	}

	private void setNewBall(Ball newBall) {
		if (this.ball != null) {
			this.ball.dispose();
		}
		this.ball = newBall;
		this.listener.setBall(newBall);
	}

	private void setTilesToDynamic() {
		for (TileData tile : tiles) {
			tile.update();
		}
	}

	private void renderTiles() {
		for (TileData tile : tiles) {
			tile.render(this.batch);
		}
	}

	private void updateScore() {
		score.addAirScore(tiles.getAmountOfTilesInAir(2), tiles.getAmountOfTilesInAir(3), tiles.getAmountOfTilesInAir(4));
		score.addHitScoreLevel1(tiles.getAmountOfTilesHit(1));
		score.addHitScoreLevel2(tiles.getAmountOfTilesHit(2));
		score.addHitScoreLevel3(tiles.getAmountOfTilesHit(3));
		score.addHitScoreLevel4(tiles.getAmountOfTilesHit(4));
	}

	private void checkForPause() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			this.stop = !this.stop;
			if (stop) {
				Gdx.input.setCursorCatched(false);
				airScoreFlame.stopFlameSound();
			} else {
				Gdx.input.setCursorCatched(true);
				airScoreFlame.resumeFlameSound(tiles.getAmountOfTilesInAir(2), tiles.getAmountOfTilesInAir(3), tiles.getAmountOfTilesInAir(4));
			}
		}
	}

}
