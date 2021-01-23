package com.example.vyom_assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    DatabaseReference databaseReference;
    CurrentUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.user_email);
        password=(EditText) findViewById(R.id.user_password);

        currentUser=(CurrentUser)getApplicationContext();
    }
    private void isUser(){


        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
        String email=username.getText().toString();
        System.out.println("11111111"+email);
        String pswd=password.getText().toString();
        String srr[]=email.split("@");
        Query checkUser=databaseReference.child(srr[0]);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String strpwd=dataSnapshot.child("password").getValue(String.class);
                    String name=dataSnapshot.child("username").getValue(String.class);

                    System.out.println("use: "+strpwd+"  "+name +"111111"+email);

                    if(strpwd.equals(pswd)){
                        currentUser.setEmail(email);
                        currentUser.setName(name);

                        Toast.makeText(getApplicationContext(),"Successfully Login "+name +"",Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(MainActivity.this,Educational_details.class);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect Password!!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void callLogin(View view){


        try{
            isUser();
        }catch(Exception e){

        }

    }
}