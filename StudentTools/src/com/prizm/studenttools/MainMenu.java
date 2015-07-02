package com.prizm.studenttools;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity
{
	String subclass[]={"Calender","Equations","Determinant","Calculator","Gpa","Memo"};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		try
		{
			Class newClass = Class.forName("com.prizm.studenttools."+subclass[position]);
			Intent newIntent = new Intent(MainMenu.this,newClass);
			startActivity(newIntent);
			
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
		setListAdapter(new ArrayAdapter<String>(MainMenu.this,android.R.layout.simple_list_item_1,subclass));
	}
	
}
