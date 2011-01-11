package br.com.jera.botaoteca;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import br.com.jera.botaoteca.database.DataHelper;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	DataHelper dataHelper = new DataHelper(getApplicationContext());
	setContentView(R.layout.main);

	List<Botao> sounds = dataHelper.createButtonsFromDatabase();
	GridView gridView = (GridView) findViewById(R.id.gridview);
	gridView.setAdapter(new BotaotecaListAdapter(this, sounds));
    }

}