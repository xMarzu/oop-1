
package com.mygdx.game.lwjgl3.abstract_engine.io;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;

public class IOManager  {
    private Input inputHandler;
    //private Output outputHandler;

    public IOManager(){
    	inputHandler=new Input();
    	Gdx.input.setInputProcessor(inputHandler);
    }
    public boolean isKeyPressed(){
    	return inputHandler.isKeyPressed();
    }
    public boolean isKeyPressed(int keyCode){
    	return inputHandler.isKeyPressed(keyCode);
    }
    public int getKeyPressed() {
    	return (inputHandler.getKeyPressed());
    }
    public int getPrevKeyPressed() {
    	return (inputHandler.getPrevKeyPressed());
    }
    public void playAudio() {
    	
    }
}

