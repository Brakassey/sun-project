package com.brakassey.sunproject.utils;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.brakassey.sunproject.exceptions.ResourceException;

/** Sprite manager make usage of picture more fun! */
public class SpriteManager {
	/** Unique instance of the singleton. */
	private static SpriteManager Instance = null ;

	/** Map containing the sprites and their ID. */
	HashMap<String, Sprite> m_sprites ;

	/** Sprite manager make usage of picture more fun! */
	private SpriteManager() {
		m_sprites = new HashMap<>() ;
	}

	/** Get the singleton of the SpriteManager. */
	public static SpriteManager getInstance() {
		if (Instance == null)
			Instance = new SpriteManager() ;
		return Instance ;
	}


	/** Add a new sprite to the game. */
	public static void addSprite(String dir, String file) throws ResourceException {
		String id = file.substring(0, file.indexOf('.')) ;

		if (!getInstance().m_sprites.containsKey(id)) {
			if (!dir.startsWith("/")) dir = '/' + dir ;
			if (!dir.endsWith("/")) dir += '/' ;

			//throw new ResourceException(path + " is already used in " + getInstance().getClass().getCanonicalName()) ;
			Sprite sprite = new Sprite(new Texture(Gdx.files.internal(/*"sprites"+*/dir+file))) ;
			getInstance().m_sprites.put(id, sprite) ;
		}
	}

	/** Get the sprite with the given id. */
	public static Sprite getSprite(String id) {
		return getInstance().m_sprites.get(id) ;
	}
}
