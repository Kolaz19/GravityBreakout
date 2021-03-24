package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Listener implements ContactListener {
    private Ball currentBall;
    private Platform currentPlatform;
    private ArrayList<TileData> tiles;

    public static final short BALL_ENTITY = 0x0001;
    public static final short PLATFORM_ENTITY = 0x0002;
    public static final short WALL_ENTITY = 0x0004;
    public static final short TILE_ENTITY = 0x0008;
    public static final short TILE_INACTIVE_ENTITY = 0x0016;

    public Listener(Ball ball, Platform platform, ArrayList<TileData> tiles) {
        this.currentBall = ball;
        this.currentPlatform = platform;
        this.tiles = tiles;
    }

    @Override
    public void beginContact(Contact contact) {
        if (includesTile(contact) && includesBall(contact)) {
            getCorrespondingTileData(getHittedTile(contact)).setDynamicFlag();
        }
    }

    @Override
    public void endContact(Contact contact) {
        if (includesBall(contact) && includesPlatform(contact)) {
            ballHitsPlatform();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void setBall(Ball ball) {
        this.currentBall = ball;
    }

    private void ballHitsPlatform() {
        Platform.Area areaToHit = currentPlatform.getArea(currentBall.getPositionX());
        currentBall.updateDirectionAfterCollision(areaToHit);
    }

    private boolean includesBall(Contact contact) {
        return ((contact.getFixtureA().getFilterData().categoryBits == BALL_ENTITY)
                || (contact.getFixtureB().getFilterData().categoryBits == BALL_ENTITY));
    }

    private boolean includesPlatform(Contact contact) {
        return ((contact.getFixtureA().getFilterData().categoryBits == PLATFORM_ENTITY)
                || (contact.getFixtureB().getFilterData().categoryBits == PLATFORM_ENTITY));
    }

    private boolean includesTile(Contact contact) {
        boolean exists = false;
        if (contact.getFixtureA().getFilterData().categoryBits == TILE_ENTITY) {
            exists = true;
        } else if (contact.getFixtureB().getFilterData().categoryBits == TILE_ENTITY) {
            exists = true;
        }
        return exists;
    }

    private Body getHittedTile(Contact contact) {
        if (contact.getFixtureA().getFilterData().categoryBits == TILE_ENTITY) {
            return contact.getFixtureA().getBody();
        } else if (contact.getFixtureB().getFilterData().categoryBits == TILE_ENTITY) {
            return contact.getFixtureB().getBody();
        }
        return null;
    }

    private TileData getCorrespondingTileData (Body hittedTile) {
        TileData tileData = null;
        for (TileData tile : tiles) {
            if (tile.getBody() == hittedTile) {
                tileData = tile;
            }
        }
        return tileData;
    }







}
