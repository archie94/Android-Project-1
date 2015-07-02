package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class TwoVariable extends Activity implements View.OnClickListener
{
	
	EditText e1,e2,e3,e4,e5,e6;
	double d1,d2,d3,d4,d5,d6;
	Button res;
	TextView res1,res2;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_variable);
		initialise();
		
		d1=d2=d3=d4=d5=d6=0.0;
		res.setOnClickListener(this);
	}


	private void initialise() 
	{
		// TODO Auto-generated method stub
		e1=(EditText)findViewById(R.id.editText_two_1);
		e2=(EditText)findViewById(R.id.editText_two_2);
		e3=(EditText)findViewById(R.id.editText_two_3);
		e4=(EditText)findViewById(R.id.editText_two_4);
		e5=(EditText)findViewById(R.id.editText_two_5);
		e6=(EditText)findViewById(R.id.editText_two_6);
		res=(Button)findViewById(R.id.button_two_Result);
		res1=(TextView)findViewById(R.id.textView_Result1);
		res2=(TextView)findViewById(R.id.textView_Result2);
	}


	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		try
		{
			d1=Double.parseDouble(e1.getText().toString());
			d2=Double.parseDouble(e2.getText().toString());
			d3=Double.parseDouble(e3.getText().toString());
			d4=Double.parseDouble(e4.getText().toString());
			d5=Double.parseDouble(e5.getText().toString());
			d6=Double.parseDouble(e6.getText().toString());
		}
		catch(Exception  e)
		{
			e.printStackTrace();
		}
		determinant();
	
	}


	private void determinant() 
	{
		// TODO Auto-generated method stub
		double D,Dx,Dy;
		D=(d1*d5)-(d2*d4);
		Dx=(d3*d5)-(d2*d6);
		Dy=(d1*d6)-(d3*d4);
		if(D==0 && (Dx!=0 || Dy!=0))
		{
			//no solution 
			res1.setText("The System has");
			res2.setText("NO SOLUTIONS");
		}
		else if(D==0 && Dx==0 && Dy==0)
		{
			//infinitely many solution
			res1.setText("The System has");
			res2.setText("INFINITELY MANY SOLUTIONS");
		}
		else
		{
			double x=(Dx*1.0)/D;
			double y=(Dy*1.0)/D;
			res1.setText("X = "+x);
			res2.setText("Y = "+y);
		}
	}
	
}
