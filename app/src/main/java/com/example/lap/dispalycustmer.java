package com.example.lap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dispalycustmer extends AppCompatActivity {
    private static  final String TAG = "dispalycustmer";
    public static  String custmername;
    TextView user_name,email,password,confirm_password,mobile,address;
    Button view,delete,update;
    DatabaseReference dbRef;
    registercus regview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispalycustmer);

        Intent intent = getIntent();
        custmername = intent.getStringExtra(viewitem.userName);
        Log.i(TAG, "custmername: " + custmername);

        user_name = findViewById(R.id.p1);
        email = findViewById(R.id.p2);
        password = findViewById(R.id.p3);
        confirm_password = findViewById(R.id.p4);
        mobile = findViewById(R.id.p4);
        address = findViewById(R.id.p5);

        delete = findViewById(R.id.button14);
       // update = findViewById(R.id.update2);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child(custmername);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    user_name.setText(dataSnapshot.child("user_name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());
                    confirm_password.setText(dataSnapshot.child("confirm_password").getValue().toString());
                    mobile.setText(dataSnapshot.child("mobile").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                } else

                    Toast.makeText(getApplicationContext(), "No source to dispaly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child(custmername);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("user_name")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child(custmername);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }


                });


            }

        });
        }

    }
