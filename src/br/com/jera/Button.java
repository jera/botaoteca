package br.com.jera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.media.MediaPlayer;

//TODO talvez o Botão da aplicação deva extender um android.widget.Button 
public class Button{
	
	private static final MediaPlayer PLAYER = new MediaPlayer();
	//TODO setar path correto para os arquivos
	private static final String PATH = "/sdcard/sounds";
	
	public Button(String name) {
		this.name = name;
	}
	
	private String name;
	
	
	public void play() throws IllegalArgumentException, IllegalStateException, IOException{
		File file = new File(PATH+File.separator+name);
	    FileInputStream fis = new FileInputStream(file);
		PLAYER.setDataSource(fis.getFD());
		PLAYER.prepare();
		PLAYER.start();
	}
	
}
