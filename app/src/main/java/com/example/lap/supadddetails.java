package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class supadddetails extends AppCompatActivity {
EditText cuname,cuid,cuAddress,cucode;
Button Suadd;
DatabaseReference dbRef;
Sup std;

    private void clearControls() {
        cuname.setText("");
        cuid.setText("");
        cuAddress.setText("");
        cucode.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supadddetails);

        cuname = findViewById(R.id.cuname);
        cuid = findViewById(R.id.cuid);
        cuAddress = findViewById(R.id.cuAddress);
        cucode = findViewById(R.id.cucode);

        Suadd = findViewById(R.id.suadd);

        std = new Sup();


        Suadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("sup");

                try {
                    if (TextUtils.isEmpty(cuname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "customername", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cuid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "custmer id", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cuAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "customer address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cucode.getText().toString()))
                        Toast.makeText(getApplicationContext(), "iteam code", Toast.LENGTH_SHORT).show();

                    else {
                        std.setCuname(cuname.getText().toString().trim());
                        std.setCuid(cuid.getText().toString().trim());
                        std.setCuAddress(cuAddress.getText().toString().trim());
                        std.setCucode(cucode.getText().toString().trim());


                  //      dbRef.push().setValue(std);
                        dbRef.child(cuid.getText().toString()).setValue(std);

                        Toast.makeText(getApplicationContext(), "data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void intent22(View viewsup){
        Intent intent33 = new Intent(this,viewSupItem.class);
        startActivity(intent33);
    }
}