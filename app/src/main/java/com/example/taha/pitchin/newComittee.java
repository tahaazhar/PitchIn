package com.example.taha.pitchin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class newComittee extends AppCompatActivity {


    String[] type = { "Traditional","Auctional"};
    String[] member = { "Customize","Random"};
    RadioGroup radioType , radioMembers, radioPeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comittee);

        EditText saveAmount = findViewById(R.id.saveAmount);
        EditText payment = findViewById(R.id.payment);





        RadioGroup radioType1 = (RadioGroup) findViewById(R.id.radioType);

        radioType1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioTraditional:
                        // do the operations
                        Toast.makeText(newComittee.this, "Traditional", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioAuctional:
                        // do the operations
                        Toast.makeText(newComittee.this, "Auctional", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        RadioGroup radioType2 = (RadioGroup) findViewById(R.id.radioMembers);

        radioType2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioRandom:
                        // do the operations
                        Toast.makeText(newComittee.this, "Random", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioCustomize:
                        // do the operations
                        Toast.makeText(newComittee.this, "Customize", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        RadioGroup radioType3 = (RadioGroup) findViewById(R.id.radioPeriod);

        radioType3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioWeekly:
                        // do the operations
                        Toast.makeText(newComittee.this, "Weekly", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioMonthly:
                        // do the operations
                        Toast.makeText(newComittee.this, "Monthly", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }
}
