package com.game.bloodbowlprobability;

public interface DieRoll {
	
	public DieRoll copy();
	
	public double probability();
	
	public double probabilityWithReroll();
	
	public double probabilityWithReroll(boolean removeReroll);
	
	public double probabilityWithoutReroll();
	
	public String toString();
	
	public boolean isReroll();
	
	public BloodBowlDieReroll getReroll();
	
	public void setReroll(BloodBowlDieReroll reroll);
	
	public int getRequiredRoll();

}
