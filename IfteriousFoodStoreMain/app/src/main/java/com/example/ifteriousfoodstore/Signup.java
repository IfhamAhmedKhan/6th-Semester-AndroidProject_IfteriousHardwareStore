package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText editTextTextPersonName,editTextTextEmailAddress2,editTextTextPassword2,editTextTextPassword3;
    Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=  editTextTextPersonName.getText().toString();
                String email=  editTextTextEmailAddress2.getText().toString();
                String password = editTextTextPassword2.getText().toString();
                String reenter_password=  editTextTextPassword3.getText().toString();
                if(TextUtils.isEmpty(username)){
                    editTextTextPersonName.setError("Please enter username to login");
                }
                else if (TextUtils.isEmpty(email)){
                    editTextTextEmailAddress2.setError("Please enter email to login");
                }
                else if (TextUtils.isEmpty(password)){
                    editTextTextPassword2.setError("Please enter password to login");
                }
                else if (TextUtils.isEmpty(reenter_password)){
                    editTextTextPassword3.setError("Please reenter your password to login");
                }
                else{
                    Intent intent = new Intent(Signup.this, Home_page.class);
                    startActivity(intent);
                }
            }
        });
    }
}