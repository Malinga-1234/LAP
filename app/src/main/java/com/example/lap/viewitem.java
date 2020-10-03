package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class  viewitem extends AppCompatActivity {

    private static final String TAG = "viewitem";
    Button btnAssign;
    public static  String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewitem);

        btnAssign = findViewById(R.id.viewBtn);
        btnAssign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(viewitem.this,display.class);
                EditText editText = (EditText) findViewById(R.id.viewer);
                String itmName = editText.getText().toString();
                Log.i(TAG, "username: "+itmName);

                intent.putExtra(userName, itmName);
                startActivity(intent);
            }
        });

    }
}