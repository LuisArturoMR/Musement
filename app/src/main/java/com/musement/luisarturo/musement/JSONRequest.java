package com.musement.luisarturo.musement;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gdaalumno on 3/28/17.
 */
public class JSONRequest extends AsyncTask<String, Void, JSONArray> {

    JSONRequestCallback jsonRequestCallback;

    public JSONRequest(JSONRequestCallback jsonRequestCallback){
        this.jsonRequestCallback = jsonRequestCallback;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        JSONArray jarray = null;

        try {

            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            int code = connection.getResponseCode();

            if(code == HttpURLConnection.HTTP_OK){

                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String curr = "";

                while((curr = br.readLine()) != null){
                    sb.append(curr);
                }

                jarray = new JSONArray(sb.toString());

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return jarray;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        jsonRequestCallback.done(jsonArray);
    }

    public interface JSONRequestCallback{
        void done(JSONArray jsonArray);
    }
}
