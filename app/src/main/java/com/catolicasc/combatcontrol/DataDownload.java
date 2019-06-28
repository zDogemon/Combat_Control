package com.catolicasc.combatcontrol;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataDownload extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String jsonFeed = downloadJson(strings[0]);
        if (jsonFeed == null) {
            //Toast.makeText(MainActivity.this, "Erro ao baixar arquivo", Toast.LENGTH_SHORT).show();
        }
        return jsonFeed;
    }

    private String downloadJson(String urlString) {
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int resposta = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            int charsLidos;
            char[] inputBuffer = new char[5];
            while (true) {
                charsLidos = reader.read(inputBuffer);
                if (charsLidos < 0) {
                    break;
                }
                if (charsLidos > 0) {
                    json.append(
                            String.copyValueOf(inputBuffer, 0, charsLidos));
                }
            }
            reader.close();
            return json.toString();

        } catch (MalformedURLException e) {
            //Toast.makeText(MainActivity.this, "URL é inválida", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // Toast.makeText(MainActivity.this, "Ocorreu um erro de IO ao baixar dados", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
