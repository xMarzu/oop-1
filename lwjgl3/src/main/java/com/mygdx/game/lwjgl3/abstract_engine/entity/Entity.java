package com.mygdx.game.lwjgl3.abstract_engine.entity;

import com.mygdx.game.lwjgl3.abstract_engine.collision.Collidable;

public abstract class Entity implements Collidable {
    private String name;
    private float x, y;

    public Entity() {
        this.name = "Unnamed";
        this.x = 0;
        this.y = 0;
    }

    public Entity(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void dispose() {
        
    }

    @Override
    public boolean checkCollision(Collidable other) {
        if (other instanceof Entity) {
            Entity otherEntity = (Entity) other;
            float thisLeft = getX();
            float thisRight = getX() + 32; // Assuming 32px width
            float thisTop = getY() + 32; // Assuming 32px height
            float thisBottom = getY();

            float otherLeft = otherEntity.getX();
            float otherRight = otherEntity.getX() + 32;
            float otherTop = otherEntity.getY() + 32;
            float otherBottom = otherEntity.getY();

            return (thisLeft < otherRight && thisRight > otherLeft &&
                    thisTop > otherBottom && thisBottom < otherTop);
        }
        return false;
    }
    
}
