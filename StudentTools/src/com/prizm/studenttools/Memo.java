package com.prizm.studenttools;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class Memo extends ListActivity implements View.OnClickListener
{
	Button add;
	String memos[];
	ListView lv1;
	String str;
	EditText et;
	DBHandler handler;
	int pos ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo);
		initialise();
		et.setOnClickListener(this);
		add.setOnClickListener(this);
		/*
		 * Finish entering the memo when 
		 * "DONE" button is tapped 
		 * also add the memo to database 
		 */
		et.setOnEditorActionListener(new OnEditorActionListener() 
		{

			boolean handled=false;
			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) 
			{
				// TODO Auto-generated method stub
				if(actionId == EditorInfo.IME_ACTION_DONE)
				{
					str=et.getText().toString();
					addDataBase();
					handled=true;
				}
				return handled;
			}
			
		});
		printDataBase();
	}
	private void addDataBase()
	{
		// add a row to the database 
		if(str.length()>0)
		{
			//add a row only if a valid memo is given as input . 
			MemoGetter m=new MemoGetter(str);
			handler.addRow(m);
			str="";
			et.setText("");//set the edit text content back to "" after inserting it to database
			printDataBase();//print the database after update
		}
	}

	private void printDataBase() 
	{
		// TODO Auto-generated method stub
		int counter=0;
		String dbString = handler.dtabasetoString();
		int p=0,i;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n')//for each newline we create a new string 
			{
				counter++;
			}
		}
		memos=new String[counter];//we have total no of present string 
		counter--;
		p=0;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n')
			{
				memos[counter--]=dbString.substring(p, i);//store each individual memo into a string
				p=i+1;
			}
		}
		//create the list activity on the basis of the strings we have collected
		lv1.setAdapter(new ArrayAdapter<String>(Memo.this,android.R.layout.simple_list_item_1,memos));
	}

	private void initialise() 
	{
		// TODO Auto-generated method stub
		str="";
		/* Changed the id from memo_listView1 to android:id/list since we are using ListActivity class now 
		 * the definition of lv1 variable has also changed from lv1=(ListView)findViewById(R.id.memo_listView1);
		 * to lv1=(ListView)findViewById(android.R.id.list); 
		 */
		lv1=(ListView)findViewById(android.R.id.list);
		add=(Button)findViewById(R.id.memo_addMore);
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
				str=et.getText().toString();
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
	
	
	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) 
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		pos = position ; 
		// Creating instance of PopupMenu
		PopupMenu popup = new PopupMenu(Memo.this,add);
		// inflate the menu with xml file 
		popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
		// register pop up with OnMenuItemClickListener
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() 
		{
			
			@Override
			public boolean onMenuItemClick(MenuItem item) 
			{
				// TODO Auto-generated method stub
				if(item.getTitle().equals("Delete"))
				{
					// delete the current memo 
					handler.deleteRow(memos[position]);
					printDataBase();
				}
				else if(item.getTitle().equals("View / Edit"))
				{
					// start a new activity MemoView in which we edit our memo 
					Toast.makeText(Memo.this,"Position = " +pos,Toast.LENGTH_SHORT).show();
					Intent newIntent = new Intent(Memo.this,MemoView.class);
					newIntent.putExtra("memo",memos[position]);
					startActivity(newIntent);
				}
				else if(item.getTitle().equals("Priority high"))
				{
					changeTextColour("red");
				}
				else if(item.getTitle().equals("Priority medium"))
				{
					changeTextColour("green");
				}
				else if(item.getTitle().equals("Priority low"))
				{
					changeTextColour("yellow");
				}
					Toast.makeText(Memo.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
					
				return true;
			}
		});
		// show popup menu 
		popup.show();
		
		printDataBase();
	}
	
	
	protected void changeTextColour(String string) 
	{
		// TODO Auto-generated method stub
		// Here we change the text colour of our list view elements 
		
	}
	
	@Override
	protected void onNewIntent(Intent intent) 
	{
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		/*
		 * When this activity is re-launched
		 * we set the new intent and update our database 
		 * with the new memo replacing the old one  
		 */
		setIntent(intent);
		String s = getIntent().getExtras().getString("memoback");
		Toast.makeText(Memo.this,"Returned with "+pos+" value "+ s,Toast.LENGTH_SHORT).show();
		if(s.length()>0)
		updateDataBase();
		else
		printDataBase();
	}
	
	
	private void updateDataBase() 
	{
		// TODO Auto-generated method stub
		// update row of the database 
		handler.updateRow(getIntent().getExtras().getString("memoback"), memos[pos]);
		printDataBase();
	}
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) 
	{
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		/*
		 * Add a background image to the ADD button 
		 */
		
		Bitmap bitmapAdd = BitmapFactory.decodeResource(getResources(), R.drawable.add_icon);
		bitmapAdd = Bitmap.createScaledBitmap(bitmapAdd, add.getWidth(), add.getHeight(), true);
		Resources r1=getResources();
		add.setBackground(new BitmapDrawable(r1,bitmapAdd));
	}
	
	
}
