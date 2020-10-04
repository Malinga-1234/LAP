package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class viewRegister extends AppCompatActivity {
    private static final String TAG = "viewRegister";
    Button btnspassing;
    public static  String custmername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_register);


        btnspassing = findViewById(R.id.button5);
        btnspassing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(viewRegister.this,dispalycustmer.class);
                EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
                String username = editText.getText().toString();


                intent.putExtra(custmername,username);
                startActivity(intent);
            }
        });
    }
}