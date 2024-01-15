package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    editTextTextEmailAddress.setError("Please enter email to login");
                }
                else if(TextUtils.isEmpty(password)){
                    editTextTextPassword.setError("Please enter password to login");
                }
                else{
                    Intent intent = new Intent(Login.this, Home_page.class);
                    startActivity(intent);
                }
            }
        });
    }
}