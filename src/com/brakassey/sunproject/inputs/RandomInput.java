package com.brakassey.sunproject.inputs;

import java.util.Random;

public class RandomInput extends Input {

    private final float TIME_CONST = 1.5f;
    private final float TIME_VAR = 1.5f;

    private Random m_rand;
    private float m_time_left;

    public RandomInput()
    {
        m_rand = new Random();
        m_time_left = TIME_CONST + TIME_VAR * m_rand.nextFloat();
    }

    @Override
    public void update(float delta) {

        if ((m_time_left -= delta) <= 0)
        {
            if (getDirection() == Direction.NONE)
            {
                switch(m_rand.nextInt() % 4)
                {
                case 0:
                    setDirection(Direction.DOWN);
                    break;
                case 1:
                    setDirection(Direction.LEFT);
                    break;
                case 2:
                    setDirection(Direction.UP);
                    break;
                case 3:
                    setDirection(Direction.RIGHT);
                    break;
                }
            }
            else
                setDirection(Direction.NONE);

            m_time_left = TIME_CONST + TIME_VAR * m_rand.nextFloat();
        }
    }

}
