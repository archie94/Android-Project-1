package com.prizm.studenttools;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList1 extends BaseAdapter
{
	private final Context context;
	private final String memoList[];
	private final String priorityList[];
	private final String checkList[];
	private CustomListInterface inter;
	private CustomListInterface interCheck;

	public CustomList1(Context context, String[] nameList, String[] pList, String[] cList)
	{
		this.context=context;
		this.memoList = nameList;
		this.priorityList=pList;
		this.checkList=cList;
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
		CheckBox checkBox;
		ImageView imageView;
		public ViewHolder(View v)
		{
			checkBox = (CheckBox)v.findViewById(R.id.custom_list_memo_checkBox);
			textView = (TextView)v.findViewById(R.id.custom_list_memo_textView);
			imageView = (ImageView)v.findViewById(R.id.custom_list_memo_imageView);
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
		
		
		/*
		 *  Priority 1 - normal - black
		 *  Priority 0- low - green
		 *  Priority 2 - high - red 
		 *  Checked - 0 - unchecked
		 *  Checked - 1 -checked 
		 */
		
		
		holder.textView.setText(memoList[position]);
		
		/*
		 * Set text colour according to priorities 
		 */
		holder.textView.setTextColor(Color.BLACK);
		if(priorityList[position].equals("0"))
			holder.textView.setTextColor(Color.GREEN);
		else if(priorityList[position].equals("2"))
			holder.textView.setTextColor(Color.RED);
		
		/*
		 * set CheckBox accordingly 
		 */
		if(checkList[position].equals("0"))
		{
			holder.checkBox.setChecked(false);
			holder.textView.setPaintFlags(holder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
		}
		else if(checkList[position].equals("1"))
		{
			holder.checkBox.setChecked(true);
			// strike off memo for completed task and set priority to normal 
			holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			holder.textView.setTextColor(Color.BLACK);
		}
		
		
		// listener for memo text 
		rowView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(inter!=null)
				{
					inter.onClick(position,arg0);				
				}
			}
		});
		
		
		
		// listener for checkbox 
		holder.checkBox.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(interCheck != null)
            	{
            		interCheck.onCustomCheck(position);
            	}
			}
		});
		
		return rowView;
	}
	
	public void setInter(CustomListInterface inter)
	{
		this.inter=inter; 
	}
	
	public void setInterCheck(CustomListInterface interCheck)
	{
		this.interCheck=interCheck;
	}
	
	public interface CustomListInterface
	{
		public void onClick(int position,View view);
		public void onCustomCheck(int position);
	}
	
	
}
