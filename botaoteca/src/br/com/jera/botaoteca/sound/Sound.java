package br.com.jera.botaoteca.sound;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;

public abstract class Sound {

	protected static MediaPlayer PLAYER = new MediaPlayer();

	protected Context context;
	protected String fileName;

	public Sound(String fileName, Context context) throws IOException {
		this.fileName = fileName;
		this.context = context;
	}

	public abstract void play() throws IllegalArgumentException, IllegalStateException, IOException;

	public String getFileName() {
		return fileName;
	}

}
