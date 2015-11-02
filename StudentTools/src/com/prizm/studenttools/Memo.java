package com.prizm.studenttools;

//import android.app.Activity;
import android.app.ListActivity;
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
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;


public class Memo extends ListActivity implements View.OnClickListener
{
	Button add;
	String memos[];
	ListView lv1;
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
		if(str!="")
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
		counter=0;
		p=0;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n')
			{
				memos[counter++]=dbString.substring(p, i);//store each individual memo into a string
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
		//lv1=(ListView)findViewById(R.id.memo_listView1);
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
		// Creating instance of PopupMenu
		PopupMenu popup = new PopupMenu(Memo.this,add);
		// inflate the menu with xml file 
		popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
		// register popup with OnMenuItemClickListener
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() 
		{
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if(item.getTitle().equals("Delete"))
					handler.deleteRow(memos[position]);
					Toast.makeText(Memo.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
					
				return true;
			}
		});
		// show popup menu 
		popup.show();
		
		printDataBase();
	}

	
}
