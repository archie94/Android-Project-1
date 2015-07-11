package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Memo extends Activity implements View.OnClickListener
{
	Button add;
	static int counter=1;
	LinearLayout l1;
	String str;
	EditText et;
	DBHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo);
		initialise();
		et.setOnClickListener(this);
		add.setOnClickListener(this);
		//printDataBase();
	}
	private void addDataBase()
	{
		if(str!="")
		{
			/*TextView tt=new EditText(this);
			tt.setId(counter++);
			tt.setText(str);
			l1.addView(tt);
			et.setText("");
			str="";*/
			MemoGetter m=new MemoGetter(str);
			handler.addRow(m);
			str="";
			et.setText("");
			printDataBase();
		}
	}

	private void printDataBase() 
	{
		// TODO Auto-generated method stub
		/*if(str!="")
		{
			TextView tt=new EditText(this);
			tt.setId(counter++);
			tt.setText(str);
			l1.addView(tt);
			et.setText("");
			str="";
		}*/
		String dbString = handler.dtabasetoString();
		TextView tt=new TextView(this);
		tt.setId(counter++);
		tt.setText(dbString);
		l1.addView(tt);
	}

	private void initialise() 
	{
		// TODO Auto-generated method stub
		str="";
		add=(Button)findViewById(R.id.memo_addMore);
		l1=(LinearLayout)findViewById(R.id.memo_l);
		et=(EditText)findViewById(R.id.memo_editText1);
		handler=new DBHandler(this,null,null,1);
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
				addDataBase();
				break;
			case R.id.memo_editText1:
				str=et.getText().toString();
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
