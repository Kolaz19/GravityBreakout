package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class StateManager extends ApplicationAdapter {
    private MainGame mainGame;
    private MainMenu menu;
    private LevelSelectMenu levelSelectMenu;
    private TutorialScreen tutorialScreen;
    private State currentState;
    private Music mainTheme;

    enum State{
        MENU,
        GAME,
        LEVELSELECT,
        TUTORIAL
    }

    @Override
    public void create() {
        mainGame = new MainGame(this);
        mainGame.create();
        menu = new MainMenu(this);
        menu.create();
        levelSelectMenu = new LevelSelectMenu(this);
        levelSelectMenu.create();
        tutorialScreen = new TutorialScreen(this);
        tutorialScreen.create();
        mainTheme = Gdx.audio.newMusic(Gdx.files.internal("menuSong.wav"));
        mainTheme.setLooping(true);
        mainTheme.setVolume(0.1f);

        changeLevel(6);
        changeState(State.GAME);
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
            case TUTORIAL:
                tutorialScreen.render();
                break;
        }

    }

    @Override
    public void dispose() {
        mainGame.dispose();
        menu.dispose();
        tutorialScreen.dispose();
        levelSelectMenu.dispose();
    }

    public void changeState(State state){

        this.currentState = state;
        switch (state) {
            case GAME:
                mainTheme.pause();
                mainGame.resize();
                break;
            case MENU:
                if (!mainTheme.isPlaying()) {
                    mainTheme.play();
                }
                menu.resize();
                break;
            case LEVELSELECT:
                if (!mainTheme.isPlaying()) {
                    mainTheme.play();
                }
                levelSelectMenu.resize();
                levelSelectMenu.update();
                break;
            case TUTORIAL:
                tutorialScreen.loadImages();
                tutorialScreen.resize();
                break;
        }
    }

    public void changeLevel(int level) {
        mainGame.setLevel(level);
    }



}
