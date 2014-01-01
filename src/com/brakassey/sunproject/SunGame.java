package com.brakassey.sunproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.brakassey.sunproject.screens.MenuScreen;

public class SunGame extends Game {
	
	 public AssetManager manager;

    @Override
    public void create() {
    	 Gdx.input.setCatchBackKey(true);
    	 manager = new AssetManager();
    	// manager.setLoader(Map.class, new MapLoader(new InternalFileHandleResolver()));
        setScreen(new MenuScreen(this));
    }

}
