package com.example.operationbasics;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText fName, lName, school, email, password, confPassword;
    Button signUpButton;
    TextView signupLoginButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fName = findViewById(R.id.firstNameEditText);
        lName = findViewById(R.id.lastNameEditText);
        school = findViewById(R.id.schoolEditText);
        email = findViewById(R.id.signUpEmailEditText);
        password = findViewById(R.id.signUpPasswordEditText);
        confPassword = findViewById(R.id.signUpConfirmPasswordEditText);
        signUpButton = findViewById(R.id.signUpLayout_SignUpButton);
        signupLoginButton = findViewById(R.id.signup_LoginTextButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        signUpButton.setOnClickListener(View->{
            //Variables
            String fNameStr, lNameStr, schoolStr, emailStr, passwordStr, confPasswordStr, passwordRegex, emailRegex;
            Pattern regexPattern;
            Matcher passwordPatternMatcher, emailPatternMatcher;

            fNameStr = fName.getText().toString();
            lNameStr = lName.getText().toString();
            schoolStr = school.getText().toString();
            emailStr = email.getText().toString();
            passwordStr = password.getText().toString();
            confPasswordStr = confPassword.getText().toString();

            //Email Pattern
            emailRegex = "^(.+)@(.+)$";
            regexPattern = Pattern.compile(emailRegex);
            emailPatternMatcher = regexPattern.matcher(emailStr);

            //Password Pattern
            passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
            regexPattern = Pattern.compile(passwordRegex);
            passwordPatternMatcher = regexPattern.matcher(passwordStr);

            //Validation
            //Missing Field Validation
            if(fNameStr.isEmpty()){
                Toast.makeText(this, "First Name is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            if(lNameStr.isEmpty()){
                Toast.makeText(this, "Last Name is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            if(schoolStr.isEmpty()){
                Toast.makeText(this, "Institution/School is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            if(emailStr.isEmpty()){
                Toast.makeText(this, "Email is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            if(passwordStr.isEmpty()){
                Toast.makeText(this, "Password is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            if(confPasswordStr.isEmpty()){
                Toast.makeText(this, "Confirm Password is missing", Toast.LENGTH_SHORT).show();
                return;
            }

            //Email Pattern Validation
            if(!emailPatternMatcher.matches()){
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
                return;
            }

            //Password Length Validation
            if(passwordStr.length() < 8 || passwordStr.length() > 16){
                Toast.makeText(this, "Password must have 8-16 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            //Password Pattern Validation
            if(!passwordPatternMatcher.matches()){
                Toast.makeText(this, "Password must contain at least one uppercase, one lowercase letter, and one number",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            //Password-Confirm Password Match Validation
            if(!passwordStr.equals(confPasswordStr)){
                System.out.println(passwordStr + "\n" + confPasswordStr);
                Toast.makeText(this, "Password and Confirm Password does not match", Toast.LENGTH_SHORT).show();
                return;
            }

            //Operation
            firebaseAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(Task->{
                if(Task.isSuccessful()){
                    String userID = firebaseAuth.getCurrentUser().getUid().toString();

                    Map<String, Object> user = new HashMap<>();
                    user.put("firstName", fNameStr);
                    user.put("lastName", lNameStr);
                    user.put("institution", schoolStr);
                    user.put("email", emailStr);

                    firestore.collection("Users")
                            .document(userID)
                            .set(user)
                            .addOnSuccessListener(Void->{
                                Log.d("UserDoc_SUCCESS", "DocumentSnapshot added with ID: " + userID);
                            })
                            .addOnFailureListener(e->{
                                Log.w("UserDoc_FAILED", "Error adding document: " + e);
                            });
                }

                //
                Toast.makeText(this, "Sign Up was Successful", Toast.LENGTH_SHORT).show();
                finish();
            });
        });

        signupLoginButton.setOnClickListener(View->{
            finish();
        });
    }
}