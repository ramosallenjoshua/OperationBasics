package com.example.operationbasics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginMenuActivity extends AppCompatActivity {

    Button loginButton;
    TextView signUpButton;
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
        signUpButton = findViewById(R.id.login_signUpButton);

        loginButton.setOnClickListener(View->{
            String emailText = emailEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();

            if(emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Email or Password is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(Task->{
                if(Task.isSuccessful()){
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "Login Failed. Error: "
                            + Task.getException().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        });

        signUpButton.setOnClickListener(View->{
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}