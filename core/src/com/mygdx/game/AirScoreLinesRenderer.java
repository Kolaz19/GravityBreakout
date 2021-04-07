package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class AirScoreLinesRenderer {
    private ShapeRenderer shapeRenderer;

    public AirScoreLinesRenderer() {
        shapeRenderer = new ShapeRenderer();
    }

    public void render(LinkedList<Vector2> pointsToConnect, Color color) {
        shapeRenderer.setColor(color);

    }

}
