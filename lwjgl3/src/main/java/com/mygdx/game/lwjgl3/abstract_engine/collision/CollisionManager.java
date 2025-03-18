package com.mygdx.game.lwjgl3.abstract_engine.collision;

import java.util.List;
import java.util.function.BiConsumer;

import com.mygdx.game.lwjgl3.abstract_engine.entity.Entity;

public class CollisionManager {
    private BiConsumer<Entity, Entity> collisionHandler;

    // Allow setting a custom collision handler
    public void setCollisionHandler(BiConsumer<Entity, Entity> handler) {
        this.collisionHandler = handler;
    }

    public void detectCollisions(List<Collidable> collidables) {
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable c1 = collidables.get(i);
                Collidable c2 = collidables.get(j);

                if (c1.checkCollision(c2)) {
                    String name1 = (c1 instanceof Entity) ? ((Entity) c1).getName() : "Unknown Entity";
                    String name2 = (c2 instanceof Entity) ? ((Entity) c2).getName() : "Unknown Entity";

                    System.out.println("Collision detected between: " + name1 + " and " + name2);

                    if (collisionHandler != null && c1 instanceof Entity && c2 instanceof Entity) {
                        collisionHandler.accept((Entity) c1, (Entity) c2);
                    }
                }
            }
        }
    }
}
