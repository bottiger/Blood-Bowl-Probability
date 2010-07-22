package com.game.bloodbowlprobability;

import android.util.Log;

public abstract class AbstractDieRoll implements DieRoll  {

	abstract public DieRoll copy();

	abstract public double probability();

	abstract public double probabilityWithReroll();

	abstract public double probabilityWithReroll(boolean removeReroll);

	abstract public double probabilityWithoutReroll();

	abstract public String toString();

	abstract public boolean isReroll();

	abstract public BloodBowlDieReroll getReroll();

	abstract public void setReroll(BloodBowlDieReroll reroll);

	abstract public int getRequiredRoll();

}
