package br.com.jera.botaoteca;

import java.io.File;

import android.app.Activity;
import android.content.Context;
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
		Context context = getApplicationContext();;
		 
		//adiciona os sons distribuidos juntamente com a aplicação
		Button b1 = new Button(ButtonColor.BLUE, context, new EmbeddedSound("serracomedor.mp3", context));
		l.addView(b1);
		
		//load sounds from the sd card and create buttons
		File home = new File(DownloadedSound.PATH);
		File files[] = home.listFiles();
		
		
		for (File f: files) {
			Button b2 = new Button(ButtonColor.ORANGE, context, new DownloadedSound(f.getName()));
			l.addView(b2);
		}
		
		
	}

}