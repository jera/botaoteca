package br.com.jera.botaoteca;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import br.com.jera.botaoteca.sound.DownloadedSound;
import br.com.jera.botaoteca.sound.EmbeddedSound;

public class Principal extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Button> buttons = new ArrayList<Button>();
		setContentView(R.layout.main);
		TableLayout l = (TableLayout) findViewById(R.id.area);
		Context context = getApplicationContext();

		// adiciona os sons distribuidos juntamente com a aplicação
		Button b1 = new Button(ButtonColor.BLUE, context, new EmbeddedSound(
				"serracomedor.mp3", context));
		buttons.add(b1);

		// load sounds from the sd card and create buttons
		File home = new File(DownloadedSound.PATH);
		File files[] = home.listFiles();

		for (File f : files) {
			Button b2 = new Button(ButtonColor.ORANGE, context,
					new DownloadedSound(f.getName()));
			buttons.add(b2);
		}

		List<TableRow> rows = UIFactory.createRows(this, buttons);
		for (TableRow row : rows) {
			l.addView(row,new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

		}
	}

}