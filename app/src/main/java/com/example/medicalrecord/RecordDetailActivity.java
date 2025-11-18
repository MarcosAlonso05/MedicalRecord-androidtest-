package com.example.medicalrecord;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.medicalrecord.EXTRA_NAME";
    public static final String EXTRA_AGE = "com.example.medicalrecord.EXTRA_AGE";
    public static final String EXTRA_CONDITION = "com.example.medicalrecord.EXTRA_CONDITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        TextView textViewName = findViewById(R.id.textViewDetailName);
        TextView textViewAge = findViewById(R.id.textViewDetailAge);
        TextView textViewCondition = findViewById(R.id.textViewDetailCondition);
        FloatingActionButton fabBack = findViewById(R.id.fab_back);
        fabBack.setOnClickListener(view -> {
            finish();
        });

        String name = getIntent().getStringExtra(EXTRA_NAME);
        int age = getIntent().getIntExtra(EXTRA_AGE, 0);
        String condition = getIntent().getStringExtra(EXTRA_CONDITION);

        textViewName.setText(name);
        textViewAge.setText("Edad: " + age);
        textViewCondition.setText("Condición: " + condition);
    }
}
