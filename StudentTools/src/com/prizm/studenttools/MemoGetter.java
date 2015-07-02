package com.prizm.studenttools;

public class MemoGetter 
{
	private int _id;
	private String _todo;
	
	public MemoGetter()
	{
		
	}
	public MemoGetter(String _todo)
	{
		this._todo=_todo;
	}
	public void set_id(int _id)
	{
		this._id=_id;
	}
	public void set_todo(String _todo)
	{
		this._todo=_todo;
	}
	public int get_id()
	{
		return _id;
	}
	public String get_todo()
	{
		return _todo;
	}
}
