package com.brakassey.sunproject.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brakassey.sunproject.Config;
import com.brakassey.sunproject.actors.BattleActor;
import com.brakassey.sunproject.screens.CombatScreen;

public class BattleEngine {
	private ArrayList<BattleActor> mParty;
	private ArrayList<BattleActor> mEnemies;
	private PriorityQueue<BattleActor> mBattleActorQueue;
	private BattleActor mCurrentBattleActor;
	private CombatScreen m_combatscreen;
	private static int width = 0;
	private static int height = 0;
	private BattleActor m_hero;
	private BattleActor mogloo;

	public BattleEngine(ArrayList<BattleActor> party,
			ArrayList<BattleActor> enemies) {
		mParty = party;
		mEnemies = enemies;
		initQueue();
	}

	public BattleEngine(CombatScreen combatscreen,
			ArrayList<BattleActor> party, String area) {
		// TODO: depending on the area generate enemies accordingly to that area
		m_combatscreen = combatscreen;
		mParty = party;
		createEnemies(area);
		initQueue();
	}
	
	// Main constructor
	public BattleEngine(CombatScreen combatscreen, String area) {
		// TODO: depending on the area generate enemies accordingly to that area
		m_combatscreen = combatscreen;

		m_hero = new BattleActor(m_combatscreen, new Texture(
				Gdx.files.internal("img/battle_actors/heroes/leef.png")));
		m_hero.setName("leef");
		mogloo = new BattleActor(m_combatscreen, new Texture(
				Gdx.files.internal("img/battle_actors/heroes/mogloo.png")));
		mogloo.setName("mogloo");
		mParty = new ArrayList<BattleActor>();
		mParty.add(m_hero);
		mParty.add(mogloo);
		createEnemies(area);
		initQueue();
	}

	public BattleActor makeActor(TextureRegion[] enemiTR) {
		BattleActor actor = new BattleActor(enemiTR);
		return actor;
	}

	private void createEnemies(String area) {
		
		TextureRegion[][] tmp = TextureRegion.split(
				new Texture(Gdx.files
						.internal("img/battle_actors/enemies/Actors_Combats_"
								+ area + ".png")), Config.ACT_BATTLE_SIZE,
				Config.ACT_BATTLE_SIZE);
		
		int rand = new Random().nextInt(4) + 1;
		mEnemies = new ArrayList<BattleActor>();
		System.out.println("Enemies: " + rand);
		for (int i = 0; i < rand; i++) {
			int randActor = new Random().nextInt(rand);
			TextureRegion[] tmpEnemiTR = new TextureRegion[4];
			
			for(int j=0; j<4; j++)
				tmpEnemiTR[j] = tmp[randActor][j];
			
			mEnemies.add(makeActor(tmpEnemiTR));
		}
	}

	private void initQueue() {
		int cap = mParty.size() + mEnemies.size();
		mBattleActorQueue = new PriorityQueue<BattleActor>(cap,
				new StatsComparator());
	}

	public void nextTurn() {
		if (mBattleActorQueue.isEmpty()) {
			mBattleActorQueue.addAll(mParty);
			mBattleActorQueue.addAll(mEnemies);
		}
		BattleActor p = mBattleActorQueue.poll();
		mCurrentBattleActor = p;
	}

	public void executeAction(String action, BattleActor BattleActor) {
	}

	public boolean isFinished() {
		Iterator<BattleActor> it = mEnemies.iterator();
		while (it.hasNext()) {
			BattleActor p = it.next();
			if (!p.isAlive())
				it.remove();
		}
		if (mEnemies.isEmpty())
			return true;
		it = mParty.iterator();
		while (it.hasNext()) {
			BattleActor p = it.next();
			if (!p.isAlive())
				it.remove();
		}
		if (mParty.isEmpty())
			return true;
		return false;
	}

	public ArrayList<BattleActor> getParty() {
		return mParty;
	}

	public ArrayList<BattleActor> getEnemies() {
		return mEnemies;
	}

	public static void setScreenSize(int w, int h) {
		width = w;
		height = h;
	}
}