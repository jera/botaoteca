package br.com.jera.botaoteca;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BotaotecaListAdapter extends ArrayAdapter<AppButton> {

	public BotaotecaListAdapter(Context context, List<AppButton> objects) {
		super(context, R.layout.gridview_item, R.id.gridview_title, objects);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final AppButton button = getItem(position);
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(getContext(), R.layout.gridview_item, null);
			holder.button = (Button) convertView.findViewById(R.id.gridview_button);
			holder.text =  (TextView)  convertView.findViewById(R.id.gridview_title);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.button.setOnClickListener(onClickListener);
		holder.button.setBackgroundDrawable(button.getColor().getDrawable(getContext()));
		holder.button.setTag(button);
		holder.text.setText(button.getName());

		return convertView;
	}

  private static class ViewHolder {
	  Button button;
	  TextView text;
  }

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				((AppButton) v.getTag()).getSound().play();
			} catch (Exception e) {
				Log.i("ERROR", e.getMessage());
			}
		}
	};
}
