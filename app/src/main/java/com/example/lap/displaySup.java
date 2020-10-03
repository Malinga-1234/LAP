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
    String cusid;
    TextView cusname,customerid,cusaddress,itemcode;
    Button view,delete;
    DatabaseReference dbRef;
    Sup supplieradd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sup);

        Intent intent = getIntent();
        cusid = intent.getStringExtra("ID").toString();


        cusname = findViewById(R.id.cname);
        customerid = findViewById(R.id.cusid);
        cusaddress = findViewById(R.id.cusadd);
        itemcode = findViewById(R.id.icode);
        delete = findViewById(R.id.button13);
//show
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("sup").child(cusid);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              /* Log.i(TAG, "Customername" + dataSnapshot.child("Customername").getValue());
                Log.i(TAG, "customerid" + dataSnapshot.child("itembrand").getValue());
                Log.i(TAG, "cusaddress" + dataSnapshot.child("itemid").getValue());
                Log.i(TAG, "itemcode" + dataSnapshot.child("itemcode").getValue());
*/
                // if (dataSnapshot.hasChildren()) {
                cusname.setText(dataSnapshot.child("cuname").getValue().toString());
                customerid.setText(dataSnapshot.child("cuid").getValue().toString());
                cusaddress.setText(dataSnapshot.child("cuAddress").getValue().toString());
                itemcode.setText(dataSnapshot.child("cucode").getValue().toString());
                //  } else
                //     Toast.makeText(getApplicationContext(), "No source to dispaly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Delete
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("sup");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("username")) {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("sup").child("cusid");
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // delete.setOnClickListener(new View.OnClickListener() {
        //   @Override
       // public void onClick (View view){
            //update
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("sup");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("cusid")) {
                        try {
                            supplieradd.setCuname(cusname.getText().toString().trim());
                            supplieradd.setCuAddress(cusaddress.getText().toString().trim());
                            supplieradd.setCucode(itemcode.getText().toString().trim());
                            supplieradd.setCuid(customerid.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("sup").child("cusid");
                            dbRef.setValue(supplieradd);

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
    }

