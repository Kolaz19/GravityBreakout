package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;

/** Holds all tiles in a level. Takes care of adding new tiles and removing old ones and gives back info about the level of the tiles */
public class TilesInLevel extends ArrayList<TileData> {
    private Texture whiteTexture, yellowTexture, blueTexture, purpleTexture;
    private int level1AirCounter, level2AirCounter, level3AirCounter, level4AirCounter;
    private int level1HitCounter, level2HitCounter, level3HitCounter, level4HitCounter;
    private World world;
    private float elapsedTime;

    public TilesInLevel (World world) {
        this.world = world;
        whiteTexture = new Texture("tile.png");
        yellowTexture = new Texture("yellowTile.png");
        blueTexture = new Texture("blueTile.png");
        purpleTexture = new Texture("purpleTile.png");
        level1AirCounter = level2AirCounter = level3AirCounter = level4AirCounter = 0;
        level1HitCounter = level2HitCounter = level3HitCounter = level4HitCounter = 0;
    }

    /** Adds all tiles for one level and deletes the old tiles */
    public void setTilesForLevel(ArrayList<TileTemplate> templates) {
        this.clear();
        for (TileTemplate template : templates) {
            this.add(new TileData(template.createTile(world), whiteTexture, yellowTexture, blueTexture, purpleTexture));
        }
    }

    public void disposeTilesOutOfBounds() {
        if (!oneSecondElapsed()) {
            return;
        }
        Iterator<TileData> iter = this.iterator();
        TileData currentTile;
        while (iter.hasNext()) {
            currentTile = iter.next();
            if(currentTile.isDynamic() && currentTile.isOutOfScreen()) {
                world.destroyBody(currentTile.getBody());
                currentTile.dispose();
                iter.remove();
            }
        }
    }

    /**Updates the counter for the amount of tiles that are in a specific level.
       Call this in every loop when also receiving the counter getters*/
    public void updateCounters() {
        resetCounters();
        for (int loop = 0; loop < this.size(); loop++) {
            if (this.get(loop).isDynamic()) {
                updateAirCounter(this.get(loop));
                updateHitCounter(this.get(loop));
            }
        }
    }

    private void resetCounters() {
        level1AirCounter = level2AirCounter = level3AirCounter = level4AirCounter = 0;
        level1HitCounter = level2HitCounter = level3HitCounter = level4HitCounter = 0;
    }

    private void updateAirCounter(TileData tile) {
        switch(tile.getLevel()) {
            case 1: level1AirCounter++;
                break;
            case 2: level2AirCounter++;
                break;
            case 3: level3AirCounter++;
                break;
            case 4: level4AirCounter++;
                break;
        }
    }

    private void updateHitCounter(TileData tile) {
        if (!tile.wasHit()) {
            return;
        }
        switch(tile.getLevel() - 1) {
            case 1: level1HitCounter++;
                break;
            case 2: level2HitCounter++;
                break;
            case 3: level3HitCounter++;
                break;
            case 4: level4HitCounter++;
                break;
        }
    }

    public int getAmountOfTilesInAir (int tileLevel) {
        switch (tileLevel) {
            case 1: return this.level1AirCounter;
            case 2: return this.level2AirCounter;
            case 3: return this.level3AirCounter;
            case 4: return this.level4AirCounter;
        }
        return 0;
    }

    public int getAmountOfTilesHit (int tileLevel) {
        switch (tileLevel) {
            case 1: return this.level1HitCounter;
            case 2: return this.level2HitCounter;
            case 3: return this.level3HitCounter;
            case 4: return this.level4HitCounter;
        }
        return 0;
    }

    /**Disposes the textures that are needed for creating single tiles*/
    public void dispose() {
        whiteTexture.dispose();
        yellowTexture.dispose();
        blueTexture.dispose();
        purpleTexture.dispose();
    }

    private boolean oneSecondElapsed() {
        updateElapsedTime();
        if (this.elapsedTime > 1) {
            this.elapsedTime = 0;
            return true;
        } else {
            return false;
        }
    }

    private void updateElapsedTime() {
        this.elapsedTime += Gdx.graphics.getDeltaTime();
    }

}
