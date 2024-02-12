package com.example.webpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=findViewById(R.id.btn_opnwebpage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebpage();
            }
        });
    }
    public void openWebpage(){
        Uri webpage=Uri.parse("https://powerbi.microsoft.com/en-in/desktop/");
        Intent intent =new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);
    }
}