package br.com.jera.botaoteca2;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.com.jera.botaoteca2.database.DataHelper;

public class BotaotecaListAdapter extends ArrayAdapter<AppButton> {

	public BotaotecaListAdapter(Context context, List<AppButton> objects) {
		super(context, R.layout.gridview_item, R.id.gridview_title, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final AppButton button = getItem(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(getContext(), R.layout.gridview_item, null);
			holder.button = (Button) convertView.findViewById(R.id.gridview_button);
			holder.text = (TextView) convertView.findViewById(R.id.gridview_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.button.setOnClickListener(play());
		holder.button.setOnLongClickListener(changeForDelete());
		holder.button.setBackgroundDrawable(button.getDrawable());
		holder.button.setTag(button);
		holder.text.setText(button.getName());

		return convertView;
	}

	private OnLongClickListener changeForDelete() {

		return new OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				final AppButton button = (AppButton) view.getTag();

				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

				builder.setMessage(getContext().getString(R.string.delete_button_msg)).setCancelable(false)
						.setPositiveButton(getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								remove(button);
								delete(button);
								dialog.cancel();
							}
						}).setNegativeButton(getContext().getString(R.string.no), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				builder.show();
				return true;
			}
		};
	}

	private void delete(AppButton button) {
		DataHelper.getDataHelper(getContext()).delete(button);
	}

	private static class ViewHolder {
		Button button;
		TextView text;
	}

	private OnClickListener play() {
		return new OnClickListener() {
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
}
