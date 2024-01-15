package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Set_Profile extends AppCompatActivity {

    EditText editTextTextPersonName3,editTextTextEmailAddress3,editTextPhone2;
    Button button15;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        button15 = findViewById(R.id.button15);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,editTextTextPersonName3.getText().toString());
                editor.putString(KEY_EMAIL,editTextTextEmailAddress3.getText().toString());
                editor.putString(KEY_PHONE,editTextPhone2.getText().toString());
                editor.apply();

                Intent intent = new Intent(Set_Profile.this,Profile.class);
                startActivity(intent);

                Toast.makeText(Set_Profile.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}