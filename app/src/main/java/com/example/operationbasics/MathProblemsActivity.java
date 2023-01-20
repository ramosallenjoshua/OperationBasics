package com.example.operationbasics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class MathProblemsActivity extends AppCompatActivity implements DataController {

    int quarter, phase, counter;

    //QUARTERS
    final int QUARTER_1 = 1;
    final int QUARTER_2 = 2;

    //Q1 PHASES
    final int COUNTING = 1;
    final int PLACE_VALUE = 2;
    final int VALUE_ARANGEMENT = 3;

    //Q2 PHASES
    final int ADDITION = 1;
    final int SUBTRACTION = 2;

    //QUESTIONS STORAGE
    int[][] additionQuestions = new int[10][3];
    int[][] subtractionQuestions = new int[10][3];
    int[] countingQuestions = new int[5];
    int[][] placeValueQuestions = new int[5][2];
    int[] valueArangementQuestions = new int[5];

    //SCORE COUNTER
    int additionScore = 0;
    int subtractionScore = 0;
    int countingScore = 0;
    int placeValueScore = 0;
    int valueArangementScore = 0;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_problems);


    }

    @Override
    public void ScoreCounter(int correctAnswer, int studentAnswer) {
        switch(quarter){
            case QUARTER_1:
                switch(phase){
                    case COUNTING:
                        if(correctAnswer == studentAnswer){
                            countingScore++;
                        }
                        counter++;
                        break;
                    case PLACE_VALUE:
                        if(correctAnswer == studentAnswer){
                            placeValueScore++;
                        }
                        counter++;
                        break;
                    case VALUE_ARANGEMENT:
                        if(correctAnswer == studentAnswer){
                            valueArangementScore++;
                        }
                        counter++;
                        break;
                }
                break;
            case QUARTER_2:
                switch (phase){
                    case ADDITION:
                        if(correctAnswer == studentAnswer){
                            additionScore++;
                        }
                        counter++;
                        break;
                    case SUBTRACTION:
                        if(correctAnswer == studentAnswer){
                            subtractionScore++;
                        }
                        counter++;
                        break;
                }
                break;
        }
    }

    @Override
    public void quit() {
        finish();
    }

    @Override
    public void next() {

    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.testProblemFragment, fragment)
                .commit();
    }

    private void generateQuestions(){
        switch(quarter){
            case QUARTER_1:
                q1QuestionGenerator();
                break;
            case QUARTER_2:
                q2QuestionGenerator();
                break;
        }
    }

    private void q1QuestionGenerator(){
        for (int i = 0; i < 10; i++){
            int sum = 1 + random.nextInt(19);
            int addend1 = sum - random.nextInt(sum);
            int addend2 = sum - addend1;

            additionQuestions[i][0] = addend1;
            additionQuestions[i][1] = addend2;
            additionQuestions[i][2] = sum;
        }
        for(int i = 0; i < 10; i++){
            int subtrahend1 = 1 + random.nextInt(19);
            int subtrahend2 = subtrahend1 - random.nextInt(subtrahend1);
            int difference = subtrahend1 - subtrahend2;

            subtractionQuestions[i][0] = subtrahend1;
            subtractionQuestions[i][1] = subtrahend2;
            subtractionQuestions[i][2] = difference;
        }
    }

    private void q2QuestionGenerator(){
        for(int i = 0; i < countingQuestions.length; i++){
            countingQuestions[i] = 1 + random.nextInt(19);
        }

        for(int i = 0; i < 5; i++){
            int digit1 = 1 + random.nextInt(8);
            int digit2 = 1 + random.nextInt(8);

            if (digit1 == digit2){
                digit2--;
            }

            placeValueQuestions[i][0] = digit1;
            placeValueQuestions[i][1] = digit2;
        }

        int valueArangement = 5 + random.nextInt(15);
        for (int i = 0; i < valueArangementQuestions.length; i++){
            valueArangementQuestions[i] = valueArangement - i;
        }
    }

    private void resetCounter(){
        counter = 0;
    }

    private void sendQuestions(int num1, int num2, int answer, Fragment fragment){
        Bundle bd = new Bundle();
        bd.putInt("num1", num1);
        bd.putInt("num2", num2);
        bd.putInt("answer", answer);
        fragment.setArguments(bd);
    }
}