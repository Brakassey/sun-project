package com.brakassey.sunproject;

import com.badlogic.gdx.ApplicationListener;

public class SunProjectApplication implements ApplicationListener  {

	/**
	 * Main class in the SunProject
	 */
	Animator myAnimator;
	String spriteAnime;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		spriteAnime = "C:\\Users\\Utilisateur\\Workspace\\SunProject\\img\\mogloo.png";
		myAnimator = new Animator(spriteAnime, 4, 4, 50, 50);
		myAnimator.create();		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		myAnimator.render();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
