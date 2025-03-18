package com.mygdx.game.lwjgl3.abstract_engine.movement;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.lwjgl3.abstract_engine.entity.MovableEntity;

public class MovementManager {   
	private int scene;
    Map<String, Integer> keyMap;

    public MovementManager(int scene) {
        this.scene = scene;
        keyMap = new HashMap<String, Integer>();
        keyMap.put("up", Keys.UP);
        keyMap.put("down", Keys.DOWN);
        keyMap.put("left", Keys.LEFT);
        keyMap.put("right", Keys.RIGHT);
    }

    public void processMovement(int keyCode, MovableEntity ent) {
        if (this.scene == 1) {
            if (keyCode == keyMap.get("up")) {
                ent.forward();
            } else if (keyCode == keyMap.get("down")) {
                ent.backward();
            } else if (keyCode == keyMap.get("left")) {
                ent.left();
            } else if (keyCode == keyMap.get("right")) {
                ent.right();
            } else {
            }
        }
    }

    public void aiMovement(int currentDir, MovableEntity ent) {
        switch (currentDir) {
            case 0:
                ent.forward();
                break;
            case 1:
                ent.backward();
                break;
            case 2:
                ent.left();
                break;
            case 3:
                ent.right();
                break;
            default:
                break;
        }
    }

    public void rebindAction(int keyCode, String action) {
        try {
            keyMap.put(action, keyCode);
        } catch (Throwable e) {
            System.out.println("Failed to bind " + Keys.toString(keyCode) + " to " + action);
        }
    }

}
