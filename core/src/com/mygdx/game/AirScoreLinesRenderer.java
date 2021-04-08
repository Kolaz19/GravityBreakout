package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class AirScoreLinesRenderer {
    private ShapeRenderer shapeRenderer;

    public AirScoreLinesRenderer(OrthographicCamera camera) {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(LinkedList<Vector2> pointsToConnect, Color color) {
        if (pointsToConnect.size() < 2) {
            return;
        }
        shapeRenderer.setColor(color);
        shapeRenderer.begin();
        for(int loop = 1; loop < pointsToConnect.size(); loop++) {
            shapeRenderer.rectLine(pointsToConnect.get(loop-1), pointsToConnect.get(loop),0.02f);
        }
        shapeRenderer.end();
    }

    public void setProjectionMatrix(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

}
