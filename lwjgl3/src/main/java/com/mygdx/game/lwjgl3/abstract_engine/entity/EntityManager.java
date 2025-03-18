package com.mygdx.game.lwjgl3.abstract_engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.lwjgl3.abstract_engine.collision.Collidable;
import com.mygdx.game.lwjgl3.abstract_engine.collision.CollisionManager;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<>();
    }

    // Get Entity using index
    public Entity get(int index) {
        if (index >= 0 && index < entityList.size()) {
            return entityList.get(index);
        }
        return null;
    }

    // Add Entity into entityList
    public void addEntities(Entity entity) {
        entityList.add(entity);
    }

    // Remove Entity from entityList

    public void remove(int index) {
        if (index >= 0 && index < entityList.size()) {
            entityList.remove(index);
        }
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public int getEntityCount() {
        return entityList.size();
    }

    public void checkCollisions(CollisionManager collisionManager) {
        List<Collidable> collidables = new ArrayList<>();
        for (Entity entity : entityList) {
            if (entity instanceof Collidable) {
                collidables.add((Collidable) entity);
            }
        }
        collisionManager.detectCollisions(collidables);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for (Entity entity : entityList) {
            if (entity instanceof RenderableEntity) {
                ((RenderableEntity) entity).draw(batch);
            }
        }
        batch.end();
    }

    public void dispose() {
        for (Entity entity : entityList) {
            if (entity instanceof RenderableEntity) {
                ((RenderableEntity) entity).dispose();
            }
        }
    }

    public void removeOffScreenEntities() {
        for (int i = entityList.size() - 1; i >= 0; i--) { // Reverse iteration to prevent index shift
            Entity entity = entityList.get(i);

            if (entity instanceof MovableEntity) {
                MovableEntity movable = (MovableEntity) entity;
                if (movable.getX() + movable.getTextureObject().getTexture().getWidth() < 0) {
                    movable.dispose(); // Free resources
                    remove(i); // Remove from entity list
                }
            } else if (entity instanceof StaticEntity) {
                StaticEntity staticEntity = (StaticEntity) entity;
                if (staticEntity.getX() + staticEntity.getTextureObject().getTexture().getWidth() < 0) {
                    staticEntity.dispose(); // Free resources
                    remove(i);
                }
            }
        }
    }

}
