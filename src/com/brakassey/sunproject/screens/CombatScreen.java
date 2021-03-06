package com.brakassey.sunproject.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.brakassey.sunproject.actors.BattleActor;
import com.brakassey.sunproject.utils.BattleEngine;
import com.brakassey.sunproject.utils.SoundManager;
import com.brakassey.sunproject.utils.StyledTable;
import com.brakassey.sunproject.utils.TextBox;

public class CombatScreen extends InputAdapter implements Screen {
	
	BattleEngine engine;
	InputMultiplexer input;
	TextureAtlas atlas;
	Stage stage;
	Stage uiStage;
	Game game;
    private SpriteBatch m_batch = new SpriteBatch();
	Group enemiesParty;
	Group heroesParty;
	SoundManager soundM = new SoundManager();
	Music themeSound;

	public CombatScreen(Game game) {
		this.game = game;
        m_batch.enableBlending();
        m_batch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void newBattle(String area) {
		BattleEngine.setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		engine = new BattleEngine(this, area);
		if (stage == null) {
			stage = new Stage(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), false);
			uiStage = new Stage(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), false);
		}
		stage.clear();
		
		for (BattleActor player : engine.getParty())
			heroesParty.addActor(player);	
		for (BattleActor player : engine.getEnemies())
			enemiesParty.addActor(player);

		stage.addActor(heroesParty);
		stage.addActor(enemiesParty);
		
		// TEST : Probleme pour un setPosition d'un actor en particulier.
		heroesParty.setOrigin(0, 0);
		heroesParty.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/2);
		heroesParty.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		heroesParty.findActor("leef").setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/2);
		heroesParty.findActor("mogloo").setPosition(Gdx.graphics.getWidth()/2+50, Gdx.graphics.getWidth()/2+50);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		m_batch.begin();
		stage.draw();
		uiStage.draw();
		m_batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		themeSound = soundM.newMusic(Gdx.files.internal("sounds/battle.mp3"));
		themeSound.setVolume(1.0f);
		themeSound.play();
		if (stage == null) {
			stage = new Stage(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), false);
			uiStage = new Stage(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), false);
			enemiesParty = new Group();
			heroesParty = new Group();
		}
		OrthographicCamera camera = new OrthographicCamera(
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		stage.setCamera(camera);
		atlas = new TextureAtlas(Gdx.files.internal("img/atlas/spritepack.atlas"));
		NinePatch patch = atlas.createPatch("dialog-box");
		StyledTable.TableStyle textBoxStyle = new StyledTable.TableStyle();
		textBoxStyle.background = new NinePatchDrawable(patch);
		textBoxStyle.font = new BitmapFont();
		textBoxStyle.padX = 8;
		textBoxStyle.padY = 4;
		TextBox textBox = new TextBox("Nouveau combat !!!", textBoxStyle);
		textBox.setWidth(Gdx.graphics.getWidth());
		textBox.setHeight(Gdx.graphics.getHeight() / 4);
		uiStage.addActor(textBox);
		input = new InputMultiplexer();
		input.addProcessor(this);
		input.addProcessor(stage);
		Gdx.input.setInputProcessor(input);
		newBattle("grassland");
		textBox.setText(engine.getEnemies().size()+" enemis apparaissent !!");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		uiStage.dispose();
		themeSound.dispose();
	}
}