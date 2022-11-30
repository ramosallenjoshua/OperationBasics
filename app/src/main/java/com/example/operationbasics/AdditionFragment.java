package com.example.operationbasics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class AdditionFragment extends Fragment{

    int num1, num2, answer;
    EditText additionAnswerEditText;
    ImageView nextButton, quitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String question = num1 + " + " + num2 + " = ? ";


        return inflater.inflate(R.layout.fragment_addition, container, false);
    }
}