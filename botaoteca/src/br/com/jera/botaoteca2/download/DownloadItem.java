package br.com.jera.botaoteca2.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import br.com.jera.botaoteca2.ButtonColor;
import br.com.jera.botaoteca2.R;
import br.com.jera.botaoteca2.database.DataHelper;
import br.com.jera.botaoteca2.sound.DownloadedSound;

public class DownloadItem extends DownloadedSound {

	private String name;
	private ButtonColor color;
	private Status status;
	private Integer index;
	private Context context;
	private Drawable background;
	// referencia ao adapter, usado para atualizar a barra de progresso
	private DownloadListAdapter adapter;

	enum Status {
		DOWNLOADING, READY, MISSING
	}

	public DownloadItem(JSONObject jsonObject, Context context) throws IOException {
		super(getFileNameDownload(jsonObject));
		String[] info = fileName.split("_");
		color = ButtonColor.valueOf(info[info.length - 1]);
		this.name = DataHelper.getDataHelper(context).getNameSound(fileName);
		status = Status.MISSING;
		this.context = context;
		background = color.getAnimatedDrawable(context);
	}

	public static String getFileNameDownload(JSONObject jsonObject) {
		try {
			return jsonObject.getString("name");
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}
		return "";
	}

	private Handler progressHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what > 100) {
				DownloadItem.this.adapter.notifyDataSetChanged();
			} else {
				adapter.updateProgress(index, msg.what);
			}
		};
	};

	private View.OnClickListener clickListener = new View.OnClickListener() {
		public void onClick(View view) {
			if (status.equals(Status.DOWNLOADING)) {
				return;
			} else if (status.equals(Status.READY)) {

			} else {
				AsyncTask<DownloadItem, Integer, Void> task = new AsyncTask<DownloadItem, Integer, Void>() {
					@Override
					protected Void doInBackground(DownloadItem... params) {
						DownloadItem item = params[0];
						try {
							item.download();
						} catch (IOException e) {
							Log.e("DOWNLOAD", e.getMessage());
						}
						return null;
					}
				};
				task.execute(DownloadItem.this);
				DownloadItem.this.adapter.notifyDataSetChanged();
			}
		};
	};

	@Override
	public void play() throws IllegalArgumentException, IllegalStateException, IOException {
		File file = new File(PATH + File.separator + fileName + ".mp3");
		FileInputStream inputStream = new FileInputStream(file);

		PLAYER.reset();
		PLAYER.setDataSource(inputStream.getFD());
		PLAYER.prepare();
		PLAYER.start();
	}

	public String getName() {
		return name;
	}

	public ButtonColor getColor() {
		return color;
	}

	public void download() throws IOException {
		try {
			status = Status.DOWNLOADING;
			long downloaded = 0;
			URL url = new URL(context.getString(R.string.server) + "download/" + URLEncoder.encode(fileName) + ".mp3");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Range", "bytes=" + downloaded + "-");

			long size = (long) connection.getContentLength();
			File file = new File(DownloadedSound.PATH + fileName + ".mp3");
			if (file.exists())
				file.delete();
			FileOutputStream fs = new FileOutputStream(file);
			connection.connect();
			InputStream stream = connection.getInputStream();
			byte buffer[];
			int newProgress = 0;
			int progress = 0;
			while (true) {
				buffer = (size - downloaded) > 1024 ? new byte[1024] : new byte[(int) (size - downloaded)];
				int read = stream.read(buffer);
				if (read == -1) {
					break;
				}
				fs.write(buffer, 0, read);
				downloaded += read;
				newProgress = (int) (100 * downloaded) / (int) size;
				if (newProgress > progress) {
					progressHandler.sendEmptyMessage(newProgress);
					progress = newProgress;
				}
			}
			stream.close();
			fs.close();
			DataHelper.getDataHelper(context).insert(fileName);
			status = Status.READY;
			progressHandler.sendEmptyMessage(101);
		} catch (NegativeArraySizeException e) {
			Log.e("DOWNLOAD", "Erro download " + e.getMessage());
		}
	}

	public Status getStatus() {
		return status;
	}

	public View.OnClickListener getClickListener() {
		return clickListener;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setAdapter(DownloadListAdapter adapter) {
		this.adapter = adapter;
	}

	public Drawable getBackground() {
		return background;
	}

}
