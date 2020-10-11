package com.example.lap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayPayment extends AppCompatActivity {
    public static final String TAG = "DisplayPayment";
    public static String searchKey;
    TextView payNum, cusName, amnt;
    Button ubtn, dbtn;
    DatabaseReference dbref;
    Payment pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment);

        Intent intent = getIntent();
        searchKey = intent.getStringExtra(SearchView.searchKey);

        payNum = findViewById(R.id.paynum);
        cusName = findViewById(R.id.cusname);
        amnt = findViewById(R.id.amount);

        ubtn = (Button) findViewById(R.id.updateBtn);
        dbtn = (Button) findViewById(R.id.deleteBtn);

        final DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Payment").child(searchKey);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    payNum.setText(dataSnapshot.child("paymentNum").getValue().toString());
                    cusName.setText(dataSnapshot.child("cusName").getValue().toString());
                    amnt.setText(dataSnapshot.child("amount").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No source to dispaly", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Payment").child(searchKey);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("paymentNum")) {
                            dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child(searchKey);
                            dbref.removeValue();
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
        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Payment").child(searchKey);
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(searchKey)) {
                            try {
                                pm.setPaymentNum(payNum.getText().toString().trim());
                                pm.setCusName(cusName.getText().toString().trim());
                                pm.setAmount(amnt.getText().toString().trim());


                                dbref = FirebaseDatabase.getInstance().getReference().child("sup").child(searchKey);
                                dbref.setValue(pm);

                                Toast.makeText(getApplicationContext(), " Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Item Code", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Updated", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}