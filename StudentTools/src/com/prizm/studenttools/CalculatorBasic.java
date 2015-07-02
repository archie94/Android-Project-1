package com.prizm.studenttools;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class CalculatorBasic extends Activity implements View.OnClickListener
{
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bequals,bplus,bsubtract,bmultiply,bdivide,bdel,bdot;
	TextView res;
	String str;
	int p=0,k=0;
	byte isallowed=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator_basic);
		initialise();
		
		res.setText(str);
		b0.setOnClickListener(this);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		bplus.setOnClickListener(this);
		bsubtract.setOnClickListener(this);
		bmultiply.setOnClickListener(this);
		bdivide.setOnClickListener(this);
		bdel.setOnClickListener(this);
		bequals.setOnClickListener(this);
		bdot.setOnClickListener(this);
		
	}

	private void initialise() 
	{
		// TODO Auto-generated method stub
		str="";
		b0=(Button)findViewById(R.id.button_calc_b_0);
		b1=(Button)findViewById(R.id.button_calc_b_1);
		b2=(Button)findViewById(R.id.button_calc_b_2);
		b3=(Button)findViewById(R.id.button_calc_b_3);
		b4=(Button)findViewById(R.id.button_calc_b_4);
		b5=(Button)findViewById(R.id.button_calc_b_5);
		b6=(Button)findViewById(R.id.button_calc_b_6);
		b7=(Button)findViewById(R.id.button_calc_b_7);
		b8=(Button)findViewById(R.id.button_calc_b_8);
		b9=(Button)findViewById(R.id.button_calc_b_9);
		bequals=(Button)findViewById(R.id.button_calc_b_equal);
		bplus=(Button)findViewById(R.id.button_calc_b_plus);
		bsubtract=(Button)findViewById(R.id.button_calc_b_subtract);
		bmultiply=(Button)findViewById(R.id.button_calc_b_multiply);
		bdivide=(Button)findViewById(R.id.button_calc_b_divide);
		bdel=(Button)findViewById(R.id.button_calc_b_del);
		bdot=(Button)findViewById(R.id.button_calc_b_dot);
		res=(TextView)findViewById(R.id.textView_calc_b_screen);
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.button_calc_b_0:
			isallowed=1;
			str=str+"0";
			break;
		case R.id.button_calc_b_1:
			isallowed=1;
			str=str+"1";
			break;
		case R.id.button_calc_b_2:
			isallowed=1;
			str+="2";
			break;
		case R.id.button_calc_b_3:
			isallowed=1;
			str+="3";
			break;
		case R.id.button_calc_b_4:
			isallowed=1;
			str+="4";
			break;
		case R.id.button_calc_b_5:
			isallowed=1;
			str+="5";
			break;
		case R.id.button_calc_b_6:
			isallowed=1;
			str+="6";
			break;
		case R.id.button_calc_b_7:
			isallowed=1;
			str+="7";
			break;
		case R.id.button_calc_b_8:
			isallowed=1;
			str+="8";
			break;
		case R.id.button_calc_b_9:
			isallowed=1;
			str+="9";
			break;
		case R.id.button_calc_b_divide:
			if(isallowed==1)
			str+="/";
			isallowed=0;
			break;
		case R.id.button_calc_b_equal:
			if(isallowed==1)
			{str=calculate();
			isallowed=1;}
			break;
		case R.id.button_calc_b_multiply:
			if(isallowed==1)
			str+="*";
			isallowed=0;
			break;
		case R.id.button_calc_b_plus:
			if(isallowed==1)
			str+="+";
			isallowed=0;
			break;
		case R.id.button_calc_b_subtract:
			if(isallowed==1)
			str+="-";
			isallowed=0;
			break;
		case R.id.button_calc_b_dot:
			str+=".";
			break;
		case R.id.button_calc_b_del:
			if(str.length()!=0)
			{
				str=str.substring(0,str.length()-1);
				if(isallowed==0)
					isallowed=1;
			}
			break;
			
		}
		res.setText(str);
		
	}

	private String calculate() 
	{
		// TODO Auto-generated method stub
		String s="";
		double values[]=new double[10];
		char ch[]=new char[10];
		int i=0;
		for(i=0;i<str.length();i++)
		{
			
			if(str.charAt(i)=='+' || str.charAt(i)=='-'|| str.charAt(i)=='*' || str.charAt(i)=='/')
			{
				values[k]=Double.parseDouble(str.substring(p,i));
				ch[k]=(char)(97+k);
				s=s+ch[k]+str.charAt(i);
				k++;
				p=i+1;
			}
		}
		values[k]=Double.parseDouble(str.substring(p));
		ch[k]=(char)(97+k);
		s=s+ch[k]+"";
		k++;
		s=postfix(s);
		s=evaluate(s,values);
		p=0;
		k=0;
		return s;
	}

	private String evaluate(String s, double[] values) 
	{
		// TODO Auto-generated method stub
		double stack[]=new double[11];
		int i,top=-1;
		for(i=0;i<s.length();i++)
		{
			if(s.charAt(i)>=97 && s.charAt(i)<=122)
				top=pushdouble(stack,values[s.charAt(i)-'a'],top);
			else
			{
				double a,b;
				a=popdouble(stack,top);
				top--;
				b=popdouble(stack,top);
				top--;
				if(s.charAt(i)=='+')
					top=pushdouble(stack,(a+b),top);
				else if(s.charAt(i)=='-')
					top=pushdouble(stack,(b-a),top);
				else if(s.charAt(i)=='*')
					top=pushdouble(stack,(a*b),top);
				else
					top=pushdouble(stack,(b/a),top);
			}
		}
		s=stack[top]+"";
		return s;
	}

	private double popdouble(double[] stack, int top) 
	{
		// TODO Auto-generated method stub
		return stack[top];
	}

	private int pushdouble(double[] stack, double d, int top) 
	{
		// TODO Auto-generated method stub
		top++;
		stack[top]=d;
		return top;
	}

	private String postfix(String s) 
	{
		// TODO Auto-generated method stub
		char stack[]=new char[11];
		int top=-1,i;
		String pof="";
		top=pushchar(stack,'#',top);
		for(i=0;i<s.length();i++)
		{
			if(s.charAt(i)>=97 && s.charAt(i)<=122)
				pof+=s.charAt(i);
			else
			{
				if(s.charAt(i)=='(')
					top=pushchar(stack,s.charAt(i),top);
				else if(s.charAt(i)==')')
				{
					while(s.charAt(i)!=')')
						{pof+=popchar(stack,top);top--;}
					popchar(stack,top);
					top--;
				}//end of else if
				else
				{
					while(prec(stack[top])>=prec(s.charAt(i)))
						{pof+=popchar(stack,top);top--;}
					top=pushchar(stack,s.charAt(i),top);
				}//end of else
			}//end of else
		}//end of for
		while(stack[top]!='#')
		{
			pof+=popchar(stack,top);
			top--;
		}
		return pof;
	}

	private int prec(char c) 
	{
		// TODO Auto-generated method stub
		int mo=0;
	    if(c=='$'||c=='^')
	    mo=5;
	    if(c=='(')
	    mo=1;
	    if(c=='*'||c=='/'||c=='%')
	    mo=4;
	    if(c=='+'||c=='-')
	    mo=3;
	    if(c=='#')
	    mo=0;
	    return mo;
	}

	private char popchar(char[] stack, int top) 
	{
		// TODO Auto-generated method stub
		return stack[top];
	}

	private int pushchar(char[] stack, char c, int top) 
	{
		// TODO Auto-generated method stub
		top++;
		stack[top]=c;
		return top;
	}

	
	
}
