package com.brakassey.sunproject.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.inputs.Input;
import com.brakassey.sunproject.inputs.Input.Button;
import com.brakassey.sunproject.screens.GameScreen;

public class Actor {

    private GameScreen m_gamescreen;

    private Texture m_texture;
    private Sprite m_sprite;

    private Input m_input;

    private float m_speed;

    private boolean m_first_press_A;

    public Actor(GameScreen gamescreen, Texture tex) {
        m_gamescreen = gamescreen;
        m_texture = tex;
        m_sprite = new Sprite(m_texture, Config.TILE_SIZE, Config.TILE_SIZE);
        m_sprite.setScale(Config.TILE_SCALE);

        m_speed = 4;

        m_first_press_A = false;
    }

    public Input getInput()
    {
        return m_input;
    }

    public void setInput(Input input)
    {
        m_input = input;
    }

    protected Sprite getSprite()
    {
        return m_sprite;
    }

    public void setOnTile(int x, int y)
    {
        m_sprite.setX(x - Config.MAP_SHIFT + 0.5f);
        m_sprite.setY(y - Config.MAP_SHIFT + 0.5f);
    }

    public void setPosition(float x, float y)
    {
        m_sprite.setX(x - Config.MAP_SHIFT);
        m_sprite.setY(y - Config.MAP_SHIFT);
    }

    public float getX()
    {
        return m_sprite.getX() + Config.MAP_SHIFT;
    }

    public float getY()
    {
        return m_sprite.getY() + Config.MAP_SHIFT;
    }

    public void update(float delta)
    {
        if (getInput() == null) return;

        getInput().update();

        switch (getInput().getDirection())
        {
        case DOWN:
            move(0, - delta * m_speed);
            break;
        case LEFT:
            move(- delta * m_speed, 0);
            break;
        case RIGHT:
            move(delta * m_speed, 0);
            break;
        case UP:
            move(0, delta * m_speed);
            break;
        default:
            break;

        }

        if (getInput().isDown(Button.A))
        {
            if (m_first_press_A)
            {
                // TODO Action
                System.out.println("Boum");

                m_first_press_A = false;
            }
        }
        else
            m_first_press_A = true;
    }

    private void move(float x, float y) {

        if (x > 0)
        {
            int X = (int) (getX() + Config.HULL);

            if (!m_gamescreen.isTileSolid(X, (int) (getY() + Config.HULL_SIDE))
             && !m_gamescreen.isTileSolid(X, (int) (getY() - Config.HULL_SIDE)))
                m_sprite.translate(x, y);
        }

        else if (x < 0)
        {
            int X = (int) (getX() - Config.HULL);

            if (!m_gamescreen.isTileSolid(X, (int) (getY() + Config.HULL_SIDE))
             && !m_gamescreen.isTileSolid(X, (int) (getY() - Config.HULL_SIDE)))
                m_sprite.translate(x, y);
        }

        if (y > 0)
        {
            int Y = (int) (getY() + Config.HULL);

            if (!m_gamescreen.isTileSolid((int) (getX() + Config.HULL_SIDE), Y)
             && !m_gamescreen.isTileSolid((int) (getX() - Config.HULL_SIDE), Y))
                m_sprite.translate(x, y);
        }

        else if (y < 0)
        {
            int Y = (int) (getY() - Config.HULL);

            if (!m_gamescreen.isTileSolid((int) (getX() + Config.HULL_SIDE), Y)
             && !m_gamescreen.isTileSolid((int) (getX() - Config.HULL_SIDE), Y))
                m_sprite.translate(x, y);
        }

    }

    public void draw(SpriteBatch batch)
    {
        m_sprite.draw(batch);
    }

    public void action(Actor actor)
    {

    }

}
