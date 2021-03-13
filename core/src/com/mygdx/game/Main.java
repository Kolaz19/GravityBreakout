package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	Box2DDebugRenderer boxRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera cam;
	Texture background;
	Platform platform;
	int backgroundWidth, backgroundHeight;

	static final float PIXELS_TO_METERS = 1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,0),true);
		background = new Texture("background.png");
		backgroundHeight = background.getHeight();
		backgroundWidth = background.getWidth();
		cam = new OrthographicCamera(backgroundWidth, backgroundHeight);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
		boxRenderer = new Box2DDebugRenderer();

		platform = new Platform(world,backgroundWidth);

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

		batch.begin();
		batch.draw(background,0,0);
		batch.end();
		boxRenderer.render(world,debugMatrix);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
