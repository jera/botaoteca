package br.com.jera.botaoteca2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.jera.botaoteca2.database.DataHelper;
import br.com.jera.botaoteca2.download.DownloadActivity;
import br.com.jeramobstats.JeraAgent;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class Principal extends Activity {

	private List<AppButton> buttons;
	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SplashDialog dialog = new SplashDialog(this);
		if (!dialog.isSplashed()) {
			dialog.show();
			dialog.setSplashed(true);
		}

		setContentView(R.layout.main);
		ImageButton buttonMoreOptions = (ImageButton) findViewById(R.id.btn_more);
		buttonMoreOptions.setOnClickListener(this.onCreateMoreOptions());
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());
	}

	@Override
	protected void onStart() {
		super.onStart();
		JeraAgent.onStartSession(this, "9HPCKRTT7VEGH3HPJDX5");
	}

	@Override
	protected void onStop() {
		super.onStop();
		JeraAgent.onEndSession(this);
	}

	@Override
	protected void onResume() {
		this.constructButtons();
		super.onResume();
	}

	private OnClickListener onCreateMoreOptions() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Principal.this.openOptionsMenu();
			}
		};
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	private void sort() {
		Collections.sort(buttons, new Comparator<AppButton>() {
			public int compare(AppButton b1, AppButton b2) {
				return b1.getName().toUpperCase().compareTo(b2.getName().toUpperCase());
			}
		});
	}

	private boolean handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			Intent i = new Intent(this, SearchActivity.class);
			i.putExtra("query", query);
			startActivity(i);
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
		case R.id.quit: {
			finish();
			return true;
		}

		case R.id.search: {
			onSearchRequested();
			return true;
		}

		case R.id.downloads: {
			if (!isOnline()) {
				Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
				return true;
			}
			Intent i = new Intent(this, DownloadActivity.class);
			startActivity(i);
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public void constructButtons() {
		buttons = DataHelper.getDataHelper(this).createButtonsFromDatabase();
		Log.i("BUTTONS", String.valueOf(buttons.size()));
		sort();
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new BotaotecaListAdapter(this, buttons));
	}

}