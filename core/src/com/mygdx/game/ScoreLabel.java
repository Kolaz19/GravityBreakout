package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ScoreLabel extends Actor {
    private Label label;
    private Label labelSaved;
    private float scale;
    private boolean getBigger;
    private boolean clickRegistered;
    private int endScore;
    private boolean isHighscore;
    private boolean substractScore;
    private boolean screenFinished;
    private int level;

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

        this.labelSaved = new Label(" ",style);
    }

    public void setLevel(int level) {
        this.level = level;
        getBigger = clickRegistered = isHighscore = substractScore = screenFinished = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.label.draw(batch,parentAlpha);
        if (screenFinished) {
            this.labelSaved.draw(batch,parentAlpha);
        }
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

        if (!clickRegistered && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            clickRegistered = true;
            endScore = Integer.parseInt(String.valueOf(this.label.getText()));
            setGameOverState();
        }

        if (clickRegistered) {
            if (isHighscore) {
                highScoreProcedure();
            } else {
                gameOverProcedure();
            }
        }
    }

    private void highScoreProcedure () {
        int subsctractSpeed = getSubstractSpeed(endScore);

        if (!substractScore && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            substractScore = true;
        }

        if (!substractScore) {
            return;
        }

        if (endScore - subsctractSpeed < 0) {
            endScore = 0;
        } else {
            this.endScore -= subsctractSpeed;
        }
        setPosition(endScore);
        this.label.setText(endScore);

        if (!screenFinished && endScore == 0) {
            labelSaved.setPosition(label.getX() - 230, label.getY());
            labelSaved.setColor(Color.GREEN);
            labelSaved.setText("Saved!");
            screenFinished = true;
        }

    }

    private void gameOverProcedure() {
        if (!screenFinished) {
            screenFinished = true;
            labelSaved.setPosition(label.getX() - 280, label.getY());
            labelSaved.setColor(Color.FIREBRICK);
            labelSaved.setText("Nice try!");
        }

        if (this.label.getY() > -50) {
            this.label.setPosition(label.getX(), label.getY() - 20);
        }
    }

    public boolean isScreenFinished() {
        return screenFinished;
    }

    private void setGameOverState() {
        if (endScore > SaveGame.getSavedHighscore(level)) {
            isHighscore = true;
        } else {
            isHighscore = false;
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

    private int getSubstractSpeed(int score) {
        if (score > 600) {
            return 3;
        } else if (score > 100) {
            return 2;
        } else {
            return 1;
        }
    }


}
