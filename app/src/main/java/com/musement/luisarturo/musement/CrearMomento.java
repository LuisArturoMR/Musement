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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CrearMomento extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTitulo;
    private EditText editTextDesc;
    private EditText editTextTime;

    private Button btnAceptar;
    private Button btnCancelar;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private DatabaseReference mDatabase;

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

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Firebase.setAndroidContext(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnAceptar){
            //create();
            submitPost();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        if (v == btnCancelar){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }

    private void submitPost(){
        final String titulo = editTextTitulo.getText().toString();
        final String desc = editTextDesc.getText().toString();

        final String userId = firebaseAuth.getCurrentUser().getUid();
        mDatabase.child("moments").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Moment moment = dataSnapshot.getValue(Moment.class);

                writeNewPost(userId, titulo, desc);

                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void writeNewPost(String userId, String titulo, String desc){

        String key = mDatabase.child("moment").push().getKey();
        Moment moment = new Moment(userId, titulo, desc);
        Map<String, Object> mapValues = moment.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/moments/" + key, mapValues);
        childUpdates.put("/user-moments/" + userId + "/" + key, mapValues);

        mDatabase.updateChildren(childUpdates);

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
