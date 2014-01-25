package com.jfmyers9;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LagerAdapter extends BaseAdapter {
    private ArrayList<LagerEntry> lagers;
    private Context context;
    private LayoutInflater layoutInflater;

    public LagerAdapter(Context context, ArrayList<LagerEntry> lagers) {
        this.lagers = lagers;
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lagers.size();
    }

    @Override
    public Object getItem(int position) {
        return lagers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lagers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.lager_grid_item, parent, false);
        }
        LagerEntry lager = (LagerEntry) getItem(position);

        TextView lagerName = (TextView) convertView.findViewById(R.id.grid_name);
        lagerName.setText(lager.getName());

        ImageView lagerImg = (ImageView) convertView.findViewById(R.id.grid_image);
        lagerImg.setImageURI(Uri.parse(lager.getImage()));
        return convertView;
    }
}
