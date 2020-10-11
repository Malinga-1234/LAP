package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchView extends AppCompatActivity {

    private static final String TAG = "SearchView";
    Button searchbtn;
    public static String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        searchbtn = findViewById(R.id.searchBtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchView.this, DisplayPayment.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String paynum = editText.getText().toString();

                intent.putExtra(searchKey, paynum);
                startActivity(intent);
            }
        });
    }
}