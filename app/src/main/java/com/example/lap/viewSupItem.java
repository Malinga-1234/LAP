package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class viewSupItem extends AppCompatActivity {
    private static final String TAG = "viewSupItem";
    Button btnspassing;
    public static  String cusid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sup_item);

        btnspassing = findViewById(R.id.supview);
        btnspassing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(viewSupItem.this,displaySup.class);
                EditText editText = (EditText) findViewById(R.id.custoid);
                String custoid = editText.getText().toString();

                intent.putExtra("ID","123");
                startActivity(intent);
    }
});
    }
}