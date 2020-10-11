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

public class displaySup extends AppCompatActivity {

    private static final String TAG = "display";
    public static  String customer;
    String cusid;
    TextView cuid,cuname,cuAddress,cucode;
    Button view,delete,update;
    DatabaseReference dbRef;
    Sup supplieradd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sup);

        Intent intent = getIntent();
        // cusid = intent.getStringExtra("ID").toString();
        customer = intent.getStringExtra(viewSupItem.customer);


        cuname = findViewById(R.id.editTextTextPersonName);
        cuid = findViewById(R.id.editTextTextPersonName3);
        cuAddress = findViewById(R.id.editTextTextPersonName4);
        cucode = findViewById(R.id.editTextTextPersonName5);
        delete = findViewById(R.id.button13);
        update=findViewById(R.id.button10);
//show
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("sup").child(customer);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              /* Log.i(TAG, "Customername" + dataSnapshot.child("Customername").getValue());
                Log.i(TAG, "customerid" + dataSnapshot.child("itembrand").getValue());
                Log.i(TAG, "cusaddress" + dataSnapshot.child("itemid").getValue());
                Log.i(TAG, "itemcode" + dataSnapshot.child("itemcode").getValue());
*/
                // if (dataSnapshot.hasChildren()) {
                cuname.setText(dataSnapshot.child("cuname").getValue().toString());
                cuid.setText(dataSnapshot.child("cuid").getValue().toString());
                cuAddress.setText(dataSnapshot.child("cuAddress").getValue().toString());
                cucode.setText(dataSnapshot.child("cucode").getValue().toString());
                //  } else
                //     Toast.makeText(getApplicationContext(), "No source to dispaly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("sup").child(customer);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("cuid")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("sup").child(customer);
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
                //update
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("sup").child(customer);
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(customer)) {
                            try {
                                supplieradd.setCuname(cuname.getText().toString().trim());
                                supplieradd.setCuAddress(cuAddress.getText().toString().trim());
                                supplieradd.setCucode(cucode.getText().toString().trim());
                                supplieradd.setCuid(cuid.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("sup").child(customer);
                                dbRef.setValue(supplieradd);

                                Toast.makeText(getApplicationContext(), " Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Item Code", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
  }
    }
