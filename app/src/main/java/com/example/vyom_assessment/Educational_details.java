package com.example.vyom_assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Educational_details extends AppCompatActivity {
    private TextView tv;
     CurrentUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_details);
        currentUser=(CurrentUser) getApplicationContext();
        String usernme=currentUser.getName().toString();
        tv=(TextView)findViewById(R.id.uname);
        tv.setText("Welcome : "+usernme);
    }

    public void add_X_details(View view){
        Intent intent=new Intent(Educational_details.this,Xthdetails.class);
        startActivity(intent);
    }
    public void addData(View view){

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference db=databaseReference;
       databaseReference.child("education_details").child("raazdev").child("percentage").setValue("83.4");
        databaseReference.child("education_details").child("raazdev").child("degree").setValue("BCA");

        Toast.makeText(getApplicationContext(),"Successfully Record Added!! ",Toast.LENGTH_LONG).show();


    }
}