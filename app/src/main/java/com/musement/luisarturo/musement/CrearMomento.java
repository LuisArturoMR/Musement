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

public class CrearMomento extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTitulo;
    private EditText editTextDesc;
    private EditText editTextTime;

    private Button btnAceptar;
    private Button btnCancelar;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_momento);

        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);
        editTextDesc = (EditText) findViewById(R.id.editTextDesc);
        editTextTime = (EditText) findViewById(R.id.editTextTime);

        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(firebaseAuth.getCurrentUser() == null){
            firebaseAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            Toast.makeText(this, "Hello "+firebaseAuth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();
        }

        Firebase.setAndroidContext(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnAceptar){
            create();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        if (v == btnCancelar){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }

    public void create(){

        Firebase ref = new Firebase(Config.FIREBASE_URL);

        String titulo = editTextTitulo.getText().toString().intern();
        String desc = editTextDesc.getText().toString().intern();
        String time = editTextTime.getText().toString().intern();

        String uid = firebaseAuth.getCurrentUser().getUid();

        Moment moment = new Moment();

        moment.setTitulo(titulo);
        moment.setDescripcion(desc);
        moment.setTime(time);

        String tmp = moment.getTitulo();
        System.out.println("Titulo: " + tmp);

        ref.child("Moments").child(uid).setValue(moment);
        //ref.child("Moments").child(uid).child("usuarios").setValue(moment);
        Toast.makeText(this, "Updating data...",Toast.LENGTH_LONG).show();

    }
}
