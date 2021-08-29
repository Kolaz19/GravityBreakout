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
            case 9: return level9();
            case 10: return level10();
            case 11: return level11();
            case 12: return level12();
        }
        return null;
    }

    public static float getSpeedForLevel(int level) {
        switch (level) {
            case 1: return 1;
            case 2: return 1.3f;
            case 3: return 1.2f;
            case 4: return 1;
            case 5: return 1.1f;
            case 6: return 1;
            case 7: return 1;
            case 8: return 1.3f;
            case 9: return 1;
            case 10: return 1;
            case 11: return 1;
            case 12: return 1.3f;
        }
        return 1;
    }

    public static float getSpeedIncreaseForLevel(int level) {
        switch (level) {
            case 1: return 1.2f;
            case 2: return 1;
            case 3: return 1.1f;
            case 4: return 1;
            case 5: return 0.8f;
            case 6: return 1.5f;
            case 7: return 1;
            case 8: return 1;
            case 9: return 0.8f;
            case 10: return 1.4f;
            case 11: return 1;
            case 12: return 1f;
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

    private static ArrayList<TileTemplate> level9() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();

        for (int k = 0; k < 15; k++) {
            templates.add(new TileTemplate(60, 140 - k * 5, 4.5f + 4 * k, 3));
        }
        for (int k = 0; k < 15; k++) {
            templates.add(new TileTemplate(160, 140 - k * 5, 4.5f + 4 * k, 3));
        }

        for (int k = 15; k > 0; k--) {
            templates.add(new TileTemplate(110, 65 + k * 5, 4.5f + 4 * k, 3));
        }
        return templates;
    }

    private static ArrayList<TileTemplate> level10() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        for (int k = 0; k < 27; k++) {
            templates.add(new TileTemplate(19 + k * 7, 60, 6, 4));
            templates.add(new TileTemplate(19 + k * 7, 65, 6, 4));

            templates.add(new TileTemplate(19 + k * 7, 147, 6, 4));
            templates.add(new TileTemplate(19 + k * 7, 152, 6, 4));
        }

        for (int k = 0; k < 11; k++) {
            templates.add(new TileTemplate(18,71 + k *7 , 4, 6));
            templates.add(new TileTemplate(202,71 + k *7 , 4, 6));
        }

        templates.add(new TileTemplate(120,120, 20, 15));
        templates.add(new TileTemplate(60,120, 20, 15));
        templates.add(new TileTemplate(75,90, 40, 5));


        return templates;
    }

    private static ArrayList<TileTemplate> level11() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        int gap = 25;

        for (int t = 0; t < 3; t++) {
            for (int k = 0; k < 10; k++) {
                templates.add(new TileTemplate(30 + k * 8, 90 + gap * t, 7, 2));
                templates.add(new TileTemplate(30 + k * 8, 87 + gap * t, 7, 2));
            }
            templates.add(new TileTemplate(66, 82 + gap * t, 79, 5));
            templates.add(new TileTemplate(66, 95 + gap * t, 79, 5));
        }

        templates.add(new TileTemplate(116.5f, 101, 180, 5));
        templates.add(new TileTemplate(116.5f, 126, 180, 5));


        return templates;
    }

    private static ArrayList<TileTemplate> level12() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();

        for (int k = 0; k < 25; k++) {
            templates.add(new TileTemplate(25 + k * 7, 131, 6, 5 + k));
        }
        for (int k = 0; k < 25; k++) {
            templates.add(new TileTemplate(25 + k * 7, 112, 6, 5 + 25 - k));
        }
        for (int k = 0; k < 25; k++) {
            templates.add(new TileTemplate(25 + k * 7, 93, 6, 5 + k));
        }
        for (int k = 0; k < 25; k++) {
            templates.add(new TileTemplate(25 + k * 7, 74, 6, 5 + 25 - k));
        }


        return templates;
    }

}