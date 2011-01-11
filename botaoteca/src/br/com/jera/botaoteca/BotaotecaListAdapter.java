package br.com.jera.botaoteca;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

public class BotaotecaListAdapter extends ArrayAdapter<Botao> {

    public BotaotecaListAdapter(Context context, List<Botao> objects) {
	super(context, R.layout.gridview_item, R.id.gridview_title, objects);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	LinearLayout itemLayout = (LinearLayout) super.getView(position, convertView, parent);
	
	final Botao button = getItem(position);

	Button nButton = (Button) itemLayout.findViewById(R.id.gridview_button);
	nButton.setTag(button);
	nButton.setOnClickListener(onClickListener);
	nButton.setBackgroundDrawable(getDrawable(button.getColor()));
	
	return itemLayout;
    }
    
    private OnClickListener onClickListener = new OnClickListener() {
	@Override
	public void onClick(View v) {
	    try {
		((Botao)v.getTag()).getSound().play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    };
}
