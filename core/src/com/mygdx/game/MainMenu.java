package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends ApplicationAdapter implements ResizableScreen {
    StateManager stateManager;
    Texture background;
    SpriteBatch batch;
    OrthographicCamera cam;

    public MainMenu(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void create() {
        resize();
        background = new Texture("background.png");
        batch = new SpriteBatch();
        cam = new OrthographicCamera(background.getWidth(), background.getHeight());
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(1100, 860);
    }
}
