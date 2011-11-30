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
import android.widget.Toast;

public class BloodBowlProbability extends Activity implements OnClickListener {

	private Dialog dialog;
	private AlertDialog alterDialog;
	private int blockDieLikehood = 0;
	private int blockDiceNumber = 0;

	private int blockDiePowPushed;
	private int blockDieStumblesPushed;
	private int blockDiePushedPushed;
	private int blockDieBothdownPushed;
	private int blockDieSkullPushed;

	private BloodBowlDieReroll required_roll = new BloodBowlDieReroll(0);
	
	/*
	private BloodBowlDieReroll rerollOne = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollTwo = new BloodBowlDieReroll(1);;
	private BloodBowlDieReroll rerollThree = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollFour = new BloodBowlDieReroll(1);

	private BloodBowlDieReroll rerollOneTeam = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollTwoTeam = new BloodBowlDieReroll(1);;
	private BloodBowlDieReroll rerollThreeTeam = new BloodBowlDieReroll(1);
	private BloodBowlDieReroll rerollFourTeam = new BloodBowlDieReroll(1);
	*/
	
	private Button twoPlusButton;
	private Button threePlusButton;
	private Button fourPlusButton;
	private Button fivePlusButton;
	private Button sixPlusButton;

	private ImageButton blockDiceTwoDown;
	private ImageButton blockDiceOneDown;
	private ImageButton blockDiceOne;
	private ImageButton blockDiceTwo;
	private ImageButton blockDiceThree;
	
	private Button twoDSixButton;
	private Button dSixtyEigthButton;
	private Button dThreeButton;
	private String selectedButtonHack;

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

	/* private Button calculateButton; */
	private Button clearButton;

	private TextView sequenceViewer;
	private TextView probabilityResult;

	private LinkedList<DieRoll> numberSequence = new LinkedList<DieRoll>();
	private LinkedList<DieRoll> numberSequenceTeamReroll = new LinkedList<DieRoll>();
	//private DieRoll[] rerollList = new DieRoll[4];
	private BloodBowlDieReroll[] rerollList = {new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1)};
	
	private BloodBowlDieReroll[] rerollListTeam = {new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1),
			new BloodBowlDieReroll(1)};

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
		
		this.twoDSixButton = (Button) this.findViewById(R.id.two_d_six);
		this.dSixtyEigthButton = (Button) this.findViewById(R.id.d_sixty_eigth);
		this.dThreeButton = (Button) this.findViewById(R.id.d_three);

		this.blockDiceTwoDown = (ImageButton) this
				.findViewById(R.id.blockDiceTwoDown);
		this.blockDiceOneDown = (ImageButton) this
				.findViewById(R.id.blockDiceOneDown);
		this.blockDiceOne = (ImageButton) this.findViewById(R.id.blockDiceOne);
		this.blockDiceTwo = (ImageButton) this.findViewById(R.id.blockDiceTwo);
		this.blockDiceThree = (ImageButton) this
				.findViewById(R.id.blockDiceThree);

		this.rerollOneButton = (Button) this.findViewById(R.id.reroll_one);
		this.rerollTwoButton = (Button) this.findViewById(R.id.reroll_two);
		this.rerollThreeButton = (Button) this.findViewById(R.id.reroll_three);
		this.rerollFourButton = (Button) this.findViewById(R.id.reroll_four);

		this.clearButton = (Button) findViewById(R.id.clearList);

		this.sequenceViewer = (TextView) findViewById(R.id.sequenceViewer);
		this.probabilityResult = (TextView) findViewById(R.id.probabilityResult);

		twoPlusButton.setOnClickListener(this);
		threePlusButton.setOnClickListener(this);
		fourPlusButton.setOnClickListener(this);
		fivePlusButton.setOnClickListener(this);
		sixPlusButton.setOnClickListener(this);
		
		twoDSixButton.setOnClickListener(this);
		dSixtyEigthButton.setOnClickListener(this);
		dThreeButton.setOnClickListener(this);

		blockDiceTwoDown.setOnClickListener(this);
		blockDiceOneDown.setOnClickListener(this);
		blockDiceOne.setOnClickListener(this);
		blockDiceTwo.setOnClickListener(this);
		blockDiceThree.setOnClickListener(this);

		rerollOneButton.setOnClickListener(this);
		rerollTwoButton.setOnClickListener(this);
		rerollThreeButton.setOnClickListener(this);
		rerollFourButton.setOnClickListener(this);

		clearButton.setOnClickListener(this);

		colorButton(rerollOneButton, Color.GREEN);
		colorButton(rerollTwoButton, Color.rgb(0x2B, 0x60, 0xDE)); // #2B60DE
		colorButton(rerollThreeButton, Color.rgb(0xF6, 0x22, 0x17)); // F62217
		colorButton(rerollFourButton, Color.YELLOW);

		this.resetBlockDieButtons();
		
		final DataTransporter dt = (DataTransporter) getLastNonConfigurationInstance();
		if (dt != null)
		{
			this.restoreState(dt);
		}
		//this.restoreState((DataTransporter) getLastNonConfigurationInstance());

	}

	private void restoreState(DataTransporter dt) {
		if (!dt.equals(null)) {
			this.numberSequence = dt.getList();
			this.numberSequenceTeamReroll = dt.getTeamList();
			this.rerollList = dt.getRerollList();
			this.rerollListTeam = dt.getRerollListTeam();
			this.dialog = dt.getDialog();
			
			this.updateSequenceDisplay();
			this.calculateProbability();
		}
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		DataTransporter dt = new DataTransporter();
		dt.setList(this.numberSequence);
		dt.setTeamList(this.numberSequenceTeamReroll);
		dt.setDialog(this.dialog);
		dt.setRerollList(this.rerollList);
		dt.setRerollListTeam(this.rerollListTeam);

		return dt;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.two_plus:
			this.updateSequence(new GenericDieRoll(2, new BloodBowlDieReroll(
					0)));
			// Toast.makeText(BloodBowlProbability.this, "Button 2 " +
			// numberSequence.toString(), Toast.LENGTH_SHORT).show();
			break;

		case R.id.three_plus:
			this.updateSequence(new GenericDieRoll(3, new BloodBowlDieReroll(
					0)));
			break;

		case R.id.four_plus:
			this.updateSequence(new GenericDieRoll(4, new BloodBowlDieReroll(
					0)));
			break;

		case R.id.five_plus:
			this.updateSequence(new GenericDieRoll(5, new BloodBowlDieReroll(
					0)));
			break;

		case R.id.six_plus:
			this.updateSequence(new GenericDieRoll(6, new BloodBowlDieReroll(
					0)));
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
			
		case R.id.two_d_six:
			this.selectedButtonHack = "12";
			this.chooseNumber(12); //FIXME
			break;
			
		case R.id.d_sixty_eigth:
			this.selectedButtonHack = "68";
			this.chooseNumber(68); //FIXME
			break;
			
		case R.id.d_three:
			this.selectedButtonHack = "3";
			this.chooseNumber(3);
			break;

		case R.id.reroll_one:
			addPersistingReroll(this.rerollList[0], this.rerollListTeam[0]);
			break;

		case R.id.reroll_two:
			addPersistingReroll(this.rerollList[1], this.rerollListTeam[1]);
			break;

		case R.id.reroll_three:
			addPersistingReroll(this.rerollList[2], this.rerollListTeam[2]);
			break;

		case R.id.reroll_four:
			addPersistingReroll(this.rerollList[3], this.rerollListTeam[3]);
			break;

		case R.id.blockDiePow:
			this.blockDiePowPushed = (this.blockDiePowPushed == 1) ? 0 : 1;
			updateBlockDie(blockDiePow, blockDiePowPushed, 1);
			break;

		case R.id.blockDieStumbles:
			this.blockDieStumblesPushed = (this.blockDieStumblesPushed == 1) ? 0
					: 1;
			updateBlockDie(blockDieStumbles, blockDieStumblesPushed, 1);
			break;

		case R.id.blockDiePushed:
			this.blockDiePushedPushed = (this.blockDiePushedPushed == 1) ? 0
					: 1;
			updateBlockDie(blockDiePushed, blockDiePushedPushed, 2);
			break;

		case R.id.blockDieBothdown:
			this.blockDieBothdownPushed = (this.blockDieBothdownPushed == 1) ? 0
					: 1;
			updateBlockDie(blockDieBothdown, blockDieBothdownPushed, 1);
			break;

		case R.id.blockDieSkull:
			this.blockDiePowPushed = (this.blockDiePowPushed == 1) ? 0 : 1;
			updateBlockDie(blockDieSkull, blockDieSkullPushed, 1);
			break;

		case R.id.blockDiceAccept:
			BloodBowlBlockDieRoll blockDie = new BloodBowlBlockDieRoll(
					7 - this.blockDieLikehood, this.blockDiceNumber);
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

		case R.id.clearList:
			this.numberSequence.clear();
			this.numberSequenceTeamReroll.clear();
			this.sequenceViewer.setText("");
			this.probabilityResult.setText("");
			break;
		}

		this.calculateProbability();

	}

	private int updateBlockDie(ImageButton blockDie, int blockDiePushed,
			int value) {

		this.blockDieLikehood += (blockDiePushed == 1) ? value : -value;

		if (blockDiePushed == 1) {
			colorButton(blockDie, Color.RED);
			return 0;
		} else {
			colorButton(blockDie, Color.WHITE);
			return 1;
		}
	}
	
	private void chooseNumber(int upperLimit) {
		LinkedList<String> numbers = new LinkedList<String>();
		int lowerLimit = upperLimit == 12 ? 3 : 2;
		for (Integer i=upperLimit; i >= lowerLimit; i--)
			numbers.add(i.toString());
		
		//CharSequence[] items = {"Red", "Green", "Blue"};
		final CharSequence[] items = numbers.toArray(new CharSequence[numbers.size()]);

		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a required roll");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    	int minRoll = Integer.parseInt(items[item].toString());
		    	DieRoll dr = null;
		    	String buttonHack = BloodBowlProbability.this.selectedButtonHack;
		    	if (buttonHack == "12") {
		    		dr = new TwoDSix(minRoll, new BloodBowlDieReroll(0));
		    	} else if (buttonHack == "68")  {
		    		dr = new DSixtyEigth(minRoll, new BloodBowlDieReroll(0));
		    	} else {
		    		dr = new DThree(minRoll, new BloodBowlDieReroll(0));
		    	}
	    		BloodBowlProbability.this.updateSequence(dr);
	    		BloodBowlProbability.this.calculateProbability(); // FIXME why do I need this?
		    }
		});
		this.alterDialog = builder.create();
		this.alterDialog.show();
		//AlertDialog alert = builder.create();
	}

	private void chooseBlockDice(int blockDiceNumber) {
		// Context mContext = getApplicationContext();
		// Dialog dialog = new Dialog(mContext);
		// BlockDiceChooser.main(this);

		this.blockDiceNumber = blockDiceNumber;

		this.dialog = new Dialog(this);
		this.dialog.setOwnerActivity(this);

		this.dialog.setContentView(R.layout.block_dice_chooser);
		this.dialog.setTitle("Choose your goals.");
		this.dialog.setCancelable(true);

		this.blockDiePow = (ImageButton) dialog.findViewById(R.id.blockDiePow);
		this.blockDieStumbles = (ImageButton) dialog
				.findViewById(R.id.blockDieStumbles);
		this.blockDiePushed = (ImageButton) dialog
				.findViewById(R.id.blockDiePushed);
		this.blockDieBothdown = (ImageButton) dialog
				.findViewById(R.id.blockDieBothdown);
		this.blockDieSkull = (ImageButton) dialog
				.findViewById(R.id.blockDieSkull);

		this.blockDiceAccept = (Button) dialog
				.findViewById(R.id.blockDiceAccept);
		this.blockDiceCancel = (Button) dialog
				.findViewById(R.id.blockDiceCancel);

		this.blockDiePow.setOnClickListener(this);
		this.blockDieStumbles.setOnClickListener(this);
		this.blockDiePushed.setOnClickListener(this);
		this.blockDieBothdown.setOnClickListener(this);
		this.blockDieSkull.setOnClickListener(this);

		this.blockDiceAccept.setOnClickListener(this);
		this.blockDiceCancel.setOnClickListener(this);

		// TODO fix this
		this.updateBlockDie(this.blockDiePow, this.blockDiePowPushed, 1);
		this.updateBlockDie(this.blockDieStumbles, this.blockDieStumblesPushed,
				1);
		this.updateBlockDie(this.blockDiePushed, this.blockDiePushedPushed, 0);
		this.updateBlockDie(this.blockDieBothdown, this.blockDieBothdownPushed,
				0);
		this.updateBlockDie(this.blockDieSkull, this.blockDieSkullPushed, 0);

		this.dialog.show();

	}

	void updateSequence(DieRoll dieRoll) {
		this.numberSequence.add(dieRoll);
		DieRoll newRoll = dieRoll.copy();
		this.numberSequenceTeamReroll.add(newRoll);
		// this.sequenceViewer.setText(numberSequence.toString());
		this.updateSequenceDisplay();
	}

	private void updateSequenceDisplay() {
		this.sequenceViewer.setText(this.buildSequenceString());
	}

	private void addPersistingReroll(BloodBowlDieReroll r,
			BloodBowlDieReroll rTeam) {
		if (!this.numberSequence.isEmpty()) {
			this.numberSequence.getLast().setReroll(r);
			this.numberSequenceTeamReroll.getLast().setReroll(rTeam);
			this.updateSequenceDisplay();
			this.calculateProbability();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder
					.setMessage(
							"The roll sequence is empty.")
					.setCancelable(false).setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
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

			if (reroll == this.rerollList[0]) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#00FF00");
			} else if (reroll == this.rerollList[1]) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#79BAEC"); // blue
			} else if (reroll == this.rerollList[2]) {
				this.appendColoredRoll(sb, diceRoll.toString(), "#FAAFBE"); // red
			} else if (reroll == this.rerollList[3]) {
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
		this.blockDiePowPushed = 1;
		this.blockDieStumblesPushed = 1;
		this.blockDiePushedPushed = 0;
		this.blockDieBothdownPushed = 0;
		this.blockDieSkullPushed = 0;
	}

	private void calculateProbability() {
		if (!this.numberSequence.isEmpty()) {
			DieRoll[] s = (DieRoll[]) this.numberSequence
					.toArray(new DieRoll[this.numberSequence.size()]);

			DieRoll[] sTeam = (DieRoll[]) this.numberSequenceTeamReroll
					.toArray(new DieRoll[this.numberSequenceTeamReroll.size()]);

			double pWithReroll = ProbabilityCalculator.main(s, 1);
			double pWithoutReroll = ProbabilityCalculator.main(sTeam, 0);

			this.probabilityResult.setText("Direct roll: "
					+ Double.toString(Math.round(pWithoutReroll*1000)/10.0)
					+ "%.\nUsing a Team reroll: "
					+ Double.toString(Math.round(pWithReroll*1000)/10.0) + "%");
		}
	}

}