package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class LevelSelectMenu extends ApplicationAdapter implements ResizableScreen {
    private StateManager stateManager;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private OrthographicCamera cam2;
    private Stage nodeStage;
    private Stage scoreStage;
    private ArrayList<LevelNodeActor> levelNodeActors;
    private ArrayList<LevelNodeScoreActor> levelNodeScoreActors;

    public LevelSelectMenu(StateManager manager) {
        this.stateManager = manager;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("levelSelectBackground.png");
        cam = new OrthographicCamera(background.getWidth(), background.getHeight());
        cam2 = new OrthographicCamera(background.getWidth(), background.getHeight());
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam2.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        nodeStage = new Stage(new FitViewport(background.getWidth(), background.getHeight(), cam), batch);
        scoreStage = new Stage(new FitViewport(background.getWidth() * 10, background.getHeight() * 10,cam2));
        addLevels();

        ButtonActor exitButton = new ButtonActor("mainMenuBackButtonDefault.png", "mainMenuBackButtonSelected.png", background.getWidth() / 2 - 65 / 2, 10 ) {
            @Override
            public void onButtonClick() {
                stateManager.changeState(StateManager.State.MENU);
            }
        };
        nodeStage.addActor(exitButton);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        MouseCoordinates.update(cam);
        batch.setProjectionMatrix(cam.combined);

        nodeStage.act();

        batch.begin();
        batch.draw(background,0, 0);
        batch.end();
        nodeStage.draw();
        scoreStage.draw();
    }

    private void addLevels() {
        levelNodeActors = new ArrayList<>();
        levelNodeScoreActors = new ArrayList<>();
        int level = 1;
        LevelNodeActor node;
        LevelNodeScoreActor score;
        for (int lv_loop = 0; lv_loop < 4; lv_loop++) {
            node = new LevelNodeActor(stateManager,10 + lv_loop * 26, 93, level);
            score = new LevelNodeScoreActor(110 + lv_loop * 260, 93*10,level);
            level++;

            levelNodeActors.add(node);
            levelNodeScoreActors.add(score);

            nodeStage.addActor(node);
            scoreStage.addActor(score);
        }

    }

    public void update() {
        for (LevelNodeActor node: levelNodeActors) {
            node.update();
        }
        for (LevelNodeScoreActor score: levelNodeScoreActors) {
            score.update();
        }
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
