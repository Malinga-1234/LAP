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

public class display extends AppCompatActivity {

    private static  final String TAG = "display";
    String username;
    TextView itemname,itemid,itemavailability,itembrand;
    Button view,delete,update;
    DatabaseReference dbRef;
    itemadd iadd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        username = intent.getStringExtra(viewitem.userName);
        Log.i(TAG, "username: " + username);

        itemname = findViewById(R.id.itemName);
        itembrand = findViewById(R.id.name);
        itemid = findViewById(R.id.itemmodel);
        itemavailability = findViewById(R.id.pwd);
        delete = findViewById(R.id.Delete2);
        update = findViewById(R.id.update2);


        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd").child(username);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "username" + dataSnapshot.child("username").getValue());
                Log.i(TAG, "itembrand" + dataSnapshot.child("itembrand").getValue());
                Log.i(TAG, "itemid" + dataSnapshot.child("itemid").getValue());
                Log.i(TAG, "itemavailability" + dataSnapshot.child("itemavailability").getValue());
                if (dataSnapshot.hasChildren()) {
                    itemname.setText(dataSnapshot.child("itemid").getValue().toString());
                    itembrand.setText(dataSnapshot.child("itembrand").getValue().toString());
                    itemid.setText(dataSnapshot.child("itemmodel").getValue().toString());
                    itemavailability.setText(dataSnapshot.child("itemavailability").getValue().toString());
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
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd").child(username);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("username")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd").child(username);
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
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd").child(username);
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("username")) {
                            try {
                                iadd.setItemid(itemid.getText().toString().trim());
                                iadd.setItembrand(itembrand.getText().toString().trim());
                                iadd.setItemavailability(itemavailability.getText().toString().trim());
                                iadd.setItemmodel(itemname.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd").child(username);
                                dbRef.setValue(iadd);

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Item Code", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }}
