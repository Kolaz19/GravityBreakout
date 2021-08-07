package com.mygdx.game;

import java.util.ArrayList;
/** Holds the tile coordinates and dimensions for building the individual levels */
public class LevelTemplate {

    public static ArrayList<TileTemplate> getLevelTemplate(int level) {
        switch (level) {
            case 1: return level1();
            case 2: return level2();
            case 3: return level3();
            case 4: return level4();
        }
        return null;
    }

    public static float getSpeedForLevel(int level) {
        switch (level) {
            case 1: return 1;
            case 2: return 1;
            case 3: return 0.8f;
        }
        return 1;
    }

    public static float getSpeedIncreaseForLevel(int level) {
        switch (level) {
            case 1: return 1;
            case 2: return 1;
            case 3: return 2f;
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

    private static ArrayList<TileTemplate> level3() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
        for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 15 + k * 7, 130 + lv_loop * 10 , 5, 8));
        }
        }
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
            for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 40 + k * 7, 100 + lv_loop * 10 , 5, 8));
            }
        }
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
            for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 80 + k * 7, 130 + lv_loop * 10 , 5, 8));
            }
        }
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
            for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 80 + k * 7, 70 + lv_loop * 10 , 5, 8));
            }
        }
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
            for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 120 + k * 7, 100 + lv_loop * 10 , 5, 8));
            }
        }
        for (int lv_loop = 3; lv_loop > 0; lv_loop--) {
            for (int k = 0; k < 3; k++) {
                templates.add(new TileTemplate( 170 + k * 14, 100 + lv_loop * 15 , 10, 12));
            }
        }

        return templates;
    }

    private static ArrayList<TileTemplate> level4() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        int counter = 7;
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 15; i++) {
                templates.add(new TileTemplate(i*14 + 12f, k* 6 +100 , 13, 4));
            }
        }

        for (int k = 0; k < 7; k++) {
                templates.add(new TileTemplate(k*14 + 14f, k* 6 +120 , 10, 10));
        }

        for (int k = 0; k < 7; k++) {
            templates.add(new TileTemplate(k*14 + 122f,  counter * 6 +114 , 10, 10));
            counter--;
        }

        for (int k = 0; k < 7; k++) {
            templates.add(new TileTemplate(110f,  k * 8 +114 , 7, 7));
        }

        return templates;
    }

}