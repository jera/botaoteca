package br.com.jera.botaoteca.download;

import java.net.URLEncoder;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.com.jera.botaoteca.R;

public class DownloadListAdapter extends ArrayAdapter<DownloadItem> {
	
	private List<DownloadItem> downloadItens;
	public DownloadListAdapter(Context context, List<DownloadItem> items) {
		super(context, R.layout.download_item, R.id.download_button_title, items);
		this.downloadItens = downloadItens;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHolder holder;
		if(convertView != null) {
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.download_button_image);
			holder.text = (TextView) convertView.findViewById(R.id.download_button_title);
			holder.bar = (ProgressBar) convertView.findViewById(R.id.progressBar);
			holder.button = (Button) convertView.findViewById(R.id.download_button);
			convertView.setTag(holder);
		}
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView image;
		TextView text;
		ProgressBar bar;
		Button button;
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
