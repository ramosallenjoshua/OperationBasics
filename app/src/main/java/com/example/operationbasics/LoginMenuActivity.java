package com.example.operationbasics;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginMenuActivity extends AppCompatActivity {

    ImageView loginButton;
    EditText emailEditText, passwordEditText;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);

        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.login_button);
        emailEditText = findViewById(R.id.user_input_email);
        passwordEditText = findViewById(R.id.user_input_password);

        loginButton.setOnClickListener(View->{
            emailEditText.getText().toString();
            passwordEditText.getText().toString();
        });

    }
}