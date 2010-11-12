package br.com.jera.botaoteca;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UIFactory {

    private static TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(
	    TableRow.LayoutParams.WRAP_CONTENT,
	    TableRow.LayoutParams.WRAP_CONTENT);
    private static LinearLayout.LayoutParams buttonAreaLayoutParams = new LinearLayout.LayoutParams(
	    LinearLayout.LayoutParams.WRAP_CONTENT,
	    LinearLayout.LayoutParams.WRAP_CONTENT);

    private static Dialog popup;

    public static List<TableRow> createRows(Activity activity, List<Button> buttons) {

	List<TableRow> rows = new ArrayList<TableRow>(buttons.size());
	TableRow row = new TableRow(activity);
	row.setLayoutParams(rowLayoutParams);

	for (int i = 0; i < buttons.size(); i++) {

	    // add the long click handler
	    buttons.get(i).setOnLongClickListener(OnLongClickHandler.getHandler(activity));
	    if ((i + 1) % 3 == 0) {
		row.addView(createButtonArea(activity, buttons.get(i)),rowLayoutParams);
		rows.add(row);
		row = new TableRow(activity);
	    } else {
		row.addView(createButtonArea(activity, buttons.get(i)),rowLayoutParams);
	    }

	}
	rows.add(row);

	return rows;
    }

    private static LinearLayout createButtonArea(Activity activity,Button button) {

	Display display = activity.getWindowManager().getDefaultDisplay();
	int width = display.getWidth();

	LinearLayout layout = new LinearLayout(activity);
	layout.setLayoutParams(buttonAreaLayoutParams);
	layout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
	layout.setOrientation(LinearLayout.VERTICAL);
	layout.setMinimumWidth(width / 3);
	layout.setPadding(2, 2, 2, 2);

	TextView text = new TextView(activity);
	text.setText(button.getName());
	text.setTextSize(12);
	text.setTypeface(Typeface.DEFAULT_BOLD);
	layout.addView(button, buttonAreaLayoutParams);
	layout.addView(text, buttonAreaLayoutParams);

	return layout;
    }

    private static class OnLongClickHandler implements OnLongClickListener {

	private Activity activity;
	private static OnLongClickHandler instance;

	private OnLongClickHandler(Activity activity) {
	    this.activity = activity;

	    if (popup == null) {
		popup = new Dialog(activity);
		popup.setContentView(R.layout.quick_menu);
		popup.setCanceledOnTouchOutside(true);
	    }

	}

	public static OnLongClickListener getHandler(Activity activity) {
	    if (instance == null) {
		instance = new OnLongClickHandler(activity);
	    }

	    return instance;
	}

	private void createButtonArea(Button button) {
	    
	   Button newButton  = new Button(button.getColor(), popup.getContext(), button.getSound());
	    
	   LinearLayout layout = (LinearLayout) popup.findViewById(R.id.buttonArea);
	   
	   layout.removeAllViews();
	   layout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
	   layout.setOrientation(LinearLayout.VERTICAL);

	   TextView text = new TextView(activity);
	   text.setText(button.getName());
	   text.setTextSize(12);
	   text.setTypeface(Typeface.DEFAULT_BOLD);
	   layout.addView(newButton);
	   layout.addView(text, buttonAreaLayoutParams);
	    
	}

	@Override
	public boolean onLongClick(View v) {
	    createButtonArea((Button)v);
	    popup.show();
	    return false;
	}
    }
}
