package br.com.jera.botaoteca.widget;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import br.com.jera.botaoteca.Button;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.database.DataHelper;

public class BotaotecaWidgetConfigure extends Activity{

    static final String TAG = "BotaotecaWidgetConfigure";

    public static final String PREFS_NAME = "br.com.jera.botaoteca.widget.BotaotecaWidgetProvider";
    public static final String WIDGET_FILE_NAME = "WIDGET_FILENAME_";

    int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public BotaotecaWidgetConfigure() {
	super();
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
	
	// Se o intent não possui o id do widget termina a atividade.
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
        
        List<Button> buttons = helper.createButtonsFromDatabase();
        LinearLayout layout = (LinearLayout) findViewById(R.id.listArea);
        
        for(Button button: buttons){
            layout.addView(UIFactory.createItem(this, button));
        }
        

    }
    
    private void savePreference(String prefix, String value){
	Context context = BotaotecaWidgetConfigure.this;
	SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(prefix + widgetId, value);
        prefs.commit();
    }
    
    
    public class OnClickHandler implements OnClickListener {

	private BotaotecaWidgetConfigure activity;
	Button button;
	
	public OnClickHandler( Button button) {
	    this.activity = BotaotecaWidgetConfigure.this;
	    this.button = button;
	}

	@Override
	public void onClick(View v) {

	    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(activity);
	    RemoteViews views = new RemoteViews(activity.getPackageName(),BotaotecaWidgetProvider.getResourceId(button.getColor()));
	    views.setTextViewText(R.id.widget_title, button.getName());
	    
	    Intent intent = new Intent(activity, BotaotecaWidgetProvider.class);
	   
	    intent.setAction(BotaotecaWidgetProvider.ACTION_WIDGET_RECEIVER);
	    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, activity.widgetId);
	    PendingIntent actionPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
	    views.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent);
	   
	    appWidgetManager.updateAppWidget(activity.widgetId, views);
	    activity.savePreference(WIDGET_FILE_NAME,button.getSound().getFileName());
	    
	   
	    activity.setResult(Activity.RESULT_OK, intent);
	    activity.finish();

	}
	
    }
    
    
    
    
    
}
