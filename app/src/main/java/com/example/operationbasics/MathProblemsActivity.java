package com.example.operationbasics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class MathProblemsActivity extends AppCompatActivity implements DataController {

    final int ADDITION_PHASE = 0;
    final int SUBTRACTION_PHASE = 1;
    final int FINISH_PHASE = 2;
    int phase;
    int additionScore = 0;
    int subtractionScore = 0;
    int additionQuestionCounter = 0;
    int subtractionQuestionCounter = 0;
    int[][] additionQuestions = new int[10][3];
    int[][] subtractionQuestions = new int[10][3];
    Fragment additionFragment;
    Fragment subtractionFragment;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_problems);

    }
    @Override
    public void additionScore(int correctAnswer, int studentAnswer) {
        if(studentAnswer == correctAnswer){
            additionScore++;

        }
        additionQuestionCounter++;
        System.out.println("additionscore:" + additionScore);
        System.out.println("counter: "+additionQuestionCounter);
    }

    @Override
    public void subtractionScore(int correctAnswer, int studentAnswer) {
        if(studentAnswer == correctAnswer){
            subtractionScore++;
        }
        subtractionQuestionCounter++;
    }

    @Override
    public void quit() {
        phase = ADDITION_PHASE;
        additionScore = 0;
        subtractionScore = 0;
        additionQuestionCounter = 0;
        subtractionQuestionCounter = 0;
        finish();
    }

    @Override
    public void next() {
        if(additionQuestionCounter > 9){
            setPhase(SUBTRACTION_PHASE);
        }
        if(subtractionQuestionCounter > 9){
            setPhase(FINISH_PHASE);
        }
        System.out.println("Phase: "+phase);

        switch(phase){
            case ADDITION_PHASE:
                additionFragment = new AdditionFragment(MathProblemsActivity.this);
                sendQuestions(
                        additionQuestions[additionQuestionCounter][0],
                        additionQuestions[additionQuestionCounter][1],
                        additionQuestions[additionQuestionCounter][2],
                        additionFragment);
                openFragment(additionFragment);
                break;

            case SUBTRACTION_PHASE:
                subtractionFragment = new SubtractionFragment(MathProblemsActivity.this);
                sendQuestions(
                        subtractionQuestions[subtractionQuestionCounter][0],
                        subtractionQuestions[subtractionQuestionCounter][1],
                        subtractionQuestions[subtractionQuestionCounter][2],
                        subtractionFragment);
                openFragment(subtractionFragment);
                break;

            case FINISH_PHASE:
                Intent intent = new Intent(this, ResultsActivity.class)
                        .putExtra("additionScore", additionScore)
                        .putExtra("subtractionScore", subtractionScore);

                startActivity(intent);
                finish();
        }
    }

    private void setPhase(int phase){
        this.phase = phase;
    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.testProblemFragment, fragment)
                .commit();
    }

    private void generateQuestions(){
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

    private void sendQuestions(int num1, int num2, int answer, Fragment fragment){
        Bundle bd = new Bundle();
        bd.putInt("num1", num1);
        bd.putInt("num2", num2);
        bd.putInt("answer", answer);
        fragment.setArguments(bd);
    }
}