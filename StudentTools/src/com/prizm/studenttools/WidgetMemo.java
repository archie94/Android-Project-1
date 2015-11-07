package com.prizm.studenttools;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.Toast;

public class WidgetMemo extends AppWidgetProvider
{
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) 
	{
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) 
	{
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Wiudget Removed", Toast.LENGTH_SHORT).show();
	}

}
