package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Business_catalogue extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lv;
Button addbusiness,b1;
EditText e1;
    private  user user1;
    private Query query;
    private String currentDateandTime,a1,verify;
    private SimpleDateFormat sdf;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;
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
        sharedPreferences=getSharedPreferences("alreadylogged", Context.MODE_PRIVATE);
        a1=sharedPreferences.getString("phonenumber","");
        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        query=databaseReference.orderByChild("mobile").equalTo(a1);
        editor=sharedPreferences.edit();
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
                sdf = new SimpleDateFormat("yyyyMMdd");
                currentDateandTime = sdf.format(new Date());

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            user1=dataSnapshot1.getValue(user.class);
                            verify=user1.getBusss_exp();
                            //Toast.makeText(getApplicationContext(),user1.getMat_exp(),Toast.LENGTH_SHORT).show();
                            if (Integer.parseInt(currentDateandTime)<=Integer.parseInt(user1.busss_exp))
                            {
                                FirebaseDatabase.getInstance().getReference("Business_Details").orderByChild("contact_number").equalTo(a1).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.getChildrenCount() == 0)
                                        {
                                            Intent i=new Intent(Business_catalogue.this,Getting_Business_details.class);
                                            startActivity(i);

                                        }else
                                        {
                                            Intent i=new Intent(Business_catalogue.this,Business_Edit.class);
                                            startActivity(i);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                /*Intent i=new Intent(Business_catalogue.this,Getting_Business_details.class);
                                startActivity(i);*/
                            }
                            else if (verify.equals("0"))
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Business_catalogue.this);
                                builder.setTitle("only prmium members");
                                builder.setMessage("click payup to pay fee for entering matrimony");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getApplicationContext(),"it will leads to payment page",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.create().show();
                            }
                            else
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Business_catalogue.this);
                                builder.setTitle("premium membership expired");
                                builder.setMessage("finish the payment for continue matrimony service");
                                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getApplicationContext(),"it will leads to payment page",Toast.LENGTH_SHORT).show();
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




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selected_category = (String) parent.getItemAtPosition(position);
        //Intent i = new Intent(Business_catalogue.this,Category.class);
        Intent i = new Intent(Business_catalogue.this,Business_info.class);
        Bundle b = new Bundle();
        b.putString("selected-category",selected_category);
        editor.putString("selected-category",selected_category);
        editor.commit();
        i.putExtras(b);
        startActivity(i);
    }
}
