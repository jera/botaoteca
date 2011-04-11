package br.com.jera.botaoteca.download;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.com.jera.botaoteca.R;
import br.com.jera.botaoteca.download.DownloadItem.Status;

public class DownloadListAdapter extends ArrayAdapter<DownloadItem> {

	private List<DownloadItem> downloadItens;
	private Drawable downloadImage;
	private Drawable readyImage;
	private GridView gridView;

	public DownloadListAdapter(Context context, List<DownloadItem> items, GridView gridView) {
		super(context, R.layout.download_item, R.id.download_button_title, items);
		this.downloadItens = items;
		downloadImage = getContext().getResources().getDrawable(R.drawable.btn_download_normal);
		readyImage = getContext().getResources().getDrawable(R.drawable.btn_ok);
		this.gridView = gridView;

		for(DownloadItem item : items) {
			item.setAdapter(this);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DownloadItem item = downloadItens.get(position);
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(getContext(), R.layout.download_item, null);
			holder.buttonImage = (ImageButton) convertView.findViewById(R.id.download_buttton_image);
			holder.text = (TextView) convertView.findViewById(R.id.download_button_title);
			holder.statusImage = (ImageView) convertView.findViewById(R.id.status_image);
			holder.bar = (ProgressBar) convertView.findViewById(R.id.progressBar);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		item.setIndex(position);

		holder.text.setText(item.getName());
		holder.buttonImage.setBackgroundDrawable(item.getBackground());

		if(item.getStatus().equals(Status.MISSING)){
			holder.buttonImage.setOnClickListener(item.getClickListener());
			holder.statusImage.setBackgroundDrawable(downloadImage);
			holder.statusImage.setVisibility(View.VISIBLE);
			holder.bar.setVisibility(View.INVISIBLE);
		}else if(item.getStatus().equals(Status.READY)){
			holder.buttonImage.setBackgroundDrawable(item.getColor().getNormalDrawable(getContext()));
			holder.statusImage.setBackgroundDrawable(readyImage);
			holder.statusImage.setVisibility(View.VISIBLE);
			holder.bar.setVisibility(View.INVISIBLE);
			holder.buttonImage.setOnClickListener(null);
		}else if(item.getStatus().equals(Status.DOWNLOADING)){
			holder.statusImage.setVisibility(View.INVISIBLE);
			holder.bar.setVisibility(View.VISIBLE);
			holder.buttonImage.setOnClickListener(null);
		}

		return convertView;
	}

	public void updateProgress(int index, int progress){
	    View v = gridView.getChildAt(index - gridView.getFirstVisiblePosition());
	    if(v != null) {
		    ViewHolder holder = (ViewHolder) v.getTag();
		    holder.bar.setProgress(progress);
	    }
	}

	private static class ViewHolder {
		ImageButton buttonImage;
		TextView text;
		ProgressBar bar;
		ImageView statusImage;
	}
}
