package br.com.jera.botaoteca;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import br.com.jera.botaoteca.sound.DownloadedSound;
import br.com.jera.botaoteca.sound.EmbeddedSound;

public class Principal extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		LinearLayout l = (LinearLayout)findViewById(R.id.area);
		 
		//adiciona os sons distribuidos juntamente com a aplicação
		Button b1 = new Button(ButtonColor.BLUE, getApplicationContext(), new EmbeddedSound("serracomedor.mp3"));
		b1.setText("serracomedor.mp3");
		b1.setPadding(1, 1, 2, 0);
		l.addView(b1);
		
		//load sounds from the sd card and create buttons
		File home = new File(DownloadedSound.PATH);
		File files[] = home.listFiles();
		
		
		for (File f: files) {
			Button b2 = new Button(ButtonColor.BLUE, getApplicationContext(), new DownloadedSound(f.getName()));
			b2.setText(f.getName());
			b2.setPadding(1, 1, 2, 0);
			l.addView(b2);
		}
		
		
	}

}