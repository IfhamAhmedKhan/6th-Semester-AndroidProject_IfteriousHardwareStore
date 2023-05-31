package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.sql.Statement;

public class Set_Profile extends AppCompatActivity {

    EditText editTextTextPersonName3,editTextTextEmailAddress3,editTextPhone2;
    Button button15;
    SharedPreferences sharedPreferences;
    TextView status;
    Connection con;
    Statement stmt;

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
        status = (TextView)findViewById(R.id.status);
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
                new Set_Profile.registeruser().execute("");
            }
        });
    }
    public class registeruser extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("Sending Data to Database");
        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Profile Edit Successful");
            editTextTextPersonName3.setText("");
            editTextTextEmailAddress3.setText("");
            editTextPhone2.setText("");
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
                String requestData = "username=" + URLEncoder.encode(editTextTextPersonName3.getText().toString(), "UTF-8") +
                        "&email=" + URLEncoder.encode(editTextTextEmailAddress3.getText().toString(), "UTF-8") +
                        "&phone=" + URLEncoder.encode(editTextPhone2.getText().toString(), "UTF-8");

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

