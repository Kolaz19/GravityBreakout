package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AirScoreFlame extends Actor {
    private ParticleEffect currentEffect, yellowEffect, blueEffect, purpleEffect;
    private Score score;
    private boolean show;

    public AirScoreFlame(Score score) {
        this.score = score;
        yellowEffect = new ParticleEffect();
        yellowEffect.load(Gdx.files.internal("yellowFlame.party"), Gdx.files.internal(""));
        blueEffect = new ParticleEffect();
        //TODO make blue flame color more contrastful
        blueEffect.load(Gdx.files.internal("blueFlame.party"), Gdx.files.internal(""));
        purpleEffect = new ParticleEffect();
        purpleEffect.load(Gdx.files.internal("purpleFlame.party"), Gdx.files.internal(""));
        currentEffect = new ParticleEffect();
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
        if (show) {
            currentEffect.draw(batch, parentAlpha);
        }
    }



    public void update(int level2Count, int level3Count, int level4Count) {
        updateEffect(level2Count, level3Count, level4Count);
        updatePosition();
    }

    private void updateEffect(int level2Count, int level3Count, int level4Count) {
        //TODO probably optimize performance and code clutter
        if (level4Count > 1) {
            show = true;
            if (this.currentEffect != purpleEffect) {
                currentEffect.reset();
                currentEffect = purpleEffect;
                currentEffect.scaleEffect(2);
                currentEffect.start();
            }
        } else if (level3Count > 1) {
            show = true;
            if (this.currentEffect != purpleEffect) {
                currentEffect.reset();
                currentEffect = blueEffect;
                currentEffect.scaleEffect(2);
                currentEffect.start();
            }
        } else if (level2Count > 1) {
            show = true;
            if (this.currentEffect != yellowEffect) {
                currentEffect.reset();
                currentEffect = yellowEffect;
                currentEffect.scaleEffect(2);
                currentEffect.start();
            }
        } else {
            show = false;
        }
    }



    private void updatePosition() {
        if (score.getScore() > 999) {
            this.currentEffect.setPosition(190, 165);
        } else if (score.getScore() > 99) {
            this.currentEffect.setPosition(195, 165);
        } else if (score.getScore() > 9) {
            this.currentEffect.setPosition(200, 165);
        } else {
            this.currentEffect.setPosition(205, 165);
        }
    }

    @Override
    public void act(float delta) {
        if (show ) {
            this.currentEffect.update(Gdx.graphics.getDeltaTime());
        }
    }
}
