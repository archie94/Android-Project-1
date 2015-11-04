package com.prizm.studenttools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MemoView extends Activity  
{
	EditText memo ;
	Button edit ; 
	String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		memo = (EditText)findViewById(R.id.memo_view_editText1);
		edit = (Button)findViewById(R.id.memo_view_button1);
		
		Bundle bundle = getIntent().getExtras();
		try
		{
			str = bundle.getString("memo");
			memo.setText(str,TextView.BufferType.EDITABLE);
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
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
		
		edit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				editMemo();
			}
			
		});
	}
	private void editMemo()
	{
		str=memo.getText().toString();
		Intent i = new Intent (MemoView.this,Memo.class);
		i.putExtra("memoback", str);
		startActivity(i);
	}
	
	
	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
