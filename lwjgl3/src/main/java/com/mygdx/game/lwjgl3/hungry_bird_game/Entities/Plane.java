package com.mygdx.game.lwjgl3.hungry_bird_game.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.lwjgl3.abstract_engine.entity.MovableEntity;
import com.mygdx.game.lwjgl3.abstract_engine.entity.TextureObject;

public class Plane extends MovableEntity {
    private float scale = 0.15f;
    private static final String[] plane_textures = {
        "assets/plane_1_blue.png",
        "assets/plane_1_pink.png",
        "assets/plane_1_red.png",
        "assets/plane_1_yellow.png"
    };
    public Plane(String name, float x, float y, float speed, String textureFile){
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
        return plane_textures[random.nextInt(plane_textures.length)];
    }

    @Override
    public void draw(SpriteBatch batch) {
        TextureObject planeTexture = getTextureObject(); // Access the texture safely
        if (planeTexture != null) {
            batch.draw(planeTexture.getTexture(), getX(), getY(),
                planeTexture.getTexture().getWidth() * scale,
                planeTexture.getTexture().getHeight() * scale);
        }
    }
}
