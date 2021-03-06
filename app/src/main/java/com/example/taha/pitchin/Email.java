package com.example.taha.pitchin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class Email extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText SignUpEmail, SignUpPassword;
    Button contSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mAuth = FirebaseAuth.getInstance();

        SignUpEmail = findViewById(R.id.SignUpEmail);
        SignUpPassword = findViewById(R.id.SignUpPassword);

        contSignUp = findViewById(R.id.contSignUp);
        contSignUp.setOnClickListener(this);

        progressBar = findViewById(R.id.progressbar);
    }

    private void registerUser(){
        String Mail = SignUpEmail.getText().toString().trim();
        String Password = SignUpPassword.getText().toString().trim();

        if(Mail.isEmpty()){
            SignUpEmail.setError("An E-Mail ID is required to sign up");
            SignUpEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Mail).matches()){
            SignUpEmail.setError("Please enter a valid E-Mail ID");
            SignUpEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            SignUpPassword.setError("A password is required to sign up");
            SignUpPassword.requestFocus();
            return;
        }

        if (Password.length() < 6){
            SignUpPassword.setError("The minimum length of the password should be 6 characters");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                //For a successful registration on the Firebase console
                if(task.isSuccessful()){
                    Toast.makeText(Email.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                }
                // Unsuccesful attempts with 2 cases
                else {
                    // If the user is already registered
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(Email.this,"You are already registered for this app", Toast.LENGTH_SHORT).show();
                    }
                    // Can not register for some other reason
                    else {
                        Toast.makeText(Email.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        startActivity(new Intent(this,id.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contSignUp:
                registerUser();
                break;
        }
    }
}
