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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class DownloadActivity extends Activity {

	private List<JSONObject> sounds;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		ListView listView = (ListView) findViewById(R.id.download_list);
		try {
			this.createSoundsInfo(this.getListJSON());
			listView.setAdapter(new DownloadListAdapter(this, sounds));
		} catch (JSONException e) {
			Log.i("ERROR", e.getMessage());
		}
	}

	public HttpClient connectToServer() {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 2000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		int timeoutSocket = 2000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		return new DefaultHttpClient(httpParameters);
	}

	public String getListJSON() {
		HttpGet get = new HttpGet("http://10.0.2.2:9080/list");
		try {
			HttpResponse response = this.connectToServer().execute(get);
			return getResponseBody(response.getEntity());
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
			Log.e("DOWNLOAD", "Erro ao listar sons: " + e.getMessage());
			this.finish();
			return "";
		}
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

	public void createSoundsInfo(String content) throws JSONException {
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