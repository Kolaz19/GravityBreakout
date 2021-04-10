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
        yellowEffect.start();
        yellowEffect.scaleEffect(2f);
        blueEffect = new ParticleEffect();
        blueEffect.load(Gdx.files.internal("blueFlame.party"), Gdx.files.internal(""));
        blueEffect.start();
        blueEffect.scaleEffect(2f);
        purpleEffect = new ParticleEffect();
        purpleEffect.load(Gdx.files.internal("purpleFlame.party"), Gdx.files.internal(""));
        purpleEffect.start();
        purpleEffect.scaleEffect(2f);

        currentEffect = yellowEffect;
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
          currentEffect.draw(batch, parentAlpha);
    }



    public void update(int level2Count, int level3Count, int level4Count) {
        updateEffect(level2Count, level3Count, level4Count);
        updatePosition();
    }

    private void updateEffect(int level2Count, int level3Count, int level4Count) {
        show = true;
        if (level4Count > 1) {
            currentEffect = purpleEffect;
        } else if (level3Count > 1) {
            currentEffect = blueEffect;
        } else if (level2Count > 1) {
            currentEffect = yellowEffect;
        } else {
            show = false;
        }
    }

    private void updatePosition() {
        this.currentEffect.setPosition(50, 50);
        return;


/*        if (score.getScore() > 999) {
            this.currentEffect.setPosition(975 - 10, 805);
        } else if (score.getScore() > 99) {
            this.currentEffect.setPosition(1000 - 10, 805);
        } else if (score.getScore() > 9) {
            this.currentEffect.setPosition(1025 - 10, 805);
        } else {
            this.currentEffect.setPosition(1050 - 10, 805);
        }*/
    }

    @Override
    public void act(float delta) {
        this.currentEffect.update(Gdx.graphics.getDeltaTime());
    }
}
