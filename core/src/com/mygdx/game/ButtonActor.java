package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ButtonActor extends Actor {
    private Texture button, buttonPressed, currentButton;
    private Vector2 position;
    private int width, height;

    public ButtonActor(String pathButtonDefault, String pathButtonPressed, int xCord, int yCord) {
        button = new Texture(pathButtonDefault);
        buttonPressed = new Texture(pathButtonPressed);
        currentButton = button;
        position = new Vector2(xCord, yCord);
        width = currentButton.getWidth();
        height = currentButton.getHeight();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentButton, position.x, position.y);
    }

    @Override
    public void act(float delta) {
        changeTextureWithInput();
    }

    private void changeTextureWithInput() {
        if (includesMouse()) {
            currentButton = buttonPressed;
        } else {
            currentButton = button;
        }
    }

    private boolean includesMouse() {
        if ((MouseCoordinates.getX() > position.x && MouseCoordinates.getX() < position.x + width)
            && (MouseCoordinates.getY() > position.y && MouseCoordinates.getY() < position.y + height)) {
            return true;
        } else {
            return false;
        }
    }

}
