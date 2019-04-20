package com.example.taha.pitchin;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth mAuth;
    Button logIn;
    Button signUp;
    EditText signInEmail, signInPassword;
    ProgressBar progressBar;

    RelativeLayout reallay2,reallay1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            reallay1.setVisibility(View.VISIBLE);
            reallay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        reallay1 = (RelativeLayout) findViewById(R.id.reallay1);
        reallay2 = (RelativeLayout) findViewById(R.id.reallay2);
        handler.postDelayed(runnable,2000);

        mAuth = FirebaseAuth.getInstance();

        signInEmail = findViewById(R.id.signInEmail);
        signInPassword = findViewById(R.id.signInPassword);

        logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(this);

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        progressBar = findViewById(R.id.progressbar);
    }

    private void userLogIn(){
        String Mail = signInEmail.getText().toString().trim();
        String Password = signInPassword.getText().toString().trim();

        if(Mail.isEmpty()){
            signInEmail.setError("An E-Mail ID is required to sign up");
            signInEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Mail).matches()){
            signInEmail.setError("Please enter a valid E-Mail ID");
            signInEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            signInPassword.setError("A password is required to sign up");
            signInPassword.requestFocus();
            return;
        }

        if (Password.length() < 6){
            signInPassword.setError("The minimum length of the password should be 6 characters");
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Mail,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent main = new Intent(SignInActivity.this, MainActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);
                }
            else{
                    Toast.makeText(SignInActivity.this,"You don't have an account, please sign up", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.logIn:
                userLogIn();
                break;

            case R.id.signUp:
                Intent signup = new Intent (this, Email.class);
                startActivity(signup);
                break;
        }

    }
}
