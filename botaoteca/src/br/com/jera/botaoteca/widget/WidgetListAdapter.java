package br.com.jera.botaoteca.widget;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.jera.botaoteca.Botao;
import br.com.jera.botaoteca.R;

public class WidgetListAdapter extends BaseAdapter {

    private List<Botao> buttons;
    private Context context;

    public WidgetListAdapter(List<Botao> buttons, Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	Resources resource = context.getResources();
	Botao button = buttons.get(position);
	LinearLayout itemLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
	ImageView imageView = (ImageView) itemLayout.findViewById(R.id.listImage);
	imageView.setBackgroundDrawable(resource.getDrawable(button.getColor().getNormal()));
	TextView textView = (TextView) itemLayout.findViewById(R.id.listText);
	textView.setText(button.getName());
	return itemLayout;
    }

}
