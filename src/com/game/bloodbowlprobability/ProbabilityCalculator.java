package com.game.bloodbowlprobability;

import java.util.LinkedList;
import java.util.List;

public class ProbabilityCalculator
{

	public static double main (BloodBowlDieRoll[] rollSequence, int team_rerolls)
	{
		
		return calculateProbability(team_rerolls, rollSequence);

	}

	public static double calculateProbability (int rerolls, BloodBowlDieRoll[] seq)
	{
		double probability = 1.0;
		int length = seq.length;
		int action_rerolls = 0;
		
		for ( BloodBowlDieRoll d : seq)
		{
			if (d.getReroll().canReroll())
			{
				action_rerolls++;
			}
		}

		if (rerolls + action_rerolls == 0)
		{
			for (int i = 0; i < length; i++)
				//probability *= probSimple(seq[i]);
				probability *= seq[i].probability();

			return probability;
		}

		if (length == 1)
		{
			if (seq[0].isReroll())
				return 1.0 - Math.pow(1.0 - seq[0].probability(), 1);
			else
				return 1.0 - Math.pow(1.0 - seq[0].probability(), rerolls + 1);
		}
			
		BloodBowlDieRoll[] shortSeq = new BloodBowlDieRoll[length - 1];
		for (int i = 1; i < length; i++)
			shortSeq[i - 1] = seq[i];

		if (seq[0].isReroll())
			// if the roll includes an aditional reroll the team reroll
			// should never be used.
			//return seq[0].probability() * calculateProbability(rerolls, shortSeq);
			return forkReroll(seq[0], seq, shortSeq, rerolls);
		else
			return seq[0].probability() * calculateProbability(rerolls, shortSeq) + (1 - seq[0].probability()) * calculateProbability(rerolls - 1, seq);
	}
	
	private static double forkReroll(BloodBowlDieRoll currentRoll, BloodBowlDieRoll[] allRolls, BloodBowlDieRoll[] futureRolls, int team_rerolls)
	{
		BloodBowlDieRoll[] allRolls2 = allRolls.clone();
		allRolls2[0].probabilityWithReroll(true);
		
		//return   currentRoll.probabilityWithoutReroll() * calculateProbability(team_rerolls, futureRolls)
		//	   + currentRoll.probabilityWithReroll() * calculateProbability(team_rerolls, futureRolls2);
		double no_reroll = currentRoll.probabilityWithoutReroll();
		double no_reroll_rest = calculateProbability(team_rerolls, futureRolls);
		
		double with_reroll = currentRoll.probabilityWithReroll();
		double with_reroll_rest = calculateProbability(team_rerolls, allRolls2);
		
		return no_reroll * no_reroll_rest + (1 - no_reroll) * with_reroll_rest;
		
	
	}
}