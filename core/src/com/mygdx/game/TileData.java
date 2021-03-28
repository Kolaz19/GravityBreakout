package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/** Holds the body and the dynamic flag of a tile */
public class TileData {
    private Body tile;
    private final float height;
    private final float width;
    private TextureRegion texture;
    private Vector2 initialImpulse;
    //We cannot set the body to dynamic in collision check, so we do it afterwards
    private boolean isDynamic;
    private int tileLevel;
    private TileParticles particles;

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
        this.tileLevel = 1;
        particles = new TileParticles(height, width);
    }


    public void setDynamicFlag() {
        isDynamic = true;
    }

    /** Checks if dynamic flag has been set and changes body to dynamic */
    public void update() {
        if (isDynamic && tile.getType() == BodyDef.BodyType.StaticBody) {
            tile.setType(BodyDef.BodyType.DynamicBody);
            Filter filter = new Filter();
            filter.categoryBits = Listener.TILE_ACTIVE_TILE;
            filter.maskBits = Listener.PLATFORM_ENTITY | Listener.WALL_ENTITY | Listener.TILE_INACTIVE_TILE | Listener.TILE_ACTIVE_TILE;
            tile.getFixtureList().get(0).setFilterData(filter);
            tile.applyLinearImpulse(initialImpulse,tile.getWorldCenter(),true);
        }
    }

    public boolean isDynamic () {
        return isDynamic;
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

    public float getDrawX() {
        return this.getX() - this.getWidth() / 2;
    }

    public float getDrawY() {
        return this.getY() - this.getHeight() / 2;
    }

    public void render(SpriteBatch batch) {
        particles.render(batch,this.getX(), this.getY());
        batch.draw(texture,this.getDrawX(), this.getDrawY(), this.getX() - this.getDrawX(),this.getY() - this.getDrawY(), this.getWidth(), this.getHeight(), 1f, 1f, (float) Math.toDegrees(this.getBody().getAngle()));
    }


    public boolean isOutOfScreen() {
        if (this.getHeight() > this.getWidth()) {
            return this.getDrawY() + this.getHeight() < 0;
        } else {
            return this.getDrawY() + this.getWidth() < 0;
        }
    }

    public void applyInitialImpulse(Vector2 vect) {
        vect.x = vect.x * 2;
        vect.y = vect.y * 2;
        this.initialImpulse = new Vector2(vect);
    }

    public void increaseTileLevel() {
        if (this.tileLevel < 4) {
            this.tileLevel++;
            particles.updateCurrentEffect(this.tileLevel);
        }
    }

    public void dispose() {
        particles.dispose();
    }

}
