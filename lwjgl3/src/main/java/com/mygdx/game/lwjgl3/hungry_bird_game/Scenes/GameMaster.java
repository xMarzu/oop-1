package com.mygdx.game.lwjgl3.hungry_bird_game.Scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.lwjgl3.abstract_engine.scene.SceneManager;

public class GameMaster extends ApplicationAdapter {
    private SpriteBatch batch;
    private SceneManager sm;

    @Override
    public void create() {
        sm = new SceneManager();
        batch = new SpriteBatch();
        sm.setScene(new MainMenuScene(sm));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        sm.update(Gdx.graphics.getDeltaTime());
        sm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
