package com.brakassey.sunproject;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.brakassey.sunproject.utils.MapManager;
import com.brakassey.sunproject.utils.Screen;
import com.brakassey.sunproject.utils.SpriteManager;

public class SunProjectApplication implements ApplicationListener {
	/** Screen informations. */
	protected Screen m_screen ;

	/** Batch are drawn the sprites. */
	protected SpriteBatch m_batch ;

	/** Camera used to detect the location to render on the map. */
	protected OrthographicCamera m_camera ;
	Animator myAnimator;
	String spriteAnime;

	@Override
	public void create() {
		
		spriteAnime = "C:\\Users\\Utilisateur\\Workspace\\SunProject\\img\\mogloo.png";
		myAnimator = new Animator(spriteAnime, 4, 4, 50, 50);
		myAnimator.create();	
		
		m_screen = new Screen(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) ;
		m_batch = new SpriteBatch() ;

		// Camera!
		m_camera = new OrthographicCamera() ;
		m_camera.setToOrtho(false, 30, 20) ; // The camera will show an area of 30x20 tiles of the map
		m_camera.update() ;

		try {
			SpriteManager.addSprite("C:\\Users\\Utilisateur\\Workspace\\SunProject\\img\\", "boss.png") ;
			SpriteManager.addSprite("C:\\Users\\Utilisateur\\Workspace\\SunProject\\img\\", "mogloo.png") ;

			MapManager.addMap("C:\\Users\\Utilisateur\\Workspace\\SunProject\\maps\\", "test.tmx", m_batch) ;
		}
		catch(Exception e) {
			e.printStackTrace() ;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) ;
		Gdx.gl.glClearColor(1, 1, 1, 1) ;
		// Show the map
		TiledMapRenderer mapRender = MapManager.getMap("test") ;
		mapRender.setView(m_camera) ;
		myAnimator.render();
		mapRender.render() ;

		m_batch.begin() ;
		/*Sprite sprite = SpriteManager.getSprite("mogloo") ;
		sprite.setPosition(50.f, 50.f) ;
		sprite.draw(m_batch) ;*/
		m_batch.end() ;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		m_batch.dispose() ;
	}

}
