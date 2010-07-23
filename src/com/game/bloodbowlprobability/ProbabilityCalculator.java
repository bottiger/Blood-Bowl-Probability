package com.game.bloodbowlprobability;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

public class ProbabilityCalculator
{

	public static double main (DieRoll[] s, int team_rerolls)
	{
		
		return calculateProbability(team_rerolls, s);

	}

	public static double calculateProbability (int rerolls, DieRoll[] s)
	{
		double probability = 1.0;
		int length = s.length;
		int action_rerolls = 0;
		
		for ( DieRoll d : s)
		{
			if (d.getReroll().canReroll())
			{
				action_rerolls++;
			}
		}

		if (rerolls + action_rerolls <= 0)
		{
			for (int i = 0; i < length; i++)
				//probability *= probSimple(seq[i]);
				probability *= s[i].probability();

			return probability;
		}

		if (length == 1)
		{
			if (s[0].isReroll())
				return 1.0 - Math.pow(1.0 - s[0].probability(), 1);
			else
				return 1.0 - Math.pow(1.0 - s[0].probability(), rerolls + 1);
		}
			
		DieRoll[] shortSeq = new DieRoll[length - 1];
		for (int i = 1; i < length; i++)
			shortSeq[i - 1] = s[i];

		if (s[0].isReroll())
			// if the roll includes an aditional reroll the team reroll
			// should never be used.
			//return seq[0].probability() * calculateProbability(rerolls, shortSeq);
			return forkReroll(s[0], shortSeq, rerolls);
		else
		{
			double first_roll = s[0].probability();
			double first_rest = calculateProbability(rerolls, shortSeq);
			double second_roll = (1 - s[0].probability());
			double second_rest = calculateProbability(rerolls - 1, s);
			
			double p_result = first_roll * first_rest 
			+ second_roll * second_rest;
			
			return p_result;
		}
	}
	
	private static double forkReroll(DieRoll currentRoll, DieRoll[] futureRolls, int team_rerolls)
	{
		
		int length = futureRolls.length;
		
		DieRoll[] futureRolls2 = new DieRoll[length];
		for (int i = 0; i < length; i++)
		{
			futureRolls2[i] = futureRolls[i].copy();
			if (currentRoll.getReroll() == futureRolls[i].getReroll())
			{
				futureRolls2[i].probabilityWithReroll(true);
			}
		}
			
		//futureRolls2[0].probabilityWithReroll(true);
		
		//return   currentRoll.probabilityWithoutReroll() * calculateProbability(team_rerolls, futureRolls)
		//	   + currentRoll.probabilityWithReroll() * calculateProbability(team_rerolls, futureRolls2);
		double no_reroll = currentRoll.probabilityWithoutReroll();
		double no_reroll_rest = calculateProbability(team_rerolls, futureRolls);
		
		double with_reroll = currentRoll.probabilityWithReroll();
		double with_reroll_rest = calculateProbability(team_rerolls, futureRolls2);
		
		double p_result = no_reroll * no_reroll_rest + (1 - no_reroll) * (no_reroll * with_reroll_rest);
		
		Log.d("no_reroll", no_reroll + ", " + no_reroll_rest + ", " + with_reroll + ", " + with_reroll_rest + ", " + p_result);
		return p_result;
	}
}