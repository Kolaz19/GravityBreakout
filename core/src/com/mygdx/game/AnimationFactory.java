package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFactory {

    public static Animation<TextureRegion> createAnimation(Texture texture, int tileWidth, int tileHeight, int amountOfFrames, float frameDuration) {
        TextureRegion[][] temporary = TextureRegion.split(texture, tileWidth, tileHeight);

        TextureRegion[] orderedTextureRegions = new TextureRegion[amountOfFrames];

        int index = 0;
        for (int i = 0; i < temporary.length; i++) {
            for (int j = 0; j < temporary[i].length; j++) {
                orderedTextureRegions[index++] = temporary[i][j];
            }
        }
        return new Animation<TextureRegion>(frameDuration, orderedTextureRegions);
    }
}
