package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileParticles {
    private ParticleEffect currentEffect;
    private boolean active;
    private float scaleFactor;

    public TileParticles(float height, float width) {
        this.currentEffect = new ParticleEffect();
        currentEffect.start();
        this.active = false;
        setupScaleFactor(width, height);
    }

    public void setupScaleFactor(float width, float height) {
        float smallestSide;
        if (height >width) {
            smallestSide = width;
        } else {
            smallestSide = height;
        }
        scaleFactor = smallestSide * 0.22f;

    }

    public void updateCurrentEffect(int tileLevel) {
        //TODO performance of loading effects?
        switch (tileLevel) {
            case 2: currentEffect.load(Gdx.files.internal("yellowParticles.party"), Gdx.files.internal(""));
                    this.active = true;
            break;
            case 3: currentEffect.load(Gdx.files.internal("blueParticles.party"), Gdx.files.internal(""));
            break;
            case 4: currentEffect.load(Gdx.files.internal("purpleParticles.party"), Gdx.files.internal(""));
            break;
        }
        currentEffect.scaleEffect(this.scaleFactor);
    }

    public void render(SpriteBatch batch) {
        if(this.active) {
            currentEffect.draw(batch);
        }
    }

    public void update(float x, float y) {
        currentEffect.setPosition(x, y);
        currentEffect.update(Gdx.graphics.getDeltaTime());
    }

    public void dispose() {
        currentEffect.dispose();
    }

}


