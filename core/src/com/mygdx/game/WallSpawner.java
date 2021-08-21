package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class WallSpawner {
    //All coordinates in Box2D dimensions
    private final float backgroundWidth;
    private final float backgroundHeight;
    private final float distanceToEdge = 2 / MainGame.PIXELS_TO_METERS;
    private World world;
    Body leftWall, rightWall, upperWall;

    public WallSpawner(World world, int backgroundWidth, int backgroundHeight) {
        this.backgroundWidth = backgroundWidth / MainGame.PIXELS_TO_METERS;
        this.backgroundHeight = backgroundHeight / MainGame.PIXELS_TO_METERS;
        this.world = world;

    }

    public void createLeftWall() {
        BodyDef bodyDefLeft = new BodyDef();
        bodyDefLeft.type = BodyDef.BodyType.StaticBody;
        bodyDefLeft.position.set(distanceToEdge, distanceToEdge);
        EdgeShape edgeShapeLeft = new EdgeShape();
        edgeShapeLeft.set(0, 0, 0, backgroundHeight - (2 * (distanceToEdge)));

        FixtureDef fixtureDefLeft = new FixtureDef();
        fixtureDefLeft.shape = edgeShapeLeft;
        fixtureDefLeft.restitution = 1;
        fixtureDefLeft.friction = 0;
        fixtureDefLeft.filter.categoryBits = Listener.WALL_ENTITY;
        fixtureDefLeft.filter.maskBits = Listener.BALL_ENTITY | Listener.TILE_INACTIVE_TILE | Listener.TILE_ACTIVE_TILE;

        leftWall = world.createBody(bodyDefLeft);
        leftWall.createFixture(fixtureDefLeft);
    }

    public void createUpperWall() {
        BodyDef bodyDefUp = new BodyDef();
        bodyDefUp.type = BodyDef.BodyType.StaticBody;
        bodyDefUp.position.set(distanceToEdge, backgroundHeight - distanceToEdge);
        EdgeShape edgeShapeUp = new EdgeShape();
        edgeShapeUp.set(backgroundWidth - (2 * distanceToEdge), 0, 0, 0);

        FixtureDef fixtureDefUp = new FixtureDef();
        fixtureDefUp.shape = edgeShapeUp;
        fixtureDefUp.restitution = 1;
        fixtureDefUp.friction = 0;
        fixtureDefUp.filter.categoryBits = Listener.WALL_ENTITY;
        fixtureDefUp.filter.maskBits = Listener.BALL_ENTITY | Listener.TILE_INACTIVE_TILE | Listener.TILE_ACTIVE_TILE;;

        upperWall = world.createBody(bodyDefUp);
        upperWall.createFixture(fixtureDefUp);
    }

    public void createRightWall() {
        BodyDef bodyDefRight = new BodyDef();
        bodyDefRight.type = BodyDef.BodyType.StaticBody;
        bodyDefRight.position.set(backgroundWidth - distanceToEdge,distanceToEdge);
        EdgeShape edgeShapeRight = new EdgeShape();
        edgeShapeRight.set(0, 0, 0, backgroundHeight - (2 * distanceToEdge));

        FixtureDef fixtureDefRight = new FixtureDef();
        fixtureDefRight.shape = edgeShapeRight;
        fixtureDefRight.restitution = 1;
        fixtureDefRight.friction = 0;
        fixtureDefRight.filter.categoryBits = Listener.WALL_ENTITY;
        fixtureDefRight.filter.maskBits = Listener.BALL_ENTITY | Listener.TILE_INACTIVE_TILE | Listener.TILE_ACTIVE_TILE;;

        rightWall = world.createBody(bodyDefRight);
        rightWall.createFixture(fixtureDefRight);
    }

    public void dispose() {
        world.destroyBody(this.leftWall);
        world.destroyBody(this.rightWall);
        world.destroyBody(this.upperWall);
    }

}
