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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame extends ApplicationAdapter implements ResizableScreen {
	private StateManager stateManager;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer boxRenderer;
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

	static final float PIXELS_TO_METERS = 7f;

	public MainGame(StateManager manager) {
		stateManager = manager;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,-10),true);
		//Textures
		backgroundTexture = new Texture("background.png");

		backgroundHeight = backgroundTexture.getHeight();
		backgroundWidth = backgroundTexture.getWidth();
		cam = new OrthographicCamera(backgroundWidth, backgroundHeight);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
		boxRenderer = new Box2DDebugRenderer();

		platform = new Platform(world,backgroundWidth);
		WallSpawner wallSpawner = new WallSpawner(world,backgroundWidth,backgroundHeight);
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
		checkForPause();

		if (!this.stop) {
			processGameLogic();
		} else {
			pauseMenu.act();
		}

		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		batch.end();
		if (!this.stop) {
			drawGame();
		} else {
			drawPauseMenu();
		}
		//boxRenderer.render(world,debugMatrix);
	}

	private void drawPauseMenu() {
		pauseMenu.draw();
		batch.begin();
		ball.render(batch);
		batch.end();
	}

	private void processGameLogic() {
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
		stage.act();
		airScoreFlame.act(Gdx.graphics.getDeltaTime());
		checkForGameOver();
	}

	private void drawGame() {
		stage.draw();
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(2), Color.YELLOW);
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(3), Color.BLUE);
		lineRenderer.render(tiles.getTileCoordinatesPerLevel(4), Color.PURPLE);
		batch.begin();
		ball.render(batch);
		platform.render(batch);
		renderTiles();
		airScoreFlame.draw(batch, 0);
		batch.end();
	}

	private void checkForGameOver() {
		if (tiles.size() == 0 || ball.getPositionY() < 0) {
			this.gameOver = true;
		}
	}

	public void resize() {
		Gdx.graphics.setWindowedMode(1100, 860);
	}

	@Override
	public void dispose () {
		world.dispose();
		backgroundTexture.dispose();
		tiles.dispose();
		batch.dispose();
	}

	public void setLevel(int level) {
		tiles.setTilesForLevel(LevelTemplate.getLevelTemplate(level));
		setNewBall(new Ball(world, platform.getOriginX(), 1f, 1f));
		this.score.resetScore();
		stop = false;
	}

	private void setNewBall(Ball newBall) {
		if (this.ball != null) {
			world.destroyBody(this.ball.getBody());
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
		}
	}

}
