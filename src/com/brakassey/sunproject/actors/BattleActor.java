package com.brakassey.sunproject.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.screens.CombatScreen;
import com.brakassey.sunproject.utils.Stats;

public class BattleActor extends Actor {
	public static final int stateIdle = 1;
	public static final int stateAttacking = 2;
	public static final int stateCasting = 3;
    private Texture m_texture;
    private Sprite m_sprite;
	private int state;
	private Stats stats;
	private CombatScreen m_combatscreen;
	private TextureRegion[] actorTR = new TextureRegion[4];;

	// Constructor for heroes
	public BattleActor(CombatScreen combatscreen, Texture tex) {
		m_combatscreen = combatscreen;
		m_texture = tex;
		TextureRegion[][] heroTR = TextureRegion.split(tex, Config.ACT_BATTLE_SIZE, Config.ACT_BATTLE_SIZE);
		
		for(int i=0; i<4; i++)
			actorTR[i] = heroTR[0][i];
		
		m_sprite = new Sprite(actorTR[0]);
	}
	// Constructor for enemies
	public BattleActor(TextureRegion[] enemiTR) {
		// TODO Auto-generated constructor stub
		actorTR = enemiTR;
		m_sprite = new Sprite(actorTR[0]);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
        m_sprite.draw(batch);

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		updateAnimations();
	}

	private void updateAnimations() {

	}

	public int getStat(String name) {
		return stats.getStat(name);
	}

	public int turnCost() {
		return stats.getMaxStat("speed") - stats.getStat("speed");
	}

	public boolean isAlive() {
		return stats.getStat("HP") > 0;
	}

	public void executeAction(String action, BattleActor target) {
	}
}