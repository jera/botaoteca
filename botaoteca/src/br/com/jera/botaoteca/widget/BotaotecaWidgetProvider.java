package br.com.jera.botaoteca.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.RemoteViews;
import br.com.jera.botaoteca.AppButton;
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
		Log.i("WIDGET", "ON_RECIEVE");
		if (intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
			int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

			SharedPreferences prefs = context.getSharedPreferences(BotaotecaWidgetConfigure.PREFS_NAME, 0);
			String fileName = prefs.getString(BotaotecaWidgetConfigure.WIDGET_FILE_NAME + appWidgetId, null);
			Log.i("RECIEVER", "ID" + appWidgetId + " FILE: " + fileName);
			AppButton button = DataHelper.getDataHelper(context).findButton(fileName);
			try {
				button.getSound().play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onReceive(context, intent);
	}

	public void onEnabled(Context context) {
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(new ComponentName("br.com.jera.botaoteca", ".widget.BotaotecaWidgetConfigure"),
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		SharedPreferences prefs = context.getSharedPreferences(BotaotecaWidgetConfigure.PREFS_NAME, 0);
		for (int id : appWidgetIds) {
			if (prefs.contains(BotaotecaWidgetConfigure.WIDGET_NAME + id)) {
				String color = prefs.getString(BotaotecaWidgetConfigure.WIDGET_COLOR + id, null);
				RemoteViews views = new RemoteViews(context.getPackageName(), getResourceId(ButtonColor.valueOf(color)));
				views.setTextViewText(R.id.widget_title, prefs.getString(BotaotecaWidgetConfigure.WIDGET_NAME + id, null));

				Intent intent = new Intent(context, BotaotecaWidgetProvider.class);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
				intent.setAction(BotaotecaWidgetProvider.ACTION_WIDGET_RECEIVER);

				PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				views.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent);
				appWidgetManager.updateAppWidget(id, views);
			}
		}
	}

	@Override
	public void onDisabled(Context context) {
		Log.i("WIDGET", "ON_DISABLED");
		super.onDisabled(context);
	}
}
