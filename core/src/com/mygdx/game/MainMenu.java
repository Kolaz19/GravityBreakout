package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private MovingBackgroundPoints backgroundAnimation;
    private Stage stage;


    public MainMenu(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void create() {
        background = new Texture("mainMenuBackground.png");
        batch = new SpriteBatch();
        cam = new OrthographicCamera(background.getWidth(), background.getHeight());
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
        backgroundAnimation = new MovingBackgroundPoints();

        stage = new Stage(new FitViewport(background.getWidth(), background.getHeight(), cam), batch);
        createButtons();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        MouseCoordinates.update(cam);

        backgroundAnimation.processLogic();
        stage.act();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        backgroundAnimation.draw(batch);
        batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(445, 600);
    }

    private void createButtons() {
        final int buttonWidth = 67;
        final int xCordToRender = background.getWidth() / 2 - buttonWidth / 2;
        int yCordToRender = 93;
        final int spaceBetweenButtons = 22;

        ButtonActor playButton = new ButtonActor("mainMenuPlayButtonDefault.png", "mainMenuPlayButtonSelected.png", xCordToRender, yCordToRender) {
            @Override
            public void onButtonClick() {
                stateManager.changeState(StateManager.State.LEVELSELECT);
            }
        };

        yCordToRender -= spaceBetweenButtons;
        ButtonActor settingsButton = new ButtonActor("mainMenuSettingsButtonDefault.png", "mainMenuSettingsButtonSelected.png", xCordToRender, yCordToRender) {
            @Override
            public void onButtonClick() {

            }
        };

        yCordToRender -= spaceBetweenButtons;
        ButtonActor tutorialButton = new ButtonActor("mainMenuTutorialButtonDefault.png", "mainMenuTutorialButtonSelected.png", xCordToRender, yCordToRender) {
            @Override
            public void onButtonClick() {

            }
        };

        yCordToRender = 9;
        ButtonActor exitButton = new ButtonActor("mainMenuExitButtonDefault.png", "mainMenuExitButtonSelected.png", xCordToRender, yCordToRender) {
            @Override
            public void onButtonClick() {
                //TODO dispose all elements from every screen
                Gdx.app.exit();
                System.exit(0);
            }
        };

        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(tutorialButton);
        stage.addActor(exitButton);
        }

}
