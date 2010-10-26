package br.com.jera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.View;

public class Button extends android.widget.Button {

	private static MediaPlayer PLAYER = new MediaPlayer();

	public static final String PATH = Environment.getExternalStorageDirectory() + File.separator + "sounds";

	private String fileName;
	private String name;
	private ButtonColor color;

	public Button(String fileName,ButtonColor color, Context context) {
		super(context);
		
		this.fileName = fileName;
		this.color = color;
		this.setOnClickListener(new Action());
	}

	private void play() throws IllegalArgumentException, IllegalStateException, IOException {
		
		PLAYER.reset();
		File file = new File(PATH + File.separator + fileName);
		FileInputStream fis = new FileInputStream(file);
		PLAYER.setDataSource(fis.getFD());
		PLAYER.prepare();
		PLAYER.start();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ButtonColor getColor() {
		return color;
	}

	public void setColor(ButtonColor color) {
		this.color = color;
	}
	
	private class Action implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			try {
				Button.this.play();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
