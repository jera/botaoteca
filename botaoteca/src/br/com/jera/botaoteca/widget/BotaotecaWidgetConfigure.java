package br.com.jera.botaoteca.widget;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import br.com.jera.botaoteca.Botao;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.database.DataHelper;

public class BotaotecaWidgetConfigure extends ListActivity{

    static final String TAG = "BotaotecaWidgetConfigure";

    public static final String PREFS_NAME = "br.com.jera.botaoteca.widget.BotaotecaWidgetProvider";
    public static final String WIDGET_FILE_NAME = "WIDGET_FILENAME_";
    private List<Botao> buttons;
    int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public BotaotecaWidgetConfigure() {
	super();
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Botao button = buttons.get(position);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews views = new RemoteViews(this.getPackageName(),BotaotecaWidgetProvider.getResourceId(button.getColor()));
        views.setTextViewText(R.id.widget_title, button.getName());
	    
        Intent intent = new Intent(this, BotaotecaWidgetProvider.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.widgetId);
        intent.setAction(BotaotecaWidgetProvider.ACTION_WIDGET_RECEIVER);
        
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(this, this.widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent);
	   
        appWidgetManager.updateAppWidget(this.widgetId, views);
        this.savePreference(WIDGET_FILE_NAME,button.getSound().getFileName());
	    
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.widget_list_layout);
	setTitle("Selecione um som");
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
        
        buttons = helper.createButtonsFromDatabase();
        ListAdapter adapter = new WidgetListAdapter(buttons, this);
        getListView().setAdapter(adapter);
        
    }
    
    
    private void savePreference(String prefix, String value){
	Context context = BotaotecaWidgetConfigure.this;
	SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(prefix + widgetId, value);
        prefs.commit();
    }
    
}
