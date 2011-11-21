package com.game.bloodbowlprobability;

import android.util.Log;

public abstract class AbstractDieRoll implements DieRoll  {
	
	protected BloodBowlDieReroll reroll;
	protected int requiredRoll;
	protected double singleDieSucces;

	public AbstractDieRoll(int minRoll) {
		super();
		this.requiredRoll = minRoll;
	}
	
	public AbstractDieRoll(int minRoll, BloodBowlDieReroll reroll) {
		this(minRoll);
		this.reroll = reroll;
	}

	//abstract public DieRoll copy();
	public DieRoll copy() {
		
		BloodBowlDieReroll reroll_copy = null;;

		if (this.reroll != null && this.reroll.canReroll())
		{
			reroll_copy = new BloodBowlDieReroll(1);
		}
		else
		{
			reroll_copy = new BloodBowlDieReroll(0);
		}
		//MultiDieRoll copy = new MultiDieRoll(this.requiredRoll, reroll_copy);
		DieRoll copy = newInstance(reroll_copy);

		return copy;
	}
	
	abstract public DieRoll newInstance(BloodBowlDieReroll reroll_copy); 
	
	//abstract public double probability();
	public double probability() {
		if (this.reroll.canReroll()) {
			return this.probabilityWithReroll();
		} else
			return this.probabilityWithoutReroll();
	}

	public double probabilityWithReroll() {
		return 2*probabilityWithoutReroll() - Math.pow(probabilityWithoutReroll(),2);
	}

	public double probabilityWithReroll(boolean removeReroll) {
		this.reroll.decrementReroll();
		return this.probabilityWithReroll();
	}

	abstract public double probabilityWithoutReroll();

	abstract public String toString();

	//abstract public boolean isReroll();

	//abstract public BloodBowlDieReroll getReroll();

	//abstract public void setReroll(BloodBowlDieReroll reroll);

	//abstract public int getRequiredRoll();
	
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
