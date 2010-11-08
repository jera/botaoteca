package br.com.jera.botaoteca.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.os.Environment;

public class DownloadedSound extends Sound{
	
	public static final String PATH = Environment.getExternalStorageDirectory()+ File.separator + "sounds";
	
	public DownloadedSound(String fileName) throws IOException {
		super(fileName, null);
	}
	
	@Override
	protected void loadFile(String fileName) throws IOException {
		File file = new File(PATH + File.separator + fileName);
		this.inputStream = new FileInputStream(file);
	}
}
