package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    editTextTextEmailAddress.setError("Please enter email to login");
                } else if (TextUtils.isEmpty(password)) {
                    editTextTextPassword.setError("Please enter password to login");
                } else {
                    new LoginUserTask().execute(email, password);
                }
            }
        });
    }

    private class LoginUserTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... credentials) {
            String email = credentials[0];
            String password = credentials[1];

            String databaseUrl = "jdbc:mysql:http://10.23.43.250/WebService1.asmx";
            String databaseUsername = "Ifham Khan";
            String databasePassword = "ImAnAn007";

            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {

                connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);


                String query = "SELECT * FROM users WHERE email = ? AND password = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);

                // Execute the query
                resultSet = statement.executeQuery();

                // If the query returns a row, the user exists and the credentials are valid
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close the database resources
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                // Login successful
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home_page.class);
                startActivity(intent);
            } else {
                // Login failed
                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
