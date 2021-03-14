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

    public static float getY() {
        return cord.y;
    }

    public static Vector2 getBoxCord() {
        return new Vector2(getX() / Main.PIXELS_TO_METERS, getY() / Main.PIXELS_TO_METERS);
    }

    public static float getBoxY() {
        return getY() / Main.PIXELS_TO_METERS;
    }

    public static float getBoxX() {
        return getX() / Main.PIXELS_TO_METERS;
    }

    //TODO Mouse hidden with button press

}
