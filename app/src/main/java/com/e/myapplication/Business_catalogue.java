package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Business_catalogue extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lv;
Button addbusiness,b1;
EditText e1;
DatabaseReference businessCategoryTable;
List<String> CategoryList = new ArrayList<>();
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_catalogue);
        lv=findViewById(R.id.lv);
        b1 = findViewById(R.id.b1);
        e1= findViewById(R.id.e1);
        addbusiness=findViewById(R.id.addbusiness);
        businessCategoryTable= FirebaseDatabase.getInstance().getReference("Categories");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i =i+1;

                String name = e1.getText().toString().trim();
                Upload upload  = new Upload(name);
                businessCategoryTable.child(String.valueOf(i)).setValue(upload);
            }
        });
       businessCategoryTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryList.clear();
                for(DataSnapshot categorysnapshot : dataSnapshot.getChildren())
                {
                 Upload upload = categorysnapshot.getValue(Upload.class);
               String NAME = upload.getName().toString();
                CategoryList.add(NAME);


                }
                ArrayAdapter adapter = new ArrayAdapter<>(Business_catalogue.this,android.R.layout.simple_list_item_1,CategoryList);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(this);
        addbusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Business_catalogue.this,Getting_Business_details.class);
                startActivity(i);
            }
        });


    }


    public void detailedCategory(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selected_category = (String) parent.getItemAtPosition(position);
        Intent i = new Intent(Business_catalogue.this,Category.class);
        Bundle b = new Bundle();
        b.putString("selected-category",selected_category);
        i.putExtras(b);
        startActivity(i);
    }
}
