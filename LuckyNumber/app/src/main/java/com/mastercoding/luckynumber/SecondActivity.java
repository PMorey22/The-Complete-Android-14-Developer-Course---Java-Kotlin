package com.mastercoding.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_btn);

        // Receiving the data from Main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // Generating Random Numbers
        int random_num= generateRandomNumber();
        luckyNumberTxt.setText(""+random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);
            }
        });

    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }


    public void shareData(String username, int randomNum){
        // Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // Additional Info
        i.putExtra(Intent.EXTRA_SUBJECT,username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: "+randomNum);

        startActivity(Intent.createChooser(i,"Choose a Platform"));



    }
}