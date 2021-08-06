package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import java.util.logging.Level;


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
        mainGame = new MainGame(this);
        mainGame.create();
        menu = new MainMenu(this);
        menu.create();
        levelSelectMenu = new LevelSelectMenu(this);
        levelSelectMenu.create();

        //changeLevel(2);
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
        this.currentState = state;
        switch (state) {
            case GAME:
                mainGame.resize();
                break;
            case MENU:
                menu.resize();
                break;
            case LEVELSELECT:
                levelSelectMenu.resize();
                levelSelectMenu.update();
                break;
        }
    }

    public void changeLevel(int level) {
        mainGame.setLevel(level);
    }



}
