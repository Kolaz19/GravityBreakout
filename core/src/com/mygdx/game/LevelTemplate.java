package com.mygdx.game;

import java.util.ArrayList;
/** Holds the tile coordinates and dimensions for building the individual levels */
public class LevelTemplate {

    public static ArrayList<TileTemplate> getLevelTemplate(int level) {
        switch (level) {
            case 1: return level1();
            case 2: return level2();
        }
        return null;
    }

    private static ArrayList<TileTemplate> level1() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k =1; k < 10; k++) {
            for (int i = 1; i < 12; i++) {
                templates.add(new TileTemplate(i*15 + 18, k* 6 +100 , 13, 4));
            }
        }
        templates.add(new TileTemplate(40, 70, 20, 20));
        return templates;
    }

    private static ArrayList<TileTemplate> level2() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k =1; k < 10; k++) {
            for (int i = 1; i < 12; i++) {
                templates.add(new TileTemplate(i*15 + 18, k* 6 +100 , 5, 4));
            }
        }
        return templates;
    }
}