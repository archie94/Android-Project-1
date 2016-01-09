package com.prizm.studenttools;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class MemoView extends Activity  
{
	/*
	 * In this activity we edit our memo according to user choice
	 */
	EditText memo ; 

	AlarmManager alarmManager;
	private PendingIntent pendingIntent;
	private TimePicker timePicker;
	private static MemoView instance;
	
	
	public static MemoView getInstance()
	{
		return instance;
	}
	
	@Override
	protected void onStart() 
	{
		// TODO Auto-generated method stub
		super.onStart();
		instance = this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		memo = (EditText)findViewById(R.id.memo_view_editText1);
		final ToggleButton alarmOn;
		alarmOn = (ToggleButton)findViewById(R.id.memo_view_toggleButton_on_off);
		timePicker = (TimePicker)findViewById(R.id.memo_view_timePicker1);
		alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		
		
		/*
		 * Collect the memo received from Memo activity 
		 * and set it in the edit text in this activity 
		 */
		Bundle bundle = getIntent().getExtras();
		try
		{
			memo.setText(bundle.getString("memo"),TextView.BufferType.EDITABLE); 
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		/*
		 * Finish editing the memo when 
		 * "DONE" is pressed
		 */
		memo.setOnEditorActionListener(new OnEditorActionListener()
		{

			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) 
			{
				// TODO Auto-generated method stub
				if(actionId == EditorInfo.IME_ACTION_DONE)
				{
					editMemo();
					return true;
				}
				return false;
			}

			
		});
		
		
		alarmOn.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(alarmOn.isChecked() == true)
				{
					Calendar calender = Calendar.getInstance();
					calender.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
					calender.set(Calendar.MINUTE, timePicker.getCurrentMinute());
					// set pending intent here 
				}
				else
				{
					// clear alarm here
				}
			}
		});
		
	}
	private void editMemo()
	{
		/*
		 * Rewind to the Memo activity with the updated memo
		 */
		Intent i = new Intent (MemoView.this,Memo.class);
		i.putExtra("memoback", memo.getText().toString());
		startActivity(i);
	}
	
	
	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		// kill this activity when it is paused / goes to background 
		finish();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent i = new Intent (MemoView.this,Memo.class);
			if(getIntent().getExtras().getString("memo").equals(memo.getText().toString())==true)
				i.putExtra("memoback", "");
			else 
				i.putExtra("memoback", memo.getText().toString());
			startActivity(i);
		}
		return super.onKeyUp(keyCode, event);
	}

}
