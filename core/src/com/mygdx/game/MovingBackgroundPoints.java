package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.ThreadLocalRandom;

public class MovingBackgroundPoints {
    private Animation<TextureRegion> animationLeft;
    private Animation<TextureRegion> animationRight;
    private final int minHeight = 5;
    private final int maxHeight = 114;
    private final int height = 1;
    private final int width;
    private int yPosLeftAnimation;
    private int yPosRightAnimation;
    private float stateTime;

    public MovingBackgroundPoints() {
        Texture pointTexture = new Texture(Gdx.files.internal("pointAnimationMainMenu.png"));
        animationLeft = AnimationFactory.createAnimation(pointTexture, 79, 1, 42, 0.1f);
        animationLeft.setPlayMode(Animation.PlayMode.NORMAL);
        animationRight = AnimationFactory.createAnimation(pointTexture, 79, 1, 42, 0.1f);
        animationRight.setPlayMode(Animation.PlayMode.REVERSED);
        width = pointTexture.getWidth();
        //To have space between start and first animation
        stateTime = 0;
        setPosition();
    }

    public void processLogic() {
        updateStateTime();
        setPosition();
    }

    private void setPosition() {
        if (stateTime != 0) {
            return;
        }

        yPosLeftAnimation = ThreadLocalRandom.current().nextInt(minHeight,maxHeight + 1);
        yPosRightAnimation = ThreadLocalRandom.current().nextInt(minHeight,maxHeight + 1);
        //If animations have not enough distance
        while (yPosLeftAnimation > yPosRightAnimation - 1 && yPosLeftAnimation < yPosRightAnimation + 1) {
            yPosRightAnimation = ThreadLocalRandom.current().nextInt(minHeight,maxHeight + 1);
        }
    }

    private void updateStateTime() {
        //This time determines the pause between the animations;
        int maxTime = 8;
        if (stateTime < maxTime) {
            stateTime += Gdx.graphics.getDeltaTime();
        } else {
            stateTime = 0;
        }
    }


    public void draw(SpriteBatch batch) {
        if(animationLeft.isAnimationFinished(stateTime)) {
            return;
        }
        batch.draw(animationLeft.getKeyFrame(stateTime), 5, yPosLeftAnimation, width, height );
        batch.draw(animationRight.getKeyFrame(stateTime), 5, yPosRightAnimation, width, height );
    }


}
