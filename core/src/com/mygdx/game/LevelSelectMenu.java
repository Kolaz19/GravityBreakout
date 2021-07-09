package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelSelectMenu extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Stage stage;

    public LevelSelectMenu(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("levelSelectBackground.png");
        cam = new OrthographicCamera(background.getWidth(), background.getHeight());
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        stage = new Stage(new FitViewport(background.getWidth(), background.getHeight(), cam), batch);
        //Single nodes
        stage.addActor(new LevelNodeActor(stateManager,10, 93, 1));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        MouseCoordinates.update(cam);
        batch.setProjectionMatrix(cam.combined);

        stage.act();

        batch.begin();
        batch.draw(background,0, 0);
        batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(595, 600);
    }

}
