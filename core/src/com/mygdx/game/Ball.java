package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Ball {
    private Body body;
    private Texture texture;
    private final int width, height;
    private final int spawnCoordinateY = 13;
    private boolean isReleased;
    private final float initialSpeedMultiplier;
    private final float speedIncreaseMultiplier;

    public Ball(World world, float platformX, float initialSpeed, float speedIncrease) {
        texture = new Texture("ball.png");
        width = texture.getWidth();
        height = texture.getHeight();
        isReleased = false;
        this.initialSpeedMultiplier = initialSpeed;
        this.speedIncreaseMultiplier = speedIncrease;
        //BodyDefinition
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.gravityScale = 0;
        bodyDef.linearDamping = 0;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.x = platformX;
        bodyDef.position.y = spawnCoordinateY;
        //Polugon Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Main.PIXELS_TO_METERS, height / 2 / Main.PIXELS_TO_METERS);
        //Fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;
        fixtureDef.density = 0;
        fixtureDef.filter.categoryBits = Listener.BALL_ENTITY;
        fixtureDef.filter.maskBits = Listener.PLATFORM_ENTITY | Listener.WALL_ENTITY | Listener.TILE_ENTITY;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void update (float platformX) {
        if(!isReleased) {
            attachBallToPlatform(platformX);
            checkForBallRelease();
        }
    }

    public float getPositionX() {
        return body.getPosition().x;
    }

    private void attachBallToPlatform (float platformX) {
        body.setTransform(platformX,spawnCoordinateY / Main.PIXELS_TO_METERS,0);
    }

    private void checkForBallRelease() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            body.setLinearVelocity(0,10 * initialSpeedMultiplier);
            isReleased = true;
        }
    }

    public void updateDirectionAfterCollision(Platform.Area platformArea) {
        //TODO Add More areas the ball can land on --> Differentiate ball speed more
        switch(platformArea) {
            case LEFT: body.applyForceToCenter(-600,25 * speedIncreaseMultiplier,true);
            break;
            case RIGHT: body.applyForceToCenter(600,25 * speedIncreaseMultiplier,true);
            break;
            case MIDDLE: body.applyForceToCenter(0,25 * speedIncreaseMultiplier,true);
            break;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,body.getPosition().x * Main.PIXELS_TO_METERS - (texture.getWidth() / 2),body.getPosition().y * Main.PIXELS_TO_METERS - (texture.getHeight() / 2));
    }





}
