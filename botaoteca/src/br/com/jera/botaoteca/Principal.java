package br.com.jera.botaoteca;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
	
	List<Button> buttons = dataHelper.createButtonsFromDatabase();
	TableLayout l = (TableLayout) findViewById(R.id.area);
	
	List<TableRow> rows = UIFactory.createRows(this, buttons);
	for (TableRow row : rows) {
	    l.addView(row, new LayoutParams(LayoutParams.WRAP_CONTENT,
		    LayoutParams.WRAP_CONTENT));

	}
    }

}