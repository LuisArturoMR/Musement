package com.musement.luisarturo.musement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
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
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void addTeam(View view){
        add();
    }

    public void create(){
        Firebase ref = new Firebase(Config.FIREBASE_URL_TEAMS);

        String name = createTeam.getText().toString().intern();

        String uid = firebaseAuth.getCurrentUser().getUid();

        Random r = new Random();
        int id = r.nextInt(80 - 65) + 65;

        ModelTeam team = new ModelTeam();

        team.setTitulo(name);
        team.setId(id);

        ref.child("Teams").child(uid).setValue(team);
        ref.child("Teams").child(uid).child("usuarios").setValue(uid);
        Toast.makeText(this, "New Team..."+ id,Toast.LENGTH_LONG).show();
    }

    public void add(){
        Firebase ref = new Firebase(Config.FIREBASE_URL);

        final int userTeamId = Integer.parseInt(addTeam.getText().toString());


        final String uid = firebaseAuth.getCurrentUser().getUid();

        ChildEventListener childEventListener = new ChildEventListener(){

            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Query query = ref.child("Teams").orderByChild("id").equalTo(userTeamId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists()) {
                     String key = dataSnapshot.getKey();
                     Toast.makeText(Team.this, "Added to your new Team ;)", Toast.LENGTH_SHORT).show();
                     updateUser(uid,userTeamId,key);

                 }
                 else{
                     Toast.makeText(Team.this, "Ups! Team doesn't exist", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onCancelled(FirebaseError firebaseError) {

             }
        });

    }

    private void updateUser(String user, int userTeamId, String key) {
        Firebase ref = new Firebase(Config.FIREBASE_URL);

        Toast.makeText(this, "Your team id is " + key, Toast.LENGTH_SHORT).show();
        // updating the user via child nodes
        ref.child("Teams").child(String.valueOf(key)).child("usuarios").setValue(user);
    }
}
