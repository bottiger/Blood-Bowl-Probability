package com.game.bloodbowlprobability;

public class DThree extends MultiDieRoll {
	
	public DThree(int minRoll, BloodBowlDieReroll reroll) {
		super(minRoll, reroll);
	}
	
	public DThree newInstance(BloodBowlDieReroll reroll_copy) {
		return new DThree(this.requiredRoll, reroll_copy);
	}

	@Override
	public double probabilityWithoutReroll() {
		return (3-(double)this.requiredRoll+1) / (double)3;
	}
	
	@Override
	public String diceName() {
		return "D3";
	}

}
