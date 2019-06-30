package com.catolicasc.combatcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RobosListAdapter extends ArrayAdapter {
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private ArrayList<Robo> robos;

    public RobosListAdapter(Context context, int resource, ArrayList<Robo> robos) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.robos = robos;
    }

    @Override
    public int getCount() {
        return robos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Robo roboAtual = robos.get(position);

        viewHolder.robo1.setText(roboAtual.getRobo1());

        viewHolder.robo2.setText(roboAtual.getRobo2());

        return convertView;
    }

    private class ViewHolder {
        final TextView robo1;
        final TextView robo2;

        ViewHolder(View v) {
            this.robo1 = v.findViewById(R.id.txtRobo1LV);
            this.robo2 = v.findViewById(R.id.txtRobo2LV);
        }
    }
}