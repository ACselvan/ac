package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Business_Edit extends AppCompatActivity {
EditText firmname,propriotorname,address,city,descreiption;
Button submit;
Spinner spinner;
TextView mobile;
    List<String> CategoryList = new ArrayList<>();
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    DatabaseReference Business_details,businessCategoryTable;
    String phonenumber;
    Query query;
    Upload upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__edit);
        firmname=(EditText)findViewById(R.id.firmname_edit);
        propriotorname=(EditText)findViewById(R.id.proprietorname_edit);
        address=(EditText)findViewById(R.id.address_edit);
        city=(EditText)findViewById(R.id.city__edit);
        descreiption=(EditText)findViewById(R.id.description_edit);
        submit=(Button)findViewById(R.id.submit_edit);
        spinner=(Spinner)findViewById(R.id.spinner_edit);
        mobile=(TextView)findViewById(R.id.mobile_edit);
        Business_details= FirebaseDatabase.getInstance().getReference("Business_Details");
        sharedPreferences=getSharedPreferences("alreadylogged", Context.MODE_PRIVATE);
        phonenumber=sharedPreferences.getString("phonenumber","");
        query=Business_details.orderByChild("contact_number").equalTo(phonenumber);
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
                ArrayAdapter adapter = new ArrayAdapter<>(Business_Edit.this,android.R.layout.simple_list_item_1,CategoryList);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    upload = dataSnapshot1.getValue(Upload.class);
                    firmname.setText(upload.getFirmname());
                    propriotorname.setText(upload.getProprietor_name());
                    address.setText(upload.getAddress());
                    city.setText(upload.getCity());
                    descreiption.setText(upload.getDescription());
                    mobile.setText(upload.getContact_number());

                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            String address1=address.getText().toString();
                            String category1=spinner.getSelectedItem().toString();
                            String city1=city.getText().toString();
                            String proprietor_name=propriotorname.getText().toString();
                            String description1=descreiption.getText().toString();
                            String firmname1=firmname.getText().toString();
                            if (!address1.equals("")&&!category1.equals("")&&!city1.equals("")&&!proprietor_name.equals("")&&!description1.equals("")&&!firmname1.equals("")) {
                                snapshot.getRef().child("address").setValue(address1);
                                snapshot.getRef().child("category").setValue(category1);
                                snapshot.getRef().child("city").setValue(city1);
                                snapshot.getRef().child("firmname").setValue(firmname1);
                                snapshot.getRef().child("proprietor_name").setValue(proprietor_name);
                                snapshot.getRef().child("description").setValue(description1);
                            }
                            else
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Business_Edit.this);
                                builder.setTitle("Empty Field");
                                builder.setMessage("All Fields are required");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder.create().show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
