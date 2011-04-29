package br.com.jera.botaoteca2.sound;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

public class EmbeddedSound extends Sound {

	private static AssetManager manager;

	public EmbeddedSound(String fileName, Context context) throws IOException {
		super(fileName, context);
	}

	@Override
	public void play() throws IllegalArgumentException, IllegalStateException, IOException {
		manager = context.getAssets();
		AssetFileDescriptor descriptor = manager.openFd(fileName);

		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();

		PLAYER.reset();
		PLAYER.setDataSource(descriptor.getFileDescriptor(), start, end);
		PLAYER.prepare();
		PLAYER.start();
	}
}
