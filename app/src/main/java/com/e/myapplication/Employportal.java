package com.e.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employportal extends AppCompatActivity {
    EditText q1,q2,q3,q4,q5;
    Button b1,datepicker,timepicker;
    DatabaseReference Employer_details;
    String Namee,Qualificationn,Addresss,Dttmm,Numm;
    TextView date_text,time_text;
    String datechar,timechar,exp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employportal);
        datepicker=(Button)findViewById(R.id.Datepicker);
        timepicker=(Button)findViewById(R.id.Timepicker);
        q1=findViewById(R.id.q1);
        q2=findViewById(R.id.q2);
        q3=findViewById(R.id.q3);
        q4=findViewById(R.id.q4);
        q5=findViewById(R.id.q5);
        date_text=(TextView)findViewById(R.id.date_text);
        time_text=(TextView)findViewById(R.id.time_text);
        b1=findViewById(R.id.b1);
        Employer_details = FirebaseDatabase.getInstance().getReference("Employer_Details");
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePick();
            }
        });
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePick();
                          }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Namee=q1.getText().toString().trim();
                Qualificationn=q2.getText().toString().trim();
                Addresss=q3.getText().toString().trim();
                //Dttmm=q4.getText().toString().trim();
                Numm=q5.getText().toString().trim();
                String id = Employer_details.push().getKey();
                Dttmm="Date:"+datechar+",  time:"+timechar;
                Employportalupload Employportalupload = new Employportalupload(Namee,Qualificationn,Addresss,Dttmm,Numm,exp);
                Employer_details.child(id).setValue(Employportalupload);
            }
        });
        }
        private void datePick()
        {
            Calendar calendar=Calendar.getInstance();
            int Year=calendar.get(Calendar.YEAR),Month=calendar.get(Calendar.MONTH),Date=calendar.get(Calendar.DATE);
            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                    String expiry=year+" "+month+" "+date;
                    Calendar calendar1=Calendar.getInstance();
                    calendar1.set(Calendar.YEAR,year);
                    calendar1.set(Calendar.MONTH,month);
                    calendar1.set(Calendar.DATE,date);
                    exp= (String) DateFormat.format("yyyyMMdd",calendar1);;
                     datechar= (String) DateFormat.format("EEEE,dd MMM yyyy",calendar1);
                    date_text.setText(datechar);
                }
            },  Year,Month,Date);
            datePickerDialog.show();

        }
        private void timePick()
        {
                    final Calendar calendar=Calendar.getInstance();
                    int Hour=calendar.get(Calendar.HOUR);
                    int Minute=calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR,hour);
                calendar1.set(Calendar.MINUTE,minute);
                    Time tme = new Time(hour,minute,0);//seconds by default set to zero
                    Format formatter;
                    formatter = new SimpleDateFormat("h:mm a");
                 timechar=formatter.format(tme);
                time_text.setText(timechar);
                }
            },Hour,Minute,false);
            timePickerDialog.show();
        }
    }

