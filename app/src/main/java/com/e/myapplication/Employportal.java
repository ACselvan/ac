package com.e.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Employportal extends AppCompatActivity {
    EditText q1,q2,q3,q4,q5;
    Button b1;
    DatabaseReference Employer_details;
    String Namee,Qualificationn,Addresss,Dttmm,Numm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employportal);

        q1=findViewById(R.id.q1);
        q2=findViewById(R.id.q2);
        q3=findViewById(R.id.q3);
        q4=findViewById(R.id.q4);
        q5=findViewById(R.id.q5);

        b1=findViewById(R.id.b1);

        Employer_details = FirebaseDatabase.getInstance().getReference("Employer_Details");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Namee=q1.getText().toString().trim();
                Qualificationn=q2.getText().toString().trim();
                Addresss=q3.getText().toString().trim();
                Dttmm=q4.getText().toString().trim();
                Numm=q5.getText().toString().trim();

                String id = Employer_details.push().getKey();
                Employportalupload Employportalupload = new Employportalupload(Namee,Qualificationn,Addresss,Dttmm,Numm);
                Employer_details.child(id).setValue(Employportalupload);

            }
        });

        }


    }

