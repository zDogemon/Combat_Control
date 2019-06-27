package com.catolicasc.combatcontrol;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RobosListAdapter extends ArrayAdapter<Robo> {

    private static final String TAG = "PokemonListAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Robo> robos;
    private TextView txtRobo1;
    private TextView txtRobo2;



    public RobosListAdapter(Context context, int resource, List<Robo> robos) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.robos = robos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            Log.d(TAG, "getView: chamada com um convertView null");
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "getView: recebeu um convertView");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Robo roboAtual = robos.get(position);

        viewHolder.txtRobo1.setText(roboAtual.getNome());
        viewHolder.txtRobo2.setText(roboAtual.getNome());

        return convertView;
    }


    private class ViewHolder {
        final TextView txtRobo1;
        final TextView txtRobo2;


        ViewHolder(View v) {
            this.txtRobo1 = v.findViewById(R.id.txtRobo1);
            this.txtRobo2 = v.findViewById(R.id.txtRobo2);

        }
    }
}

