package com.prizm.studenttools;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Equations extends ListActivity
{
	String classes[] = {"TwoVariable","ThreeVariable"};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		try
		{
			Class startClass = Class.forName("com.prizm.studenttools."+classes[position]);
			Intent startIntent = new Intent(Equations.this,startClass);
			startActivity(startIntent);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Equations.this,android.R.layout.simple_list_item_1,classes));
		
	}
	
	
	
}
