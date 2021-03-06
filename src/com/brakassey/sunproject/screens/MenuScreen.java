package com.brakassey.sunproject.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MenuScreen implements Screen {

    private Game m_game;

    private SpriteBatch m_batch = new SpriteBatch();

    public MenuScreen(Game game) {
        m_game = game;
        m_batch.enableBlending();
        m_batch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1) ;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT) ;

        if (Gdx.input.isKeyPressed(Keys.ENTER) || Gdx.input.isTouched())
            m_game.setScreen(new GameScreen(m_game, new TmxMapLoader().load("maps/map1.tmx")));

		m_batch.begin();
		m_batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
