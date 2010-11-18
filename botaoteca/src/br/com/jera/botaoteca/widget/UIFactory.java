package br.com.jera.botaoteca.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RemoteViews;
import android.widget.TextView;
import br.com.jera.botaoteca.Button;
import br.com.jera.botaoteca.ButtonColor;
import br.com.jera.botaoteca.R;

public class UIFactory {

    private static LayoutParams itemLayoutParams = new LayoutParams(
	    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

    private static LayoutParams textLayoutParams = new LayoutParams(
	    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

    public static LinearLayout createItem(BotaotecaWidgetConfigure activity, Button button) {

	LinearLayout layout = new LinearLayout(activity);
	layout.setLayoutParams(itemLayoutParams);
	layout.setOrientation(LinearLayout.HORIZONTAL);
	int paddding = dp(4, activity);
	layout.setPadding(paddding, paddding, paddding, paddding);

	paddding = dp(2, activity);
	TextView textView = new TextView(activity);
	textView.setText(button.getName());

	button.setPadding(paddding, paddding, paddding, paddding);
	layout.addView(button,
		new LayoutParams(dp(20, activity), dp(20, activity)));
	layout.addView(textView, textLayoutParams);

	textView.setOnClickListener( new OnClickHandler(activity, button) );

	return layout;
    }

    public static int dp(int pixel, Context context) {

	int unit = TypedValue.COMPLEX_UNIT_DIP;
	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	int dipPixel = (int) TypedValue.applyDimension(unit, pixel, metrics);

	return dipPixel;
    }

    private static class OnClickHandler implements OnClickListener {

	private BotaotecaWidgetConfigure activity;
	Button button;
	
	public OnClickHandler(BotaotecaWidgetConfigure activity, Button button) {
	    this.activity = activity;
	    this.button = button;
	}

	@Override
	public void onClick(View v) {

	    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(activity);

	    RemoteViews views = new RemoteViews(activity.getPackageName(),getResourceId());
	    appWidgetManager.updateAppWidget(activity.widgetId, views);
	    
	    Intent resultValue = new Intent();
	    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, activity.widgetId);
	    activity.setResult(Activity.RESULT_OK, resultValue);
	    activity.finish();

	}
	
	private int getResourceId(){
	    
	    ButtonColor color = button.getColor();
	    
	    if(color.equals(ButtonColor.YELLOW)){
		return R.layout.widget_yellow;
	    }
	    
	    if(color.equals(ButtonColor.GREEN)){
		return R.layout.widget_green;
	    }
	    
	    if(color.equals(ButtonColor.ORANGE)){
		return R.layout.widget_orange;
	    }
	    
	    if(color.equals(ButtonColor.BLUE)){
		return R.layout.widget_blue;
	    }
	    
	    if(color.equals(ButtonColor.RED)){
		return R.layout.widget_red;
	    }
	    
	    return 0;
	}
    }
}
