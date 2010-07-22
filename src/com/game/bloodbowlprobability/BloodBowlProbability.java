package com.game.bloodbowlprobability;

import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.TextView;

public class BloodBowlProbability extends Activity implements OnClickListener {
	
	private Dialog dialog;
	private int blockDieLikehood = 0;
	private int blockDiceNumber = 0;
	
	private int blockDiePowPushed = 0;
	private int blockDieStumblesPushed = 0;
	private int blockDiePushedPushed = 0;
	private int blockDieBothdownPushed = 0;
	private int blockDieSkullPushed = 0;

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
	
	private ImageButton blockDiePow;
	private ImageButton blockDieStumbles;
	private ImageButton blockDiePushed;
	private ImageButton blockDieBothdown;
	private ImageButton blockDieSkull;
	
	private Button blockDiceAccept;
	private Button blockDiceCancel;

	private Button calculateButton;
	private Button clearButton;

	private TextView sequenceViewer;
	private TextView probabilityResult;

	private LinkedList<DieRoll> numberSequence = new LinkedList<DieRoll>();
	private LinkedList<DieRoll> numberSequenceTeamReroll = new LinkedList<DieRoll>();

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
		
		/*
		this.blockDiePow = (Button) this.findViewById(R.id.blockDiePow);
		this.blockDieStumbles = (Button) this.findViewById(R.id.blockDieStumbles);
		this.blockDiePushed = (Button) this.findViewById(R.id.blockDiePushed);
		this.blockDieBothdown = (Button) this.findViewById(R.id.blockDieBothdown);
		this.blockDieSkull = (Button) this.findViewById(R.id.blockDieSkull);
		*/

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
		
		/*
		blockDiePow.setOnClickListener(this);
		blockDieStumbles.setOnClickListener(this);
		blockDiePushed.setOnClickListener(this);
		blockDieBothdown.setOnClickListener(this);
		blockDieSkull.setOnClickListener(this);
		*/

		calculateButton.setOnClickListener(this);
		clearButton.setOnClickListener(this);

		colorButton(rerollOneButton, Color.GREEN);
		colorButton(rerollTwoButton, Color.rgb(0x2B, 0x60, 0xDE)); // #2B60DE
		colorButton(rerollThreeButton, Color.rgb(0xF6, 0x22, 0x17)); // F62217
		colorButton(rerollFourButton, Color.YELLOW);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.two_plus:
			this.updateSequence(new BloodBowlDieRoll(2, new BloodBowlDieReroll(0)));
			// Toast.makeText(BloodBowlProbability.this, "Button 2 " +
			// numberSequence.toString(), Toast.LENGTH_SHORT).show();
			break;

		case R.id.three_plus:
			this.updateSequence(new BloodBowlDieRoll(3, new BloodBowlDieReroll(0)));
			break;

		case R.id.four_plus:
			this.updateSequence(new BloodBowlDieRoll(4, new BloodBowlDieReroll(0)));
			break;

		case R.id.five_plus:
			this.updateSequence(new BloodBowlDieRoll(5, new BloodBowlDieReroll(0)));
			break;

		case R.id.six_plus:
			this.updateSequence(new BloodBowlDieRoll(6, new BloodBowlDieReroll(0)));
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
			
		case R.id.blockDiePow:
			blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
			this.blockDieLikehood += (blockDiePowPushed == 1) ? 1 : -1;
			break;	
			
		case R.id.blockDieStumbles:
			blockDieStumblesPushed = updateBlockDie(blockDieStumbles, blockDieStumblesPushed);
			this.blockDieLikehood += (blockDieStumblesPushed == 1) ? 1 : -1;
			break;
			
		case R.id.blockDiePushed:
			blockDiePushedPushed = updateBlockDie(blockDiePushed, blockDiePushedPushed);
			this.blockDieLikehood += (blockDiePushedPushed == 1) ? 2 : -2;
			break;
			
		case R.id.blockDieBothdown:
			blockDieBothdownPushed = updateBlockDie(blockDieBothdown, blockDieBothdownPushed);
			this.blockDieLikehood += (blockDieBothdownPushed == 1) ? 1 : -1;
			break;
			
		case R.id.blockDieSkull:
			blockDieSkullPushed = updateBlockDie(blockDieSkull, blockDieSkullPushed);
			this.blockDieLikehood += (blockDieSkullPushed == 1) ? 1 : -1;
			break;
			
		case R.id.blockDiceAccept:
			BloodBowlBlockDieRoll blockDie = new BloodBowlBlockDieRoll(7 - this.blockDieLikehood, this.blockDiceNumber);
			// TODO fix me
			this.updateSequence(blockDie);
			dialog.dismiss();
			this.blockDieLikehood = 0;
			this.resetBlockDieButtons();
			break;
			
		case R.id.blockDiceCancel:
			this.blockDieLikehood = 0;
			this.dialog.dismiss();
			this.resetBlockDieButtons();
			break;

		case R.id.calculateProbability:

			if (!this.numberSequence.isEmpty()) {
				DieRoll[] s = (DieRoll[]) this.numberSequence
						.toArray(new DieRoll[this.numberSequence
								.size()]);
				
				DieRoll[] sTeam = (DieRoll[]) this.numberSequenceTeamReroll
				.toArray(new DieRoll[this.numberSequenceTeamReroll
						.size()]);
				
				double pWithReroll = ProbabilityCalculator.main(s, 1);
				double pWithoutReroll = ProbabilityCalculator.main(sTeam, 0);

				this.probabilityResult.setText("Direct roll: " + Double.toString(Math
						.round(pWithoutReroll * 100))
						+ "%.\nUsing a Team reroll: "
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

	private int updateBlockDie(ImageButton blockDie, int blockDiePushed) {
		if (blockDiePushed == 1)
		{
			colorButton(blockDie, Color.WHITE);
			return 0;
		}
		else
		{
			colorButton(blockDie, Color.RED);
			return 1;
		}
	}

	private void chooseBlockDice(int blockDiceNumber) {
		// Context mContext = getApplicationContext();
		// Dialog dialog = new Dialog(mContext);
		//BlockDiceChooser.main(this);
		
		this.blockDiceNumber = blockDiceNumber;
		
		this.dialog = new Dialog(this);
		this.dialog.setOwnerActivity(this);

		this.dialog.setContentView(R.layout.block_dice_chooser);
		this.dialog.setTitle("Choose your goal(s)");
		this.dialog.setCancelable(true);
		
		this.blockDiePow = (ImageButton) dialog.findViewById(R.id.blockDiePow);
		this.blockDieStumbles = (ImageButton) dialog.findViewById(R.id.blockDieStumbles);
		this.blockDiePushed = (ImageButton) dialog.findViewById(R.id.blockDiePushed);
		this.blockDieBothdown = (ImageButton) dialog.findViewById(R.id.blockDieBothdown);
		this.blockDieSkull = (ImageButton) dialog.findViewById(R.id.blockDieSkull);
		
		this.blockDiceAccept = (Button) dialog.findViewById(R.id.blockDiceAccept);
		this.blockDiceCancel = (Button) dialog.findViewById(R.id.blockDiceCancel);

		this.blockDiePow.setOnClickListener(this);
		this.blockDieStumbles.setOnClickListener(this);
		this.blockDiePushed.setOnClickListener(this);
		this.blockDieBothdown.setOnClickListener(this);
		this.blockDieSkull.setOnClickListener(this);
		
		this.blockDiceAccept.setOnClickListener(this);
		this.blockDiceCancel.setOnClickListener(this);
		
		// TODO fix this
		this.updateBlockDie(this.blockDiePow, 1);
		this.updateBlockDie(this.blockDieStumbles, 1);
		this.updateBlockDie(this.blockDiePushed, 1);
		this.updateBlockDie(this.blockDieBothdown, 1);
		this.updateBlockDie(this.blockDieSkull, 1);
		
		this.dialog.show();
		
	}

	private void updateSequence(DieRoll dieRoll) {
		this.numberSequence.add(dieRoll);
		this.numberSequenceTeamReroll.add(dieRoll.copy());
		// this.sequenceViewer.setText(numberSequence.toString());
		this.updateSequenceDisplay();
	}

	private void updateSequenceDisplay() {
		this.sequenceViewer.setText(this.buildSequenceString());
	}

	private void addPersistingReroll(BloodBowlDieReroll r, BloodBowlDieReroll rTeam) {
		if (!this.numberSequence.isEmpty())
		{
			this.numberSequence.getLast().setReroll(r);
			this.numberSequenceTeamReroll.getLast().setReroll(rTeam);
			this.updateSequenceDisplay();
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Can not apply this reroll to any roll. Roll sequence is empty.")
			       .setCancelable(false)
			       .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
		}
	}

	private void colorButton(Button b, int color) {
		Drawable d = b.getBackground();
		PorterDuffColorFilter filter = new PorterDuffColorFilter(color,
				PorterDuff.Mode.SRC_ATOP);
		d.setColorFilter(filter);
	}
	
	private void colorButton(ImageButton b, int color) {
		Drawable d = b.getBackground();
		PorterDuffColorFilter filter = new PorterDuffColorFilter(color,
				PorterDuff.Mode.SRC_ATOP);
		d.setColorFilter(filter);
	}

	private Spanned buildSequenceString() {
		StringBuilder sb = new StringBuilder();

		for (DieRoll diceRoll : this.numberSequence) {

			int required_roll = diceRoll.getRequiredRoll();
			BloodBowlDieReroll reroll = diceRoll.getReroll();

			if (reroll == this.rerollOne) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#00FF00");
			} else if (reroll == this.rerollTwo) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#FAAFBE"); // red
			} else if (reroll == this.rerollThree) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#F75D59"); // blue
			} else if (reroll == this.rerollFour) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#FFFF00");
			} else {
				sb.append(diceRoll.toString());
				sb.append(", ");
			}

		}

		return Html.fromHtml(sb.toString());
	}

	private void appendColoredRoll(StringBuilder s, String r, String c) {
		s.append("<font color=\"" + c + "\">");
		s.append(r);
		s.append("</font>, ");
	}
	
	private void resetBlockDieButtons() {
		this.blockDiePowPushed = 0;
		this.blockDieStumblesPushed = 0;
		this.blockDiePushedPushed = 0;
		this.blockDieBothdownPushed = 0;
		this.blockDieSkullPushed = 0;
	}

}