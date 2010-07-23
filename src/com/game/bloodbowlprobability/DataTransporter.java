package com.game.bloodbowlprobability;

import java.util.LinkedList;

import android.app.Dialog;

public class DataTransporter {
	
	private LinkedList<DieRoll> list;
	private LinkedList<DieRoll> teamList;
	private Dialog dialog;
	private BloodBowlDieReroll[] rerollList;
	private BloodBowlDieReroll[] rerollListTeam;
	

	public BloodBowlDieReroll[] getRerollList() {
		return rerollList;
	}


	public void setRerollList(BloodBowlDieReroll[] rerollList) {
		this.rerollList = rerollList;
	}


	public BloodBowlDieReroll[] getRerollListTeam() {
		return rerollListTeam;
	}


	public void setRerollListTeam(BloodBowlDieReroll[] rerollListTeam) {
		this.rerollListTeam = rerollListTeam;
	}


	public Dialog getDialog() {
		return dialog;
	}


	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}


	public LinkedList<DieRoll> getList() {
		return list;
	}


	public void setList(LinkedList<DieRoll> list) {
		this.list = list;
	}


	public LinkedList<DieRoll> getTeamList() {
		return teamList;
	}


	public void setTeamList(LinkedList<DieRoll> teamList) {
		this.teamList = teamList;
	}
}
