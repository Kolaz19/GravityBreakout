package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MouseCoordinates {
    private static Vector2 cord;

    static {
        cord = new Vector2();
    }


    public static void update(OrthographicCamera cam) {
        Vector3 mousePos = new Vector3();
        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.input.getY();
        cam.unproject(mousePos);
        cord.set(mousePos.x, mousePos.y);
    }

    public static Vector2 getCord() {
        return cord;
    }

    public static float getX() {
        return cord.x;
    }

    public static void correctMouseOutOfBounds(OrthographicCamera cam) {
        Vector3 mousePos = new Vector3();
        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.input.getY();
        cam.unproject(mousePos);
        if (mousePos.x < 0) {
            mousePos.x = 0;
            mousePos.y = 150;
            cam.project(mousePos);
            Gdx.input.setCursorPosition((int) mousePos.x, (int) mousePos.y);
        } else if (mousePos.x > 220) {
            mousePos.x = 220;
            mousePos.y = 150;
            cam.project(mousePos);
            Gdx.input.setCursorPosition((int) mousePos.x, (int) mousePos.y);
        }
    }

    public static float getY() {
        return cord.y;
    }

    public static Vector2 getBoxCord() {
        return new Vector2(getX() / MainGame.PIXELS_TO_METERS, getY() / MainGame.PIXELS_TO_METERS);
    }

    public static float getBoxY() {
        return getY() / MainGame.PIXELS_TO_METERS;
    }

    public static float getBoxX() {
        return getX() / MainGame.PIXELS_TO_METERS;
    }


}
