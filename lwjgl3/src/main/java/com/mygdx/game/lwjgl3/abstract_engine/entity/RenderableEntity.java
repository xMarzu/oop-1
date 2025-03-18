package com.mygdx.game.lwjgl3.abstract_engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderableEntity {
    
    public void draw(SpriteBatch batch);

    public void dispose();
}
