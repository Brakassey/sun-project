package com.brakassey.sunproject.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brakassey.sunproject.screens.GameScreen;

public class AnimatedActor extends Actor {

	int        FRAME_COLS;
    int        FRAME_ROWS;
    Animation                       walkAnimation;
    Texture                         tex;
    SpriteBatch                     spriteBatch;
    TextureRegion                   currentFrame;
    String spriteAnime;
    float stateTime;
    private TextureRegion walkFrame;
	private TextureRegion[] walkUpFrames;
	private TextureRegion[] walkDownFrames;
	private TextureRegion[] walkRightFrames;
	private TextureRegion[] walkLeftFrames;
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Animation walkRightAnimation;
	private Animation walkLeftAnimation;


	public AnimatedActor(GameScreen gamescreen, Texture tex, int FRAME_COLS, int FRAME_ROWS) {
		super(gamescreen, tex);
		this.FRAME_COLS = FRAME_COLS;
		this.FRAME_ROWS = FRAME_ROWS;
		TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() /
								FRAME_COLS, tex.getHeight() / FRAME_ROWS);

		walkUpFrames = new TextureRegion[FRAME_COLS-1];
		walkDownFrames = new TextureRegion[FRAME_COLS-1];
		walkRightFrames = new TextureRegion[FRAME_COLS-1];
		walkLeftFrames = new TextureRegion[FRAME_COLS-1];

		    for (int j = 0; j < FRAME_COLS - 1; j++) {
		            walkUpFrames[j] = tmp[0][j];
		            walkDownFrames[j] = tmp[1][j];
		            walkRightFrames[j] = tmp[2][j];
		            walkLeftFrames[j] = tmp[3][j];
		    }

		walkUpAnimation = new Animation(0.025f, walkUpFrames);
		walkDownAnimation = new Animation(0.025f, walkDownFrames);
		walkRightAnimation = new Animation(0.025f, walkRightFrames);
		walkLeftAnimation = new Animation(0.025f, walkLeftFrames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
		walkFrame = walkDownAnimation.getKeyFrame(stateTime, true);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void update(float delta){
        stateTime += Gdx.graphics.getDeltaTime();
		if (getInput() == null) return;

        getInput().update(delta);
        switch (getInput().getDirection())
        {
        case DOWN:
	        currentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
	        walkFrame = walkDownFrames[0];
            move(0, - delta * m_speed);
            break;
        case LEFT:
            currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
            walkFrame = walkLeftFrames[0];
            move(- delta * m_speed, 0);
            break;
        case RIGHT:
	        currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
	        walkFrame = walkRightFrames[0];
            move(delta * m_speed, 0);
            break;
        case UP:
	        currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
	        walkFrame = walkUpFrames[0];
            move(0, delta * m_speed);
            break;
        default:
        	currentFrame = walkFrame;
            break;

        }
        getSprite().setRegion(currentFrame);
	}


}
