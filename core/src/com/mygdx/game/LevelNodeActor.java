package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;



public class LevelNodeActor extends Actor implements OnButtonClick {
    final int level;
    private final Vector2 position;
    private final int height, width;
    private Texture texture;
    private boolean active;
    private StateManager stateManager;

    public LevelNodeActor(StateManager stateManager, int xCord, int yCord , int level) {
        this.level = level;
        texture = new Texture("thumbnailLevel" + level + ".png");
        height = 17;
        width = 21;
        position = new Vector2(xCord, yCord);
        this.stateManager = stateManager;
        update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        boolean includesMouse = this.includesMouse(position,width,height);
        if (this.active) {
            if(includesMouse) {
                batch.setColor(Color.GREEN);
            }
        } else {
            if(includesMouse) {
                batch.setColor(Color.GRAY);
            } else {
                batch.setColor(Color.DARK_GRAY);
            }
        }

        batch.draw(texture,position.x, position.y, width, height);
        batch.setColor(1,1,1,1);
    }

    @Override
    public void act(float delta) {
        boolean includesMouse = includesMouse(position,width, height);
        if (active && includesMouse && isButtonClicked()) {
            onButtonClick();
        }
    }

    public void update() {
        this.active = SaveGame.isLevelUnlocked(this.level);
    }

    @Override
    public void onButtonClick() {
        if (active) {
            stateManager.changeLevel(this.level);
            stateManager.changeState(StateManager.State.GAME);
        }
    }
}


