package com.brakassey.sunproject;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

/**
 * This class allows to make moving animation with a sprites sheet.
 */

public class Animator implements ApplicationListener {

	int        FRAME_COLS;        
    int        FRAME_ROWS;           
    Animation                       walkAnimation;         
    Texture                         walkSheet;            
    SpriteBatch                     spriteBatch;           
    TextureRegion                   currentFrame;          
    String spriteAnime;
    float stateTime;                                      
    float spriteSpeed = 20.0f;
    float spriteX;
    float spriteY;
    private TextureRegion walkFrame;
	private TextureRegion[] walkUpFrames;
	private TextureRegion[] walkDownFrames;
	private TextureRegion[] walkRightFrames;
	private TextureRegion[] walkLeftFrames;
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Animation walkRightAnimation;
	private Animation walkLeftAnimation;
	private TiledMapRenderer currentMap;
	private OrthographicCamera currentCam;
	
	/**
	 * The constructor:
	 * @param spriteAnime
	 * 	This is the sprites sheet path
	 * @param FRAME_COLS
	 * 	The number of columns in the sprites sheet
	 * @param FRAME_ROWS
	 * 	The number of rows in the sprites sheet
	 * @param originX
	 * 	The animation origin at X coordinate
	 * @param originY
	 * 	The animation origin at Y coordinate
	 */
	public Animator(String spriteAnime, int FRAME_COLS, int FRAME_ROWS, float originX, float originY){
		this.spriteAnime = spriteAnime;
		this.FRAME_COLS = FRAME_COLS;
		this.FRAME_ROWS = FRAME_ROWS;
		spriteX = originX;
		spriteY = originY;
	}

    
    @Override
    public void create() {
		
		
		walkSheet = new Texture(Gdx.files.internal(spriteAnime));    
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
								FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);                               
		
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
    }
    
    public void setMap(TiledMapRenderer tmr, OrthographicCamera m_camera){
    	currentMap = tmr;
    	currentCam = m_camera;
    }

    @Override
    public void render() {
	
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                           
	    stateTime += Gdx.graphics.getDeltaTime(); 
	
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)){
		      spriteX -= Gdx.graphics.getDeltaTime() * spriteSpeed;
	          currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
	          walkFrame = walkLeftFrames[0];
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)){
		      spriteX += Gdx.graphics.getDeltaTime() * spriteSpeed;
	          currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
	          walkFrame = walkRightFrames[0];
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_UP)){ 
		      spriteY += Gdx.graphics.getDeltaTime() * spriteSpeed;
	          currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
	          walkFrame = walkUpFrames[0];
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)){
		      spriteY -= Gdx.graphics.getDeltaTime() * spriteSpeed;
	          currentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
	          walkFrame = walkDownFrames[0];
		}
		else{
			currentFrame = walkFrame;
		}
		// Trouver la formule pour que le personnage et la camera soient à la même vitesse !!! (possibilité de jouer avec spriteSpeed).
		currentCam.position.set(spriteX, spriteY, 0);
		currentCam.update();
	    currentMap.render();
	    spriteBatch.begin();
	    spriteBatch.draw(currentFrame, spriteX, spriteY);
	    spriteBatch.end();
    }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}