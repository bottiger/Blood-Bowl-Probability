package com.game.bloodbowlprobability;

public class BloodBowlDieReroll {
	
	private int reroll;

	public BloodBowlDieReroll(int reroll) {
		this.reroll = reroll;
	}

	public int getReroll() {
		return reroll;
	}
	
	public boolean canReroll() {
		return reroll > 0;
	}
	
	public void decrementReroll() {
		this.reroll -= 1;
	}

}
