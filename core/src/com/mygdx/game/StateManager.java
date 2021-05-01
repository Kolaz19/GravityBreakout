package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;



public class StateManager extends ApplicationAdapter {
    private Main main;
    private MainMenu menu;
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
        switch (this.currentState) {
            case GAME:
                main.render();
                break;
            case MENU:
                menu.render();
                break;
        }

    }

    @Override
    public void dispose() {

    }

    public void changeState(State state){
        this.currentState = state;
        switch (state) {
            case GAME:
                if (main == null) {
                    main = new Main(this);
                    main.create();
                }
                main.resize();
                break;
            case MENU:
                if (menu == null) {
                    menu = new MainMenu(this);
                    menu.create();
                }
                menu.resize();
                break;
        }
    }



}
