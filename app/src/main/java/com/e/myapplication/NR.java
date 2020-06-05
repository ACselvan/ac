package com.e.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class NR extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 7;
    private static final int PICK_IMAGE_REQUESTT = 7;

    EditText name;
    ProgressBar pb,pb1;
    Button upload;
    ImageView iv,iv1;
    Uri ImageUri,ImageUri2;
    String user_id;
    private StorageReference st,stt;
    private DatabaseReference db;
    String r;
    private String Tag;
    EditText Name, Age, Height, Income, education, Job, Fn, Mn, sbl, t10,company;
    Button button4, uploadimage, uploadHoroscope;
    DatabaseReference Matrimony_details;
    RadioGroup radioGroup;
    RadioButton GenderButton;
    int i;
    String Namee, Agee, Sexx, Heightt, Incomee, educationn, Jobb, Fnn, Mnn, sbll, t100,companyy,HoroscopeImage=null,profileImage=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nr);
    radioGroup =findViewById(R.id.radioGroup);
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        company = findViewById(R.id.company);
        iv1 = findViewById(R.id.iv1);
        Height = findViewById(R.id.Height);
        Income = findViewById(R.id.Income);
        education = findViewById(R.id.education);
        Job = findViewById(R.id.Job);
        Fn = findViewById(R.id.Fn);
        Mn = findViewById(R.id.Mn);
        sbl = findViewById(R.id.sbl);
        t10 = findViewById(R.id.t10);
        uploadHoroscope = findViewById(R.id.uploadHoroscope);
        uploadimage = findViewById(R.id.uploadimage);
        button4 = findViewById(R.id.button4);
        upload =findViewById(R.id.upload);
        name = findViewById(R.id.name);
        pb = findViewById(R.id.pb);
        iv = findViewById(R.id.iv);
        pb1 = findViewById(R.id.pb1);




        stt = FirebaseStorage.getInstance().getReference("Uploads/profilephoto");

        st = FirebaseStorage.getInstance().getReference("Uploads/Horoscope");
      //  db= FirebaseDatabase.getInstance().getReference("Uploads/");

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                fileChooser2();

            }
        });

        Matrimony_details = FirebaseDatabase.getInstance().getReference("Matrimony_Details");

        uploadHoroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;

                fileChooser();


            }

        });





        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                GenderButton = findViewById(selectedId);
                if(selectedId == -1)
                {
                    Toast.makeText(NR.this,"Select Gender ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Sexx = GenderButton.getText().toString();
                    Namee =Name.getText().toString().trim();
                    Agee = Age.getText().toString().trim();
                    Heightt = Height.getText().toString().trim();
                    Incomee = Income.getText().toString().trim();
                    educationn = education.getText().toString().trim();
                    Jobb = Job.getText().toString().trim();
                    Mnn = Mn.getText().toString().trim();
                    Fnn = Fn.getText().toString().trim();
                    sbll = sbl.getText().toString().trim();
                    companyy=company.getText().toString().trim();
                    t100 = t10.getText().toString().trim();
                   String id = Matrimony_details.push().getKey();


                        if((HoroscopeImage!=null)&&(profileImage!=null)) {

                            String idd = Matrimony_details.push().getKey();
                            //  String imageurl = uri.toString();

                            up1 Image = new up1(Namee, Agee, Sexx, Heightt, Incomee, educationn, Jobb, educationn, Mnn, Fnn, sbll, t100, companyy, HoroscopeImage, profileImage, Matrimony_details);
                            Matrimony_details.child(idd).setValue(Image);
                            HoroscopeImage=null;
                            profileImage=null;

                            Intent i = new Intent(NR.this, Matrimonydisplay.class);
                            startActivity(i);
                        }

                  else
                    {
                        Toast.makeText(getApplicationContext(),"No filee selected",Toast.LENGTH_SHORT).show();
                    }




                }











                }



        });







    }


    private void fileChooser() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,PICK_IMAGE_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (i == 0) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                ImageUri = data.getData();
                Picasso.with(this).load(ImageUri).into(iv);
                iv.setImageURI(ImageUri);


                if(ImageUri != null) {
                    final StorageReference fileReference = st.child(System.currentTimeMillis() + "." + getFileExtension(ImageUri));

                    fileReference.putFile(ImageUri)

                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Log.d(TAG, "onSuccess: uri= "+ uri.toString());
                                            Handler handler =new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pb.setProgress(0);
                                                    Toast.makeText(NR.this,"Horoscope Uploaded Succesfully",Toast.LENGTH_SHORT).show();

                                                }
                                            },500);
                                            HoroscopeImage = uri.toString();


                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(NR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() /taskSnapshot.getTotalByteCount());
                            pb.setProgress((int)progress);
                        }
                    });

                }


            }

        }


    if(i!=0) {
        if (requestCode == PICK_IMAGE_REQUESTT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            ImageUri2 = data.getData();
            Picasso.with(this).load(ImageUri2).into(iv1);
            iv1.setImageURI(ImageUri2);


            if(ImageUri2 != null) {
                final StorageReference fileReference2 = stt.child(System.currentTimeMillis() + "." + getFileExtension2(ImageUri2));

                fileReference2.putFile(ImageUri2)

                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                fileReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        // Log.d(TAG, "onSuccess: uri= "+ uri.toString());
                                        Handler handlerr =new Handler();
                                        handlerr.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pb1.setProgress(0);
                                                Toast.makeText(NR.this,"profilephoto Uploaded Succesfully",Toast.LENGTH_SHORT).show();

                                            }
                                        },500);
                                        profileImage = uri.toString();


                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(NR.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progresss = (100.0 * taskSnapshot.getBytesTransferred() /taskSnapshot.getTotalByteCount());
                        pb1.setProgress((int)progresss);
                    }
                });





            }
    }}
}
    private  String getFileExtension2(Uri uri)
    {
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private  String getFileExtension(Uri uri)
    {
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void fileChooser2() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,PICK_IMAGE_REQUESTT);


    }





}