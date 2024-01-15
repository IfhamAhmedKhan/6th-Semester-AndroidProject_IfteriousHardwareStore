package com.example.ifteriousfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bill_Payment_Deal_Four extends AppCompatActivity {
    EditText editTextTextPersonName2;
    EditText editTextTextPostalAddress;
    EditText editTextPhone;
    Button button12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment_deal_four);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPostalAddress =findViewById(R.id.editTextTextPostalAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextTextPersonName2.getText().toString();
                String address = editTextTextPostalAddress.getText().toString();
                String phone = editTextPhone.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    editTextTextPersonName2.setError("Please enter your username");
                } else if (TextUtils.isEmpty(address)) {
                    editTextTextPostalAddress.setError("Please enter your address");
                } else if (TextUtils.isEmpty(phone)) {
                    editTextPhone.setError("Please enter your phone number");
                } else {
                    Toast.makeText(Bill_Payment_Deal_Four.this, "Your order is confirmed "+username, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}