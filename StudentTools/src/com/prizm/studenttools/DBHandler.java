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
	private static final String filename="ST.db";
	public static final String table1="memo";
	public static final String table1_col_id="_id";
	public static final String table1_col_todo="todo";
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
		String query = "CREATE TABLE "+table1+" ("+table1_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+table1_col_todo+" TEXT );";
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
}
