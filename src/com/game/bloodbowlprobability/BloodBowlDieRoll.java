package com.game.bloodbowlprobability;

import android.util.Log;

public class BloodBowlDieRoll extends AbstractDieRoll  {

	private final double dieSides = 6.0;
	private int requiredRoll;
	private BloodBowlDieReroll reroll;
	private double singleDieSucces;
	
	public BloodBowlDieRoll(int minRoll, BloodBowlDieReroll reroll) {

		this.requiredRoll = minRoll;
		this.reroll = reroll;
		this.singleDieSucces = ((this.dieSides + 1) - this.requiredRoll);
	}

	public BloodBowlDieRoll copy() {
		
		BloodBowlDieReroll reroll_copy;

		if (this.reroll.canReroll())
		{
			reroll_copy = new BloodBowlDieReroll(1);
		}
		else
		{
			reroll_copy = new BloodBowlDieReroll(0);
		}
		BloodBowlDieRoll copy = new BloodBowlDieRoll(this.requiredRoll, reroll_copy);

		return copy;
	}

	// double singleDieSucces = ((this.dieSides + 1) - this.requiredRoll);

	public double probability() {
		if (this.reroll.canReroll()) {
			return this.probabilityWithReroll();
		} else
			return this.probabilityWithoutReroll();
	}

	public double probabilityWithReroll() {
		double totalOutcome = Math.pow(this.dieSides, 2);
		int succesOneDie = (int) (this.singleDieSucces * 6);
		int succesTwoDieOverlap = (int) Math.pow(this.singleDieSucces, 2);
		int totalSuccesSpan = succesOneDie * 2 - succesTwoDieOverlap;
		return totalSuccesSpan / totalOutcome;
	}

	public double probabilityWithReroll(boolean removeReroll) {
		this.reroll.decrementReroll();
		return this.probabilityWithReroll();
	}

	public double probabilityWithoutReroll() {
		return this.singleDieSucces / this.dieSides;
	}

	public String toString() {
		if (this.reroll.canReroll())
			return this.requiredRoll + "+ (reroll)";
		else
			return this.requiredRoll + "+";
	}

	public boolean isReroll() {
		return reroll.canReroll();
	}

	public BloodBowlDieReroll getReroll() {
		return this.reroll;
	}

	public void setReroll(BloodBowlDieReroll reroll) {
		this.reroll = reroll;
	}

	public int getRequiredRoll() {
		return this.requiredRoll;
	}

}
