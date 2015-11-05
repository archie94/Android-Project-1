package com.prizm.studenttools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MemoView extends Activity  
{
	/*
	 * In this activity we edit our memo according to user choice
	 */
	EditText memo ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		memo = (EditText)findViewById(R.id.memo_view_editText1);
		
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
	

}
