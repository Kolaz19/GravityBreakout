package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AirScoreFlame  {
    private ParticleEffect currentEffect, yellowEffect, blueEffect, purpleEffect;
    private ParticleEffect currentTransEffect, yellowTransEffect, blueTransEffect, purpleTransEffect;
    private Score score;
    private boolean show;
    private Sound flameSound;

    public AirScoreFlame(Score score) {
        this.score = score;
        yellowEffect = new ParticleEffect();
        yellowEffect.load(Gdx.files.internal("yellowFlame.party"), Gdx.files.internal(""));
        yellowEffect.scaleEffect(2f);
        yellowEffect.start();
        blueEffect = new ParticleEffect();
        blueEffect.load(Gdx.files.internal("blueFlame.party"), Gdx.files.internal(""));
        blueEffect.scaleEffect(2f);
        blueEffect.start();
        purpleEffect = new ParticleEffect();
        purpleEffect.load(Gdx.files.internal("purpleFlame.party"), Gdx.files.internal(""));
        purpleEffect.scaleEffect(2f);
        purpleEffect.start();
        currentEffect = new ParticleEffect();

        yellowTransEffect = new ParticleEffect();
        yellowTransEffect.load(Gdx.files.internal("yellowFlameTrans.party"), Gdx.files.internal(""));
        yellowTransEffect.setPosition(105,80);
        yellowTransEffect.scaleEffect(30f);
        yellowTransEffect.start();
        blueTransEffect = new ParticleEffect();
        blueTransEffect.load(Gdx.files.internal("blueFlameTrans.party"), Gdx.files.internal(""));
        blueTransEffect.setPosition(105, 80);
        blueTransEffect.scaleEffect(30f);
        blueTransEffect.start();
        purpleTransEffect = new ParticleEffect();
        purpleTransEffect.load(Gdx.files.internal("purpleFlameTrans.party"), Gdx.files.internal(""));
        purpleTransEffect.setPosition(105, 80);
        purpleTransEffect.scaleEffect(30f);
        purpleTransEffect.start();
        currentTransEffect = new ParticleEffect();

        flameSound = Gdx.audio.newSound(Gdx.files.internal("fire.wav"));
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
        if (show) {
            currentEffect.draw(batch, parentAlpha);
            currentTransEffect.draw(batch, parentAlpha);
        }
    }

    public void update(int level2Count, int level3Count, int level4Count) {
        updateShowEffect(level2Count, level3Count, level4Count);
        updateEffect(level2Count, level3Count, level4Count);
        updatePosition();
    }

    private void updateEffect(int level2Count, int level3Count, int level4Count) {
        if (!this.show) {
            return;
        }

        if (level4Count > 1) {
            if (this.currentEffect != purpleEffect) {
                currentEffect = purpleEffect;
                currentTransEffect = purpleTransEffect;
            }
        } else if (level3Count > 1) {
            if (this.currentEffect != blueEffect) {
                currentEffect = blueEffect;
                currentTransEffect = blueTransEffect;
            }
        } else if (level2Count > 1) {
            if (this.currentEffect != yellowEffect) {
                currentEffect = yellowEffect;
                currentTransEffect = yellowTransEffect;
            }
        }
    }

    private void updateShowEffect(int level2Count, int level3Count, int level4Count) {
        boolean showBefore = show;
        this.show = level2Count > 1 || level3Count > 1 || level4Count > 1;
        if (!showBefore && show) {
            flameSound.loop();
        } else if (!showBefore) {
            flameSound.stop();
        }
    }



    private void updatePosition() {
        if (score.getScore() > 9999) {
            this.currentEffect.setPosition(184, 165);
        } else if (score.getScore() > 999) {
            this.currentEffect.setPosition(190, 165);
        } else if (score.getScore() > 99) {
            this.currentEffect.setPosition(195, 165);
        } else if (score.getScore() > 9) {
            this.currentEffect.setPosition(201, 165);
        } else {
            this.currentEffect.setPosition(206, 165);
        }
    }

    //@Override
    public void act(float delta) {
        if (show ) {
            this.currentEffect.update(Gdx.graphics.getDeltaTime());
            this.currentTransEffect.update(Gdx.graphics.getDeltaTime());
        }
    }

    public void dispose() {
        yellowTransEffect.dispose();
        yellowEffect.dispose();
        blueEffect.dispose();
        blueTransEffect.dispose();
        purpleEffect.dispose();
        purpleTransEffect.dispose();
        flameSound.dispose();
    }

    public void stopFlameSound() {
        flameSound.stop();
    }

    public void resumeFlameSound(int level2Count, int level3Count, int level4Count) {
        if (level2Count > 1 || level3Count > 1 || level4Count > 1) {
            flameSound.loop();
        }
    }
}
