package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HighscoreLabel extends Actor {
    private Label label;
    private boolean initialized;
    private boolean getBigger;
    float time;
    private Score score;
    private int level;

    public HighscoreLabel(Score score, int level) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("silkscreen.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        this.label = new Label("0",style);

        this.label.setFontScale(0.01f);
        this.getBigger = true;
        this.initialized = false;
        this.score = score;
        this.level = level;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.label.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        if (!initialized) {
            initialize();
        }

        if (getBigger) {
            this.label.setFontScale(this.label.getFontScaleX() + 0.03f);
            if (this.label.getFontScaleX() > 3f) {
                getBigger = false;
            }
        }

        changeColor();
    }

    private void initialize () {
        if(this.score.getScore() > SaveGame.getSavedHighscore(level)) {
            this.label.setText("New Highscore!");
            this.label.setColor(Color.GREEN);
            this.label.setPosition(1480 / 2 - 655,1130 / 2);
        } else {
            this.label.setText("Game Over");
            this.label.setColor(Color.FIREBRICK);
            this.label.setPosition(1480 / 2 - 430,1130 / 2);
        }
        initialized = true;
    }

    private void changeColor() {
        time += Gdx.graphics.getDeltaTime();

        if (time > 1) {
            time = 0;
            if (label.getColor().toString().equals("b22222ff")) {
                label.setColor(Color.DARK_GRAY);
            } else if (label.getColor().toString().equals("3f3f3fff")) {
                label.setColor(Color.FIREBRICK);
            } else if (label.getColor().toString().equals("00ff00ff")) {
                label.setColor(Color.BLUE);
            } else if (label.getColor().toString().equals("0000ffff")) {
                label.setColor(Color.GREEN);
            }

        }
    }


}
