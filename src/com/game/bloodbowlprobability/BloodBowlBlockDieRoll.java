package com.game.bloodbowlprobability;

public class BloodBowlBlockDieRoll extends AbstractDieRoll {

	private final double dieSides = 6.0;
	private final int diceNumber;
	private int requiredRoll;
	private double singleDieSucces;

	public BloodBowlBlockDieRoll(int minRoll, int diceNumber) {

		this.requiredRoll = minRoll;
		this.diceNumber = diceNumber;
		this.singleDieSucces = ((this.dieSides + 1) - this.requiredRoll);
	}

	@Override
	public BloodBowlBlockDieRoll copy() {
		BloodBowlBlockDieRoll copy = new BloodBowlBlockDieRoll(
				this.requiredRoll, this.diceNumber);

		return copy;

	}

	@Override
	public double probability() {
		if (this.diceNumber > 0) {
			double p_fail = Math
					.pow(1 - (this.singleDieSucces / this.dieSides),
							this.diceNumber);
			return 1 - p_fail;
		} else {
			// TODO test this
			int failiure = 7 - this.requiredRoll;
			double p_fail = Math.pow((failiure / this.dieSides), Math
					.abs(this.diceNumber)+1);
			return p_fail;
		}
	}

	@Override
	public double probabilityWithReroll(boolean removeReroll) {
		return this.probability();
	}

	@Override
	public double probabilityWithReroll() {
		return this.probability();
	}

	@Override
	public double probabilityWithoutReroll() {
		return this.probability();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		int shownDiceNumber = (this.diceNumber >= 1) ? this.diceNumber : this.diceNumber-1;
		return shownDiceNumber + " Block dice (" + (this.requiredRoll) + "+)";
	}

	@Override
	public int getRequiredRoll() {
		return this.requiredRoll;
	}

	@Override
	public BloodBowlDieReroll getReroll() {
		return new BloodBowlDieReroll(0);
	}

	@Override
	public boolean isReroll() {
		return false;
	}

	@Override
	public void setReroll(BloodBowlDieReroll reroll) {
	}

}
