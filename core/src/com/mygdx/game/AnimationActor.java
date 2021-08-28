package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimationActor extends Actor {
    private Animation<TextureRegion> animation;
    private Vector2 pos;
    private float stateTime;

    public AnimationActor(Animation animation, int posX, int posY){
        this.animation = animation;
        this.animation.setPlayMode(Animation.PlayMode.LOOP);
        pos = new Vector2(posX, posY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(animation.getKeyFrame(stateTime),pos.x, pos.y);
    }

    @Override
    public void act(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void dispose() {
        this.animation.getKeyFrame(0).getTexture().dispose();
    }

}
