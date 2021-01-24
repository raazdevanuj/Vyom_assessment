package com.example.vyom_assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Xthdetails extends AppCompatActivity {

    private  EditText s_name;
    private  EditText Board_name;
    private  EditText Percentage;
    private  EditText PassingYear;
    private DatabaseReference databaseReference;

    CurrentUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xthdetails);
        s_name=(EditText) findViewById(R.id.s_name);
        Board_name=(EditText) findViewById(R.id.Board_name);
        Percentage=(EditText) findViewById(R.id.Percentage);
        PassingYear=(EditText) findViewById(R.id.PassingYear);

        currentUser=(CurrentUser) getApplicationContext();

        isAvailable();
    }
    private void isAvailable(){
     //   System.out.println("55555544444555555");
        String key[]=currentUser.getEmail().split("@");
        databaseReference=FirebaseDatabase.getInstance().getReference().child("education_details").child("Xth");
        Query checkUser=databaseReference.child(key[0]);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String school_name=dataSnapshot.child("schoolname").getValue(String.class);
                    String board_name=dataSnapshot.child("boardname").getValue(String.class);
                    String percentage=dataSnapshot.child("percentage").getValue(String.class);
                    String passingyear= dataSnapshot.child("passingyear").getValue(String.class);
                   // int passingyear= dataSnapshot.child("passingyear").getValue(Integer.class);

                    s_name.setText(school_name);
                    Board_name.setText(board_name);
                    Percentage.setText(percentage);
                 //   System.out.println("55555555555555");
                    PassingYear.setText(passingyear);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void SaveXDetails(View view){
        String school_name=s_name.getText().toString();
        String board_name=Board_name.getText().toString();
        String percentage=Percentage.getText().toString();
        String pass=PassingYear.getText().toString();
        if (school_name.isEmpty()) {
            s_name.setError("Enter School/College name");
            return;
        } else {
            s_name.setError(null);
        }
        if (board_name.isEmpty()) {
            Board_name.setError("Enter Board/University name");
            return;
        } else {
            Board_name.setError(null);
        }
        if (percentage.isEmpty()) {
            Percentage.setError("Enter Percentage");
            return;
        } else {
            Percentage.setError(null);
        }
        if (pass.isEmpty()) {
           PassingYear.setError("Enter Passing Year");
            return;
        } else {
            PassingYear.setError(null);
        }

//        String msg="";
//        if(school_name==""||school_name.equals("")){
//                msg="Enter School Name";
//        }
//        if(msg!=""){
//            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//            dlgAlert.setMessage(msg);
//            dlgAlert.setTitle("Error Alert");
//            dlgAlert.setPositiveButton("OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int which) {
//
//                        }
//                    });
//            return;
//        }
        String key[]=currentUser.getEmail().split("@");


        System.out.println("Key: "+key[0]);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("education_details").child("Xth").child(key[0]).child("schoolname").setValue(school_name);
        databaseReference.child("education_details").child("Xth").child(key[0]).child("boardname").setValue(board_name);
        databaseReference.child("education_details").child("Xth").child(key[0]).child("percentage").setValue(percentage);
        databaseReference.child("education_details").child("Xth").child(key[0]).child("passingyear").setValue(pass);

        Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(Xthdetails.this,Educational_details.class);
        startActivity(intent);

    }
}