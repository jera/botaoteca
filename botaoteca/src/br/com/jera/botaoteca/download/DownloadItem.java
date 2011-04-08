package br.com.jera.botaoteca.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import br.com.jera.botaoteca.ButtonColor;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.sound.DownloadedSound;

public class DownloadItem {
	
	private String name;
	private String fileName;
	private ButtonColor color;
	private Status status;
	private Integer index;
	private Context context;
	//referência ao adapter, usado para atualizar a barra de progresso
	private DownloadListAdapter adapter;

	enum Status {
		DOWNLOADING,
		READY,
		MISSING
	}
	
	public DownloadItem(JSONObject jsonObject, Context context) {
		try {
			fileName = jsonObject.getString("name");
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}
		String[] info = fileName.split("_");
		color = ButtonColor.valueOf(info[info.length - 1]);
		this.name = getNameSound(fileName);
		status = Status.MISSING;
		this.context = context;
	}
	
	private Handler progressHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what > 100) {
				DownloadItem.this.adapter.notifyDataSetChanged();
			}
			else{
				adapter.updateProgress(index, msg.what);
			}
		};
	};
	
	private View.OnClickListener clickListener = new View.OnClickListener(){
		public void onClick(View view) {
			AsyncTask<DownloadItem, Integer, Void> task = new AsyncTask<DownloadItem, Integer, Void>(){
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
		};
	};
	
	private String getNameSound(String file) {
		String[] name = file.split("_");
		String nameSound = "";
		for (int i = 0; i < name.length - 1; i++) {
			nameSound += name[i] + " ";
		}
		return nameSound;
	}
	
	public String getName() {
		return name;
	}

	public ButtonColor getColor() {
		return color;
	}

	public void download() throws IOException {
		status = Status.DOWNLOADING;
		long downloaded = 0;
		URL url = new URL(context.getString(R.string.server)+"download/"+URLEncoder.encode(fileName)+".mp3");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
		
		long size = (long) connection.getContentLength();
		File file = new File(DownloadedSound.PATH  + fileName+".mp3");
		file.createNewFile();
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
		status= Status.READY;
		progressHandler.sendEmptyMessage(101);
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

}
