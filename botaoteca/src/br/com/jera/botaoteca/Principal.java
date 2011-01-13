package br.com.jera.botaoteca;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import br.com.jera.botaoteca.database.DataHelper;

public class Principal extends Activity {

    private DataHelper dataHelper;
    private List<Botao> botoes;
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.main);
	dataHelper = new DataHelper(getApplicationContext());
	if (!handleIntent(getIntent())) {

	    botoes = dataHelper.createButtonsFromDatabase();
	    gridView = (GridView) findViewById(R.id.gridview);
	    gridView.setAdapter(new BotaotecaListAdapter(this, botoes));
	    
	    Button search = (Button)findViewById(R.id.buttonSearch);
	    search.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    	
	            onSearchRequested();
	    	
	        }
	    });
	    
	    Button all = (Button)findViewById(R.id.buttonAll);
	    all.setOnClickListener(new View.OnClickListener() {
	        
	        @Override
	        public void onClick(View v) {
	            botoes = dataHelper.createButtonsFromDatabase();
		    gridView = (GridView) findViewById(R.id.gridview);
		    gridView.setAdapter(new BotaotecaListAdapter(Principal.this, botoes));
	        }
	    });
	}
	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onNewIntent(Intent intent) {
	setIntent(intent);
	handleIntent(intent);
    }
    

    private boolean handleIntent(Intent intent) {
	if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	    dataHelper = new DataHelper(getApplicationContext());
	    String query = intent.getStringExtra(SearchManager.QUERY);
	    List<Botao> nBotoes = new ArrayList<Botao>();
	    for (Botao botao : botoes) {
		if(botao.getName().toUpperCase().contains(query.toUpperCase()) ) {
		    nBotoes.add(botao);
		}
	    }
	    
	    gridView = (GridView) findViewById(R.id.gridview);
	    gridView.setAdapter(new BotaotecaListAdapter(this, nBotoes));

	    return true;
	}

	return false;
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