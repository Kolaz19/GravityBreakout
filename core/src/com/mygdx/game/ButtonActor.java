package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ButtonActor extends Actor implements OnButtonClick {
    private Texture button, buttonPressed, currentButton;
    private Vector2 position;
    private int width, height;

    public ButtonActor(String pathButtonDefault, String pathButtonPressed, int xCord, int yCord) {
        button = new Texture(Gdx.files.internal(pathButtonDefault));
        buttonPressed = new Texture(Gdx.files.internal(pathButtonPressed));
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
        boolean includesMouse = includesMouse(position, width, height);
        changeTextureWithInput(includesMouse);
        if (includesMouse && isButtonClicked()) {
            onButtonClick();
        }
    }

    private void changeTextureWithInput(boolean includesMouse) {
        if (includesMouse) {
            currentButton = buttonPressed;
        } else {
            currentButton = button;
        }
    }

    public void dispose() {
        button.dispose();
        buttonPressed.dispose();
    }


}
