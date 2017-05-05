package com.musement.luisarturo.musement;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


/**
 * Fragment class for each nav menu item
 */
public class Fragment_Dashboard extends Fragment implements JSONRequest.JSONRequestCallback, AdapterView.OnItemClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    JSONRequest r;
    ListView lv;
    JSONArray array;

    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    private String mText;
    private int mColor;

    private OnFragmentInteractionListener mListener;

    public Fragment_Dashboard(){
        //empty constructor
    }

    public static Fragment_Dashboard newInstance(String param1, String param2) {
        Fragment_Dashboard fragment = new Fragment_Dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        r = new JSONRequest(this);
        r.execute("https://jsonplaceholder.typicode.com/posts");

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void done(JSONArray jsonArray) {
        array = jsonArray;
        JSONAdapter adpt = new JSONAdapter(jsonArray, this.getActivity());
        lv = (ListView)getActivity().findViewById(R.id.listview);
        lv.setAdapter(adpt);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            String title = array.getJSONObject(i).getString("title");
            String body = array.getJSONObject(i).getString("body");

            

            mListener.changeFragment(title, body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void changeFragment(String title, String body);
    }


}
