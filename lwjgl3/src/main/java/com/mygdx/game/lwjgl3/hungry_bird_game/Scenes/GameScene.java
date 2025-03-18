package com.mygdx.game.lwjgl3.hungry_bird_game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.lwjgl3.abstract_engine.collision.CollisionManager;
import com.mygdx.game.lwjgl3.abstract_engine.entity.Entity;
import com.mygdx.game.lwjgl3.abstract_engine.entity.EntityManager;
import com.mygdx.game.lwjgl3.abstract_engine.io.IOManager;
import com.mygdx.game.lwjgl3.abstract_engine.io.Output;
import com.mygdx.game.lwjgl3.abstract_engine.scene.Scene;
import com.mygdx.game.lwjgl3.abstract_engine.scene.SceneManager;
import com.mygdx.game.lwjgl3.hungry_bird_game.Entities.Bird;
import com.mygdx.game.lwjgl3.hungry_bird_game.Entities.Food;
import com.mygdx.game.lwjgl3.hungry_bird_game.Entities.Plane;

import java.util.Iterator;

public class GameScene extends Scene {
    private SpriteBatch batch;
    private Bird testBird;
    private Food testFood;
    private EntityManager em;
    //private Entity entity;
    private IOManager io;
    private CollisionManager cm;
    private SceneManager sm;
    private Output backgroundMusic;
    private BitmapFont font;
    private boolean isPaused = false;
    private boolean escPressedLastFrame = false;
    //private Plane testPlane;
    private Array<Plane> planes = new Array<>();
    private float planeRespawnTimer;
    private float planeRespawnInterval = MathUtils.random(0.5f, 1.0f);
    private float foodRespawnTimer;


    public GameScene(SceneManager sm) {
        this.sm = sm;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        em = new EntityManager();
        io = new IOManager();
        cm = new CollisionManager();

        testBird = new Bird("Bird", 200, 100, "bird.png");
        em.addEntities(testBird);

        //testPlane = new Plane("Plane", Gdx.graphics.getWidth() + 50, 100, 15, Plane.getRandomTexture());
        //em.addEntities(testPlane);

        backgroundMusic = new Output("BackgroundMusic", "background.mp3", 0.08f);
        backgroundMusic.playMusic();
    }

    @Override
    public void update(float delta) {
        if (io.isKeyPressed(Input.Keys.ESCAPE) && !escPressedLastFrame) {
            isPaused = !isPaused;
            escPressedLastFrame = true;
            if (isPaused) {
                pause();
            } else {
                resume();
            }
        }
        if (!io.isKeyPressed(Input.Keys.ESCAPE)) {
            escPressedLastFrame = false;
        }

        if (isPaused) {
            if (io.isKeyPressed(Input.Keys.Q)) {
                Gdx.app.exit();
            }
            if (io.isKeyPressed(Input.Keys.M)) {
                sm.setScene(new MainMenuScene(sm));
            }
        } else {

            testBird.updatePhysics(); // Update physics first

            float minY = 0; // Ground level
            float maxY = Gdx.graphics.getHeight() * 0.89f; // 90% of screen height (higher ceiling)

            if (testBird.getY() < minY) {
                testBird.setY(minY); // Prevent going below screen
            } else if (testBird.getY() > maxY) {
                testBird.setY(maxY); // Prevent flying too high
            }

            if (io.isKeyPressed(Input.Keys.SPACE)) {
                testBird.flap();
            }
            /*
            // Plane Movement and Respawning
            if (testPlane != null) {
                testPlane.left();
                if (testPlane.getX() + testPlane.getTextureObject().getTexture().getWidth() < 700) {
                    testPlane.dispose();
                    em.removeEntity(testPlane);
                    testPlane = null;
                    planeRespawnTimer = 0;
                }
            }
            if (testPlane == null) {
                planeRespawnTimer += delta;
                if (planeRespawnTimer >= planeRespawnInterval) {
                    float randomY = MathUtils.random(100, Gdx.graphics.getHeight() - 100);
                    testPlane = new Plane("Plane", Gdx.graphics.getWidth() + 50, randomY, 15, Plane.getRandomTexture());
                    em.addEntities(testPlane);
                    planeRespawnTimer = 0;
                    planeRespawnInterval = MathUtils.random(0.5f, 1f);
                }
            }
            */
            // Plane Movement and Respawning
            for (Plane plane : planes) {
                plane.left();
            }
            // Spawning more planes
            planeRespawnTimer += delta;
            if (planeRespawnTimer >= planeRespawnInterval) {
                float randomY = MathUtils.random(100, Gdx.graphics.getHeight() - 100);
                float randomSpeed = MathUtils.random(8, 15);
                Plane newPlane = new Plane("Plane", Gdx.graphics.getWidth() + 50, randomY, randomSpeed, Plane.getRandomTexture());
                planes.add(newPlane);
                em.addEntities(newPlane);
                planeRespawnTimer = 0;
                planeRespawnInterval = MathUtils.random(0.5f, 1.0f);
            }

            // Food Movement and Respawning
            if (testFood != null) {
                testFood.left();
                if (testFood.getX() + testFood.getTextureObject().getTexture().getWidth() < -50) {
                    testFood.dispose();
                    em.removeEntity(testFood);
                    testFood = null;
                    foodRespawnTimer = 0;
                }
            }
            if (testFood == null) {
                foodRespawnTimer += delta;
                if (foodRespawnTimer >= 2.0f) {
                    float randomY = MathUtils.random(50, Gdx.graphics.getHeight() - 100);
                    testFood = new Food("Food", Gdx.graphics.getWidth() + 50, randomY, 5, Food.getRandomTexture());
                    em.addEntities(testFood);
                    foodRespawnTimer = 0;
                }
            }

            em.checkCollisions(cm);
            handleCollisions();
            em.removeOffScreenEntities();

        }
    }

    @Override
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(Color.BLACK);
        if (isPaused) {
            batch.begin();
            GlyphLayout layout = new GlyphLayout();
            String text = "[ PAUSED ]\n\nPress ESC to Resume\n\nPress M to Return to Main Menu\n\nPress Q to Quit";
            layout.setText(font, text);

            float x = (Gdx.graphics.getWidth() - layout.width) / 2;
            float y = (Gdx.graphics.getHeight() + layout.height) / 2;

            font.draw(batch, text, x, y);
            batch.end();
        } else {
            em.draw(batch);
            batch.begin();
            font.draw(batch, "Press SPACE to Flap", 20, Gdx.graphics.getHeight() - 20);
            font.draw(batch, "Press ESC to Pause", 20, Gdx.graphics.getHeight() - 40);

            batch.end();
        }
    }

    @Override
    public void pause() {
        isPaused = true;
        Output.stopMusic("BackgroundMusic");
    }

    @Override
    public void resume() {
        isPaused = false;
        backgroundMusic.playMusic();
    }

    @Override
    public void dispose() {
        batch.dispose();
        em.dispose();
        font.dispose();
        Output.stopMusic("BackgroundMusic");
    }

    private void handleCollisions() {
        for (int i = em.getEntityCount() - 1; i >= 0; i--) { // Iterate backwards to avoid index shifting
            Entity entity = em.get(i);

            if (entity instanceof Plane || entity instanceof Food) {
                if (testBird.checkCollision(entity)) {
                    System.out.println("Collision detected between Bird and " + entity.getName());
                    entity.dispose(); // Free resources
                    em.remove(i); // Remove entity from entity list
                }
            }
        }
    }

}
