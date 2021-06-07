package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class LevelNodeActor extends Actor implements OnButtonClick {
    final int level;
    private final Vector2 position;
    private int height, width;
    private Texture texture;
    private boolean active;

    public LevelNodeActor(int xCord, int yCord , int level) {
        this.level = level;
        texture = new Texture("thumbnailLevel" + level);
        height = texture.getHeight();
        width = texture.getWidth();
        position = new Vector2(xCord, yCord);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

    }

    @Override
    public void act(float delta) {
        boolean includesMouse = includesMouse(position,width, height);
        if (active && includesMouse && isButtonClicked()) {
            onButtonClick();
        }
    }

    @Override
    public void onButtonClick() {

    }
}


