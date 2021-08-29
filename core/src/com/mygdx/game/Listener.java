package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Listener implements ContactListener {
    private Ball currentBall;
    private Platform currentPlatform;
    private ArrayList<TileData> tiles;
    private Sound ballHitSound;
    private Sound tileLevel1Sound, tileLevel2Sound, tileLevel3Sound, tileLevel4Sound;
    private int effectVolume;

    public static final short BALL_ENTITY = 0x0001;
    public static final short PLATFORM_ENTITY = 0x0002;
    public static final short WALL_ENTITY = 0x0004;
    public static final short TILE_INACTIVE_TILE = 0x0008;
    public static final short TILE_ACTIVE_TILE = 0x0016;

    public Listener(Ball ball, Platform platform, ArrayList<TileData> tiles) {
        this.currentBall = ball;
        this.currentPlatform = platform;
        this.tiles = tiles;
        ballHitSound = Gdx.audio.newSound(Gdx.files.internal("ballHit.wav"));
        tileLevel1Sound =  Gdx.audio.newSound(Gdx.files.internal("tileHitLevel1.wav"));
        tileLevel2Sound = Gdx.audio.newSound(Gdx.files.internal("tileHitLevel2.wav"));
        tileLevel3Sound = Gdx.audio.newSound(Gdx.files.internal("tileHitLevel3.wav"));
        tileLevel4Sound = Gdx.audio.newSound(Gdx.files.internal("tileHitLevel4.wav"));
    }

    public void updateEffectVolume() {
        effectVolume = SaveGame.getSavedEffectVolume();
    }

    @Override
    public void beginContact(Contact contact) {
        if (includesInactiveTile(contact) && includesBall(contact)) {
            TileData hittedTile =  getCorrespondingTileData(getHittedTile(contact));
            hittedTile.setDynamicFlag();
            hittedTile.applyInitialImpulse(this.currentBall.getLinearVelocity());
            ballHitSound.play(effectVolume / 100f);
        }
    }

    @Override
    public void endContact(Contact contact) {
        if (includesBall(contact) && includesPlatform(contact)) {
            ballHitsPlatform();
            ballHitSound.play(effectVolume / 100f);
        }

        if(includesActiveTile(contact) && includesPlatform(contact)) {
            TileData tileData = getCorrespondingTileData(getHittedTile(contact));
            playTileHitSound(tileData.getLevel());
            tileData.increaseTileLevel();
            this.currentPlatform.startHitAnimation(tileData.getLevel());
            tileData.setHit();
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
        Platform.Area areaToHit = currentPlatform.getArea(currentBall.getPositionX() * MainGame.PIXELS_TO_METERS);
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

    private boolean includesInactiveTile(Contact contact) {
        boolean exists = false;
        if (contact.getFixtureA().getFilterData().categoryBits == TILE_INACTIVE_TILE) {
            exists = true;
        } else if (contact.getFixtureB().getFilterData().categoryBits == TILE_INACTIVE_TILE) {
            exists = true;
        }
        return exists;
    }

    private boolean includesActiveTile(Contact contact) {
        boolean exists = false;
        if (contact.getFixtureA().getFilterData().categoryBits == TILE_ACTIVE_TILE) {
            exists = true;
        } else if (contact.getFixtureB().getFilterData().categoryBits == TILE_ACTIVE_TILE) {
            exists = true;
        }
        return exists;
    }

    private Body getHittedTile(Contact contact) {
        if ((contact.getFixtureA().getFilterData().categoryBits == TILE_INACTIVE_TILE)
        || (contact.getFixtureA().getFilterData().categoryBits == TILE_ACTIVE_TILE)) {
            return contact.getFixtureA().getBody();
        } else if ((contact.getFixtureB().getFilterData().categoryBits == TILE_INACTIVE_TILE)
        || (contact.getFixtureB().getFilterData().categoryBits == TILE_ACTIVE_TILE)) {
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

    public void dispose() {
        ballHitSound.dispose();
        tileLevel1Sound.dispose();
        tileLevel2Sound.dispose();
        tileLevel3Sound.dispose();
        tileLevel4Sound.dispose();
    }

    public void playTileHitSound(int levelPreLevelUp) {
        switch(levelPreLevelUp) {
            case 1: tileLevel1Sound.play(effectVolume / 100f);
            break;
            case 2: tileLevel2Sound.play(effectVolume / 100f);
            break;
            case 3: tileLevel3Sound.play(effectVolume / 100f);
            break;
            case 4: tileLevel4Sound.play(effectVolume / 100f);
            break;
        }
    }

}
