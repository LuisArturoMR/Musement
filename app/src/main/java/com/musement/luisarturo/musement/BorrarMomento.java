package com.musement.luisarturo.musement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BorrarMomento extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTitulo;

    private Button btnAceptar;
    private Button btnCancelar;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_momento);


        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);

        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Firebase.setAndroidContext(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btnAceptar){
            delete();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        if (v == btnCancelar){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
    }

    public void delete(){
        Firebase ref = new Firebase(Config.FIREBASE_URL);

        String titulo = editTextTitulo.getText().toString().intern();
        String uid = firebaseAuth.getCurrentUser().getUid();

        Moment moment = new Moment();
        moment.setTitulo(titulo);

        ref.child("Moments").child(uid).removeValue();
        Toast.makeText(this, "Updating data...",Toast.LENGTH_LONG).show();

    }
}
