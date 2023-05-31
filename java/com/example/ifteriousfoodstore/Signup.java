package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

public class Signup extends AppCompatActivity {

    EditText editTextTextPersonName,editTextTextEmailAddress2,editTextTextPassword2,editTextTextPassword3;
    Button button4;
    TextView status;
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
                    new Signup.registeruser1().execute("");
                }
            }
        });
    }
    public class registeruser1 extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("Sending Data to Database");
        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Profile Edit Successful");
            editTextTextPersonName.setText("");
            editTextTextEmailAddress2.setText("");
            editTextTextPassword2.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // Create the URL for your web service
                URL url = new URL("http://10.23.43.250/WebService1.asmx");

                // Open a connection to the web service URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to POST
                connection.setRequestMethod("POST");

                // Enable output and input streams
                connection.setDoOutput(true);
                connection.setDoInput(true);

                // Set the required headers
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("charset", "utf-8");

                // Create the request payload
                String requestData = "username=" + URLEncoder.encode(editTextTextPersonName.getText().toString(), "UTF-8") +
                        "&email=" + URLEncoder.encode(editTextTextEmailAddress2.getText().toString(), "UTF-8") +
                        "&password=" + URLEncoder.encode(editTextTextPassword2.getText().toString(), "UTF-8");

                // Get the output stream and write the request payload
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(requestData);
                writer.flush();
                writer.close();
                outputStream.close();

                // Get the response from the web service
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                inputStream.close();

                // Process the response from the web service
                if (response.toString().equals("success")) {
                    isSuccess = true;
                    z = "Data stored successfully";
                } else {
                    isSuccess = false;
                    z = "Failed to store data";
                }
            } catch (Exception e) {
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }

    }


    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());
        }

        return connection;
    }
}



