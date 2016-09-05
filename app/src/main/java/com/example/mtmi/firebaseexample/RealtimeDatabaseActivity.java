package com.example.mtmi.firebaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDatabaseActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("kisiler").child("1");
        databaseReference.child("name").setValue("ahmet");
        databaseReference.child("surname").setValue("cengiz");
        databaseReference.child("age").setValue("25");
        databaseReference.child("deparment").setValue("CS");
        DatabaseReference itemsReference = databaseReference.child("yapilcaklar");

        itemsReference.child("0").setValue("hello");
        itemsReference.child("1").setValue("world - 2");
        itemsReference.child("2").setValue("tutorial");

       DatabaseReference otherReference=databaseReference.child("other");
        otherReference.child("0").setValue("deneme");
        otherReference.child("1").setValue("deneme2");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
