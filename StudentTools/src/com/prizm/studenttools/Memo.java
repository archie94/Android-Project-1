package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Memo extends Activity implements View.OnClickListener
{
	Button add;
	static int counter=1;
	LinearLayout l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo);
		initialise();
		add.setOnClickListener(this);
	}

	private void initialise() 
	{
		// TODO Auto-generated method stub
		add=(Button)findViewById(R.id.memo_addMore);
		l1=(LinearLayout)findViewById(R.id.memo_l);
	}

	@Override
	public void onClick(View arg0) 
	{
		try
		{
			// TODO Auto-generated method stub
			switch(arg0.getId())
			{
			case R.id.memo_addMore:
				EditText et=new EditText(this);
				et.setId(counter++);
				et.setHint("Enter memo here");
				l1.addView(et);
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
