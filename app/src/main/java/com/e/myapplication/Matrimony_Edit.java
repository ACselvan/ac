package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Matrimony_Edit extends AppCompatActivity {
private EditText name,age,height,comapny,income,education,job,fathername,mothername,siblings;
private     TextView cellno,sex;
   private RadioGroup radioGroup;
    private RadioButton radioButton;
    Button submit;
    private String phonenumber,a1;
    DatabaseReference Business_details;
    Query query;
    private up1 up;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrimony__edit);
        name=(EditText)findViewById(R.id.Name_matrimony_edit);
        age=(EditText)findViewById(R.id.Age_matrimony_edit);
        height=(EditText)findViewById(R.id.Height_matrimony_edit);
        comapny=(EditText)findViewById(R.id.company_matrimony_edit);
        income=(EditText)findViewById(R.id.Income_matrimony_edit);
        education=(EditText)findViewById(R.id.education_matrimony_edit);
        job=(EditText)findViewById(R.id.Job_matrimony_edit);
        cellno=(TextView)findViewById(R.id.cellno_edit);
        mothername=(EditText)findViewById(R.id.Mn_matrimony_edit);
        siblings=(EditText)findViewById(R.id.sbl_matrimony_edit);
        fathername=(EditText)findViewById(R.id.Fn_matrimony_edit);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup_matrimony_edit);
        submit=(Button)findViewById(R.id.submit_matrimony_edit);
        sex=(TextView)findViewById(R.id.sex_edit);
        sharedPreferences=getSharedPreferences("alreadylogged", Context.MODE_PRIVATE);
        phonenumber=sharedPreferences.getString("phonenumber","");
        Business_details= FirebaseDatabase.getInstance().getReference("Matrimony_Details");
        query=Business_details.orderByChild("cellno").equalTo(phonenumber);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    up=dataSnapshot1.getValue(up1.class);
                    name.setText(up.getName());
                    age.setText(up.getAge());
                    height.setText(up.getHeight());
                    comapny.setText(up.getCompanyy());
                    income.setText(up.getIncome());
                    education.setText(up.getEducation());
                    job.setText(up.getJob());
                    cellno.setText(up.getCellno());
                    mothername.setText(up.getMothersname());
                    siblings.setText(up.getSiblings());
                    fathername.setText(up.getFathersname());
                    sex.setText(up.getSex());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("")&&!age.getText().toString().equals("")&&!height.getText().toString().equals("")&&!comapny.getText().toString().equals("")&&!income.getText().toString().equals("")&&!education.getText().toString().equals("")&&!job.getText().toString().equals("")&&!cellno.getText().toString().equals("")&&!mothername.getText().toString().equals("")&&!siblings.getText().toString().equals(""))
                {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            int selectedId = radioGroup.getCheckedRadioButtonId();
                            radioButton = findViewById(selectedId);

                            if (selectedId==-1)
                            {
                                a1=sex.getText().toString();
                            }
                            else
                            {
                             a1=radioButton.getText().toString();
                            }

                            snapshot.getRef().child("name").setValue(name.getText().toString());
                            snapshot.getRef().child("age").setValue(age.getText().toString());
                            snapshot.getRef().child("height").setValue(height.getText().toString());
                            snapshot.getRef().child("companyy").setValue(comapny.getText().toString());
                            snapshot.getRef().child("income").setValue(income.getText().toString());
                            snapshot.getRef().child("education").setValue(education.getText().toString());
                            snapshot.getRef().child("job").setValue(job.getText().toString());
                            snapshot.getRef().child("cellno").setValue(cellno.getText().toString());
                            snapshot.getRef().child("mothersname").setValue(mothername.getText().toString());
                            snapshot.getRef().child("siblings").setValue(siblings.getText().toString());
                            snapshot.getRef().child("sex").setValue(a1);
                            Intent i1=new Intent(Matrimony_Edit.this,Matrimony_info.class);
                            startActivity(i1);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                }

                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Matrimony_Edit.this);
                    builder.setTitle("Empty Field");
                    builder.setMessage("Enter all fields");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logandhome,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            editor.putString("phonenumber", "");
            editor.commit();


            Intent i1 = new Intent(Matrimony_Edit.this, logIn.class);

            startActivity(i1);
            finish();
        }
        if (id==R.id.home1)
        {
            Intent i1 = new Intent(Matrimony_Edit.this, Main2Activity.class);
            startActivity(i1);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        int backButtonCount=0;
        backButtonCount++;
        if(backButtonCount == 1)
        {
            Intent i1=new Intent(Matrimony_Edit.this,Matrimony_info.class);
            startActivity(i1);
            finish();

        }

    }
}
