package br.com.jera.botaoteca;

import java.io.IOException;

import android.content.Context;
import android.view.View;
import br.com.jera.botaoteca.sound.Sound;

public class Button extends android.widget.Button {

	private String name;
	private ButtonColor color;
	private Sound sound;

	public Button(ButtonColor color, Context context, Sound sound) {
		super(context);

		this.color = color;
		this.sound = sound;
		this.setOnClickListener(new Action());
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
				Button.this.sound.play();
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
