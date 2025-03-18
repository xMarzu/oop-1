package com.mygdx.game.lwjgl3.abstract_engine.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
    public abstract void create();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
    public void pause() {}
    public void resume() {}
}
