package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class TileData {
    private Body tile;
    //We cannot set the body to dynamic in collision check, so we do it afterwards
    private boolean isDynamic;

    public TileData(Body tile) {
        this.tile = tile;
        isDynamic = false;
    }

    public void setDynamicFlag() {
        isDynamic = true;
    }

    public void update() {
        if (isDynamic) {
            tile.setType(BodyDef.BodyType.DynamicBody);
        }
    }

}
