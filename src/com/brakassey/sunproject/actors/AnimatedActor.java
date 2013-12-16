package com.brakassey.sunproject.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.screens.GameScreen;

public class AnimatedActor extends Actor {

    private TextureRegion currentFrame;
    private float stateTime;
    private TextureRegion walkFrame;

    private TextureRegion[] walkUpFrames = new TextureRegion[4];
    private TextureRegion[] walkDownFrames = new TextureRegion[4];
    private TextureRegion[] walkRightFrames = new TextureRegion[4];
    private TextureRegion[] walkLeftFrames = new TextureRegion[4];

	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Animation walkRightAnimation;
	private Animation walkLeftAnimation;

	public AnimatedActor(GameScreen gamescreen, Texture tex) {
		super(gamescreen, tex);
		TextureRegion[][] tmp = TextureRegion.split(tex, Config.TILE_SIZE, Config.TILE_SIZE);

	    for (int j = 0; j < 4; ++j) {
	        final int[] tab = {0, 1, 0, 2};

            walkUpFrames[j] = tmp[0][tab[j]];
            walkDownFrames[j] = tmp[1][tab[j]];
            walkRightFrames[j] = tmp[2][tab[j]];
            walkLeftFrames[j] = tmp[3][tab[j]];
	    }

		walkUpAnimation = new Animation(Config.ANIM_SPEED, walkUpFrames);
		walkDownAnimation = new Animation(Config.ANIM_SPEED, walkDownFrames);
		walkRightAnimation = new Animation(Config.ANIM_SPEED, walkRightFrames);
		walkLeftAnimation = new Animation(Config.ANIM_SPEED, walkLeftFrames);
		stateTime = 0f;
		walkFrame = walkDownAnimation.getKeyFrame(stateTime, true);
	}

	@Override
	public void update(float delta){
        stateTime += delta * getSpeed();

		super.update(delta);

        if (getInput() == null)
        {
            currentFrame = walkFrame;
        }
        else
        {
            switch (getInput().getDirection())
            {
            case DOWN:
    	        currentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
    	        walkFrame = walkDownFrames[0];
                break;
            case LEFT:
                currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
                walkFrame = walkLeftFrames[0];
                break;
            case RIGHT:
    	        currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
    	        walkFrame = walkRightFrames[0];
                break;
            case UP:
    	        currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
    	        walkFrame = walkUpFrames[0];
                break;
            default:
            	currentFrame = walkFrame;
                break;
            }
        }
        getSprite().setRegion(currentFrame);
	}

}
