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

public class item extends AppCompatActivity {
    //private Button button6;

    EditText itemid, itemmodel, itembrand, itemavailability;
    Button Itemadd;
    DatabaseReference dbRef;
    itemadd ita;

    private void clearControls() {
        itemid.setText("");
        itemmodel.setText("");
        itembrand.setText("");
        itemavailability.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemid = findViewById(R.id.name);
        itemmodel = findViewById(R.id.itemmodel);
        itembrand = findViewById(R.id.pwd);
        itemavailability = findViewById(R.id.itembrand);


        Itemadd = findViewById(R.id.itemadd);

        ita = new itemadd();

        Itemadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("ItemAdd");

                try {
                    if (TextUtils.isEmpty(itemid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "itemid", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(itemmodel.getText().toString()))
                        Toast.makeText(getApplicationContext(), "itemmodel:", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(itembrand.getText().toString()))
                        Toast.makeText(getApplicationContext(), "itembrand", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(itemavailability.getText().toString()))
                        Toast.makeText(getApplicationContext(), "itemavailability", Toast.LENGTH_SHORT).show();

                    else {
                        ita.setItemid(itemid.getText().toString().trim());
                        ita.setItemmodel(itemmodel.getText().toString().trim());
                        ita.setItembrand(itembrand.getText().toString().trim());
                        ita.setItemavailability(itemavailability.getText().toString().trim());


                        dbRef.child(itemid.getText().toString()).setValue(ita);

                        Toast.makeText(getApplicationContext(), "data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void intent99(View view){
        Intent intent11 = new Intent(this,viewitem.class);
        startActivity(intent11);
    }
}

       // button6 = (Button) findViewById(R.id.button6);
     //   button6.setOnClickListener((view) { openitem(); });

