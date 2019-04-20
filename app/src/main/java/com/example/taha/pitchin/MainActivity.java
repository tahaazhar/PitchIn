package com.example.taha.pitchin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton newCommittee, current, history, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newCommittee= findViewById(R.id.newCommittee);
        newCommittee.setOnClickListener(this);

        current= findViewById(R.id.current);
        current.setOnClickListener(this);

        history= findViewById(R.id.history);
        history.setOnClickListener(this);

        about= findViewById(R.id.about);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newCommittee:
                Intent i= new Intent(this,newComittee.class);
                startActivity(i);
                Toast.makeText(this, "New Committee", Toast.LENGTH_LONG).show();
                break;

            case R.id.history:
                Intent j= new Intent(this,historyActivity.class);
                startActivity(j);
                Toast.makeText(this, "History", Toast.LENGTH_LONG).show();
                break;
            case R.id.about:
                Intent k= new Intent(this,aboutActivity.class);
                startActivity(k);
                Toast.makeText(this, "New About", Toast.LENGTH_LONG).show();
                break;
            case R.id.current:
                Intent l= new Intent(this,currentActivity.class);
                startActivity(l);
                Toast.makeText(this, "Current", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
