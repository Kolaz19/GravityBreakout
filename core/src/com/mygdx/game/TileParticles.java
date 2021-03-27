package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileParticles {
    ParticleEffect currentEffect;
    private boolean active;

    public TileParticles() {
        this.currentEffect = new ParticleEffect();
        this.active = false;
    }

    public void updateCurrentEffect(int tileLevel) {
        switch (tileLevel) {
            case 2: currentEffect.load(Gdx.files.internal("yellowParticles.party"), Gdx.files.internal(""));
                    currentEffect.start();
                    this.active = true;
            break;
            case 3: currentEffect.load(Gdx.files.internal("blueParticles.party"), Gdx.files.internal(""));
            break;
            case 4: currentEffect.load(Gdx.files.internal("purpleParticles.party"), Gdx.files.internal(""));
            break;
        }
    }

    public void render(SpriteBatch batch, float x, float y) {
        if(this.active) {
            currentEffect.getEmitters().get(0).setPosition(x, y);
            currentEffect.update(Gdx.graphics.getDeltaTime());
            currentEffect.draw(batch);
        }
    }

    public void dispose() {
        currentEffect.dispose();
    }

}


