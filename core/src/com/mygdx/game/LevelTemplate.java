package com.mygdx.game;

import java.util.ArrayList;

public class LevelTemplate {

    public static ArrayList<TileTemplate> level1() {
        ArrayList<TileTemplate> templates = new ArrayList<TileTemplate>();
        templates.add(new TileTemplate(50,50,20,20));
        templates.add(new TileTemplate(150,150,50,10));
        return templates;
    }

}