package com.mastercoding.widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox box1, box2;
    Button btn;

    RadioGroup radioGroup;

    Spinner spinner;

    TimePicker timePicker;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checkbox
        box1 = findViewById(R.id.checkbox1);
        box2 = findViewById(R.id.checkbox2);


        //  Radio Group - Radio Buttons
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                RadioButton radioButton = findViewById(checked);
                Toast.makeText(MainActivity.this,
                        "Selected: "+radioButton.getText(),
                        Toast.LENGTH_LONG).show();



            }
        });


        // Spinner
        spinner = findViewById(R.id.spinner);

        String[] courses = {"C++", "Java", "Kotlin", "Data Structures"};

        ArrayAdapter ad = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                courses);

        ad.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,
                        "You Select: "+courses[i],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        // Time Picker
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display the time picker
                DialogFragment  timePickerFrag =
                        new TimePickerFragment();

                timePickerFrag.show(
                        getSupportFragmentManager(),
                        "Pick Time Now:"
                );


            }
        });


        Button b3 = findViewById(R.id.btn_date);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment =
                        new DatePickerFragment();

                dialogFragment.show(
                        getSupportFragmentManager(),
                        "Pick A Date"
                );
            }
        });


        // Progress bar
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(75);
      //  progressBar.incrementProgressBy()









        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase Progress by 10%
                progressBar.incrementProgressBy(10);





                if (box1.isChecked()){
                    Toast.makeText(MainActivity.this,
                            "Cheese Topping is added", Toast.LENGTH_SHORT).show();
                }else{

                }

                if (box2.isChecked()){
                    Toast.makeText(MainActivity.this,
                            "Tomato Topping is added", Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });







    }
}