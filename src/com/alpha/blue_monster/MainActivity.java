package com.alpha.blue_monster;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * @author Dominic
 *
 */
public class MainActivity extends Activity {
	
	CustomView ourSurfaceView;
	Button feedButton;
	private int padding;
	private int screenWidth;
	private Monster monster;
	private final int MAX_HP = 200;
	private final int MAX_HAPPINESS = 150;
	private final int LEVEL =1;
	private final int EXP = 1;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Point screenSize = new Point();
		Display screenDisplay = getWindowManager().getDefaultDisplay();
		screenDisplay.getSize(screenSize);
		screenWidth = screenSize.x;
		padding = 20;
		super.onCreate(savedInstanceState);
		
		monster=new Monster("Blue Monster", "nickname", MAX_HP, MAX_HAPPINESS, LEVEL,EXP);
		FrameLayout Main = new FrameLayout(this);
		RelativeLayout Widgets = new RelativeLayout(this);
		ourSurfaceView = new CustomView(this, monster);
		
		feedButton = new Button(this);
		Drawable feedImg = getResources().getDrawable(R.drawable.feed);
		feedButton.setBackground(feedImg);
		LayoutParams buttonLayoutParams = new LayoutParams(feedImg.getMinimumWidth(), feedImg.getMinimumHeight());
		feedButton.setLayoutParams(buttonLayoutParams);
		feedButton.setX(screenWidth-feedImg.getMinimumWidth()-padding);
		feedButton.setY(padding);
		feedButton.setOnClickListener(feedButtonClickListener);
		
		Button patButton = new Button(this);
		Drawable patImg = getResources().getDrawable(R.drawable.pat);
		patButton.setBackground(patImg);
		patButton.setLayoutParams(buttonLayoutParams);
		patButton.setX(screenWidth-2*(feedImg.getMinimumWidth()+padding));
		patButton.setY(padding);
		
		ourSurfaceView.setOnTouchListener(surfaceViewOnTouchListener);
		Widgets.addView(feedButton);
		Widgets.addView(patButton);
		Main.addView(ourSurfaceView);
		Main.addView(Widgets);
		setContentView(Main);
		
	}
	
	private OnTouchListener surfaceViewOnTouchListener = new OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent e) {
			float[] monsterCorr = ourSurfaceView.monsterCoordinate();
			Monster temp = ourSurfaceView.getMonster();
			float x = e.getX();
			float y = e.getY();
			
			switch(e.getAction()){
				case MotionEvent.ACTION_DOWN:{
					if(x>= monsterCorr[0] && x<= monsterCorr[1] && y>= monsterCorr[2] && y<=monsterCorr[3]){
						ourSurfaceView.setpat(true);
					}
					break;
				}
				case MotionEvent.ACTION_MOVE:{
					if(x>= monsterCorr[0] && x<= monsterCorr[1] && y>= monsterCorr[2] && y<=monsterCorr[3]){
						if(x-monsterCorr[0] > monsterCorr[1]-x){
							ourSurfaceView.setbackwards(false);
						}else{
							ourSurfaceView.setbackwards(true);
						}
					}
					break;
				}
				case MotionEvent.ACTION_UP:{
					if(ourSurfaceView.getpat()){
						ourSurfaceView.setpat(false);
						ourSurfaceView.setpatRelease(true);
						int healpt = 30;
						if(((Float)temp.get("happiness")).floatValue() < ((Float)temp.get("maxhappiness")).floatValue()-healpt){
							float currentvalue = ((Float)temp.get("happiness")).floatValue();
							temp.set("happiness", new Float(currentvalue+healpt));
						}
					}
					break;
				}
				case MotionEvent.ACTION_CANCEL:{
					if(ourSurfaceView.getpat()){
						ourSurfaceView.setpat(false);
						ourSurfaceView.setpatRelease(true);
						int healpt = 30;
						if(((Float)temp.get("happiness")).floatValue() < ((Float)temp.get("maxhappiness")).floatValue()-healpt){
							float currentvalue = ((Float)temp.get("happiness")).floatValue();
							temp.set("happiness", new Float(currentvalue+healpt));
						}
					}
					break;
				}
			}
			
			return true;
		}
		
	};
	
	private OnClickListener feedButtonClickListener = new OnClickListener(){

		@Override
		public void onClick(View view) {
			Monster temp = ourSurfaceView.getMonster();
			int healpt = 30;
			if(((Float)temp.get("hp")).floatValue() < ((Float)temp.get("maxhp")).floatValue()-healpt){
				float currentvalue = ((Float)temp.get("hp")).floatValue();
				temp.set("hp", new Float(currentvalue+healpt));
			}
			ourSurfaceView.setMonster(temp);
		}
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		ourSurfaceView.pause();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
