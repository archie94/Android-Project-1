package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Gpa extends Activity implements View.OnClickListener
{
	
	

	EditText et;
	String classes[];
	LinearLayout l1,l2;
	Button b;
	Button btn[];
	int num;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpa);
		
		et=(EditText)findViewById(R.id.gpa_num);
		l1=(LinearLayout)findViewById(R.id.gpa_layout);
		l2=(LinearLayout)findViewById(R.id.gpa_l2);
		b=(Button)findViewById(R.id.gpa_ok);
		num=0;
		et.setOnClickListener(this);
		b.setOnClickListener(this);
		for(int i=0;i<num;i++)
		{
			btn[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		try
		{
			switch(arg0.getId())
			{
			case R.id.gpa_num:
				num=Integer.parseInt(et.getText().toString());
				break;
			case R.id.gpa_ok:
				classes=new String[num];
				btn=new Button[num];
				int i;
				for(i=0;i<num;i++)
				{  
					classes[i]="Semester"+(i+1);
					btn[i]=new Button(this);
					btn[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					btn[i].setId(i);
					btn[i].setText(classes[i]);
					l2.addView(btn[i]);
				}
				break;
			}
			for(int i=0;i<num;i++)
			{
				if(arg0.getId()==i)
				{
					
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
