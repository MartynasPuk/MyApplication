package com.example.martynas.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.martynas.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;

import static android.support.constraint.Constraints.TAG;

public class LoginActivity extends Activity {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private EditText pass;
    private AutoCompleteTextView username;
    private Button button;

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = findViewById(R.id.password);
        username = findViewById(R.id.email);
        button = findViewById(R.id.email_sign_in_button);


        mAuth = FirebaseAuth.getInstance();


        /*//button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onStart() {
                super.onStart();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                mAuth.signInWithEmailAndPassword(username.toString(), password.toString());
                /*if(user != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    test.setText("Wrong password or e-mail");
                }
                updateUI(currentUser);
            }*/
        //});*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(username.getText().toString(), pass.getText().toString());
            }
        });
    }

    //button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //mAuth.signInWithEmailAndPassword(username.toString(), password.toString());
                /*if(user != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    test.setText("Wrong password or e-mail");
                }*/
        //updateUI(currentUser);
    }
    //});


    private void signIn(String username, String pass) {
        Log.d(TAG, "signIn:"+username);
        if(!validateForm()) {
            return;
        }

        //showProgressDialog();

        mAuth.signInWithEmailAndPassword(username, String.valueOf(pass))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }



    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String password = pass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pass.setError("Required.");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }

    public void updateUI(FirebaseUser user) {
        if(user != null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, "E-mail or password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}