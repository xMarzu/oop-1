package com.mygdx.game.lwjgl3.abstract_engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureObject {
    private Texture texture;

    public TextureObject() {
        this.texture = null;
    }

    public TextureObject(String textureFile){
        this.texture = new Texture(Gdx.files.internal(textureFile));
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

}
