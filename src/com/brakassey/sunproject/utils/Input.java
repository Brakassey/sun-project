package com.brakassey.sunproject.utils;

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

    Direction getDirection()
    {
        return m_direction;
    }

    void setDirection(Direction direction)
    {
        m_direction = direction;
    }

    boolean isDown(Button button)
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

    boolean isUp(Button button)
    {
        return !isDown(button);
    }

    void setState(Button button, boolean pressed)
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

}
