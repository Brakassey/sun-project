package com.brakassey.sunproject.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;

public class Input {

    /**
     * Directions.
     */
    public enum Direction
    {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Buttons.
     */
    public enum Button
    {
        A,
        B,
        START,
        SELECT
    }

    private Direction m_direction       = Direction.NONE;
    private boolean m_buttons[]         = new boolean[4];

    public Input()
    {
        m_buttons[0] = false;
        m_buttons[1] = false;
        m_buttons[2] = false;
        m_buttons[3] = false;
    }

    public Direction getDirection()
    {
        return m_direction;
    }

    private void setDirection(Direction direction)
    {
        m_direction = direction;
    }

    public boolean isDown(Button button)
    {
        switch (button) {
        case A:
            return m_buttons[0];

        case B:
            return m_buttons[1];

        case START:
            return m_buttons[2];

        case SELECT:
            return m_buttons[3];
        }
        return false;
    }

    public boolean isUp(Button button)
    {
        return !isDown(button);
    }

    private void setState(Button button, boolean pressed)
    {
        switch (button) {
        case A:
            m_buttons[0] = pressed;
            break;

        case B:
            m_buttons[1] = pressed;
            break;

        case START:
            m_buttons[2] = pressed;
            break;

        case SELECT:
            m_buttons[3] = pressed;
            break;
        }
    }

    public void update() {
        if (Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen))
        {
            // TODO TOUCHSCREEN CONTROLS

        }
        else
        {
            // KEYBOARD CONTROLS
            setState(Button.A,      Gdx.input.isKeyPressed(Keys.C));
            setState(Button.B,      Gdx.input.isKeyPressed(Keys.V));
            setState(Button.START,  Gdx.input.isKeyPressed(Keys.D));
            setState(Button.SELECT, Gdx.input.isKeyPressed(Keys.F));

            if (Gdx.input.isKeyPressed(Keys.DPAD_UP))
                setDirection(Direction.UP);

            else if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
                setDirection(Direction.DOWN);

            else if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
                setDirection(Direction.RIGHT);

            else if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
                setDirection(Direction.LEFT);

            else
                setDirection(Direction.NONE);
        }
    }

}
