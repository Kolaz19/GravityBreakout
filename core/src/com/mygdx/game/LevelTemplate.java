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
            case 5: return level5();
            case 6: return level6();
            case 7: return level7();
            case 8: return level8();
        }
        return null;
    }

    public static float getSpeedForLevel(int level) {
        switch (level) {

        }
        return 1;
    }

    public static float getSpeedIncreaseForLevel(int level) {
        switch (level) {

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

    private static ArrayList<TileTemplate> level5() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(35 + k * 55, 150  , 50, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(74 + k * 55, 140  , 50, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(35 + k * 55, 130  , 50, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(74 + k * 55, 120  , 50, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(180 + k * 12, 150  , 10, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(180 + k * 12, 130  , 10, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(15 + k * 12, 140  , 10, 5));
        }

        for (int k = 0; k < 3; k++) {
            templates.add(new TileTemplate(15 + k * 12, 120  , 10, 5));
        }

        return templates;
    }

    private static ArrayList<TileTemplate> level6() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int lv_loop = 0; lv_loop < 16; lv_loop++) {
            templates.add(new TileTemplate( 12 + lv_loop * 12, 115 - (lv_loop * 3)  , 10, 20));
            templates.add(new TileTemplate( 12 + lv_loop * 12, 131 - (lv_loop * 3)  , 10, 10));
            templates.add(new TileTemplate( 12 + lv_loop * 12, 139 - (lv_loop * 3)  , 10, 5));
            templates.add(new TileTemplate( 12 + lv_loop * 12, 143 - (lv_loop * 3)  , 10, 2));
        }

        return templates;
    }

    private static ArrayList<TileTemplate> level7() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        int yPlacement = 120;

        for (int k = 0; k < 6; k++) {
            templates.add(new TileTemplate( 30 + 32 * k, 145, 30, 30));
        }

        for (int l = 0; l < 21; l++) {
            templates.add(new TileTemplate( 20 + l *  9, yPlacement, 8, 8));
            if (yPlacement == 120) {
                yPlacement = 95;
            } else {
                yPlacement =  120;
            }
        }

        for (int p = 0; p < 3; p++) {
            templates.add(new TileTemplate(110, 60 + 10 * p, 140, 8));
        }
        return templates;
    }

    private static ArrayList<TileTemplate> level8() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();

        for (int k = 0; k < 6; k++) {
            templates.add(new TileTemplate( 25 + k * (40 - k), 120 - k * (5 - k),30 - k, 30 - k ));
        }

        for (int k = 0; k < 6; k++) {
            templates.add(new TileTemplate( 25 + k * (40 - k), 85 - k * (5 - k),15 - k, 30 - k ));
        }

        for (int k = 0; k < 6; k++) {
            templates.add(new TileTemplate( 25 + k * (40 - k), 140 - k * (5 - k),30 - k, 5 ));
        }

        for (int k = 0; k < 10; k++) {
            templates.add(new TileTemplate( 18 + k * 20, 147,15 - k, 5 ));
        }
        for (int k = 0; k < 10; k++) {
            templates.add(new TileTemplate( 30 + k * 20, 153,15 - k, 5 ));
        }


        return templates;
    }

}