package br.com.jera.botaoteca.sound;

import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;


public abstract class Sound {
	
	private static MediaPlayer PLAYER = new MediaPlayer();
	
	protected FileInputStream inputStream;
	protected Context context;
	private String fileName;
	
	public Sound(String fileName, Context context) {
		this.fileName = fileName;
		this.context = context;
		try {
			loadFile(fileName);
		} catch (Exception e) {
			Log.wtf("File", "Impossible to load File "+fileName);
			e.printStackTrace();
		}
	}
	
	protected abstract void loadFile(String fileName) throws Exception;
	
	public void play() throws IllegalArgumentException, IllegalStateException, IOException {
		PLAYER.reset();
		PLAYER.setDataSource(inputStream.getFD());
		PLAYER.prepare();
		PLAYER.start();
	}

	public String getFileName() {
		return fileName;
	}
	
	
	
}
