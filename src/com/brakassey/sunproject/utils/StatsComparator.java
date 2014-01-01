package com.brakassey.sunproject.utils;

import java.util.Comparator;

import com.brakassey.sunproject.actors.BattleActor;

class StatsComparator implements Comparator<BattleActor> {
	@Override
	public int compare(BattleActor p1, BattleActor p2) {
		// TODO Auto-generated method stub
		int turnCost1 = p1.turnCost();
		int turnCost2 = p2.turnCost();
		if (turnCost1 > turnCost2)
			return 1;
		else if (turnCost1 == turnCost2)
			return 0;
		return -1;
	}
}