package com.example.operationbasics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MathProblemsActivity extends AppCompatActivity implements DataController {

    final int ADDITION_PHASE = 0;
    final int SUBTRACTION_PHASE = 1;
    final int FINISH_PHASE = 2;
    int phase = ADDITION_PHASE;
    int additionScore = 0;
    int subtractionScore = 0;
    int additionQuestionCounter = 0;
    int subtractionQuestionCounter = 0;
    Fragment additionFragment = new AdditionFragment();
    Fragment subtractionFragment = new SubtractionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_problems);

        openFragment(additionFragment);
    }

    @Override
    public void additionScore(int correctAnswer, int studentAnswer) {
        if(studentAnswer == correctAnswer){
            additionScore++;
        }
        additionQuestionCounter++;
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
    }

    @Override
    public void next() {
        if(additionQuestionCounter < 9){
            setPhase(SUBTRACTION_PHASE);
        }
        if(subtractionQuestionCounter < 9){
            setPhase(FINISH_PHASE);
        }

        switch(phase){
            case ADDITION_PHASE:
                openFragment(additionFragment);
                break;

            case SUBTRACTION_PHASE:
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
}