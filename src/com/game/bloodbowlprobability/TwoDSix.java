package com.game.bloodbowlprobability;

public class TwoDSix extends MultiDieRoll {
	
	public TwoDSix(int minRoll, BloodBowlDieReroll reroll) {
		super(minRoll, reroll);
	}
	
	public TwoDSix newInstance(BloodBowlDieReroll reroll_copy) {
		return new TwoDSix(this.requiredRoll, reroll_copy);
	}

	/*
	(1,1) 	(1,2) 	(1,3) 	(1,4) 	(1,5) 	(1,6)
	(2,1) 	(2,2) 	(2,3) 	(2,4) 	(2,5) 	(2,6)
	(3,1) 	(3,2) 	(3,3) 	(3,4) 	(3,5) 	(3,6)
	(4,1) 	(4,2) 	(4,3) 	(4,4) 	(4,5) 	(4,6)
	(5,1) 	(5,2) 	(5,3) 	(5,4) 	(5,5) 	(5,6)
	(6,1) 	(6,2) 	(6,3) 	(6,4) 	(6,5) 	(6,6) 
	*/
	@Override
	public double probabilityWithoutReroll() {
		int maximumRoll = 12;
		int possibleCombination = 36;
		double succesfulOutcomes = 0;
		
		int triangleLenght;
		if (this.requiredRoll > 7) {
			triangleLenght = ((maximumRoll+1)-this.requiredRoll);
			succesfulOutcomes = triangleArea(triangleLenght);
		} else {
			triangleLenght = this.requiredRoll-1;
			succesfulOutcomes = possibleCombination-triangleArea(triangleLenght);
		}
		double res = succesfulOutcomes/possibleCombination;
		return res;
	}
	
	private int triangleArea(int length) {
		int area = 0;
		for (int i=length; i > 0; i--)
			area += i;
		return area;
	}

	@Override
	public String diceName() {
		return "2D6";
	}

}
