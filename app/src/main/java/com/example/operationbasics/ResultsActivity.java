package com.example.operationbasics;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    int quarter, additionScore, subtractionScore, totalScore;
    CharSequence resultScoreString, totalScoreString;
    final int QUARTER_1 = 0;
    final int QUARTER_2 = 1;
    TextView resultScore, resultScoreTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultScore = findViewById(R.id.resultScore);
        resultScoreTotal = findViewById(R.id.resultScoreTotal);

        Bundle extras = getIntent().getExtras();

        additionScore = extras.getInt("additionScore");
        subtractionScore = extras.getInt("subtractionScore");
        System.out.println(additionScore + "\n" + subtractionScore);
        totalScore = additionScore + subtractionScore;

        resultScoreString = "Addition Score: " + additionScore + "\n" + "Subtraction Score: " + subtractionScore;
        totalScoreString = "Total Score: " + totalScore;

        resultScore.setText(resultScoreString);
        resultScoreTotal.setText(totalScoreString);
    }
}