package br.com.jera.botaoteca.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import br.com.jera.botaoteca.Button;

public class UIFactory {

    private static LayoutParams itemLayoutParams = new LayoutParams(
	    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    
    private static LayoutParams textLayoutParams = new LayoutParams(
	    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    
    public static LinearLayout createItem(Activity activity, Button button) {
	LinearLayout layout = new LinearLayout(activity);
	layout.setLayoutParams(itemLayoutParams);
	layout.setOrientation(LinearLayout.HORIZONTAL);
	int paddding = dp(4,activity);
	layout.setPadding(paddding, paddding, paddding, paddding);
	
	paddding = dp(2,activity);
	TextView textView = new TextView(activity);
	textView.setPadding(paddding, paddding, paddding, paddding);
	textView.setText(button.getName());
	
	button.setPadding(paddding, paddding, paddding, paddding);
	layout.addView(button, new LayoutParams(dp(20,activity),dp(20,activity)));
	layout.addView(textView, textLayoutParams);

	return layout;
    }
    
    
    public static  int dp(int pixel, Context context){
    
        int unit = TypedValue.COMPLEX_UNIT_DIP;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dipPixel = (int)TypedValue.applyDimension(unit, pixel, metrics);
        
        return dipPixel;
    }
}
