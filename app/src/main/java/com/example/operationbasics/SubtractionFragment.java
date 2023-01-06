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

public class SubtractionFragment extends Fragment {

    int num1, num2, correctAnswer;
    EditText subtractionAnswerEditText;
    TextView subtractionQuestionText;
    ImageView nextButton, quitButton;
    DataController dataController;

    public SubtractionFragment(DataController dataController){
        this.dataController = dataController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_subtraction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subtractionQuestionText = view.findViewById(R.id.subtractionQuestionText);
        subtractionAnswerEditText = view.findViewById(R.id.subtractionAnswerEditText);
        nextButton = view.findViewById(R.id.subtractionNextButton);
        quitButton = view.findViewById(R.id.subtractionQuitButton);


        extractQuestions();
        String question = num1 + " - " + num2 + " = ? ";
        System.out.println(question);
        subtractionQuestionText.setText(question);

        nextButton.setOnClickListener(View->{
            String studentAnswerStr = subtractionAnswerEditText.getText().toString();
            int studentAnswer = Integer.parseInt(studentAnswerStr);
            dataController.subtractionScore(correctAnswer, studentAnswer);
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