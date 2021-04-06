package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AirScoreParticles extends Actor {
    private boolean show;

    public void checkAmountToTrigger(int tileLevelCount) {
        show = tileLevelCount > 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (show) {
            super.draw(batch, parentAlpha);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
