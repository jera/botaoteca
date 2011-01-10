package br.com.jera.botaoteca;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import br.com.jera.botaoteca.database.DataHelper;
import br.com.jera.botaoteca.sound.Sound;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	DataHelper dataHelper = new DataHelper(getApplicationContext());
	setContentView(R.layout.main);

	Display display = getWindowManager().getDefaultDisplay();
	int width = display.getWidth();

	// Buttons
	/*
	 * android.widget.Button buttonSearch = (android.widget.Button)
	 * findViewById(R.id.buttonSearch); android.widget.Button buttonFavorite
	 * = (android.widget.Button) findViewById(R.id.buttonFavorites);
	 * android.widget.Button buttonAll = (android.widget.Button)
	 * findViewById(R.id.buttonAll); buttonSearch.setWidth(width / 3);
	 * buttonFavorite.setWidth(width / 3); buttonAll.setWidth(width / 3);
	 */

	List<Button> sounds = dataHelper.createButtonsFromDatabase();
	GridView gridView = (GridView) findViewById(R.id.gridview);
	gridView.setAdapter(new BotaotecaListAdapter(sounds,this));

/*	ImageButton logo = new ImageButton(this);
	logo.setBackgroundDrawable(getResources().getDrawable(
		R.drawable.btn_jera));
	logo.setOnClickListener(openLink());
	tableLayout.addView(logo);*/

	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    private OnClickListener openLink() {
	return new OnClickListener() {

	    public void onClick(View v) {
		Intent intent = new Intent("android.intent.action.VIEW",
			Uri.parse("http://www.m.jera.com.br"));
		startActivity(intent);

	    }
	};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	return super.onCreateOptionsMenu(menu);
    }

}