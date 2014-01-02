package com.brakassey.sunproject.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.utils.Stats;

public class BattleActor extends Actor {
	public static final int stateIdle = 1;
	public static final int stateAttacking = 2;
	public static final int stateCasting = 3;
    private Texture m_texture;
    private Sprite m_sprite;
	private int state;
	private Stats stats;

	public BattleActor(Texture tex) {
		m_texture = tex;
		m_sprite = new Sprite(m_texture, Config.TILE_SIZE, Config.TILE_SIZE);
		m_sprite.setScale(Config.TILE_SCALE);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

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