package com.catolicasc.combatcontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CombatesFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private ListView lvRobos;
    private static final String robots = "https://github.com/zDogemon/Combat_Control/blob/master/robots.json";
    private List<Robo> robos;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_combates, container, false);


        //lvRobos = findViewById(R.id.lvRobos);
       // DownloadDeDados down = new DownloadDeDados();
        //down.execute(robots);

    }

    private class DownloadDeDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String json = downloadJson(strings[0]);
            if (json == null) {
                Log.e(TAG, "doInBackground: Erro baixando RSS");
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParseJson parseJson = new ParseJson();
            parseJson.parse(s);
            robos = parseJson.getRobos();

//            RobosListAdapter robosListAdapter = new RobosListAdapter (CombatesFragment.this,
//                    R.layout.activity_robos_list_adapter, parseJson.getRobos());
//            lvRobos.setAdapter(robosListAdapter);

        }

        private String downloadJson(String urlString) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int resposta = connection.getResponseCode();
                Log.d(TAG, "downloadJson: O código de resposta foi: " + resposta);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                int charsLidos;
                char[] inputBuffer = new char[500];
                while (true) {
                    charsLidos = reader.read(inputBuffer);
                    if (charsLidos < 0) {
                        break;
                    }
                    if (charsLidos > 0) {
                        stringBuilder.append(
                                String.copyValueOf(inputBuffer, 0, charsLidos));
                    }
                }
                reader.close();
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadJson: URL é inválida " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downloadJson: Ocorreu um erro de IO ao baixar dados: "
                        + e.getMessage());
            }
            return null;
        }
    }

        }

