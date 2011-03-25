package br.com.jera.botaoteca.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {

	private URL url;
	private int size;
	private int downloaded;

	private static final int MAX_BUFFER_SIZE = 1024;


	public Download(URL url) {
		this.url = url;
		size = -1;
		downloaded = 0;
	}

	public byte[] download() throws IOException {
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

	}

	public String getUrl() {
		return url.toString();
	}

	public int getSize() {
		return size;
	}

	public int getProgress() {
		return (downloaded * 100) / size;
	}

}
