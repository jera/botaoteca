package br.com.jera.botaoteca;

import java.util.List;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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



	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.layout.menu, menu);
	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	 switch (item.getItemId()) {
	    case R.id.quit:
	        finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
    }
    

}