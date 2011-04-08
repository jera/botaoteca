package br.com.jera.botaoteca.download;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.jera.botaoteca.R;

public class DownloadActivity extends Activity {

	private List<DownloadItem> downloadItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		GridView gridView = (GridView) findViewById(R.id.download_gridview);
		ImageButton buttonMoreOptions = (ImageButton) findViewById(R.id.button_back);
		buttonMoreOptions.setOnClickListener(this.onBack());

		try {
			this.createSoundsInfo(this.getListJSON());
			gridView.setAdapter(new DownloadListAdapter(this, downloadItems));
		} catch (JSONException e) {
			Log.i("ERROR", e.getMessage());
		}
	}

	public HttpClient connectToServer() {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 4000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		int timeoutSocket = 4000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		return new DefaultHttpClient(httpParameters);
	}

	public String getListJSON() {
		HttpGet get = new HttpGet(getString(R.string.server) + "list");
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
		downloadItems = new ArrayList<DownloadItem>(length);
		for (int i = 0; i < length; i++) {
			downloadItems.add(new DownloadItem((JSONObject) soundsArray.get(i)));
		}
	}

	private OnClickListener onBack() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadActivity.this.finish();
			}
		};
	}

}