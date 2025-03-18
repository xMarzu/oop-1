package com.mygdx.game.lwjgl3.abstract_engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticEntity extends Entity implements RenderableEntity {
    private TextureObject texture;

    public StaticEntity() {
        super();
        this.texture = new TextureObject("droplet.png");
    }

    public StaticEntity(String name, float x, float y, String textureFile) {
        super(name, x, y);
        this.texture = new TextureObject(textureFile);
    }

    public TextureObject getTextureObject() {
        return texture;
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
