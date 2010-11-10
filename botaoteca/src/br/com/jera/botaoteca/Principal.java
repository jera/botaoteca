package br.com.jera.botaoteca;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import br.com.jera.botaoteca.database.DataHelper;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	DataHelper dataHelper = new DataHelper(getApplicationContext());
	setContentView(R.layout.main);

	Display display = getWindowManager().getDefaultDisplay();
	int width = display.getWidth();

	// Buttons
	android.widget.Button buttonSearch = (android.widget.Button) findViewById(R.id.buttonSearch);
	android.widget.Button buttonFavorite = (android.widget.Button) findViewById(R.id.buttonFavorites);
	android.widget.Button buttonAll = (android.widget.Button) findViewById(R.id.buttonAll);
	buttonSearch.setWidth(width / 3);
	buttonFavorite.setWidth(width / 3);
	buttonAll.setWidth(width / 3);

	List<Button> buttons = dataHelper.createButtonsFromDatabase();
	TableLayout l = (TableLayout) findViewById(R.id.area);

	List<TableRow> rows = UIFactory.createRows(this, buttons);
	for (TableRow row : rows) {
	    l.addView(row, new LayoutParams(LayoutParams.FILL_PARENT,
		    LayoutParams.WRAP_CONTENT));
 
	}

    }

}