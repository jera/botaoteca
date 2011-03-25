package br.com.jera.botaoteca;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

public class BotaotecaListAdapter extends ArrayAdapter<AppButton> {

	public BotaotecaListAdapter(Context context, List<AppButton> objects) {
		super(context, R.layout.gridview_item, R.id.gridview_title, objects);
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemLayout = (LinearLayout) super.getView(position, convertView, parent);
		final AppButton button = getItem(position);
		Button nButton = (Button) itemLayout.findViewById(R.id.gridview_button);
		nButton.setTag(button);
		nButton.setOnClickListener(onClickListener);
		nButton.setBackgroundDrawable(button.getColor().getDrawable(getContext()));
		return itemLayout;
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
