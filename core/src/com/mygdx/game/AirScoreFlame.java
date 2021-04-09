package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AirScoreFlame extends Actor {
    private ParticleEffect currentEffect, yellowEffect, blueEffect, purpleEffect;
    private Score score;

    public AirScoreFlame(Score score) {
        currentEffect = new ParticleEffect();
        this.score = score;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentEffect.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {

    }
}
