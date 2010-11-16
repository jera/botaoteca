package br.com.jera.botaoteca.widget;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class BotaotecaWidgetConfigure extends ListActivity {

    static final String TAG = "BotaotecaWidgetConfigure";

    private static final String PREFS_NAME = "br.com.jera.botaoteca.widget.BotaotecaWidgetProvider";

    int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public BotaotecaWidgetConfigure() {
	super();
    }
    
    private void savePreference(String prefix, String value){
	Context context = BotaotecaWidgetConfigure.this;
	SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(prefix + widgetId, value);
        prefs.commit();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// encontr o id do widget no intent.
	Intent intent = getIntent();
	Bundle extras = intent.getExtras();
	if (extras != null) {
	    widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
		    AppWidgetManager.INVALID_APPWIDGET_ID);
	}
	
	// Seo intent não possui o id do widget termina a atividade.
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

    }

}
