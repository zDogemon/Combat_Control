package com.catolicasc.combatcontrol;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RobosListAdapter extends ArrayAdapter<Robo> {

    private static final String TAG = "PokemonListAdapter";
    private List<Robo> robos;
    private TextView txtRobo1;
    private TextView txtRobo2;


    public RobosListAdapter(Activity context, ArrayList<Robo> robo) {
        super(context, 0, robo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_robos_list_adapter, parent, false
            );
        }

        Robo robo = getItem(position);

        TextView txtRobo1 = (TextView) listItemView.findViewById(R.id.txtRobo1);
        txtRobo1.setText(robo.getNome());

        TextView txtRobo2 = (TextView) listItemView.findViewById(R.id.txtRobo2);
        txtRobo1.setText(robo.getNome());

        return listItemView;
    }
}

