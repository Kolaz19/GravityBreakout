package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LevelNodeScoreActor extends Actor {
    private Vector2 position;
    private int level;
    private Label label;
    private int currentScore;

    public LevelNodeScoreActor(int xCord, int yCord, int level) {
        this.position = new Vector2(xCord, yCord);
        this.level = level;

        //Font/Label
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("silkscreen.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(parameter);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        this.label = new Label("0",style);
        this.label.setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.label.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
