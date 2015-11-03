package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MemoView extends Activity  
{
	EditText memo ;
	Button edit ; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		memo = (EditText)findViewById(R.id.memo_view_editText1);
		edit = (Button)findViewById(R.id.memo_view_button1);
		
	}
	

}
