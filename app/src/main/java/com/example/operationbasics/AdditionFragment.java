package com.example.operationbasics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdditionFragment extends Fragment{

    int num1, num2, correctAnswer;
    EditText additionAnswerEditText;
    TextView additionQuestionText;
    ImageView nextButton, quitButton;
    DataController dataController;

    public AdditionFragment(DataController dataController){
        this.dataController = dataController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        additionQuestionText = view.findViewById(R.id.additionQuestionText);
        additionAnswerEditText = view.findViewById(R.id.additionAnswerEditText);
        nextButton = view.findViewById(R.id.additionNextButton);
        quitButton = view.findViewById(R.id.additionQuitButton);


        extractQuestions();
        String question = num1 + " + " + num2 + " = ? ";
        System.out.println(question);
        additionQuestionText.setText(question);

        nextButton.setOnClickListener(View->{
            String studentAnswerStr = additionAnswerEditText.getText().toString();
            int studentAnswer = Integer.parseInt(studentAnswerStr);
            dataController.additionScore(correctAnswer, studentAnswer);
            dataController.next();
        });
        quitButton.setOnClickListener(View->{
            dataController.quit();
        });
    }

    private void extractQuestions(){
        num1 = getArguments().getInt("num1");
        num2 = getArguments().getInt("num2");
        correctAnswer = getArguments().getInt("answer");
    }
}