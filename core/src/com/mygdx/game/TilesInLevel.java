package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;

/** Holds all tiles in a lever takes care of adding new tiles and removing old ones and gives back info about the level of the tiles */
public class TilesInLevel extends ArrayList<TileData> {
    private Texture whiteTexture, yellowTexture, blueTexture, purpleTexture;
    private int level1Counter, level2Counter, level3Counter, level4counter;
    private World world;

    public TilesInLevel (World world) {
        this.world = world;
        whiteTexture = new Texture("tile.png");
        yellowTexture = new Texture("yellowTile.png");
        blueTexture = new Texture("blueTile.png");
        purpleTexture = new Texture("purpleTile.png");
        level1Counter = level2Counter = level3Counter = level4counter;
    }

    /** Adds all tiles for one level and deletes the old tiles */
    public void setTilesForLevel(ArrayList<TileTemplate> templates) {
        this.clear();
        for (TileTemplate template : templates) {
            this.add(new TileData(template.createTile(world), whiteTexture, yellowTexture, blueTexture, purpleTexture));
        }
    }

    public void disposeTilesOutOfBounds() {
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
    public void updateLevelCounters() {

    }

    /**Disposes the textures that are needed for creating single tiles*/
    public void dispose() {
        whiteTexture.dispose();
        yellowTexture.dispose();
        blueTexture.dispose();
        purpleTexture.dispose();
    }

}
