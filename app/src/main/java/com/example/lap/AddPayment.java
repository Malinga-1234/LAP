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

public class AddPayment extends AppCompatActivity {

    EditText paynum, cusname, amount;
    Button addpay, searchPay;
    DatabaseReference dbref;
    Payment pm;

    private void clearFields(){
        pm.setPaymentNum("");
        pm.setCusName("");
        pm.setAmount("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        searchPay = findViewById(R.id.button16);
        paynum = findViewById(R.id.paymentNum);
        cusname = findViewById(R.id.cusName);
        amount = findViewById(R.id.amount);

        addpay = findViewById(R.id.button5);
        pm = new Payment();

        addpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Payment");

                try {
                    if (TextUtils.isEmpty(paynum.getText().toString()))
                        Toast.makeText(getApplicationContext(), "paymentNum", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cusname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "cusName:", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(amount.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Amount", Toast.LENGTH_SHORT).show();


                    else {
                        pm.setPaymentNum(paynum.getText().toString().trim());
                        pm.setCusName(cusname.getText().toString().trim());
                        pm.setAmount(amount.getText().toString().trim());



                        dbref.child(paynum.getText().toString()).setValue(pm);

                        Toast.makeText(getApplicationContext(), "data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearFields();

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Data entry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity();
            }
        });
    }
    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchView.class);
        startActivity(intent);
    }

}