package com.example.lap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customerDetails extends AppCompatActivity {
    EditText name, email, pwd, cpwd, mobile, address;
    Button btnCus,Delete,update;
    DatabaseReference dbRef;
    registercus ggg;
    private static final String TAG = "customerDetails";

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
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        // pwd = findViewById(R.id.mobile);
        //  cpwd = findViewById(R.id.u);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        Delete = findViewById(R.id.button11);
        btnCus = findViewById(R.id.button12);
        update = findViewById(R.id.button2);

        btnCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewData();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Updatedata();
            }
        });


    }



    public void ViewData(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child("-MI-FysGN9MEaz-j8TzS");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                if (dataSnapshot.hasChildren()) {

                    email.setText(dataSnapshot.child("email").getValue().toString());
                    //  pwd.setText(dataSnapshot.child("pwd").getValue().toString());
                    //   cpwd.setText(dataSnapshot.child("cpwd").getValue().toString());
                    mobile.setText(dataSnapshot.child("mobile").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "Inside thE functyion No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void Updatedata(){

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.mobile);
        cpwd = findViewById(R.id.itembrand);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        ggg = new registercus();
        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child("-MI-FysGN9MEaz-j8TzS");
        ggg.setUser_name(name.getText().toString());
        ggg.setEmail(email.getText().toString());
        ggg.setMobile(mobile.getText().toString());
        ggg.setAddress(address.getText().toString());
        ggg.setPassword(pwd.getText().toString());
        ggg.setConfirm_password(cpwd.getText().toString());

        upRef.setValue(ggg);

    }

    public void DeleteData(){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child("-MI3yUafVswlpvhD3bsH");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    dbRef = FirebaseDatabase.getInstance().getReference().child("RegisterCus").child("-MI3yUafVswlpvhD3bsH");
                    dbRef.removeValue();
                    clearControls();
                    Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}