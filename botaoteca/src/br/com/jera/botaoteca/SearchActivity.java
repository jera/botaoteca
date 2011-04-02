package br.com.jera.botaoteca;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.jera.botaoteca.database.DataHelper;

public class SearchActivity extends Activity {

	private DataHelper dataHelper;
	private List<AppButton> buttons;
	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		dataHelper = new DataHelper(getApplicationContext());
		String query = (String) this.getIntent().getExtras().get("query");
		buttons = dataHelper.filterButtons(query);
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
			//TODO MSG não encontrado
		}
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
}