package br.com.jera.botaoteca.download;

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
		this.downloadItens = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DownloadItem item = downloadItens.get(position);
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(getContext(), R.layout.download_item, null);
			holder.buttonImage = (ImageView) convertView.findViewById(R.id.download_buttton_image);
			holder.text = (TextView) convertView.findViewById(R.id.download_button_title);
			holder.downloadButton = (Button) convertView.findViewById(R.id.btn_download);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(item.getName());
		holder.buttonImage.setBackgroundDrawable(item.getColor().getNormalDrawable(getContext()));
		holder.buttonImage.setTag(holder);
		
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView buttonImage;
		TextView text;
		ProgressBar bar;
		Button downloadButton;
	}
}
