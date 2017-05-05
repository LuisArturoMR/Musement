package com.musement.luisarturo.musement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Team extends AppCompatActivity {

    private EditText createTeam, addTeam;
    private Button bCreateTeam, bAddTeam;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        createTeam = (EditText) findViewById(R.id.editText3);
        addTeam = (EditText) findViewById(R.id.editText4);

        bCreateTeam = (Button)findViewById(R.id.button3);
        bAddTeam = (Button)findViewById(R.id.button4);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Firebase.setAndroidContext(this);
    }

    public void createTeam(View view){
        create();
    }

    public void create(){
        Firebase ref = new Firebase(Config.FIREBASE_URL);

        String name = createTeam.getText().toString().intern();

        String uid = firebaseAuth.getCurrentUser().getUid();

        Random r = new Random();
        int id = r.nextInt(80 - 65) + 65;

        ModelTeam team = new ModelTeam();

        team.setTitulo(name);
        team.setId(id);

        ref.child("Teams").child(uid).setValue(team);
        Toast.makeText(this, "New Team..."+ id,Toast.LENGTH_LONG).show();
    }
}
