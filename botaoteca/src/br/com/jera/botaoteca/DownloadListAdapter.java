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
		JSONObject obj = objects.get(position);
		ViewHolder holder;
		String file = null;
		try {
			file = obj.getString("name");
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}
		String[] info = file.split("_");
		String color = info[info.length - 1];
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(getContext(), R.layout.download_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.btn_download);
			holder.text = (TextView) convertView.findViewById(R.id.download_button_title);
			holder.button = (Button) convertView.findViewById(R.id.download_button);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(this.getNameSound(file));
		holder.button.setOnClickListener(this.createListener(getContext().getString(R.string.server) + "download", file));
		holder.button.setBackgroundDrawable(ButtonColor.valueOf(color).getDrawable(getContext()));
		holder.button.setTag(holder);
		return convertView;
	}

	private static class ViewHolder {
		ImageView image;
		TextView text;
		Button button;
	}

	private String getNameSound(String file) {
		String[] name = file.split("_");
		String nameSound = "";
		for (int i = 0; i < name.length - 1; i++) {
			nameSound += name[i] + " ";
		}
		return nameSound;
	}

	private View.OnClickListener createListener(final String url, final String fileName) {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadSoundTask task = new DownloadSoundTask();
				task.execute(url + "/" + URLEncoder.encode(fileName) + ".mp3", fileName);
				ViewHolder viewHolder = (ViewHolder) v.getTag();
				viewHolder.image.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.btn_ok));
			}
		};
	}

}
