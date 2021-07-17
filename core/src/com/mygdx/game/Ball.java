package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.concurrent.ThreadLocalRandom;

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
        bodyDef.position.y = spawnCoordinateY / MainGame.PIXELS_TO_METERS;
        //Polugon Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / MainGame.PIXELS_TO_METERS, height / 2 / MainGame.PIXELS_TO_METERS);
        //Fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;
        fixtureDef.density = 0;
        fixtureDef.filter.categoryBits = Listener.BALL_ENTITY;
        fixtureDef.filter.maskBits = Listener.PLATFORM_ENTITY | Listener.WALL_ENTITY | Listener.TILE_INACTIVE_TILE;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void update (float platformX) {
        if(!isReleased) {
            attachBallToPlatform(platformX);
            checkForBallRelease();
        }
    }

    public Vector2 getLinearVelocity () {
        return body.getLinearVelocity();
    }

    public float getPositionX() {
        return body.getPosition().x;
    }

    public float getPositionY() {
        return body.getPosition().y;
    }

    private void attachBallToPlatform (float platformX) {
        body.setTransform(platformX,spawnCoordinateY / MainGame.PIXELS_TO_METERS,0);
    }

    private void checkForBallRelease() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int randomXForce = 0;
            while (randomXForce == 0) {
                randomXForce = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
            }
            body.setLinearVelocity(randomXForce,10 * initialSpeedMultiplier);
            isReleased = true;
        }
    }

    public void updateDirectionAfterCollision(Platform.Area platformArea) {
        float xForce = 0;
        switch(platformArea) {
            case LEFTLEFT: xForce = -500;
            break;
            case LEFTMID: xForce = -350;
            break;
            case LEFTRIGHT: xForce = - 150;
            break;
            case RIGHTLEFT: xForce = 150;
            break;
            case RIGHTMID: xForce = 350;
            break;
            case RIGHTRIGHT: xForce = 500;
            break;
        }
        body.applyForceToCenter(xForce, 25 * speedIncreaseMultiplier, true);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,body.getPosition().x * MainGame.PIXELS_TO_METERS - (texture.getWidth() / 2),body.getPosition().y * MainGame.PIXELS_TO_METERS - (texture.getHeight() / 2));
    }

    public Body getBody() {
        return this.body;
    }




}
