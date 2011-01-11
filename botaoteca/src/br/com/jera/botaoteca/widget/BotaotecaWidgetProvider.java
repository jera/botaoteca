package br.com.jera.botaoteca.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import br.com.jera.botaoteca.Botao;
import android.widget.RemoteViews;
import br.com.jera.botaoteca.ButtonColor;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.database.DataHelper;

public class BotaotecaWidgetProvider extends AppWidgetProvider {

    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    public static int getResourceId(ButtonColor color) {

	if (color.equals(ButtonColor.YELLOW)) {
	    return R.layout.widget_yellow;
	}

	if (color.equals(ButtonColor.GREEN)) {
	    return R.layout.widget_green;
	}

	if (color.equals(ButtonColor.ORANGE)) {
	    return R.layout.widget_orange;
	}

	if (color.equals(ButtonColor.BLUE)) {
	    return R.layout.widget_blue;
	}

	if (color.equals(ButtonColor.RED)) {
	    return R.layout.widget_red;
	}

	return 0;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
	if (intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
	    int appWidgetId = intent.getIntExtra(
		    AppWidgetManager.EXTRA_APPWIDGET_ID,
		    AppWidgetManager.INVALID_APPWIDGET_ID);

	    SharedPreferences prefs = context.getSharedPreferences(
		    BotaotecaWidgetConfigure.PREFS_NAME, 0);
	    String fileName = prefs.getString(
		    BotaotecaWidgetConfigure.WIDGET_FILE_NAME + appWidgetId,
		    null);
	    Log.i("RECIEVER", "ID" + appWidgetId + " FILE: " + fileName);
	    DataHelper helper = new DataHelper(context);
	    Botao button = helper.findButton(fileName);
	    try {
		button.getSound().play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	super.onReceive(context, intent);
    }
    
    public void updateWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	 SharedPreferences prefs = context.getSharedPreferences(
		    BotaotecaWidgetConfigure.PREFS_NAME, 0);
	for (int i : appWidgetIds) {
	    ButtonColor color = ButtonColor.valueOf(prefs.getString(BotaotecaWidgetConfigure.WIDGET_FILE_NAME + i,null));
	    RemoteViews views = new RemoteViews(context.getPackageName(),BotaotecaWidgetProvider.getResourceId(color));
	    String text = prefs.getString(BotaotecaWidgetConfigure.WIDGET_NAME + i,null);
	    views.setTextViewText(R.id.widget_title, text);
	    appWidgetManager.updateAppWidget(i, views);
	}
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    
    @Override
    public void onEnabled(Context context) {
	int[] allids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, BotaotecaWidgetProvider.class));
	updateWidget(context,AppWidgetManager.getInstance(context),allids);
        super.onEnabled(context);
    }
    
    @Override
    public void onDisabled(Context context) {
	int[] allids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, BotaotecaWidgetProvider.class));
	updateWidget(context,AppWidgetManager.getInstance(context),allids);
        super.onDisabled(context);
    }
}
