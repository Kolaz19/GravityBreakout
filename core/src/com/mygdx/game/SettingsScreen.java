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
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;

public class SettingsScreen extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background, deleteLevel1Texture, deleteLevel2Texture, deleteButtonSavedTexture;
    private SpriteBatch batch;
    private OrthographicCamera cam, camFont;
    private Stage stage;
    private BitmapFont font;
    private FitViewport fitViewport;
    private int musicVolume;
    private int effectVolume;
    private HashMap<Ball.BallColor, Texture> ballTextures;
    private Ball.BallColor[] ballColors;
    private Ball.BallColor currentBallColor;
    private int deleteLevel;


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

        ballTextures = new HashMap<>();
        ballColors = Ball.BallColor.values();
        for (Ball.BallColor ballColor : ballColors) {
            ballTextures.put(ballColor, new Texture(Gdx.files.internal("ball" + ballColor.toString() + ".png")));
        }
        currentBallColor = SaveGame.getSavedBallColor();
        deleteLevel = 0;
        deleteLevel1Texture = new Texture(Gdx.files.internal("deleteLevel1.png"));
        deleteLevel2Texture = new Texture(Gdx.files.internal("deleteLevel2.png"));
        deleteButtonSavedTexture = new Texture(Gdx.files.internal("deleteButtonSaved.png"));
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
        batch.draw(ballTextures.get(currentBallColor), 61, 56, 7, 7);

        batch.setProjectionMatrix(camFont.combined);
        font.draw(batch, getMusicString(), 89 * 7 / 2 - 170, 725);
        font.draw(batch, getEffectString(), 89 * 7 / 2 - 170, 620);
        font.draw(batch, "Ball Color", 89 * 7 / 2 - 170, 442);
        font.setColor(Color.RED);
        font.draw(batch, "Reset Progress", 89 * 7 / 2 - 250, 282);
        font.setColor(Color.WHITE);
        batch.end();

        stage.draw();

        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        if (deleteLevel == 1) {
            batch.draw(deleteLevel1Texture, background.getWidth() - 16, 34);
        } else if (deleteLevel == 2) {
            batch.draw(deleteLevel2Texture, background.getWidth() - 16, 34);
        } else if (deleteLevel == 3) {
            batch.draw(deleteButtonSavedTexture,background.getWidth() - 17, 33);
        }
        batch.end();

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
        for (Ball.BallColor ballColor : ballColors) {
            ballTextures.get(ballColor).dispose();
        }
        deleteLevel2Texture.dispose();
        deleteLevel1Texture.dispose();
        deleteButtonSavedTexture.dispose();
    }

    private String getMusicString() {
        return "Music     " + String.valueOf(musicVolume);
    }

    private String getEffectString() {
        return "Effects   " + String.valueOf(effectVolume);
    }

    public void resetDeleteLevel() {
        deleteLevel = 0;
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

        //Ball Color
        stage.addActor(new ButtonActor("leftButtonSmallDefault.png", "leftButtonSmallSelected.png",8, 55) {
            @Override
            public void onButtonClick() {
                int newIndex = 0;
                for (int k = 0; k < ballColors.length; k++) {
                    if (ballColors[k] == currentBallColor) {
                        newIndex = getCorrectIndex(k - 1);
                        break;
                    }
                }
                currentBallColor = ballColors[newIndex];
                SaveGame.saveBallColor(currentBallColor);
            }
        });
        stage.addActor(new ButtonActor("rightButtonSmallDefault.png", "rightButtonSmallSelected.png", background.getWidth() - 14, 55) {
            @Override
            public void onButtonClick() {
                int newIndex = 0;
                for (int k = 0; k < ballColors.length; k++) {
                    if (ballColors[k] == currentBallColor) {
                        newIndex = getCorrectIndex(k + 1);
                        break;
                    }
                }
                currentBallColor = ballColors[newIndex];
                SaveGame.saveBallColor(currentBallColor);
            }
        });

        //Delete Progress
        stage.addActor(new ButtonActor("deleteButtonDefault.png", "deleteButtonSelected.png", background.getWidth() - 17, 33) {
            @Override
            public void onButtonClick() {
                if (deleteLevel == 2) {
                    SaveGame.resetSaveGame();
                    SaveGame.saveBallColor(currentBallColor);
                    SaveGame.saveMusicVolume(musicVolume);
                    SaveGame.saveEffectVolume(effectVolume);
                }
                if (deleteLevel < 3) {
                    deleteLevel++;
                }
            }
        });


    }

    private int getCorrectIndex(int index) {
        if (index == ballColors.length) {
            index = 0;
        } else if (index < 0) {
            index = ballColors.length - 1;
        }
        return index;
    }


}