package br.com.jera.botaoteca2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.jera.botaoteca2.database.DataHelper;

public class SearchActivity extends Activity {

	private List<AppButton> buttons;
	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		ImageButton buttonMoreOptions = (ImageButton) findViewById(R.id.button_back);
		buttonMoreOptions.setOnClickListener(this.onBack());

		String query = (String) this.getIntent().getExtras().get("query");
		buttons = DataHelper.getDataHelper(this).filterButtons(query);
		List<AppButton> nButtons = new ArrayList<AppButton>();

		for (AppButton button : buttons) {
			if (button.getName().toUpperCase().contains(query.toUpperCase())) {
				nButtons.add(button);
			}
		}

		if (!nButtons.isEmpty()) {
			gridView = (GridView) findViewById(R.id.gridview);
			gridView.setAdapter(new BotaotecaListAdapter(this, nButtons));
		} else {
			Toast.makeText(getApplicationContext(), getString(R.string.not_result), Toast.LENGTH_LONG).show();
		}
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}

	private OnClickListener onBack() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SearchActivity.this.finish();
			}
		};
	}
}