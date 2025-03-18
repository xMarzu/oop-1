
package com.mygdx.game.lwjgl3.abstract_engine.io;

import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	private int keyPressed = 0;
	private int prevKeyPressed = 0;
	private boolean isPressed = false;

	public Input() {
	}

	public int getKeyPressed() {
		return keyPressed;
	}
	public int getPrevKeyPressed() {
		return prevKeyPressed;
	}

	public boolean isKeyPressed() {
		return isPressed;
	}

	public boolean isKeyPressed(int keyCode) {
		return (isPressed & keyCode == keyPressed);
	}

    @Override
    public boolean keyDown(int keyCode) {
    	if(isPressed) {
    		prevKeyPressed=keyPressed;
    		keyPressed=keyCode;
    	}
    	else {
        keyPressed = keyCode;
        isPressed = true;}
        return false;

    }

    @Override
    public boolean keyUp(int keyCode) {
        
        if(keyCode==keyPressed) {
        	keyPressed=0;
        	if(prevKeyPressed!=0) {
        		keyPressed=prevKeyPressed;
        		prevKeyPressed=0;
        	}
        }
        if(keyCode==prevKeyPressed) {
        	prevKeyPressed=0;}
        isPressed=!(keyPressed==0&&prevKeyPressed==0);
        
        return false;

    }

	@Override
	public boolean keyTyped(char character) {
		return false;// for text input
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;// mouse ctrl
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;// mouse ctrl
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;// mouse ctrl
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		return false;// mouse ctrl
	}

	@Override
	public boolean touchCancelled(int x, int y, int pointer, int button) {
		return false;// mouse ctrl
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
