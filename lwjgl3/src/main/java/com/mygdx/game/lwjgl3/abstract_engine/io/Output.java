package com.mygdx.game.lwjgl3.abstract_engine.io;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class Output{
	
    private static HashMap<String, Music> musicTracks = new HashMap<>();
    private float volume = 0.5f;
	private String key;	
	private String filePath;
	
    public Output(String key, String filePath, float volume) {
        this.key = key;
        musicTracks.put(key, Gdx.audio.newMusic(Gdx.files.internal(filePath)));
        System.out.println("Music loaded: " + key);
        setVolume(volume);

    }

    // Play music track
    public void playMusic() {
        Music music = musicTracks.get(key);
        if (music != null) {
            music.setLooping(true);
            music.setVolume(getVolume());
            music.play();
        } else {
            System.out.println("Music track not found: " + key);
        }
    }

    // Stop  music track
    public static void stopMusic(String key) {
        Music music = musicTracks.get(key);
        if (music != null) {
            music.stop();
        }
    }
    
    

    // Pause a specific music track
//    public static void pauseMusic(String key) {
//        Music music = musicTracks.get(key);
//        if (music != null) {
//            music.pause();
//        }
//    }

    // Resume a specific music track
//    public static void resumeMusic(String key) {
//        Music music = musicTracks.get(key);
//        if (music != null) {
//            music.play();
//        }
//    }
    
    // Increase Volume capped at 0
    public void increaseVolume() {
        if (getVolume() < 1.0f) {
        	float tempVolume = getVolume();
            setVolume(tempVolume +=0.1f);
            System.out.println("Volume Increased: " + getVolume());
        }
    }
    
    // Decrease Volume capped at 0
    public void decreaseVolume() {
        if (getVolume() > 0.0f) {
            float tempVolume = getVolume();
            setVolume(tempVolume -= 0.1f);
            System.out.println("Volume Decreased: " + getVolume());
        }
    }

    // Dispose of all loaded music
    public static void dispose() {
        for (Music music : musicTracks.values()) {
            music.dispose();
        }
        musicTracks.clear();
    }
    
    public void setVolume(float volume) {
    	this.volume = volume;
    }
    
    public float getVolume() {
    	return volume;
    }
    
    public void setAudioKey(String key) {
    	this.key = key;
    }
    
    public String getAudioKey() {
    	return key;
    }
    
    public String getFilePath() {
    	return filePath;
    }
    
    public void setFilePath(String filePath) {
    	this.filePath = filePath;
    }
}
