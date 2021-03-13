package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class Platform {
    private Body body;
    private int width, height;
    private Texture texture;
    private final float box2DPosY = 8 / Main.PIXELS_TO_METERS;


    public Platform(World world, int backgroundWidth) {
        texture = new Texture("platform.png");
        width = texture.getWidth();
        height = texture.getHeight();
        //BodyDefinition
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.x = (backgroundWidth / 2) / Main.PIXELS_TO_METERS;
        bodyDef.position.y = box2DPosY;
        //Polugon Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        //Fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void updateCoordinate() {
        body.setTransform(MouseCoordinates.getBoxX(), box2DPosY,0);
        //TODO set Boundaries
    }



}
