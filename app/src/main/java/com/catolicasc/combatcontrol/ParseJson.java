package com.catolicasc.combatcontrol;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    private static final String TAG = "ParseJson";
    private List<Robo> robos;

    public ParseJson() {
        robos = new ArrayList<>();
    }

    public List<Robo> getRobos() {
        return robos;
    }


//    public boolean parse(String jsonString) {
//        try {
//            JSONObject json = new JSONObject(jsonString);
//            JSONArray robos = json.getJSONArray("robo");
//
//            for (int i = 0; i < robos.length(); i++) {
//                JSONObject Robo = robos.getJSONObject(i);
//                Robo p = new Robo();
//                p.setNome(Robo.getString("nome"));
//
//                this.robos.add(p);
//            }
//
//            return true;
//        } catch (JSONException e) {
//            Log.e(TAG, "parse: erro ao fazer parse do JSON: " + e.getMessage());
//            return false;
//        }
//    }
}
