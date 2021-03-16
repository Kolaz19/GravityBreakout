package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Listener implements ContactListener {
    private Ball currentBall;
    private Platform currentPlatform;

    public static final short BALL_ENTITY = 0x0001;
    public static final short PLATFORM_ENTITY = 0x0002;
    public static final short WALL_ENTITY = 0x0003;
    public static final short TILE_ENTITY = 0x0004;

    public Listener(Ball ball, Platform platform) {
        this.currentBall = ball;
        this.currentPlatform = platform;
    }

    public void setBall(Ball ball) {
        this.currentBall = ball;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
        if (includesBall(contact) && includesPlatform(contact)) {
            ballHitsPlatform();
        }
        if (contact.getFixtureA().getFilterData().categoryBits == TILE_ENTITY) {

        }
        if (contact.getFixtureB().getFilterData().categoryBits == TILE_ENTITY) {

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void ballHitsPlatform() {
        Platform.Area areaToHit = currentPlatform.getArea(currentBall.getPositionX());
        currentBall.updateDirectionAfterCollision(areaToHit);
    }

    private boolean includesBall(Contact contact) {
        return ((contact.getFixtureA().getFilterData().categoryBits == BALL_ENTITY)
            || (contact.getFixtureB().getFilterData().categoryBits == BALL_ENTITY));
    }

    private boolean includesPlatform(Contact contact) {
        return ((contact.getFixtureA().getFilterData().categoryBits == PLATFORM_ENTITY)
                || (contact.getFixtureB().getFilterData().categoryBits == PLATFORM_ENTITY));
    }








}
