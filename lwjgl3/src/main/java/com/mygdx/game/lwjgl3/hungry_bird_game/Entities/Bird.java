package com.mygdx.game.lwjgl3.hungry_bird_game.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.lwjgl3.abstract_engine.entity.MovableEntity;
import com.mygdx.game.lwjgl3.abstract_engine.entity.TextureObject;

public class Bird extends MovableEntity {
    private float gravity = -0.3f; // Gravity effect
    private float flapStrength = 8f; // Jump force
    private float scale = 0.3f; // Scale of the bird

    public Bird(String name, float x, float y, String textureFile) {
        super(name, x, y, 0, textureFile);
    }

    public void updatePhysics() {
        setSpeed(getSpeed() + gravity); // Apply gravity to speed
        setY(getY() + getSpeed()); // Move bird using speed
    }

    public void flap() {
        setSpeed(flapStrength); // Apply an upward force
    }

    public void setScale(float newScale) {
        this.scale = newScale;
    }

    @Override
    public void draw(SpriteBatch batch) {
        TextureObject birdTexture = getTextureObject(); // Access the texture safely
        if (birdTexture != null) {
            batch.draw(birdTexture.getTexture(), getX(), getY(),
                    birdTexture.getTexture().getWidth() * scale,
                    birdTexture.getTexture().getHeight() * scale);
        }
    }
}