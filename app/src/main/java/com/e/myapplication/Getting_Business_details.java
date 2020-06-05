package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Getting_Business_details extends AppCompatActivity {
    Spinner s;
   TextView r3,r5;
    EditText t7,t8,t9,t10,r4,r6;
    Button b2;
    String Firmname,Address,Timing,Contact_number,category,Description,Proprietor_name;
    DatabaseReference Business_details;

    DatabaseReference businessCategoryTable;
    List<String> CategoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting__business_details);
        s=findViewById(R.id.s);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        //t9=findViewById(R.id.t9);
        t10=findViewById(R.id.t10);
        b2=findViewById(R.id.b2);
        r3=findViewById(R.id.r3);
        r5=findViewById(R.id.r5);
        r4=findViewById(R.id.r4);
        r6=findViewById(R.id.r6);
        Business_details= FirebaseDatabase.getInstance().getReference("Business_Details");
        businessCategoryTable= FirebaseDatabase.getInstance().getReference("Categories");

        businessCategoryTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryList.clear();//to prevent repititoin and again retrievin
                for(DataSnapshot categorysnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = categorysnapshot.getValue(Upload.class);
                    String NAME = upload.getName().toString();
                    CategoryList.add(NAME);


                }
                ArrayAdapter adapter = new ArrayAdapter<>(Getting_Business_details.this,android.R.layout.simple_list_item_1,CategoryList);
                s.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               category= s.getSelectedItem().toString();
            Firmname=t7.getText().toString().trim();
            Address=t8.getText().toString().trim();
            Description=r6.getText().toString().trim();
            Proprietor_name=r4.getText().toString().trim();
          //  Timing=t9.getText().toString().trim();
            Contact_number=t10.getText().toString().trim();


            String id=Business_details.push().getKey();
              Upload upload=new Upload(Firmname,Address,Contact_number,category,Description,Proprietor_name);
              Business_details.child(id).setValue(upload);






            }
        });

    }
}
