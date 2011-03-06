package br.com.jera.botaoteca;

import android.content.Context;
import android.graphics.drawable.Drawable;

public enum ButtonColor {

	GREEN("Green", R.drawable.btn_normal_green, R.drawable.btn_pressed_green), BLUE("Blue", R.drawable.btn_normal_blue, R.drawable.btn_pressed_blue), RED(
			"Red", R.drawable.btn_normal_red, R.drawable.btn_pressed_red), YELLOW("Yellow", R.drawable.btn_normal_yellow,
			R.drawable.btn_pressed_yellow), ORANGE("Orange", R.drawable.btn_normal_orange, R.drawable.btn_pressed_orange);

	private String name;
	private int normal;
	private int pressed;

	ButtonColor(String name, int normal, int pressed) {
		this.name = name;
		this.normal = normal;
		this.pressed = pressed;
	}
	
	public Drawable getDrawable(Context context) {
		return context.getResources().getDrawable(this.normal);
	}

	public String getName() {
		return name;
	}

	public int getNormal() {
		return normal;
	}

	public int getPressed() {
		return pressed;
	}

}
