package com.brakassey.sunproject.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.inputs.UserInput;

public class GameScreen implements Screen {

    private Game m_game;

    private UserInput m_input = new UserInput();
    private SpriteBatch m_batch = new SpriteBatch();

    private TiledMap m_map;
    private MapRenderer m_map_renderer;

    private OrthographicCamera m_camera;
    private OrthographicCamera m_gui_camera;

    public GameScreen(Game game, TiledMap map) {
        m_game = game;
        m_map = map;

        m_batch.enableBlending();
        m_batch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        m_camera = new OrthographicCamera(Gdx.graphics.getWidth() / Config.WIN_DIV, Gdx.graphics.getHeight() / Config.WIN_DIV);

        m_map_renderer = new OrthogonalTiledMapRenderer(m_map, Config.TILE_SCALE, m_batch);

        m_gui_camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        m_gui_camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        m_gui_camera.update();
    }

    @Override
    public void render(float delta) {
        // TODO Update game
        m_input.update();


        // TODO Render game
        Gdx.gl.glClearColor(0, 0, 0, 1) ;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT) ;

        // Camera
        m_camera.position.set(21, 21, 0);
        m_camera.update();
        m_map_renderer.setView(m_camera);


        // Map
        m_map_renderer.render();

        m_batch.begin();


        // Objects
        //...

        m_batch.end();

        m_batch.setProjectionMatrix(m_gui_camera.combined);
        m_batch.begin();


        // GUI

        // Input buttons
        m_input.renderLayout(m_batch);

        m_batch.end();
    }

    @Override
    public void resize(int width, int height) {
        m_camera.setToOrtho(false, Gdx.graphics.getWidth() / Config.WIN_DIV, Gdx.graphics.getHeight() / Config.WIN_DIV);

        m_gui_camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        m_gui_camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        m_gui_camera.update();
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
