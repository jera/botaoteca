package br.com.jera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.View;

//TODO talvez o Botão da aplicação deva extender um android.widget.Button 
public class Button extends android.widget.Button {

	private static MediaPlayer PLAYER = new MediaPlayer();
	// TODO setar path correto para os arquivos
	public static final String PATH = Environment.getExternalStorageDirectory()+File.separator+"sounds";

	public Button(String name, Context context) {
		super(context);
		this.name = name;
		this.setOnClickListener(new Action());
	}

	private String name;

	public void play() throws IllegalArgumentException, IllegalStateException, IOException {
		PLAYER.reset();
		File file = new File(PATH + File.separator + name);
		FileInputStream fis = new FileInputStream(file);
		PLAYER.setDataSource(fis.getFD());
		PLAYER.prepare();
		PLAYER.start();
	}
	
	private class Action implements OnClickListener{
		@Override
		public void onClick(View v) {
			try {
				Button.this.play();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
