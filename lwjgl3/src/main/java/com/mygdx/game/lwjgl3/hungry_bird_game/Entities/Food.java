package com.mygdx.game.lwjgl3.hungry_bird_game.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.lwjgl3.abstract_engine.entity.MovableEntity;
import com.mygdx.game.lwjgl3.abstract_engine.entity.TextureObject;

public class Food extends MovableEntity {
    private float scale = 0.3f; // Scale factor for food size
    private static final String[] food_textures = {
        "assets/burger.png",
        "assets/apple.png"
    };

    public Food(String name, float x, float y, float speed, String textureFile) {
        super(name, x, y, speed, textureFile);
    }

    @Override
    public void left() {
        super.left();
    }

    public void setScale(float newScale) {
        this.scale = newScale;
    }

    public static String getRandomTexture() {
        Random random = new Random();
        return food_textures[random.nextInt(food_textures.length)];
    }

    @Override
    public void draw(SpriteBatch batch) {
        TextureObject foodTexture = getTextureObject();
        if (foodTexture != null) {
            batch.draw(foodTexture.getTexture(), getX(), getY(),
                foodTexture.getTexture().getWidth() * scale,
                foodTexture.getTexture().getHeight() * scale);
        }
    }
}
