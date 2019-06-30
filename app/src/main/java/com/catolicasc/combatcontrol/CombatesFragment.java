package com.catolicasc.combatcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class CombatesFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private ListView lvRobos;
    private static final String robots = "https://raw.githubusercontent.com/zDogemon/Combat_Control/master/robots.json";
    private ArrayList<Robo> robos;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_combates, container, false);

        robos = new ArrayList<>();
        lvRobos = v.findViewById(R.id.lvRobos);


        DataDownload dataDownload = new DataDownload();
        String jsonString = null;
        try {
            jsonString = dataDownload.execute(robots).get();
            JSONTokener jsonTokener = new JSONTokener(jsonString);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            JSONArray jsonArray = jsonObject.getJSONArray("partidas");
            for(int i = 0;i<jsonArray.length();i++) {
                Robo robo = new Robo(jsonArray.getJSONObject(i).getString("robot1"),jsonArray.getJSONObject(i).getString("robot2"));
                robos.add(robo);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RobosListAdapter robosListAdapter = new RobosListAdapter(getContext(), R.layout.activity_robos_list_adapter, robos);
        lvRobos.setAdapter(robosListAdapter);
        lvRobos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView c = (TextView) view.findViewById(R.id.txtRobo1LV);
                String robo1x = c.getText().toString();

                TextView c2 = (TextView) view.findViewById(R.id.txtRobo2LV);
                String robo2x = c2.getText().toString();

                Intent i = new Intent(getContext(), LobbyActivity.class);
                i.putExtra("robo1", robo1x);
                i.putExtra("robo2", robo2x);
                startActivity(i);
            }
        });
        return v;

    }
}
