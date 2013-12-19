package com.brakassey.sunproject.inputs;

import com.brakassey.sunproject.actors.Actor;

public class FollowInput extends Input {

    private Actor m_me;
    private Actor m_target;
    private Direction m_last_dir;

    public FollowInput(Actor me, Actor target)
    {
        m_me = me;
        m_target = target;
        m_last_dir = Direction.NONE;
    }

    @Override
    public void update(float delta) {

        if (m_target == null) return;

        float x = m_target.getX() - m_me.getX();
        float y = m_target.getY() - m_me.getY();

        if (Math.abs(x) + Math.abs(y) <= 1.1f)
        {
            setDirection(Direction.NONE);
            m_last_dir = Direction.NONE;
            return;
        }

        // Already moving
        if (m_last_dir != Direction.NONE)
        {
            if (!m_me.isMoving())
            {
                // Try another direction
                switch (m_last_dir)
                {
                case UP:
                case DOWN:
                    if (x > 0)
                    {
                        setDirection(Direction.RIGHT);
                        m_last_dir = Direction.RIGHT;
                    }
                    else
                    {
                        setDirection(Direction.LEFT);
                        m_last_dir = Direction.LEFT;
                    }
                    break;

                case LEFT:
                case RIGHT:
                    if (y > 0)
                    {
                        setDirection(Direction.UP);
                        m_last_dir = Direction.UP;
                    }

                    else
                    {
                        setDirection(Direction.DOWN);
                        m_last_dir = Direction.DOWN;
                    }
                    break;

                default:
                    break;
                }
            }
            else
            {
                switch (m_last_dir)
                {
                case UP:
                case DOWN:
                    if (Math.abs(y) < 0.1f)
                        m_last_dir = Direction.NONE;
                    break;

                case LEFT:
                case RIGHT:
                    if (Math.abs(x) < 0.1f)
                        m_last_dir = Direction.NONE;
                    break;

                default:
                    break;
                }
            }
        }

        // Stopped
        if (m_last_dir == Direction.NONE)
        {
            if (Math.abs(x) > Math.abs(y))
            {
                // Horizontal
                if (x > 0)
                {
                    setDirection(Direction.RIGHT);
                    m_last_dir = Direction.RIGHT;
                }
                else
                {
                    setDirection(Direction.LEFT);
                    m_last_dir = Direction.LEFT;
                }
            }
            else
            {
                // Vertical
                if (y > 0)
                {
                    setDirection(Direction.UP);
                    m_last_dir = Direction.UP;
                }

                else
                {
                    setDirection(Direction.DOWN);
                    m_last_dir = Direction.DOWN;
                }
            }
        }
    }

}
