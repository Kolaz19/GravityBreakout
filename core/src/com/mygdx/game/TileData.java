package com.mygdx.game;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/** Holds the body and the dynamic flag of a tile */
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

    /** Checks if dynamic flag has been set and changes body to dynamic */
    public void update() {
        if (isDynamic) {
            tile.setType(BodyDef.BodyType.DynamicBody);
        }
    }

    public Body getBody() {
        return this.tile;
    }

}
