package com.mygdx.game.lwjgl3.abstract_engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.lwjgl3.abstract_engine.movement.Movement;

public class MovableEntity extends Entity implements Movement, RenderableEntity {
    private float speed;
    private TextureObject texture;

    public MovableEntity() {
        super();
        this.speed = 0;
        this.texture = new TextureObject("droplet.png");
    }

    public MovableEntity(String name, float x, float y, float speed, String textureFile) {
        super(name, x, y);
        this.speed = speed;
        this.texture = new TextureObject(textureFile);
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public TextureObject getTextureObject() {
        return texture;
    }    

    @Override
    public void left() {
        this.setX(this.getX() - speed);
    }

    @Override
    public void right() {
        this.setX(this.getX() + speed);
    }

    @Override
    public void forward() {
        this.setY(speed + this.getY());
    }

    @Override
    public void backward() {
        this.setY(this.getY() - speed);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture.getTexture(), getX(), getY(), texture.getTexture().getWidth(),
                texture.getTexture().getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
