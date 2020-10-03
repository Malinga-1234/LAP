package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText name, email, pwd, cpwd, mobile, address;
    Button Register;
    DatabaseReference dbRef;
    registercus ggg;

    private void clearControls() {
        name.setText("");
        email.setText("");
        pwd.setText("");
        cpwd.setText("");
        mobile.setText("");
        address.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        cpwd = findViewById(R.id.itembrand);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);

        Register = findViewById(R.id.register);

        ggg = new registercus();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus");

                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "user name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pwd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "password", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cpwd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "cpwd", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(mobile.getText().toString()))
                        Toast.makeText(getApplicationContext(), "mobile", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "address", Toast.LENGTH_SHORT).show();
                    else {
                        ggg.setUser_name(name.getText().toString().trim());
                        ggg.setEmail(email.getText().toString().trim());
                        ggg.setPassword(pwd.getText().toString().trim());
                        ggg.setConfirm_password(cpwd.getText().toString().trim());
                        ggg.setMobile(mobile.getText().toString().trim());
                        ggg.setAddress(address.getText().toString().trim());

                        dbRef.push().setValue(ggg);

                        Toast.makeText(getApplicationContext(), "data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}