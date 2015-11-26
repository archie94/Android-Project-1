package com.prizm.studenttools;

public class MemoGetter 
{
	private int _id;
	private String _todo;
	private int _priority;
	private int _checked;
	
	
	/*
	 *  Priority 1 - normal - black
	 *  Priority 0- low - green
	 *  Priority 2 - high - red 
	 *  Checked - 0 - unchecked
	 *  Checked - 1 -checked 
	 */
	public MemoGetter()
	{
		
	}
	public MemoGetter(String _todo)
	{
		this._todo=_todo;
		this._priority=1;
		this._checked=0;
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
	public int get_priority()
	{
		return _priority;
	}
	public int get_checked()
	{
		return _checked;
	}
}
