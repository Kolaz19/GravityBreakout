package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public interface OnButtonClick {

    public default boolean includesMouse(Vector2 position, int width, int height) {
        float hey = MouseCoordinates.getX();
        hey = MouseCoordinates.getY();

        if ((MouseCoordinates.getX() > position.x && MouseCoordinates.getX() < position.x + width)
                && (MouseCoordinates.getY() > position.y && MouseCoordinates.getY() < position.y + height)) {
            return true;
        } else {
            return false;
        }
    }

    public default boolean isButtonClicked() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }

    public abstract void onButtonClick();
}
