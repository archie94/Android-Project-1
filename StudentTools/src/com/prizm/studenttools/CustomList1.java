package com.prizm.studenttools;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomList1 extends BaseAdapter
{
	private final Context context;
	private final String memoList[];
	private CustomListInterface inter;

	public CustomList1(Context context, String[] nameList)
	{
		this.context=context;
		this.memoList = nameList;
	}
	
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return memoList.length;
	}
	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return position ;
	}
	@Override
	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return position;
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
	public View getView(final int position, View convertView, ViewGroup parent) 
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
		holder.textView.setText(memoList[position]);
		rowView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(inter!=null)
				{
					inter.onClick(position);
				}
			}
		});
		return rowView;
	}
	
	public void setInter(CustomListInterface inter)
	{
		this.inter=inter; 
	}
	
	public interface CustomListInterface
	{
		public void onClick(int position);
	}
	
}
