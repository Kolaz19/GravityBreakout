package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ScoreLabel extends Actor {
    private Label label;

    public ScoreLabel() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("silkscreen.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        this.label = new Label("0",style);
        this.label.setPosition(1050,805);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.label.setPosition(1050,50);
        this.label.draw(batch,parentAlpha);
    }

    public void setScore(int score) {
        setPosition(score);
        this.label.setText(String.valueOf(score));
    }

    @Override
    public void act(float delta) {

    }

    private void setPosition(int score) {
        if (score > 999) {
            this.label.setPosition(975, 805);
        } else if (score > 99) {
            this.label.setPosition(1000, 805);
        } else if (score > 9) {
            this.label.setPosition(1025, 805);
        } else {
            this.label.setPosition(1050, 805);
        }
    }


}
