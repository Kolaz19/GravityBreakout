package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer boxRenderer;
	private Matrix4 debugMatrix;
	private OrthographicCamera cam;
	private Texture background;
	private Platform platform;
	private Ball ball;
	private ArrayList<TileData> tiles;
	private Listener listener;
	private int backgroundWidth, backgroundHeight;

	static final float PIXELS_TO_METERS = 7f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,-10),true);
		background = new Texture("background.png");
		backgroundHeight = background.getHeight();
		backgroundWidth = background.getWidth();
		cam = new OrthographicCamera(backgroundWidth, backgroundHeight);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
		boxRenderer = new Box2DDebugRenderer();

		platform = new Platform(world,backgroundWidth);
		WallSpawner wallSpawner = new WallSpawner(world,backgroundWidth,backgroundHeight);
		wallSpawner.createLeftWall();
		wallSpawner.createUpperWall();
		wallSpawner.createRightWall();
		listener = new Listener(ball,platform);
		setLevel();
		world.setContactListener(listener);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		world.step(1f / 60f, 6, 2);
		batch.setProjectionMatrix(cam.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0	);

		MouseCoordinates.update(cam);
		platform.updateCoordinate();
		ball.update(platform.getOriginX());
		setTilesToDynamic();


		batch.begin();
		batch.draw(background,0,0);
		batch.end();
		boxRenderer.render(world,debugMatrix);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		batch.dispose();
	}

	private void setLevel() {
		//TODO dispose old items
		ArrayList<TileTemplate> templates = LevelTemplate.level1();
		setNewTiles(new ArrayList<TileData>());
		for (TileTemplate template : templates) {
			tiles.add(new TileData(template.createTile(world)));
		}
		setNewBall(new Ball(world, platform.getOriginX(), 1f, 1f));
	}

	private void setNewBall(Ball newBall) {
		this.ball = newBall;
		this.listener.setBall(newBall);
	}

	private void setNewTiles(ArrayList<TileData> newTiles) {
		this.tiles = newTiles;
		this.listener.setTiles(newTiles);
	}

	private void setTilesToDynamic() {
		for (TileData tile : tiles) {
			tile.update();
		}
	}

}
