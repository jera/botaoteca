package br.com.jera.botaoteca;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class DownloadActivity extends Activity {

	private List<JSONObject> sounds;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		ListView listView = (ListView)findViewById(R.id.download_list);
		String jString = "{\"sounds\":[{\"name\": \"Serra Comedor\", \"filename\": \"serracomedor.mp3\", \"color\": \"BLUE\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}] }";
		try {
			getSoundsInfo(jString);
			listView.setAdapter(new DownloadListAdapter(this, sounds));
		} catch (JSONException e) {
			Log.i("ERROR", e.getMessage());
		}
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
	
	/*public void download() {
		ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		DownloadSoundTask task = new DownloadSoundTask(bar);
		task.execute("http://joe-miller.net/wp-content/uploads/2011/01/trollface-1f965e4.png");
	}*/
	
	public List<JSONObject> getSounds() {
		return sounds;
	}

	public void setSounds(List<JSONObject> sounds) {
		this.sounds = sounds;
	}

}