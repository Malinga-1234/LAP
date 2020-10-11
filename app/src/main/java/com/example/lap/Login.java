package com.example.lap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login<FirebaseAuth> extends AppCompatActivity {
    // private Button button4;
    //private Button button5;

    EditText username,password;
    Button login;
    TextView tvsingIn;
    FirebaseAuth RegisterCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

   //    RegisterCus = FirebaseAuth.getInstance();
//       username = findViewById(R.id.itemmodel);
//        password = findViewById(R.id.editText8);
//        login = findViewById(R.id.button3);
//        login.setOnClickListener(new View.OnClickListener(){

                              //       @Override
                               //      public void onClick(View view) {
//                                         String name = username.getText().toString();
//                                         String pwd = password.getText().toString();
//                                         if(name.isEmpty()){
//                                             username.setError("Please Enter user name ");
//                                             username.requestFocus();
//                                         }
//                                         else if(pwd.isEmpty()){
//                                             password.setError("Please enter your password");
//                                             password.requestFocus();
//                                         }
//                                         else if(name.isEmpty() && pwd.isEmpty()){
//                                             Toast.makeText(Login.this, "Fields Are Empty!",Toast.LENGTH_SHORT).show();
//                                         }
//                                         else if (!(name.isEmpty() && pwd.isEmpty())){
//                                             RegisterCus.createUserWithNameAndPassword(name,pwd).addOnCompleteListener(Login.this, new onCompleteListener)
//                                         }
//                                     }
//



        Button button4 = (Button) findViewById(R.id.button4); //registration
        Button button5 = (Button) findViewById(R.id.button3); //login

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Login.this, menu.class);
                startActivity(intent1);

            }
        });



        /*
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
    }
    public void openRegister(){


        Intent intent= new Intent(this, Register.class);
        startActivity(intent);
    }*/

    }
}

