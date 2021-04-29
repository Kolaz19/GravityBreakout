package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;



public class StateManager extends ApplicationAdapter {
    private Main main;
    private State currentState;

    enum State{
        MENU,
        GAME
    }

    @Override
    public void create() {
        changeState(State.GAME);
    }

    @Override
    public void render() {
        switch (currentState) {
            case GAME:
                main.render();
                break;
        }

    }

    @Override
    public void dispose() {

    }

    public void changeState(State state){
        currentState = state;
        switch (state) {
            case GAME:
                if (main == null) {
                    main = new Main();
                    main.create();
                }
                main.resize();
                break;
            case MENU:
        }
    }



}
