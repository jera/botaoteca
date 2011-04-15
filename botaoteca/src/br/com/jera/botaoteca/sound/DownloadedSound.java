package br.com.jera.botaoteca.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

public class DownloadedSound extends Sound {

	public static final String PATH = Environment.getExternalStorageDirectory() + File.separator + "botaoteca" + File.separator;

	static {
		new File(PATH).mkdirs();
	}

	public DownloadedSound(String fileName) throws IOException {
		super(fileName, null);
	}

	@Override
	public void play() throws IllegalArgumentException, IllegalStateException, IOException {
		File file = new File(PATH + File.separator + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		PLAYER.reset();
		PLAYER.setDataSource(inputStream.getFD());
		PLAYER.prepare();
		PLAYER.start();

	}

	public static Sound create(String fileName, byte[] data) throws IOException {
		File file = new File(PATH + File.separator + fileName);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(data);
		fos.flush();
		return new DownloadedSound(fileName + ".mp3");
	}
}
