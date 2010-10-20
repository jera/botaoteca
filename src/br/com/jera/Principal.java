package br.com.jera;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;

public class Principal extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		updateSongList();
	}

	public void updateSongList() {
		File home = new File("/sdcard/sounds");
		if (home.listFiles().length > 0) {
			for (File f : home.listFiles()) {
				System.out.println(f.getName());
			}
		}
	}
}