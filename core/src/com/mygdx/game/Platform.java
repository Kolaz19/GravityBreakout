package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class Platform {
    private Body body;
    private TextureRegion defaultTexture, currentFrame;
    private Animation<TextureRegion> currentAnimation, hitAnimationYellow, hitAnimationBlue, hitAnimationPurple;
    private final float width, height, backgroundWidth;
    private final float box2DPosY = 8 / Main.PIXELS_TO_METERS;
    private final int boundaryWidth = 11;
    private float animationTime;

    enum Area {
        LEFTLEFT,
        LEFTMID,
        LEFTRIGHT,
        MIDDLE,
        RIGHTLEFT,
        RIGHTMID,
        RIGHTRIGHT
    }

    public Platform(World world, int backgroundWidth) {
        defaultTexture = new TextureRegion(new Texture("platform.png"));
        hitAnimationYellow = AnimationFactory.createAnimation(new Texture("platformYellow.png"),17,4, 4, 0.1f);
        hitAnimationBlue = AnimationFactory.createAnimation(new Texture("platformBlue.png"),17,4, 4, 0.1f);
        hitAnimationPurple = AnimationFactory.createAnimation(new Texture("platformPurple.png"),17,4, 4, 0.1f);
        hitAnimationYellow.setPlayMode(Animation.PlayMode.NORMAL);
        hitAnimationBlue.setPlayMode(Animation.PlayMode.NORMAL);
        hitAnimationPurple.setPlayMode(Animation.PlayMode.NORMAL);

        currentFrame = this.defaultTexture;

        width = defaultTexture.getRegionWidth();
        height = defaultTexture.getRegionHeight();
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
        fixtureDef.filter.maskBits = Listener.BALL_ENTITY | Listener.TILE_INACTIVE_TILE | Listener.TILE_ACTIVE_TILE;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void update() {
        updateCoordinate();
        updateFrame();
    }

    private void updateFrame() {
        if (this.currentAnimation == null) {
            return;
        }
        this.animationTime = this.animationTime + Gdx.graphics.getDeltaTime();

        if (this.currentAnimation.isAnimationFinished(this.animationTime)) {
            currentFrame = defaultTexture;
        } else {
            this.currentFrame = this.currentAnimation.getKeyFrame(this.animationTime);
        }
    }

    public void startHitAnimation(int tileLevel) {
        this.animationTime = 0;
        switch(tileLevel) {
            case 2: this.currentAnimation = this.hitAnimationYellow;
            break;
            case 3: this.currentAnimation = this.hitAnimationBlue;
            break;
            case 4: this.currentAnimation = this.hitAnimationPurple;
            break;
        }
    }

    private void updateCoordinate() {
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
        Area[] areasInOrder = Area.values();
        float areaLengths = this.width / areasInOrder.length;
        float startPosWorld = this.body.getPosition().x * Main.PIXELS_TO_METERS - this.width / 2;

        for (int loop = 0; loop < areasInOrder.length; loop++) {
            float leftBorderOfArea = startPosWorld + areaLengths * loop;
            float rightBorderOfArea = startPosWorld + areaLengths * (loop + 1);
            if (x >= leftBorderOfArea && x < rightBorderOfArea) {
                return areasInOrder[loop];
            }
        }
        //If ball touches sides (the exception)
        if (x < body.getPosition().x * Main.PIXELS_TO_METERS) {
            return areasInOrder[0];
        } else {
            return areasInOrder[areasInOrder.length-1];
        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, body.getPosition().x * Main.PIXELS_TO_METERS - (width / 2), body.getPosition().y * Main.PIXELS_TO_METERS - (height / 2));
    }

}
