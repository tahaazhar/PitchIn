package com.example.taha.pitchin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dob extends AppCompatActivity implements View.OnClickListener {
    EditText dob;
    Button contDOB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob);
        setContentView(R.layout.activity_email);

        dob = findViewById(R.id.dob);

        contDOB = findViewById(R.id.contDOB);
        contDOB.setOnClickListener(this);
    }

    private void registerUser(){
        String Mail = dob.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contDOB:
                registerUser();
                break;
        }

    }
}
