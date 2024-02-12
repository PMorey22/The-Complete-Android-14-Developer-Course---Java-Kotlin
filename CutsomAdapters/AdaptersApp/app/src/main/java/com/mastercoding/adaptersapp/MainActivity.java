package com.mastercoding.adaptersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1- AdapterView: ListView
        listview = findViewById(R.id.listview);

        // 2- Data Source: String Array
        String[] countries = {"USA", "Germany", "Saudi Arabia","France"};


        // 3- Adapter: acts as a bridge between the
        //            'data source' and the 'AdapterView'
        MyCustomAdapter adapter = new MyCustomAdapter(this, countries);

        // Link Listview with the Adapter
        listview.setAdapter(adapter);

    }
}