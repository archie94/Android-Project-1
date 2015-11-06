package com.prizm.studenttools;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList1 extends ArrayAdapter<String>
{
	private final Context context;
	private final String memo[];

	public CustomList1(Context context, int resource, String[] memo) 
	{
		super(context, resource, memo);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.memo=memo;
	}

	class ViewHolder 
	{
		TextView textView;
		public ViewHolder(View v)
		{
			textView = (TextView)v.findViewById(R.id.custom_list_memo_textView);
			
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		View rowView=convertView;
		ViewHolder holder;
		if(rowView==null)
		{
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView=inflater.inflate(R.layout.custom_list1_row,parent,false );
			holder = new ViewHolder(rowView);
			rowView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)rowView.getTag();
		}
		return rowView;
	}
	
	
}
