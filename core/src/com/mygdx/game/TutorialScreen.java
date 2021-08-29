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

public class TutorialScreen extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam, camFont;
    private Stage stage;
    private BitmapFont font;
    private FitViewport fitViewport;
    private HashMap<Integer, String> texts;
    private HashMap<Integer, Texture> textures;
    private int currentHint;


    public TutorialScreen (StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void create() {
        background = new Texture("backgroundTutorial.png");
        batch = new SpriteBatch();

        cam = new OrthographicCamera(180, 140);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0	);
        camFont = new OrthographicCamera(180 * 10, 140* 10);
        camFont.position.set(camFont.viewportWidth / 2, camFont.viewportHeight / 2, 0	);
        camFont.update();

        stage = new Stage(new FitViewport(180, 140, cam));
        addButtons();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("forwa.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.color = Color.WHITE;
        parameter.spaceY = 10;
        font = generator.generateFont(parameter);
        generator.dispose();

        textures = new HashMap<>();
        texts = new HashMap<>();
        addTexts();
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
        if (!textures.isEmpty()) {
            batch.draw(textures.get(currentHint), 24, 49, 129, 81);
        }
        batch.setProjectionMatrix(camFont.combined);
        font.draw(batch,texts.get(currentHint), 240, 460, 1300, 10, true);
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
        disposeImages();
        stage.dispose();
    }

    public void loadImages() {
        for (int k = 1; k <= 8; k++) {
            textures.put(k,new Texture(Gdx.files.internal("hint" + k + ".png")));
        }
        this.currentHint = 1;
    }

    public void disposeImages() {
        for (int k = 1; k <= textures.size(); k++) {
            textures.get(k).dispose();
        }
        textures.clear();
    }


    @Override
    public void resize() {
        Gdx.graphics.setWindowedMode(900, 700);
    }

    private void addButtons() {
        stage.addActor(new ButtonActor("mainMenuBackButtonDefault.png", "mainMenuBackButtonSelected.png",background.getWidth()/2 - 65 / 2, 5) {
            @Override
            public void onButtonClick() {
                disposeImages();
                stateManager.changeState(StateManager.State.MENU);
            }
        });

        stage.addActor(new ButtonActor("leftButtonDefault.png", "leftButtonSelected.png", 9, 80) {
            @Override
            public void onButtonClick() {
                if (currentHint == 1) {
                    currentHint = textures.size();
                } else {
                    currentHint--;
                }
            }
        });

        stage.addActor(new ButtonActor("rightButtonDefault.png", "rightButtonSelected.png", 157, 80) {
            @Override
            public void onButtonClick() {
                if (currentHint == textures.size()) {
                    currentHint = 1;
                } else {
                    currentHint++;
                }
            }
        });


    }


    private void addTexts() {
        texts.put(1,"In Gravity Breakout, the goal is not to clear the screen. You should rather try to get the most out of the tiles that are left on the field.");
        texts.put(2,"You can increase your score in two ways. One rule stays the same: If a tile falls out of your screen, you can no longer get points with it. You want to avoid that!");
        texts.put(3,"The first way to increase your points is by catching falling tiles with your platform and letting them bounce back up. This will give you points every time you hit a tile.");
        texts.put(4,"Hitting a tile will also increase it's tile level. The tile levels are white (Default), then yellow, then blue, then purple. The higher the tile level, the higher the points when hitting the tile.");
        texts.put(5,"The second and more effective way to increase your score is by having at least two tiles with the same tile level on your screen. This is indicated by a line between these tiles and a flame in the middle. This does give points over time!");
        texts.put(6,"You get more points for every additional tile that is connected (Multiplier). Here is also true: the higher the tile level, the higher the points you get with the multiplier.");
        texts.put(7,"The speed of your ball increases every time the ball hits your platform. Use this to your advantage! The initial speed and the speed increase does vary per level.");
        texts.put(8,"Every level has a minimum requirement of 500 points to unlock. You have to reach this highscore in the previous level!");
    }

}