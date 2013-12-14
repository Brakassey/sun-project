package com.brakassey.sunproject.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Input {

    /**
     * Configuration for buttons.
     */
    final int MARGIN = 24;
    final int SIZE = 96;
    final int DSIZE = SIZE * 2;

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

    private Texture DPad_button;
    private Texture A_button;
    private Texture B_button;
    private Sprite DPad;
    private Sprite A;
    private Sprite B;

    public Input()
    {
        m_buttons[0] = false;
        m_buttons[1] = false;
        m_buttons[2] = false;
        m_buttons[3] = false;

        DPad_button = new Texture("img/ui/dpad.png");
        A_button = new Texture("img/ui/A.png");
        B_button = new Texture("img/ui/B.png");
        DPad = new Sprite(DPad_button);
        A = new Sprite(A_button);
        B = new Sprite(B_button);

        int screenw = Gdx.graphics.getWidth();
        DPad.setBounds(MARGIN, MARGIN, DSIZE, DSIZE);
        A.setBounds(screenw - MARGIN - SIZE, MARGIN * 2, SIZE, SIZE);
        B.setBounds(screenw - MARGIN * 2 - SIZE * 2, MARGIN, SIZE, SIZE);
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
        int screenw = Gdx.graphics.getWidth();
        int screenh = Gdx.graphics.getHeight();

        if (Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen))
        {
            // TODO TOUCHSCREEN CONTROLS
            setState(Button.A, false);
            setState(Button.B, false);
            setDirection(Direction.NONE);
            for (int i = 0; i < 5; ++i)
            {
                if (Gdx.input.isTouched(i))
                {
                    int x = Gdx.input.getX(i);
                    int y = screenh - Gdx.input.getY(i);

                    // DPAD
                    if (x > (MARGIN)
                     && x < (MARGIN + DSIZE)
                     && y > (MARGIN)
                     && y < (MARGIN + DSIZE))
                    {
                        x -= MARGIN + (DSIZE / 2);
                        y -= MARGIN + (DSIZE / 2);

                        // Dead zone in the center
                        if (Math.abs(x) + Math.abs(y) < DSIZE / 10)
                            continue;

                        boolean hori = Math.abs(x) > Math.abs(y);

                        if (hori && (x > 0))
                            setDirection(Direction.RIGHT);

                        else if (hori && (x <= 0))
                            setDirection(Direction.LEFT);

                        else if (y > 0)
                            setDirection(Direction.UP);

                        else // y <= 0
                            setDirection(Direction.DOWN);
                    }

                    // A
                    else if (x > (screenw - MARGIN - SIZE)
                          && x < (screenw - MARGIN)
                          && y > (MARGIN * 2)
                          && y < (MARGIN * 2 + SIZE))
                    {
                        setState(Button.A, true);
                    }

                    // B
                    else if ((x > screenw - MARGIN * 2 - SIZE * 2)
                          && (x < screenw - MARGIN * 2 - SIZE)
                          && (y > MARGIN)
                          && (y < MARGIN + SIZE))
                    {
                        setState(Button.B, true);
                    }
                }
            }
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

    public void renderLayout(SpriteBatch batch)
    {
        if (getDirection() != Direction.NONE)
            DPad.setColor(1, 1, 1, 0.3f);
        else
            DPad.setColor(1, 1, 1, 0.8f);

        if (isDown(Button.A))
            A.setColor(1, 1, 1, 0.3f);
        else
            A.setColor(1, 1, 1, 0.8f);

        if (isDown(Button.B))
            B.setColor(1, 1, 1, 0.3f);
        else
            B.setColor(1, 1, 1, 0.8f);

        DPad.draw(batch);
        A.draw(batch);
        B.draw(batch);
    }

}
