package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/** Holds the body and the dynamic flag of a tile */
public class TileData {
    private Body tile;
    private final float height;
    private final float width;
    private TextureRegion texture;
    //We cannot set the body to dynamic in collision check, so we do it afterwards
    private boolean isDynamic;

    public TileData(Body tile, Texture texture) {
        this.tile = tile;
        isDynamic = false;
        this.texture = new TextureRegion(texture);
        //Set height and width through Vertex coordinates
        PolygonShape shape = (PolygonShape) this.tile.getFixtureList().get(0).getShape();
        Vector2 vect = new Vector2(0,0);
        shape.getVertex(0, vect);
        float smallestX = vect.x * Main.PIXELS_TO_METERS;
        float smallestY = vect.y * Main.PIXELS_TO_METERS;
        shape.getVertex(2, vect);
        float highestX = vect.x * Main.PIXELS_TO_METERS;
        float highestY = vect.y * Main.PIXELS_TO_METERS;
        this.height = highestY - smallestY;
        this.width = highestX - smallestX;
    }

    public void setDynamicFlag() {
        isDynamic = true;
    }

    /** Checks if dynamic flag has been set and changes body to dynamic */
    public void update() {
        if (isDynamic) {
            tile.setType(BodyDef.BodyType.DynamicBody);
        }
    }

    public Body getBody() {
        return this.tile;
    }

    public float getX() {
        return getBody().getPosition().x * Main.PIXELS_TO_METERS;
    }

    public float getY() {
        return getBody().getPosition().y * Main.PIXELS_TO_METERS;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,this.getX() - this.getWidth() / 2, this.getY() - this.getHeight() / 2, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 1f, 1f, (float) Math.toDegrees(this.getBody().getAngle()));
    }

}
