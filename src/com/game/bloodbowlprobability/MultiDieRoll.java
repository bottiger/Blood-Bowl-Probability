package com.game.bloodbowlprobability;

import android.util.Log;

public abstract class MultiDieRoll extends AbstractDieRoll  {
	
	public MultiDieRoll(int minRoll, BloodBowlDieReroll reroll) {
		super(minRoll, reroll);
	}

	public abstract double probabilityWithoutReroll();
	public abstract String diceName();

	public String toString() {
		String text = this.requiredRoll + "+ with a " + diceName();
		if (this.reroll.canReroll())
			return text + " (reroll)";
		else
			return text;
	}

}
