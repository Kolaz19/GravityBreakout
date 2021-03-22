package com.mygdx.game;

import java.util.ArrayList;
/** Holds the tile coordinates and dimensions for building the individual levels */
public class LevelTemplate {

    public static ArrayList<TileTemplate> level1() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k =1; k < 5; k++) {
            for (int i = 1; i < 12; i++) {
                templates.add(new TileTemplate(i*15 + 18, k* 6 +130 , 13, 4));
            }
        }
        return templates;
    }
}