package com.brakassey.sunproject.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.brakassey.sunproject.actors.BattleActor;


public class BattleEngine {
	private ArrayList<BattleActor> mParty;
	private ArrayList<BattleActor> mEnemies;
	private PriorityQueue<BattleActor> mBattleActorQueue;
	private BattleActor mCurrentBattleActor;
	private static int width = 0;
	private static int height = 0;

	public BattleEngine(ArrayList<BattleActor> party,
			ArrayList<BattleActor> enemies) {
		mParty = party;
		mEnemies = enemies;
		initQueue();
	}

	public BattleEngine(ArrayList<BattleActor> party, String area) {
		// TODO: depending on the area generate enemies accordingly to that area
	}

	public BattleEngine(String area) {
		// TODO: depending on the area generate enemies accordingly to that area
		mParty = new ArrayList<BattleActor>();
		createEnemies(area);
		initQueue();
	}

	public BattleActor makeActor() {
		// TODO
		BattleActor actor = new BattleActor( new Texture(Gdx.files.internal("img/charsets/leef.png")));
		return actor;
	}

	private void createEnemies(String area) {
		int rand = new Random().nextInt(5) + 1;
		mEnemies = new ArrayList<BattleActor>();
		System.out.println("Enemies: " + rand);
		for (int i = 0; i < rand; i++) {
			mEnemies.add(makeActor());
		}
	}

	private void initQueue() {
		int cap = mParty.size() + mEnemies.size();
		mBattleActorQueue = new PriorityQueue<BattleActor>(cap, new StatsComparator());
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