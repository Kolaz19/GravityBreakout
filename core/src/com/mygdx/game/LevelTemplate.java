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

    public static float getSpeedForLevel(int level) {
        switch (level) {
            case 1: return 1;
        }
        return 1;
    }

    public static float getSpeedIncreaseForLevel(int level) {
        switch (level) {
            case 1: return 1;
        }
        return 1;
    }

    private static ArrayList<TileTemplate> level1() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k =1; k < 12; k++) {
            templates.add(new TileTemplate(18.3f * k, 150, 15, 5));
        }
        for (int k =1; k < 10; k++) {
            templates.add(new TileTemplate(18.3f + 18.3f * k, 143, 15, 5));
        }
        for (int k =1; k < 8; k++) {
            templates.add(new TileTemplate(36.6f + 18.3f * k, 136, 15, 5));
        }
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