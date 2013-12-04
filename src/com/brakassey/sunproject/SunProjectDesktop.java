package com.brakassey.sunproject;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class SunProjectDesktop {

	/**
	 * Test Class
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int WIDTH = 800;
		int HEIGHT = 600;
		new LwjglApplication(new SunProjectApplication(), "SunProject", WIDTH, HEIGHT, true);
	}

}
