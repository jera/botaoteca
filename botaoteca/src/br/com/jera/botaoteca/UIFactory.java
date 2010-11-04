package br.com.jera.botaoteca;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UIFactory {
		
	private static TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
	private static LinearLayout.LayoutParams buttonAreaLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	
	
	public static List<TableRow> createRows(Activity activity, List<Button> buttons){
		List<TableRow> rows  = new ArrayList<TableRow>(buttons.size());
		TableRow row = new TableRow(activity);
		row.setLayoutParams(rowLayoutParams);
		
		for (Button button : buttons) {
			row.addView(createButtonArea(activity, button),rowLayoutParams);
		}
		
		rows.add(row);
		return rows;
	}
	
	private static LinearLayout createButtonArea(Activity activity, Button button){
		LinearLayout layout = new LinearLayout(activity);
		layout.setLayoutParams(buttonAreaLayoutParams);

		layout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(button,buttonAreaLayoutParams);
		TextView text = new TextView(activity);
		text.setText("Nome do botão");
		layout.addView(text,buttonAreaLayoutParams);
		
		return layout;
	}
}
