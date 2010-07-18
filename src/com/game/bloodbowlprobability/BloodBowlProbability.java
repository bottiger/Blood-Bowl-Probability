package com.game.bloodbowlprobability;

import java.util.LinkedList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BloodBowlProbability extends Activity implements OnClickListener {

	private BloodBowlDieReroll required_roll = new BloodBowlDieReroll(0);
	private BloodBowlDieReroll rerollOne = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollTwo = new BloodBowlDieReroll(1);;
	private BloodBowlDieReroll rerollThree = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollFour = new BloodBowlDieReroll(1);
	
	private BloodBowlDieReroll rerollOneTeam = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollTwoTeam = new BloodBowlDieReroll(1);;
	private BloodBowlDieReroll rerollThreeTeam = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollFourTeam = new BloodBowlDieReroll(1);

	private Button twoPlusButton;
	private Button threePlusButton;
	private Button fourPlusButton;
	private Button fivePlusButton;
	private Button sixPlusButton;

	private Button blockDiceTwoDown;
	private Button blockDiceOneDown;
	private Button blockDiceOne;
	private Button blockDiceTwo;
	private Button blockDiceThree;

	private Button rerollOneButton;
	private Button rerollTwoButton;
	private Button rerollThreeButton;
	private Button rerollFourButton;

	private Button calculateButton;
	private Button clearButton;

	private TextView sequenceViewer;
	private TextView probabilityResult;

	private LinkedList<BloodBowlDieRoll> numberSequence = new LinkedList<BloodBowlDieRoll>();
	private LinkedList<BloodBowlDieRoll> numberSequenceTeamReroll = new LinkedList<BloodBowlDieRoll>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.twoPlusButton = (Button) this.findViewById(R.id.two_plus);
		this.threePlusButton = (Button) this.findViewById(R.id.three_plus);
		this.fourPlusButton = (Button) this.findViewById(R.id.four_plus);
		this.fivePlusButton = (Button) this.findViewById(R.id.five_plus);
		this.sixPlusButton = (Button) this.findViewById(R.id.six_plus);

		this.blockDiceTwoDown = (Button) this
				.findViewById(R.id.blockDiceTwoDown);
		this.blockDiceOneDown = (Button) this
				.findViewById(R.id.blockDiceOneDown);
		this.blockDiceOne = (Button) this.findViewById(R.id.blockDiceOne);
		this.blockDiceTwo = (Button) this.findViewById(R.id.blockDiceTwo);
		this.blockDiceThree = (Button) this.findViewById(R.id.blockDiceThree);

		this.rerollOneButton = (Button) this.findViewById(R.id.rerollOne);
		this.rerollTwoButton = (Button) this.findViewById(R.id.rerollTwo);
		this.rerollThreeButton = (Button) this.findViewById(R.id.rerollThree);
		this.rerollFourButton = (Button) this.findViewById(R.id.rerollFour);

		this.calculateButton = (Button) findViewById(R.id.calculateProbability);
		this.clearButton = (Button) findViewById(R.id.clearList);

		this.sequenceViewer = (TextView) findViewById(R.id.sequenceViewer);
		this.probabilityResult = (TextView) findViewById(R.id.probabilityResult);

		twoPlusButton.setOnClickListener(this);
		threePlusButton.setOnClickListener(this);
		fourPlusButton.setOnClickListener(this);
		fivePlusButton.setOnClickListener(this);
		sixPlusButton.setOnClickListener(this);

		blockDiceTwoDown.setOnClickListener(this);
		blockDiceOneDown.setOnClickListener(this);
		blockDiceOne.setOnClickListener(this);
		blockDiceTwo.setOnClickListener(this);
		blockDiceThree.setOnClickListener(this);

		rerollOneButton.setOnClickListener(this);
		rerollTwoButton.setOnClickListener(this);
		rerollThreeButton.setOnClickListener(this);
		rerollFourButton.setOnClickListener(this);

		calculateButton.setOnClickListener(this);
		clearButton.setOnClickListener(this);

		colorButton(rerollOneButton, Color.GREEN);
		colorButton(rerollTwoButton, Color.BLUE);
		colorButton(rerollThreeButton, Color.RED);
		colorButton(rerollFourButton, Color.YELLOW);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.two_plus:
			this.updateSequence(2, new BloodBowlDieReroll(0));
			// Toast.makeText(BloodBowlProbability.this, "Button 2 " +
			// numberSequence.toString(), Toast.LENGTH_SHORT).show();
			break;

		case R.id.three_plus:
			this.updateSequence(3, new BloodBowlDieReroll(0));
			break;

		case R.id.four_plus:
			this.updateSequence(4, new BloodBowlDieReroll(0));
			break;

		case R.id.five_plus:
			this.updateSequence(5, new BloodBowlDieReroll(0));
			break;

		case R.id.six_plus:
			this.updateSequence(6, new BloodBowlDieReroll(0));
			break;

		case R.id.blockDiceTwoDown:
			this.chooseBlockDice(-2);
			break;

		case R.id.blockDiceOneDown:
			this.chooseBlockDice(-1);
			break;

		case R.id.blockDiceOne:
			this.chooseBlockDice(1);
			break;

		case R.id.blockDiceTwo:
			this.chooseBlockDice(2);
			break;

		case R.id.blockDiceThree:
			this.chooseBlockDice(3);
			break;

		case R.id.rerollOne:
			addPersistingReroll(this.rerollOne, this.rerollOneTeam);
			break;

		case R.id.rerollTwo:
			addPersistingReroll(this.rerollTwo, this.rerollTwoTeam);
			break;

		case R.id.rerollThree:
			addPersistingReroll(this.rerollThree, this.rerollThreeTeam);
			break;

		case R.id.rerollFour:
			addPersistingReroll(this.rerollFour, this.rerollFourTeam);
			break;

		case R.id.calculateProbability:

			if (!this.numberSequence.isEmpty()) {
				BloodBowlDieRoll[] s = (BloodBowlDieRoll[]) this.numberSequence
						.toArray(new BloodBowlDieRoll[this.numberSequence
								.size()]);
				
				BloodBowlDieRoll[] sTeam = (BloodBowlDieRoll[]) this.numberSequenceTeamReroll
				.toArray(new BloodBowlDieRoll[this.numberSequenceTeamReroll
						.size()]);
				
				double pWithReroll = ProbabilityCalculator.main(s, 1);
				double pWithoutReroll = ProbabilityCalculator.main(sTeam, 0);

				this.probabilityResult.setText(Double.toString(Math
						.round(pWithoutReroll * 100))
						+ "%. With team reroll => "
						+ Double.toString(Math.round(pWithReroll * 100)) + "%");
			}
			break;

		case R.id.clearList:
			this.numberSequence.clear();
			this.numberSequenceTeamReroll.clear();
			this.sequenceViewer.setText("");
			this.probabilityResult.setText("");
			break;
		}

	}

	private void chooseBlockDice(int blockDiceNumber) {
		// Context mContext = getApplicationContext();
		// Dialog dialog = new Dialog(mContext);
		Dialog dialog = new Dialog(this);
		dialog.setOwnerActivity(this);

		dialog.setContentView(R.layout.block_dice_chooser);
		dialog.setTitle("Choose your goal(s)");
		dialog.setCancelable(true);
		dialog.show();
	}

	private void updateSequence(int requiredRoll, BloodBowlDieReroll reroll) {
		this.numberSequence.add(new BloodBowlDieRoll(requiredRoll, reroll));
		this.numberSequenceTeamReroll.add(new BloodBowlDieRoll(requiredRoll, reroll));
		// this.sequenceViewer.setText(numberSequence.toString());
		this.updateSequenceDisplay();
	}

	private void updateSequenceDisplay() {
		this.sequenceViewer.setText(this.buildSequenceString());
	}

	private void addPersistingReroll(BloodBowlDieReroll r, BloodBowlDieReroll rTeam) {
		// BloodBowlDieRoll newestRoll = this.numberSequence.getLast();
		this.numberSequence.getLast().setReroll(r);
		this.numberSequenceTeamReroll.getLast().setReroll(rTeam);
		this.updateSequenceDisplay();
	}

	private void colorButton(Button b, int color) {
		Drawable d = b.getBackground();
		PorterDuffColorFilter filter = new PorterDuffColorFilter(color,
				PorterDuff.Mode.SRC_ATOP);
		d.setColorFilter(filter);
	}

	private Spanned buildSequenceString() {
		StringBuilder sb = new StringBuilder();

		for (BloodBowlDieRoll diceRoll : this.numberSequence) {

			int required_roll = diceRoll.getRequiredRoll();
			BloodBowlDieReroll reroll = diceRoll.getReroll();

			if (reroll == this.rerollOne) {
				this.appendColoredRoll(sb, required_roll, "#00FF00");
			} else if (reroll == this.rerollTwo) {
				this.appendColoredRoll(sb, required_roll, "#0000FF");
			} else if (reroll == this.rerollThree) {
				this.appendColoredRoll(sb, required_roll, "#FF0000");
			} else if (reroll == this.rerollFour) {
				this.appendColoredRoll(sb, required_roll, "#FFFF00");
			} else {
				sb.append(required_roll);
				sb.append("+, ");
			}

		}

		return Html.fromHtml(sb.toString());
	}

	private void appendColoredRoll(StringBuilder s, int r, String c) {
		s.append("<font color=\"" + c + "\">");
		s.append(r);
		s.append("+</font>, ");
	}

}