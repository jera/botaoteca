package br.com.jera.botaoteca.sound;

import java.io.IOException;

import android.content.res.AssetManager;
import android.content.res.Resources;


public class EmbeddedSound extends Sound {
	
	private static final AssetManager manager = Resources.getSystem().getAssets();
	
	public EmbeddedSound(String fileName) {
		super(fileName);
	}
	
	@Override
	protected void loadFile(String fileName) throws IOException {
		this.inputStream = manager.openFd(fileName).createInputStream();
	}
	
}
