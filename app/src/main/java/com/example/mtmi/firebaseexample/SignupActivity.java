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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText email,password;
    Button signin,signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth=FirebaseAuth.getInstance();

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        signin= (Button) findViewById(R.id.signin);
        signup= (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailInput=email.getText().toString();
                String passInput=password.getText().toString();

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
                            startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                });

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("button: ","button clicked");

                String emailInput=email.getText().toString();
                final String passInput=password.getText().toString();

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

                firebaseAuth.signInWithEmailAndPassword(emailInput,passInput).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            finish();
                        } else{
                            //yorum
                            Toast.makeText(SignupActivity.this,"wrong password or email!",Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
    }
}
