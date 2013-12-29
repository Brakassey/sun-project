package com.brakassey.sunproject.screens;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.actors.Actor;
import com.brakassey.sunproject.actors.AnimatedActor;
import com.brakassey.sunproject.inputs.FollowInput;
import com.brakassey.sunproject.inputs.RandomInput;
import com.brakassey.sunproject.inputs.UserInput;


public class GameScreen implements Screen {

    private Game m_game;

    private UserInput m_input = new UserInput();
    private SpriteBatch m_batch = new SpriteBatch();

    private TiledMap m_map;
    private MapRenderer m_map_renderer;

    private OrthographicCamera m_camera;
    private OrthographicCamera m_gui_camera;

    private List<Actor> m_actors;
    AnimatedActor m_hero;

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

        m_hero = new AnimatedActor(this, new Texture(Gdx.files.internal("img/charsets/leef.png")));

        m_hero.setInput(m_input);
        m_hero.setSpeed(3.2f);
        m_hero.setOnTile(18, 18);

        Actor mogloo = new AnimatedActor(this, new Texture("img/charsets/mogloo.png"));
        mogloo.setInput(new FollowInput(mogloo, m_hero));
        mogloo.setSpeed(3.2f);
        mogloo.setOnTile(10, 10);

        Actor mogloo2 = new AnimatedActor(this, new Texture("img/charsets/mogloo.png"));
        mogloo2.setInput(new FollowInput(mogloo2, mogloo));
        mogloo2.setSpeed(3.2f);
        mogloo2.setOnTile(9, 9);

        Actor mogloo3 = new AnimatedActor(this, new Texture("img/charsets/mogloo.png"));
        mogloo3.setInput(new RandomInput());
        mogloo3.setSpeed(1f);
        mogloo3.setOnTile(8, 8);

        m_actors = new ArrayList<>();
        m_actors.add(m_hero);
        m_actors.add(mogloo);
        m_actors.add(mogloo2);
        m_actors.add(mogloo3);
    }

    @Override
    public void render(float delta) {

        // Update game
        for (Actor a : m_actors)
            a.update(delta);


        // Render game
        Gdx.gl.glClearColor(0, 0, 0, 1) ;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT) ;

        // Camera
        // NO IDEA HOW BUT DOES REMOVE TEXTURE GLITCHING
        m_camera.position.set(
                ((int) (m_hero.getX() * Config.WIN_DIV)) / (float) (Config.WIN_DIV) + 0.001f,
                ((int) (m_hero.getY() * Config.WIN_DIV)) / (float) (Config.WIN_DIV) + 0.001f,
                0);
        m_camera.update();
        m_map_renderer.setView(m_camera);


        // Map
        m_map_renderer.render();

        m_batch.begin();


        // Objects
        // Big sort of death to have a good Z positioning
        Collections.sort(m_actors, new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                float z = o2.getY() - o1.getY();
                if (z == 0) return 0;
                if (z < 0) return -1;
                return 1; }
        });
        // Actually draw objects
        for (Actor a : m_actors){
            a.draw(m_batch);
        }
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

    public boolean isTileSolid(int x, int y) {
        TiledMapTileLayer lay = ((TiledMapTileLayer) m_map.getLayers().get(0));

        if (x < 0 || y < 0) return true;
        if (x >= lay.getWidth()) return true;
        if (y >= lay.getHeight()) return true;

        return lay.getCell(x, y).getTile().getId() > 32;
    }

}
