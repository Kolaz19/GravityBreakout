package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Platform {
    private Body body;
    private Texture texture;
    private final float width, height, backgroundWidth;
    private final float box2DPosY = 8 / Main.PIXELS_TO_METERS;
    private final int boundaryWidth = 11;

    enum Area {
        LEFT,
        RIGHT,
        MIDDLE
    }

    public Platform(World world, int backgroundWidth) {
        texture = new Texture("platform.png");
        width = texture.getWidth();
        height = texture.getHeight();
        this.backgroundWidth = backgroundWidth;
        //BodyDefinition
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.x = (backgroundWidth / 2) / Main.PIXELS_TO_METERS;
        bodyDef.position.y = box2DPosY;
        //Polugon Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f / Main.PIXELS_TO_METERS, height / 2f / Main.PIXELS_TO_METERS);
        //Fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        fixtureDef.friction = 0;
        fixtureDef.filter.categoryBits = Listener.PLATFORM_ENTITY;
        fixtureDef.filter.maskBits = Listener.BALL_ENTITY | Listener.TILE_ENTITY;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void updateCoordinate() {
        if (isMouseOverLeftBoundary()) {
            body.setTransform(boundaryWidth / Main.PIXELS_TO_METERS,box2DPosY,0);
        } else if (isMouseOverRightBoundary()) {
            body.setTransform((backgroundWidth - boundaryWidth) / Main.PIXELS_TO_METERS, box2DPosY, 0);
        } else {
            body.setTransform(MouseCoordinates.getBoxX(), box2DPosY, 0);
        }
    }

    private boolean isMouseOverLeftBoundary() {
        return MouseCoordinates.getX() < boundaryWidth;
    }

    private boolean isMouseOverRightBoundary() {
        return MouseCoordinates.getX() > backgroundWidth - boundaryWidth;
    }

    public float getOriginX() {
        return body.getPosition().x;
    }

    public Area getArea(float x) {
        //TODO restrict right and left area to only platform
        float oneThird = width / 3f / Main.PIXELS_TO_METERS;
        if (x > body.getPosition().x + oneThird / 2) {
            return Area.RIGHT;
        } else if(x < body.getPosition().x - oneThird / 2) {
            return Area.LEFT;
        } else {
            return Area.MIDDLE;
        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x * Main.PIXELS_TO_METERS - (width / 2), body.getPosition().y * Main.PIXELS_TO_METERS - (height / 2));
    }

}
