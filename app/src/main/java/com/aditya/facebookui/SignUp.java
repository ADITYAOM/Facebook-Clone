package com.aditya.facebookui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private static final String TAG = SignUp.class.getSimpleName();
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConf;
    private Button mSignUp;
    private Button mLogIn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mEmail = (EditText) findViewById(R.id.editTextemail_signup);
        mPassword= (EditText) findViewById(R.id.editTextTextPassword);
        mPasswordConf = (EditText) findViewById(R.id.editTextTextPasswordConnfirmation);
        mSignUp = (Button) findViewById(R.id.button2);
        mLogIn = (Button) findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.this.startActivity(new Intent(SignUp.this, MainActivity.class));
                SignUp.this.finish();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.this.registerUser();
            }
        });

    }


    private void registerUser() {
        String userEmail = mEmail.getText().toString().trim();
        String userPassword = mPassword.getText().toString().trim();
        String confPassword = mPasswordConf.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail)) {
            showToast("Enter email address!");
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            showToast("Enter Password!");
            return;
        }

        if(TextUtils.isEmpty(confPassword)){
            showToast("Enter Password!");
            return;
        }

        if(!userPassword.equals(confPassword)){
            showToast("Password does not match!");
            return;
        }


        //register user
        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "New user registration: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            SignUp.this.showToast("Authentication failed. " + task.getException());
                        } else {
                            SignUp.this.startActivity(new Intent(SignUp.this, Home.class));
                            SignUp.this.finish();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

}
