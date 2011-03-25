package br.com.jera.botaoteca;

import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.com.jera.botaoteca.network.DownloadSoundTask;

public class DownloadListAdapter extends ArrayAdapter<JSONObject> {
	
	private List<JSONObject> objects;
	public DownloadListAdapter(Context context, List<JSONObject> objects) {
		super(context, R.layout.download_item, R.id.download_button_title, objects);
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemLayout = (LinearLayout) super.getView(position, convertView, parent);
		
		JSONObject obj = objects.get(position);
		ImageView image = (ImageView) itemLayout.findViewById(R.id.download_button_image);
		TextView text = (TextView) itemLayout.findViewById(R.id.download_button_title);
		ProgressBar bar = (ProgressBar) itemLayout.findViewById(R.id.progressBar);
		Button button = (Button) itemLayout.findViewById(R.id.download_button);
		
		try {
			String file = obj.getString("name");
			String[] info =  file.split("_");
			String color = info[info.length-1];
			button.setOnClickListener(createListener(bar, "http://10.0.2.2:9080/download", file));
			String name = file.substring(0, file.lastIndexOf("_")).replace('_', ' ');
			text.setText(name);
			image.setBackgroundDrawable(ButtonColor.valueOf(color).getDrawable(getContext()));
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}

		return itemLayout;
	}
	
	private View.OnClickListener createListener(final ProgressBar bar, final String url, final String fileName) {
		return new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DownloadSoundTask task = new DownloadSoundTask();
				task.execute(url+"/"+URLEncoder.encode(fileName)+".mp3", fileName);
				bar.setVisibility(View.VISIBLE);
				v.setVisibility(View.INVISIBLE);
			}
		};
	}

}
