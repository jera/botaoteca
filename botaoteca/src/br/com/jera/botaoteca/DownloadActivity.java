package br.com.jera.botaoteca;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class DownloadActivity extends Activity {

	private List<JSONObject> sounds;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		ListView listView = (ListView) findViewById(R.id.download_list);

		try {
			getSoundsInfo(this.getListJSON());
			listView.setAdapter(new DownloadListAdapter(this, sounds));
		} catch (JSONException e) {
			Log.i("ERROR", e.getMessage());
		}
	}

	public String getListJSON() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://10.0.2.2:9080/list");
		String result = null;
		try {
			HttpResponse response = client.execute(get);
			result = getResponseBody(response.getEntity());
		} catch (Exception e) {
			Log.e("DOWNLOAD", "Erro ao listar sons: " + e.getMessage());
		}
		return result;
	}

	public String getResponseBody(final HttpEntity entity) throws IOException, ParseException {
		InputStream instream = entity.getContent();
		String charset = getContentCharSet(entity);
		if (charset == null) {
			charset = HTTP.UTF_8;
		}
		Reader reader = new InputStreamReader(instream, charset);
		StringBuilder buffer = new StringBuilder();
		try {
			char[] tmp = new char[1024];
			int i;
			while ((i = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, i);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	public String getContentCharSet(final HttpEntity entity) throws ParseException {
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}
		return charset;
	}

	public void getSoundsInfo(String content) throws JSONException {
		JSONObject responseObject = new JSONObject(content);
		JSONArray soundsArray = responseObject.getJSONArray("sounds");

		int length = soundsArray.length();
		sounds = new ArrayList<JSONObject>(length);
		for (int i = 0; i < length; i++) {
			sounds.add((JSONObject) soundsArray.get(i));
		}

	}

	/*
	 * public void download() { ProgressBar bar = (ProgressBar)
	 * findViewById(R.id.progressBar1); DownloadSoundTask task = new
	 * DownloadSoundTask(bar); task.execute(
	 * "http://joe-miller.net/wp-content/uploads/2011/01/trollface-1f965e4.png"
	 * ); }
	 */

	public List<JSONObject> getSounds() {
		return sounds;
	}

	public void setSounds(List<JSONObject> sounds) {
		this.sounds = sounds;
	}

}