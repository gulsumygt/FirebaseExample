package com.example.mtmi.firebaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mtmi.firebaseexample.dataClasses.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText email,password,ad,soyad,tel_no,tc_no;
    Button signup;
    Person person;
    private String userId;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.sifre);
        ad= (EditText) findViewById(R.id.ad);
        soyad= (EditText) findViewById(R.id.soyad);
        tel_no= (EditText) findViewById(R.id.tel_no);
        tc_no= (EditText) findViewById(R.id.tc_no);

        signup= (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailInput=email.getText().toString();
                String passInput=password.getText().toString();
                String adInput=ad.getText().toString();
                String soyadInput=soyad.getText().toString();
                String tel_noInput=tel_no.getText().toString();
                String tc_noInput=tc_no.getText().toString();

                person=new Person(adInput,emailInput,passInput,soyadInput,tc_noInput,tel_noInput);

                if(TextUtils.isEmpty(emailInput)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passInput)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passInput.length() < 5 ){
                    Toast.makeText(getApplicationContext(),"şifre 5 karakten az olamaz", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(emailInput,passInput).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"Authentication failed",Toast.LENGTH_SHORT);
                        }
                        else{

                            userId=firebaseAuth.getCurrentUser().getUid();

                            databaseReference=firebaseDatabase.getReference("Kisiler").child(userId);

                          databaseReference.setValue(person);

                            Log.v("giriş: ","yapıldı");
                            startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                });

            }
        });

    }
}
