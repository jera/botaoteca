package br.com.jera.botaoteca;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		
		try {
			text.setText(obj.getString("name"));
			image.setBackgroundDrawable( getDrawable(ButtonColor.valueOf(obj.getString("color") )));
		} catch (JSONException e) {
			Log.e("ERROR", e.getMessage());
		}

		return itemLayout;
	}
	
	private Drawable getDrawable(ButtonColor color) {
		if (color.equals(ButtonColor.GREEN)) {
			return getContext().getResources().getDrawable(R.drawable.btn_green);
		}
		if (color.equals(ButtonColor.BLUE)) {
			return getContext().getResources().getDrawable(R.drawable.btn_blue);
		}
		if (color.equals(ButtonColor.RED)) {
			return getContext().getResources().getDrawable(R.drawable.btn_red);
		}
		if (color.equals(ButtonColor.YELLOW)) {
			return getContext().getResources().getDrawable(R.drawable.btn_yellow);
		}
		if (color.equals(ButtonColor.ORANGE)) {
			return getContext().getResources().getDrawable(R.drawable.btn_orange);
		}
		return null;
	}
}
