package com.prizm.studenttools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;



public class DBHandler extends SQLiteOpenHelper
{
	private static final int databaseversion=1;
	private static final String filename="STT.db";
	public static final String table1="memo";
	public static final String table1_col_id="_id";
	public static final String table1_col_todo="todo";
	public static final String table1_col_priority="priority";
	public static final String table1_col_checked="checked";
	public DBHandler(Context context, String name, CursorFactory factory,int version) 
	{
		super(context, filename, factory, databaseversion);
		// TODO Auto-generated constructor stub
	} 

	@Override
	public void onCreate(SQLiteDatabase arg0) 
	{
		// TODO Auto-generated method stub
		//onUpgrade(arg0,1,1);
		String query = "CREATE TABLE "+table1+" ("+table1_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+table1_col_todo+" TEXT, "+table1_col_priority+" INTEGER, "+table1_col_checked+" INTEGER );";
		arg0.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
	{
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS "+table1);//check this too
		onCreate(arg0);
	}
	
	public void addRow(MemoGetter m)
	{
		ContentValues value=new ContentValues();
		value.put(table1_col_todo, m.get_todo());
		value.put(table1_col_priority, m.get_priority());
		value.put(table1_col_checked, m.get_checked());
		SQLiteDatabase db = getWritableDatabase();
		db.insert(table1, null, value);
		db.close();
	}
	
	
	public void deleteRow(String item)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DELETE FROM "+table1+" WHERE "+table1_col_todo+"="+"\""+item+"\";");
		db.close();//check 
	}
	
	
	public void updateRow(String item1,String item2)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("UPDATE "+table1+" SET "+table1_col_todo+" = \'"+item1+"\'"+" WHERE "+table1_col_todo+"="+"\""+item2+"\";");
		db.close();
	}
	public void updateRowPriority(String item,int newPriority)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("UPDATE "+table1+" SET "+table1_col_priority+" = \'"+newPriority+"\'"+" WHERE "+table1_col_todo+"="+"\""+item+"\";");
		db.close();
	}
	public void updateRowChecked(String item,int checked)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("UPDATE "+table1+" SET "+table1_col_checked+" = \'"+checked+"\'"+" WHERE "+table1_col_todo+"="+"\""+item+"\";");
		db.close();
	}
	
	
	
	public String dtabasetoString()
	{
		SQLiteDatabase db = getWritableDatabase();
		String dbString="";
		String query="SELECT * FROM "+table1+" WHERE 1";//check 
		Cursor c=db.rawQuery(query, null);
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			if(c.getString(c.getColumnIndex("todo"))!=null)
			{
				dbString+=c.getString(c.getColumnIndex("todo"));
				dbString+="\n";
			}
			c.moveToNext();
		}
		db.close();
		return dbString;
	}
	
	
	public String dtabasetoStringPriority()
	{
		SQLiteDatabase db = getWritableDatabase();
		String dbString="";
		String query="SELECT * FROM "+table1+" WHERE 1";//check 
		Cursor c=db.rawQuery(query, null);
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			if(c.getString(c.getColumnIndex("priority"))!=null)
			{
				dbString+=c.getString(c.getColumnIndex("priority"));
				dbString+="\n";
			}
			c.moveToNext();
		}
		db.close();
		return dbString;
	}
	
	public String dtabasetoStringChecked()
	{
		SQLiteDatabase db = getWritableDatabase();
		String dbString="";
		String query="SELECT * FROM "+table1+" WHERE 1";//check 
		Cursor c=db.rawQuery(query, null);
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			if(c.getString(c.getColumnIndex("checked"))!=null)
			{
				dbString+=c.getString(c.getColumnIndex("checked"));
				dbString+="\n";
			}
			c.moveToNext();
		}
		db.close();
		return dbString;
	}
	
	
}
