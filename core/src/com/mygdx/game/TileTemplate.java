package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

/** Does only hold tile coordinates and dimensions for building a body */
public class TileTemplate {
    //All coordinates in box2d world measurments
    private float x;
    private float y;
    private float width;
    private float height;

    public TileTemplate(float x, float y, float width, float height) {
        this.x = x / Main.PIXELS_TO_METERS;
        this.y = y / Main.PIXELS_TO_METERS;
        this.height = height / Main.PIXELS_TO_METERS;
        this.width = width / Main.PIXELS_TO_METERS;
    }

    public Body createTile(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = false;
        bodyDef.linearDamping = 0;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.x = this.x;
        bodyDef.position.y = this.y;
        //Polugon Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width / 2, this.height / 2);
        //Fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;
        fixtureDef.density = 10;
        fixtureDef.filter.categoryBits = Listener.TILE_ENTITY;
        fixtureDef.filter.maskBits = Listener.PLATFORM_ENTITY | Listener.TILE_ENTITY | Listener.WALL_ENTITY;
        //Create body in world
        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        return body;
    }

}
