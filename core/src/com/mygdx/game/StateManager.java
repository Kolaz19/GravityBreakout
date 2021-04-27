package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;


public class StateManager extends ApplicationAdapter {
    private Main main;


    @Override
    public void create() {
        main = new Main();
        main.create();
    }

    @Override
    public void render() {
        main.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
