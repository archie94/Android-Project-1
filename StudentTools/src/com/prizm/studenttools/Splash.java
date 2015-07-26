package com.prizm.studenttools;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity
{
	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle APB) 
	{
		// TODO Auto-generated method stub
		super.onCreate(APB);
		setContentView(R.layout.splash);
		
		
		
		
		
		//SoundPool class can be used for small clips like gun shots etc 
		//MediaPlayer is used for background music etc 
		ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);
		//Splash.this refers to the context of Splash class
		ourSong.start();
		
		
		
		Thread  timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(3000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					Intent openStartingPoint = new Intent("com.prizm.studenttools.MAINMENU");//use action name written in manifest
					startActivity(openStartingPoint);   
				}
			}
		};//end of thread
		timer.start();
	}//end of onCreate

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
	
	

}//end of Splash class
