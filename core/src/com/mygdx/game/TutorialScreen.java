package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;

public class TutorialScreen extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Stage stage;
    private Label label;
    private BitmapFont font;

    public TutorialScreen (StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void create() {
        background = new Texture("backgroundTutorial.png");
        batch = new SpriteBatch();


        cam = new OrthographicCamera(180, 140);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);


        stage = new Stage(new FitViewport(180, 140, cam));
        addButtons();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("forwa.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 200;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        MouseCoordinates.update(cam);

        stage.act();


        batch.begin();


        batch.draw(background, 0, 0);

        font.draw(batch, "Hey", 0, 0, 100, 10, true);

        batch.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(900, 700);
    }

    private void addButtons() {
        stage.addActor(new ButtonActor("mainMenuBackButtonDefault.png", "mainMenuBackButtonSelected.png",background.getWidth()/2 - 65 / 2, 5) {
            @Override
            public void onButtonClick() {
                stateManager.changeState(StateManager.State.MENU);
            }
        });

        stage.addActor(new ButtonActor("leftButtonDefault.png", "leftButtonSelected.png", 9, 80) {
            @Override
            public void onButtonClick() {

            }
        });

        stage.addActor(new ButtonActor("rightButtonDefault.png", "rightButtonSelected.png", 157, 80) {
            @Override
            public void onButtonClick() {

            }
        });


    }

}