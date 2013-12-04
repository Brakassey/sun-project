package com.brakassey.sunproject.utils;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.brakassey.sunproject.exceptions.ResourceException;

/** Sprite manager make usage of picture more fun! */
public class MapManager {
	private static final float TILE_SCALE = 1/16.f ;

	/** Unique instance of the singleton. */
	private static MapManager Instance = null ;

	/** Map containing the maps and their ID. */
	private HashMap<String, TiledMapRenderer> m_maps ;

	/** Map loader. */
	private TmxMapLoader m_loader = new TmxMapLoader() ;
	

	/** Sprite manager make usage of picture more fun! */
	private MapManager() {
		m_maps = new HashMap<>() ;
	}

	/** Get the singleton of the SpriteManager. */
	public static MapManager getInstance() {
		if (Instance == null)
			Instance = new MapManager() ;
		return Instance ;
	}


	/** Add a new sprite to the game. */
	public static void addMap(String dir, String file, SpriteBatch batch) throws ResourceException {
		String id = file.substring(0, file.indexOf('.')) ;

		if (!getInstance().m_maps.containsKey(id)) {
			if (!dir.endsWith("/")) dir += '/' ;

			TiledMap map = getInstance().m_loader.load(/*"maps"+*/dir+file) ;
			OrthogonalTiledMapRenderer rendered = new OrthogonalTiledMapRenderer(map, TILE_SCALE) ;
			getInstance().m_maps.put(id, rendered) ;
		}
	}

	/** Get the sprite with the given id. */
	public static TiledMapRenderer getMap(String id) {
		return getInstance().m_maps.get(id) ;
	}
}