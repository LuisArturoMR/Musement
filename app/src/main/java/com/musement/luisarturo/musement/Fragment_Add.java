package com.musement.luisarturo.musement;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



/**
 * Fragment class for each nav menu item
 */
public class Fragment_Add extends android.app.Fragment implements View.OnClickListener{

    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    private String mText;
    private int mColor;

    private View mContent;
    private TextView mTextView;



    private Button btnCrear;
    private Button btnBorrar;
    private Button btnEditar;

    public Fragment_Add() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnCrear = (Button) view.findViewById(R.id.buttonCrear);
        btnBorrar = (Button) view.findViewById(R.id.buttonBorrar);
        btnEditar = (Button) view.findViewById(R.id.buttonEditar);

        btnCrear.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        outState.putInt(ARG_COLOR, mColor);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        if(v == btnCrear){
            //Intent intent = new Intent(getActivity(), CrearMomento.class);
            //startActivity(intent);
        }

        if(v == btnBorrar){
            //Intent intent = new Intent(getActivity(), BorrarMomento.class);
            //startActivity(intent);
        }
    }

}
