package com.example.mtmi.firebaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mtmi.firebaseexample.dataClasses.Arabalar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArabaEkleActivity extends AppCompatActivity {

    EditText marka, model,plaka;
    Button btn2;
    Arabalar arabalar;

    String userId;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        marka = (EditText) findViewById(R.id.marka);
        model = (EditText) findViewById(R.id.model);
        plaka = (EditText) findViewById(R.id.plaka);

        btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("hello: ", " buton click");
                String markaInput = marka.getText().toString();
                String modelInput = model.getText().toString();
                String plakaInput = plaka.getText().toString();

                arabalar = new Arabalar(markaInput, modelInput);

                userId=auth.getCurrentUser().getUid();

                reference=database.getReference("Arabalar").child(plakaInput);
                reference.setValue(arabalar);

                reference = database.getReference("Kisiler").child(userId);

                DatabaseReference itemReference = reference.child("Plakalar").child("1");
                itemReference.setValue(plakaInput);


            }
        });
    }
}
