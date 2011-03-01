package br.com.jera.botaoteca.widget;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import br.com.jera.botaoteca.AppButton;
import br.com.jera.botaoteca.R;

public class WidgetListAdapter extends ArrayAdapter<AppButton> {

    public WidgetListAdapter(Context context, List<AppButton> objects) {
	super(context, R.layout.list_item, R.id.listText, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View itemLayout = super.getView(position, convertView, parent);
	
	Resources resource = getContext().getResources();
	ImageView imageView = (ImageView) itemLayout.findViewById(R.id.listImage);
	imageView.setBackgroundDrawable(resource.getDrawable(getItem(position).getColor().getNormal()));
	
	return itemLayout;
    }

}
