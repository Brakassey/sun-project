package com.brakassey.sunproject.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brakassey.sunproject.utils.Input;

public class GameScreen implements Screen {

    private Game m_game;

    private Input m_input = new Input();
    SpriteBatch m_batch = new SpriteBatch();

    public GameScreen(Game game) {
        m_game = game;
        m_batch.enableBlending();
        m_batch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1) ;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) ;

        // TODO Update game
        m_input.update();

        // TODO Render game
        m_batch.begin();

        m_input.renderLayout(m_batch);

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

    }

    @Override
    public void dispose() {

    }

}
