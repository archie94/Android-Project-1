package com.prizm.studenttools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThreeVariable extends Activity implements View.OnClickListener
{
	EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
	double d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12;
	Button res;
	TextView res1,res2,res3;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three_variable);
		initialise();
		res.setOnClickListener(this);
		d1=d2=d3=d4=d5=d6=d7=d8=d9=d10=d11=d12=0.0;
	}
	
	
	private void initialise() 
	{
		// TODO Auto-generated method stub
		e1=(EditText)findViewById(R.id.editText_three_1);
		e2=(EditText)findViewById(R.id.editText_three_2);
		e3=(EditText)findViewById(R.id.editText_three_3);
		e4=(EditText)findViewById(R.id.editText_three_4);
		e5=(EditText)findViewById(R.id.editText_three_5);
		e6=(EditText)findViewById(R.id.editText_three_6);
		e7=(EditText)findViewById(R.id.editText_three_7);
		e8=(EditText)findViewById(R.id.editText_three_8);
		e9=(EditText)findViewById(R.id.editText_three_9);
		e10=(EditText)findViewById(R.id.editText_three_10);
		e11=(EditText)findViewById(R.id.editText_three_11);
		e12=(EditText)findViewById(R.id.editText_three_12);
		res1=(TextView)findViewById(R.id.textView_3Result1);
		res2=(TextView)findViewById(R.id.textView_3Result2);
		res3=(TextView)findViewById(R.id.textView_3Result3);
		res=(Button)findViewById(R.id.button_three_Result);
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
			d7=Double.parseDouble(e7.getText().toString());
			d8=Double.parseDouble(e8.getText().toString());
			d9=Double.parseDouble(e9.getText().toString());
			d10=Double.parseDouble(e10.getText().toString());
			d11=Double.parseDouble(e11.getText().toString());
			d12=Double.parseDouble(e12.getText().toString());
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
		double D,Dx,Dy,Dz;
		D = d1*(d5*d9-d6*d8)+d2*(d6*d7-d4*d9)+d3*(d4*d8-d5*d7);
		
		Dx = d10*(d5*d9-d6*d8)+d2*(d6*d12-d9*d11)+d3*(d11*d8-d5*d12);
		
		Dy = d1*(d11*d9-d6*d12)+d10*(d6*d7-d9*d4)+d3*(d4*d12-d7*d11);
		
		Dz = d1*(d5*d12-d8*d11)+d2*(d11*d7-d4*d12)+d10*(d4*d8-d5*d7);
		
		if(D==0 &&(Dx!=0 || Dy!=0 || Dz!=0))
		{
			res1.setText("The System has");
			res2.setText("     NO       ");
			res3.setText("   SOLUTIONS  ");
		}
		else if(D==0 && Dx==0 && Dy==0 && Dz==0)
		{
			res1.setText("The System has");
			res2.setText("  INFINITELY  ");
			res3.setText("MANY SOLUTIONS");
		}
		else
		{
			double x=(D*1.0)/Dx;
			double y=(D*1.0)/Dy;
			double z=(D*1.0)/Dz;
			res1.setText(" X = "+x);
			res2.setText(" Y = "+y);
			res3.setText(" Z = "+z);
		}
	}
	
	
	

}
