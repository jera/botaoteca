package br.com.jera.botaoteca.sound;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;


public class EmbeddedSound extends Sound {
	
	private static AssetManager manager;
	
	public EmbeddedSound(String fileName, Context context) {
		super(fileName, context);
	}
	
	@Override
	protected void loadFile(String fileName) throws IOException {
		manager = context.getAssets();
		this.inputStream = manager.openFd(fileName).createInputStream();
	}
	
}
