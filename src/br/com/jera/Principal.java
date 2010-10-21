package br.com.jera;

import java.io.File;


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Principal extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		File home = new File(Button.PATH);
		File files[] = home.listFiles();
		
		
		LinearLayout l = (LinearLayout)findViewById(R.id.area);
		for (File f: files) {
			Button b = new Button(f.getName(), getApplicationContext());
			b.setText(f.getName());
			b.setPadding(1, 1, 2, 0);
			l.addView(b);
		}
		
	}

}