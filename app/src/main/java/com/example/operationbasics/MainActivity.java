package com.example.operationbasics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView testButton;
    ImageView assessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = findViewById(R.id.testButton);
        assessButton = findViewById(R.id.assesButton);

        testButton.setOnClickListener(View ->{
            Intent intent = new Intent(getApplicationContext(), MathProblemsActivity.class);
            startActivity(intent);
        });

        assessButton.setOnClickListener(View ->{
            Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
            startActivity(intent);
        });
    }
}