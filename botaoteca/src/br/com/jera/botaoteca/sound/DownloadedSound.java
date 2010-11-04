package br.com.jera.botaoteca.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.os.Environment;

public class DownloadedSound extends Sound{
	
	public static final String PATH = Environment.getExternalStorageDirectory()+ File.separator + "sounds";
	
	public DownloadedSound(String fileName) {
		super(fileName, null);
	}
	
	@Override
	protected void loadFile(String fileName) throws FileNotFoundException {
		File file = new File(PATH + File.separator + fileName);
		this.inputStream = new FileInputStream(file);
	}
}
