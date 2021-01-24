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

public class gradDetails extends AppCompatActivity {

    private EditText s_name;
    private  EditText Board_name;
    private  EditText Percentage;
    private  EditText PassingYear;
    private  EditText course;
    private  EditText specif;
    private DatabaseReference databaseReference;

    CurrentUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_details);
        s_name=(EditText) findViewById(R.id.s_name);
        Board_name=(EditText) findViewById(R.id.Board_name);
        Percentage=(EditText) findViewById(R.id.Percentage);
        PassingYear=(EditText) findViewById(R.id.PassingYear);
        course=(EditText) findViewById(R.id.course);
        specif=(EditText) findViewById(R.id.specif);
        currentUser=(CurrentUser) getApplicationContext();

        isAvailable();
    }
    private void isAvailable(){
        String key[]=currentUser.getEmail().split("@");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("education_details").child("grad");
        Query checkUser=databaseReference.child(key[0]);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String school_name=dataSnapshot.child("collegename").getValue(String.class);
                    String board_name=dataSnapshot.child("universityname").getValue(String.class);
                    String percentage=dataSnapshot.child("percentage").getValue(String.class);
                    String passingyear= dataSnapshot.child("passingyear").getValue(String.class);
                    String specify=dataSnapshot.child("specif").getValue(String.class);
                    String courses=dataSnapshot.child("course").getValue(String.class);
                    s_name.setText(school_name);
                    Board_name.setText(board_name);
                    Percentage.setText(percentage);
                    PassingYear.setText(passingyear);
                    specif.setText(specify);
                    course.setText(courses);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void SavegradDetails(View view){
        String school_name=s_name.getText().toString();
        String board_name=Board_name.getText().toString();
        String percentage=Percentage.getText().toString();
        String pass=PassingYear.getText().toString();
        String cour=course.getText().toString();
        String spc=specif.getText().toString();
        if(cour.isEmpty()){
            course.setError("Enter course name");
            return;
        }
        else
        {
            course.setError(null);
        }
        if(spc.isEmpty()){
            specif.setError("Enter specialization");
            return;
        }else
        {
            specif.setError(null);
        }
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
        databaseReference.child("education_details").child("grad").child(key[0]).child("course").setValue(cour);
        databaseReference.child("education_details").child("grad").child(key[0]).child("specif").setValue(spc);
        databaseReference.child("education_details").child("grad").child(key[0]).child("collegename").setValue(school_name);
        databaseReference.child("education_details").child("grad").child(key[0]).child("universityname").setValue(board_name);
        databaseReference.child("education_details").child("grad").child(key[0]).child("percentage").setValue(percentage);
        databaseReference.child("education_details").child("grad").child(key[0]).child("passingyear").setValue(pass);



        Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(gradDetails.this,Educational_details.class);
        startActivity(intent);

    }
}