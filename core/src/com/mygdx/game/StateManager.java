package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;



public class StateManager extends ApplicationAdapter {
    private MainGame mainGame;
    private MainMenu menu;
    private LevelSelectMenu levelSelectMenu;
    private State currentState;

    enum State{
        MENU,
        GAME,
        LEVELSELECT
    }

    @Override
    public void create() {
        changeState(State.MENU);
    }

    @Override
    public void render() {
        switch (this.currentState) {
            case GAME:
                mainGame.render();
                break;
            case MENU:
                menu.render();
                break;
            case LEVELSELECT:
                levelSelectMenu.render();
                break;
        }

    }

    @Override
    public void dispose() {

    }

    public void changeState(State state){
        //TODO clean Game after switch from pause to main menu
        this.currentState = state;
        switch (state) {
            case GAME:
                if (mainGame == null) {
                    mainGame = new MainGame(this);
                    mainGame.create();
                    mainGame.setLevel(1);
                }
                mainGame.setLevel(1);
                mainGame.resize();
                break;
            case MENU:
                if (menu == null) {
                    menu = new MainMenu(this);
                    menu.create();
                }
                menu.resize();
                break;
            case LEVELSELECT:
                if (levelSelectMenu == null) {
                    levelSelectMenu = new LevelSelectMenu(this);
                    levelSelectMenu.create();
                }
                levelSelectMenu.resize();
                break;
        }
    }



}
