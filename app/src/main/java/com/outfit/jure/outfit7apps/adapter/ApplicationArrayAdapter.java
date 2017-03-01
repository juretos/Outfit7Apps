package com.outfit.jure.outfit7apps.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.outfit.jure.outfit7apps.R;
import com.outfit.jure.outfit7apps.model.App;

import java.util.ArrayList;

public class ApplicationArrayAdapter extends ArrayAdapter<App> {
    private final Context context;
    private final ArrayList<App> apps;

    public ApplicationArrayAdapter(Context context, ArrayList<App> apps) {
        super(context, R.layout.rowlayout, apps);
        this.context = context;
        this.apps = apps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        App item = getItem(position);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(item.getName());
        imageView.setImageDrawable(item.getIcon());
        return rowView;
    }
}