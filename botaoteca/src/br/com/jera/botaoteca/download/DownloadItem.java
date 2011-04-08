package br.com.jera.botaoteca.download;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.com.jera.botaoteca.ButtonColor;

public class DownloadItem {
	
	private String name;
	private ButtonColor color;

	public DownloadItem(JSONObject jsonObject) {
		String file = null;
		try {
			file = jsonObject.getString("name");
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}
		String[] info = file.split("_");
		color = ButtonColor.valueOf(info[info.length - 1]);
		this.name = getNameSound(file);
	}
	
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

/*	public byte[] download() throws IOException {
		InputStream stream = null;

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		size = connection.getContentLength();
		ByteArrayOutputStream os = new ByteArrayOutputStream(size);
		connection.connect();
		stream = connection.getInputStream();
		byte buffer[];

		while (true) {
			buffer = (size - downloaded) > MAX_BUFFER_SIZE ? new byte[MAX_BUFFER_SIZE] : new byte[size - downloaded];
			int read = stream.read(buffer);
			if (read == -1) {
				break;
			}
			os.write(buffer, 0, read);
			downloaded += read;
			int progress = getProgress();
		}
		stream.close();
		return os.toByteArray();

	}*/

}
