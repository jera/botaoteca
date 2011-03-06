package br.com.jera.botaoteca.network;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import br.com.jera.botaoteca.sound.Sound;

public class DownloadSoundTask extends AsyncTask<String, Integer, Sound> implements DownloadCallBack {

	private ProgressBar progressBar;

	public DownloadSoundTask(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	protected Sound doInBackground(String... params) {
		try {
			Download download = new Download(new URL(params[0]), this);
			byte[] lala = download.download();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Sound result) {
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		progressBar.setProgress(values[0]);
		Log.i("DOWNLOAD", "percent " + values[0]);
	}

	@Override
	public void updateProgress(int progressPercent) {
		Log.i("DOWNLOAD", "percent " + progressPercent);
		publishProgress(new Integer[] { progressPercent });

	}

}
