package br.com.jera.botaoteca.download;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import br.com.jera.botaoteca.sound.DownloadedSound;

public class DownloadSoundTask extends AsyncTask<String, Integer, Void> {


	@Override
	protected Void doInBackground(String... params) {
		try {
			Download download = new Download(new URL(params[0]));
			byte[] data = download.download();
			DownloadedSound.create(params[1], data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}

}