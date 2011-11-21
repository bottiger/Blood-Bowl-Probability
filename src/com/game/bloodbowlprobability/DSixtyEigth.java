package com.game.bloodbowlprobability;

public class DSixtyEigth extends MultiDieRoll {
	
	public DSixtyEigth(int minRoll, BloodBowlDieReroll reroll) {
		super(minRoll, reroll);
	}
	
	public DSixtyEigth newInstance(BloodBowlDieReroll reroll_copy) {
		return new DSixtyEigth(this.requiredRoll, reroll_copy);
	}

	@Override
	public double probabilityWithoutReroll() {
		return (68-(double)this.requiredRoll+1) / (double)68;
	}
	
	@Override
	public String diceName() {
		return "D68";
	}

}
