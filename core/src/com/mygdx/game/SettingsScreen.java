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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;

public class SettingsScreen extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam, camFont;
    private Stage stage;
    private BitmapFont font;
    private FitViewport fitViewport;
    private int musicVolume;
    private int effectVolume;


    public SettingsScreen(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void create() {
        background = new Texture("mainMenuBackground.png");
        batch = new SpriteBatch();

        cam = new OrthographicCamera(89, 120);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
        camFont = new OrthographicCamera(89 * 7, 120 * 7);
        camFont.position.set(camFont.viewportWidth / 2, camFont.viewportHeight / 2, 0	);
        camFont.update();

        stage = new Stage(new FitViewport(89, 120, cam));
        addButtons();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("forwa.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = Color.WHITE;
        parameter.spaceY = 10;
        font = generator.generateFont(parameter);
        generator.dispose();

        musicVolume = SaveGame.getSavedMusicVolume();
        effectVolume = SaveGame.getSavedEffectVolume();
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
        batch.setProjectionMatrix(camFont.combined);
        font.draw(batch,getMusicString(),89 * 7 / 2 - 170, 725    );
        font.draw(batch,getEffectString(),89 * 7 / 2 - 170, 620    );
        batch.end();

        stage.draw();
    }



    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        for (Actor actor: stage.getActors()) {
            ButtonActor button = (ButtonActor) actor;
            button.dispose();
        }
        stage.dispose();
    }

    private String getMusicString() {
        return "Music     " + String.valueOf(musicVolume);
    }

    private String getEffectString() {
        return "Effects   " + String.valueOf(effectVolume);
    }



    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(445, 600);
    }

    private void addButtons() {
        stage.addActor(new ButtonActor("mainMenuBackButtonDefault.png", "mainMenuBackButtonSelected.png",background.getWidth()/2 - 65 / 2, 8) {
            @Override
            public void onButtonClick() {
                stateManager.changeState(StateManager.State.MENU);
            }
        });
        // Music Volume
        stage.addActor(new ButtonActor("leftButtonSmallDefault.png", "leftButtonSmallSelected.png",8, 95) {
            @Override
            public void onButtonClick() {
                if (musicVolume < 10) {
                    musicVolume = 0;
                } else {
                    musicVolume -= 10;
                }
                SaveGame.saveMusicVolume(musicVolume);
                stateManager.updateMusicVolume(musicVolume);
            }
        });
        stage.addActor(new ButtonActor("rightButtonSmallDefault.png", "rightButtonSmallSelected.png", background.getWidth() - 14, 95) {
            @Override
            public void onButtonClick() {
                if (musicVolume > 90) {
                    musicVolume = 100;
                } else {
                    musicVolume += 10;
                }
                SaveGame.saveMusicVolume(musicVolume);
                stateManager.updateMusicVolume(musicVolume);
            }
        });

        //Effect Volume
        stage.addActor(new ButtonActor("leftButtonSmallDefault.png", "leftButtonSmallSelected.png",8, 80) {
            @Override
            public void onButtonClick() {
                if (effectVolume < 10) {
                    effectVolume = 0;
                } else {
                    effectVolume -= 10;
                }
                SaveGame.saveEffectVolume(effectVolume);
            }
        });
        stage.addActor(new ButtonActor("rightButtonSmallDefault.png", "rightButtonSmallSelected.png", background.getWidth() - 14, 80) {
            @Override
            public void onButtonClick() {
                if (effectVolume > 90) {
                    effectVolume = 100;
                } else {
                    effectVolume += 10;
                }
                SaveGame.saveEffectVolume(effectVolume);
            }
        });


    }


}