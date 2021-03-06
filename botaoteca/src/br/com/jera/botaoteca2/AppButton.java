package br.com.jera.botaoteca2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import br.com.jera.botaoteca2.sound.Sound;

public class AppButton implements Comparable<AppButton> {
	private String fileName;
	private String name;
	private Drawable drawable;
	private Sound sound;
	private ButtonColor color;

	public AppButton(ButtonColor color, String fileName, String name, Context context, Sound sound) {
		this.fileName = fileName;
		this.drawable = color.getAnimatedDrawable(context);
		this.name = name;
		this.sound = sound;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sound getSound() {
		return sound;
	}

	public String toString() {
		return name;
	}

	public String getFileName() {
		return this.fileName;
	}

	@Override
	public int compareTo(AppButton another) {
		return name.toUpperCase().compareTo(another.getName().toUpperCase());
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public ButtonColor getColor() {
		return color;
	}
}
