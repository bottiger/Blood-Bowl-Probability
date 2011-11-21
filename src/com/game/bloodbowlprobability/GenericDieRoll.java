package com.game.bloodbowlprobability;

import android.util.Log;

public class GenericDieRoll extends AbstractDieRoll  {

	private final double dieSides = 6.0;
	
	public GenericDieRoll(int minRoll, BloodBowlDieReroll reroll) {
		super(minRoll, reroll);
		singleDieSucces = ((this.dieSides + 1) - this.requiredRoll);
	}

	public GenericDieRoll newInstance(BloodBowlDieReroll reroll_copy) {
		return new GenericDieRoll(this.requiredRoll, reroll_copy);
	}
	/*
	public GenericDieRoll copy() {
		
		BloodBowlDieReroll reroll_copy;

		if (this.reroll.canReroll())
		{
			reroll_copy = new BloodBowlDieReroll(1);
		}
		else
		{
			reroll_copy = new BloodBowlDieReroll(0);
		}
		GenericDieRoll copy = new GenericDieRoll(this.requiredRoll, reroll_copy);

		return copy;
	}
	*/

	/*public double probabilityWithReroll() {
		double totalOutcome = Math.pow(this.dieSides, 2);
		int succesOneDie = (int) (this.singleDieSucces * 6);
		int succesTwoDieOverlap = (int) Math.pow(this.singleDieSucces, 2);
		int totalSuccesSpan = succesOneDie * 2 - succesTwoDieOverlap;
		return totalSuccesSpan / totalOutcome;
	}*/

	/*public double probabilityWithReroll(boolean removeReroll) {
		this.reroll.decrementReroll();
		return this.probabilityWithReroll();
	}*/

	public double probabilityWithoutReroll() {
		return this.singleDieSucces / this.dieSides;
	}

	public String toString() {
		if (this.reroll.canReroll())
			return this.requiredRoll + "+ (reroll)";
		else
			return this.requiredRoll + "+";
	}
}
