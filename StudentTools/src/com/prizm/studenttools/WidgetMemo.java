package com.prizm.studenttools;



import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class WidgetMemo extends AppWidgetProvider
{
	DBHandler handler;
	TextView tv1,tv2,tv3;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) 
	{
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		handler = new DBHandler(context,null,null,1);
		int counter=0;
		String dbString = handler.dtabasetoString();
		String dbStringP = handler.dtabasetoStringPriority();
		int p=0,i;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n')//for each newline we create a new string 
			{
				counter++;
			}
		}
		
		String memo[]=new String[counter];
		String priorities[] = new String[counter];
		int num = counter;
		counter--;
		p=0;
		for(i=0;i<dbString.length();i++)
		{
			if(dbString.charAt(i)=='\n' && counter>=0)
			{
				memo[counter--]=dbString.substring(p, i);//store each individual memo into a string
				p=i+1;
			}
		}
		
		counter = num;
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
		
		final int n = appWidgetIds.length ;
		for(i=0;i<n;i++)
		{
			int appWidgetId = appWidgetIds[i];
			RemoteViews v =new RemoteViews(context.getPackageName(), R.layout.widget_memo_layout);
			/*
			 * Set the  3 latest memos in our widget view 
			 * If total no of memos is less than 3 then set 
			 * the other text views to a null string 
			 */
			if(num>=3)
			{
				v.setTextViewText(R.id.widget_memo_layout_firstTV, memo[0]);
				if(priorities[0].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.GREEN);
				else if(priorities[0].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.RED);
				
				v.setTextViewText(R.id.widget_memo_layout_secondTV, memo[1]);
				if(priorities[1].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_secondTV, Color.GREEN);
				else if(priorities[1].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_secondTV, Color.RED);
				
				v.setTextViewText(R.id.widget_memo_layout_thirdTV, memo[2]);
				if(priorities[2].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_thirdTV, Color.GREEN);
				else if(priorities[2].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_thirdTV, Color.RED);
				
				appWidgetManager.updateAppWidget(appWidgetId, v);
			}
			else if(num==2)
			{
				v.setTextViewText(R.id.widget_memo_layout_firstTV, memo[0]);
				if(priorities[0].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.GREEN);
				else if(priorities[0].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.RED);
				
				v.setTextViewText(R.id.widget_memo_layout_secondTV, memo[1]);
				if(priorities[1].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_secondTV, Color.GREEN);
				else if(priorities[1].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_secondTV, Color.RED);
				
				v.setTextViewText(R.id.widget_memo_layout_thirdTV, "");
				appWidgetManager.updateAppWidget(appWidgetId, v);
			}
			else if(num==1)
			{
				v.setTextViewText(R.id.widget_memo_layout_firstTV, memo[0]);
				if(priorities[0].equals("0"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.GREEN);
				else if(priorities[0].equals("2"))
					v.setTextColor(R.id.widget_memo_layout_firstTV, Color.RED);
				
				v.setTextViewText(R.id.widget_memo_layout_secondTV, "");
				v.setTextViewText(R.id.widget_memo_layout_thirdTV, "");
				appWidgetManager.updateAppWidget(appWidgetId, v);
			}
			else if(num==0)
			{
				v.setTextViewText(R.id.widget_memo_layout_firstTV, "");
				v.setTextViewText(R.id.widget_memo_layout_secondTV, "");
				v.setTextViewText(R.id.widget_memo_layout_thirdTV, "");
				appWidgetManager.updateAppWidget(appWidgetId, v);
			}
			
			// On clicking the widget we want to launch Memo activity 
			Intent launchApp = new Intent(context, Memo.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchApp, 0);
			v.setOnClickPendingIntent(R.id.widget_memo_linear_layout, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, v);
		}
		
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) 
	{
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Widget Removed", Toast.LENGTH_SHORT).show();
	}
	
	

}
