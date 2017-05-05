package com.musement.luisarturo.musement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class momentTest extends AppCompatActivity implements View.OnClickListener{

    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_test);


        aceptar = (Button)findViewById(R.id.AceptarMoment);
        aceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == aceptar){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
