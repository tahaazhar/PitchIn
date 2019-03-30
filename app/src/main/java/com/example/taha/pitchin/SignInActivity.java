package com.example.taha.pitchin;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    Button Login;
    TextView TxtViewaPss;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}
