package com.musement.luisarturo.musement;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by gdaalumno on 3/28/17.
 */
public class JSONAdapter extends BaseAdapter {

    JSONArray jarray;
    Activity activity;

    public JSONAdapter(JSONArray jarray, Activity activity){
        this.jarray = jarray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return jarray.length();
    }

    @Override
    public Object getItem(int i) {

        try{
            return jarray.getJSONObject(i);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView id = (TextView)view.findViewById(R.id.id);
        try {
            id.setText("ID: " + jarray.getJSONObject(i).getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
