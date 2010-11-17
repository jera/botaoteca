package br.com.jera.botaoteca.widget;

import java.util.List;

import br.com.jera.botaoteca.Button;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.database.DataHelper;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BotaotecaWidgetConfigure extends Activity {

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
	
	setContentView(R.layout.widiget_dialog);
	
	DataHelper helper = new DataHelper(this);
	
	// encontra o id do widget no intent.
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
        
        List<Button> buttons = helper.createButtonsFromDatabase();
        LinearLayout layout = (LinearLayout) findViewById(R.id.listArea);
        
        for(Button button: buttons){
            layout.addView(UIFactory.createItem(this, button));
        }
        

    }

}
