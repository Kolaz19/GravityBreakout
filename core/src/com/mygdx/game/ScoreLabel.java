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
    private float scale;
    private boolean getBigger;

    public ScoreLabel() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("silkscreen.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        this.label = new Label("0",style);
        this.label.setPosition(1480,1130);
        this.scale = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.label.draw(batch,parentAlpha);
    }

    public void setScore(int score) {
        setPosition(score);
        this.label.setText(String.valueOf(score));
    }

    @Override
    public void act(float delta) {
        if (label.getFontScaleX() > 1.1f) {
            getBigger = false;
        } else if (label.getFontScaleX() < 1.0f) {
            getBigger = true;
        }

        if (getBigger) {
            label.setFontScale(label.getFontScaleX() + 0.005f);
        } else {
            label.setFontScale(label.getFontScaleX() - 0.005f);
        }
    }

    private void setPosition(int score) {
        if (score > 9999) {
            this.label.setPosition(1325, 1130);
        } else if (score > 999) {
            this.label.setPosition(1365, 1130);
        } else if (score > 99) {
            this.label.setPosition(1404, 1130);
        } else if (score > 9) {
            this.label.setPosition(1440, 1130);
        } else {
            this.label.setPosition(1475, 1130);
        }
    }


}
