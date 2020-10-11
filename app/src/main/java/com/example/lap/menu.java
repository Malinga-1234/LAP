package com.example.lap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class menu extends AppCompatActivity {

    EditText name, email, pwd, cpwd, mobile, address;


    private Button button9;
    private Button button5;
    private Button button6;
    private Button button8;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button9 = (Button) findViewById(R.id.button9);
        button5 = (Button) findViewById(R.id.customerview);
        button6 = (Button) findViewById(R.id.button6);
        button8 = (Button) findViewById(R.id.button8);
        payBtn = (Button) findViewById(R.id.button7);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutUs();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openitem();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensupadddetails();
            }
        });
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaymentActivity();
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, customerDetails.class);
                startActivity(intent);
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Register").child("ggg");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            name.setText(dataSnapshot.child("user_name").getValue().toString());
                            email.setText(dataSnapshot.child("email").getValue().toString());
                            pwd.setText(dataSnapshot.child("password").getValue().toString());
                            cpwd.setText(dataSnapshot.child("confirm_password").getValue().toString());
                            mobile.setText(dataSnapshot.child("mobile").getValue().toString());
                            address.setText(dataSnapshot.child("address").getValue().toString());

                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                   //Delete


//                   DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Register").child("ggg");
//                    delRef.add(new
//
//                   ValueEventListener() {
//
//                   });


                });


            }


        });

    }

            public void openAboutUs() {
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
            }

            public void opencustomeerDetails() {
                Intent intent = new Intent(this, customerDetails.class);
                startActivity(intent);
            }

            public void openitem() {
                Intent intent = new Intent(this, item.class);
                startActivity(intent);
            }

            public void opensupadddetails() {
                Intent intent = new Intent(this, supadddetails.class);
                startActivity(intent);
            }

            public void openPaymentActivity(){
                Intent intent = new Intent(this, AddPayment.class);
                startActivity(intent);
            }




}



