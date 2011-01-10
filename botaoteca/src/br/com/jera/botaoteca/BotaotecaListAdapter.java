package br.com.jera.botaoteca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BotaotecaListAdapter extends BaseAdapter {

    private List<Botao> buttons;
    private Context context;
    private Map<Integer, OnClickListener> listenters = new HashMap<Integer, View.OnClickListener>();

    public BotaotecaListAdapter(List<Botao> buttons, Context context) {
	this.buttons = buttons;
	this.context = context;
    }

    @Override
    public int getCount() {
	return buttons.size();
    }

    @Override
    public Object getItem(int index) {
	return buttons.get(index);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    private Drawable getDrawable(ButtonColor color) {
	if (color.equals(ButtonColor.GREEN)) {
	    return context.getResources().getDrawable(R.drawable.btn_green);
	}
	if (color.equals(ButtonColor.BLUE)) {
	    return context.getResources().getDrawable(R.drawable.btn_blue);
	}
	if (color.equals(ButtonColor.RED)) {
	    return context.getResources().getDrawable(R.drawable.btn_red);
	}
	if (color.equals(ButtonColor.YELLOW)) {
	    return context.getResources().getDrawable(R.drawable.btn_yellow);
	}
	if (color.equals(ButtonColor.ORANGE)) {
	    return context.getResources().getDrawable(R.drawable.btn_orange);
	}
	return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	LinearLayout itemLayout;
	final Botao button = buttons.get(position);
	if (convertView == null) {
	    itemLayout = (LinearLayout) LayoutInflater.from(context).inflate(
		    R.layout.gridview_item, parent, false);
	    listenters.put(position, new OnClickListener() {
		@Override
		public void onClick(View v) {
		    try {
			button.getSound().play();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	} else {
	    itemLayout = (LinearLayout) convertView;
	}

	android.widget.Button nButton = (android.widget.Button) itemLayout.findViewById(R.id.gridview_button);
	nButton.setOnClickListener(listenters.get(position));
	nButton.setBackgroundDrawable(getDrawable(button.getColor()));
	TextView title = (TextView) itemLayout
		.findViewById(R.id.gridview_title);
	title.setText(button.getName());
	return itemLayout;
    }
}
