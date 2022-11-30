package com.example.operationbasics;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity{
    EditText user_input_email;
    EditText user_input_password;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_input_password=(EditText)findViewById(R.id.user_input_password);
        user_input_email=(EditText)findViewById(R.id.user_input_email);
        login_button=(Button)findViewById(R.id.)
    }
}
