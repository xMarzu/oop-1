package com.mygdx.game.lwjgl3.hungry_bird_game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.lwjgl3.abstract_engine.io.IOManager;
import com.mygdx.game.lwjgl3.abstract_engine.io.Output;
import com.mygdx.game.lwjgl3.abstract_engine.scene.Scene;
import com.mygdx.game.lwjgl3.abstract_engine.scene.SceneManager;

public class MainMenuScene extends Scene {
    private BitmapFont font;
    private final SceneManager sm;
    private IOManager io;
    private Output backgroundMusic;
    private Texture backgroundTexture;
    private Texture titleTexture;
    private SpriteBatch batch;

    public MainMenuScene(SceneManager sm) {
        this.sm = sm;
    }

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        io = new IOManager();
        backgroundMusic = new Output("BackgroundMusic", "play.mp3", 0.08f);
        backgroundMusic.playMusic();
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("assets/background.png"));
        //testing probably dont keep
        titleTexture = new Texture(Gdx.files.internal("assets/title.png"));
    }

    @Override
    public void update(float delta) {
        if (io.isKeyPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
            sm.setScene(new GameScene(sm));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(titleTexture, 200, 540, Gdx.graphics.getWidth()*0.8f, Gdx.graphics.getHeight()*0.3f);
        // Create a layout to measure text size
        GlyphLayout layout = new GlyphLayout();
        String text = "[ MAIN MENU ]\n\nPress SPACE to Start";
        layout.setText(font, text);

        // Calculate centered positions
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() + layout.height) / 2; // Adding height centers it better

        // Draw text at the centered position
        font.draw(batch, text, x, y);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        backgroundTexture.dispose();
        Output.stopMusic("BackgroundMusic");
    }
}
