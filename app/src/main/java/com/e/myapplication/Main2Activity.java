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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
TextView t1,t2,t3;
Button signout,date;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String dat;
    DatabaseReference databaseReference;
    private  user user1;
    private Query query,query1;
    private String currentDateandTime;
    private SimpleDateFormat sdf;
    String a1,verify="";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        sharedPreferences=getSharedPreferences("alreadylogged", Context.MODE_PRIVATE);
         a1=sharedPreferences.getString("phonenumber","");
        databaseReference=FirebaseDatabase.getInstance().getReference("user");
        query=databaseReference.orderByChild("mobile").equalTo(a1);
        query1=databaseReference.orderByChild("mobile").equalTo(a1);
        editor=sharedPreferences.edit();

        date=(Button)findViewById(R.id.date);
        signout=(Button)findViewById(R.id.signout);
        //check();
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                editor.putString("phonenumber","");
                editor.commit();
                finish();

                Intent i1=new Intent(Main2Activity.this,logIn.class);
                startActivity(i1);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main2Activity.this,Business_catalogue.class);
                startActivity(i);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 sdf = new SimpleDateFormat("ddMMyyyy");
                 currentDateandTime = sdf.format(new Date());

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            user1=dataSnapshot1.getValue(user.class);
                            verify=user1.getMat_exp();
                            //Toast.makeText(getApplicationContext(),user1.getMat_exp(),Toast.LENGTH_SHORT).show();
                            if (Integer.parseInt(currentDateandTime)<=Integer.parseInt(user1.mat_exp))
                            {FirebaseDatabase.getInstance().getReference("Matrimony_Details").orderByChild("cellno").equalTo(a1).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  //  up1 up=dataSnapshot.getValue(up1.class);
                                    if (dataSnapshot.getChildrenCount() == 0)
                                    {
                                        Intent i1=new Intent(Main2Activity.this,NR.class);
                                        startActivity(i1);
                                        finish();
                                    }
                                    else
                                    {
                                        Intent i1=new Intent(Main2Activity.this,Matrimony_info.class);
                                        //Toast.makeText(getApplicationContext(),up.getSex(),Toast.LENGTH_SHORT).show();
                                        startActivity(i1);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                               /* Intent i1=new Intent(Main2Activity.this,Matrimony.class);
                                startActivity(i1);*/
                            }
                            else if (verify.equals("0"))
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
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
                                AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
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
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k= new Intent(Main2Activity.this,Work_Portal.class);
                startActivity(k);


            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Main2Activity.this,BusinessPortal.class);
                startActivity(i1);
            }
        });

    }
    private void check()
    {
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    user1=dataSnapshot1.getValue(user.class);
                   // verify=user1.getId();
                    editor.putString("id_user",user1.getId());
                    editor.commit();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
