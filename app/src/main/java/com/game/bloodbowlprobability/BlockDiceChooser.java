package com.game.bloodbowlprobability;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

public class BlockDiceChooser {
	
	private static int blockDiePowPushed = 0;
	private static int blockDieStumblesPushed = 0;
	private static int blockDiePushedPushed = 0;
	private static int blockDieBothdownPushed = 0;
	private static int blockDieSkullPushed = 0;
	
	private static Button blockDiePow;
	private static Button blockDieStumbles;
	private static Button blockDiePushed;
	private static Button blockDieBothdown;
	private static Button blockDieSkull;

	/**
	 * @param args
	 */
	public static void main(Activity A) {
		
		Dialog dialog = new Dialog(A);
		dialog.setOwnerActivity(A);

		dialog.setContentView(R.layout.block_dice_chooser);
		dialog.setTitle("Choose your goal(s)");
		dialog.setCancelable(true);
		
		blockDiePow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
    			//this.blockDieLikehood += (blockDiePowPushed == 1) ? 1 : -1;
            }
        });
		
		blockDieStumbles.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
            }
        });
		
		blockDiePushed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
            }
        });
		
		blockDieBothdown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
            }
        });
		
		blockDieSkull.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	blockDiePowPushed = updateBlockDie(blockDiePow, blockDiePowPushed);
            }
        });
		
		dialog.show();

	}
	
	private static int updateBlockDie(Button blockDie, int blockDiePushed) {
		if (blockDiePushed == 1)
		{
			colorButton(blockDie, Color.RED);
			return 0;
		}
		else
		{
			colorButton(blockDie, Color.WHITE);
			return 1;
		}
	}
	
	private static void colorButton(Button b, int color) {
		Drawable d = b.getBackground();
		PorterDuffColorFilter filter = new PorterDuffColorFilter(color,
				PorterDuff.Mode.SRC_ATOP);
		d.setColorFilter(filter);
	}

}
