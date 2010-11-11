package br.com.jera.botaoteca;

import java.io.IOException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import br.com.jera.botaoteca.sound.Sound;

public class Button extends android.widget.Button {

	private String name;
	private ButtonColor color;
	private Sound sound;
	private Context context;
	private static LinearLayout.LayoutParams params;
	
	public Button(ButtonColor color, Context context, Sound sound) {
		super(context);

		this.color = color;
		this.sound = sound;
		this.context  = context;
		params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		this.setOnClickListener(new Action());
		this.updateViewProperties();
	}
	
	public void updateViewProperties(){
		
		Resources resources = context.getResources();
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState( PRESSED_ENABLED_STATE_SET , resources.getDrawable(color.getPressed()));
		drawable.addState( EMPTY_STATE_SET , resources.getDrawable(color.getNormal()));
		this.setBackgroundDrawable(drawable);
		
		setLayoutParams(params);
	}

	public String getName() {
		return name;
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

	public void setName(String name) {
	    this.name = name;
	}

	public Sound getSound() {
	    return sound;
	}
	

}
