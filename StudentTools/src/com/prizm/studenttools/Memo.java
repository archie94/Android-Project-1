package com.prizm.studenttools;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

//import com.prizm.studenttools.CustomList1.CustomListCheckboxInterface;
import com.prizm.studenttools.CustomList1.CustomListInterface;


public class Memo extends ListActivity implements View.OnClickListener, CustomListInterface 
{
	Button add;
	String memos[];
	String priorities[];
	String checks[];
	ListView lv1;
	String str;
	EditText et;
	DBHandler handler;
	int pos ; 
	ProgressBar progressBar;
	TextView progressIndication;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
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
				// Defines what happens when we click on done key 
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
	
	private void initialise() 
	{
		str="";
		/* Changed the id from memo_listView1 to android:id/list since we are using ListActivity class now 
		 * the definition of lv1 variable has also changed from lv1=(ListView)findViewById(R.id.memo_listView1);
		 * to lv1=(ListView)findViewById(android.R.id.list); 
		 */
		lv1=(ListView)findViewById(android.R.id.list);
		//lv1=(ListView)findViewById(R.id.customList);
		add=(Button)findViewById(R.id.memo_addMore);
		et=(EditText)findViewById(R.id.memo_editText1);
		handler=new DBHandler(this,null,null,1);
		progressBar = (ProgressBar)findViewById(R.id.memo_progressBar1);
		progressIndication = (TextView)findViewById(R.id.memo_progressIndication);
	}
	
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) 
	{
		super.onWindowFocusChanged(hasFocus);
		/*
		 * Add a background image to the ADD button 
		 */
		
		Bitmap bitmapAdd = BitmapFactory.decodeResource(getResources(), R.drawable.add_icon);
		bitmapAdd = Bitmap.createScaledBitmap(bitmapAdd, add.getWidth(), add.getHeight(), true);
		Resources r1=getResources();
		add.setBackground(new BitmapDrawable(r1,bitmapAdd));
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
			updateWidget(); // update widget after an addition to datatbase 
		}
	}

	private void updateWidget() 
	{
		// TODO Auto-generated method stub
		// call the widget to force refresh it 
		AppWidgetManager man = AppWidgetManager.getInstance(this);
	    int[] ids = man.getAppWidgetIds(new ComponentName(this,WidgetMemo.class));
		Intent updateWidget = new Intent();
		updateWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		updateWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
		sendBroadcast(updateWidget);
		
	}
	
	
	private void printDataBase() 
	{
		// TODO Auto-generated method stub
		int counter=0;
		int count=0;
		String dbString = handler.dtabasetoString();
		String dbStringP = handler.dtabasetoStringPriority();
		String dbStringC = handler.dtabasetoStringChecked();
		int p=0,i;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n')//for each newline we create a new string 
			{
				counter++;
			}
		}
		memos=new String[counter];//we have total no of present string 
		priorities=new String[counter];
		checks=new String[counter];
		count=counter;
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
		counter=count;
		counter--;
		p=0;
		for(i=0;i<dbStringP.length();i++)
		{
			if(dbStringP.charAt(i)=='\n')
			{
				priorities[counter--]=dbStringP.substring(p, i);//store each individual memo into a string
				p=i+1;
			}
		}
		
		counter=count;
		counter--;
		p=0;
		int count2=0;
		for(i=0;i<dbStringC.length();i++)
		{
			if(dbStringC.charAt(i)=='\n')
			{
				checks[counter--]=dbStringC.substring(p, i);//store each individual memo into a string
				p=i+1;
				if(checks[counter+1].equals("1"))
					count2++;
			}
		}
		
		/*
		 *  Priority 1 - normal - black
		 *  Priority 0- low - green
		 *  Priority 2 - high - red 
		 *  Checked - 0 - unchecked
		 *  Checked - 1 -checked 
		 */
		
		
		
		//create the list activity on the basis of the strings we have collected
		//lv1.setAdapter(new ArrayAdapter<String>(Memo.this,android.R.layout.simple_list_item_1,memos));
		CustomList1 customList = new CustomList1(this,memos,priorities,checks);
		customList.setInter(this);
		customList.setInterCheck(this);
		lv1.setAdapter(customList);
		
		//lv1.setAdapter(new CustomList1(this,memos));
		progressIndication.setText(count2+"/"+count+" Completed");
		progressBar.setProgress(count2*100/count);
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
	
	
	
	
	
	protected void changeTextColour(String m,String string) 
	{
		// TODO Auto-generated method stub
		// Here we change the text colour of our list view elements 
		if(string.equals("red"))
			handler.updateRowPriority(m, 2);
		else if(string.equals("green"))
			handler.updateRowPriority(m, 0);
		else 
			handler.updateRowPriority(m, 1);
		printDataBase();
		updateWidget();
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
		String s="";
		if(getIntent().getExtras()!=null)
		s = getIntent().getExtras().getString("memoback");
		
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
		updateWidget(); // update widget after updating a memo  
	}
	
	
	
	
	
	
	
	@Override
	public void onClick(final int position,View view) 
	{
		// This method describes what options to display when a memo is clicked 
		
		
		// Change the background of the item which is pressed 
		//view.setSelected(true);
		view.setPressed(true);
		
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
					updateWidget(); // update widget after a deletion 
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
					changeTextColour(memos[position],"red");
				}
				else if(item.getTitle().equals("Priority medium"))
				{
					changeTextColour(memos[position],"black");
				}
				else if(item.getTitle().equals("Priority low"))
				{
					changeTextColour(memos[position],"green");
				}
					Toast.makeText(Memo.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
					
				return true;
			}
		});
		// show popup menu 
		popup.show();
		
		printDataBase();
	}

	@Override
	public void onCustomCheck(int position) 
	{
		// This method will identify the checkbox corresponding to memo is checked or not 
		if(checks[position].equals("0"))
			handler.updateRowChecked(memos[position], 1);
		else if(checks[position].equals("1"))
			handler.updateRowChecked(memos[position], 0);
		printDataBase();
	}
	
	
	
}
