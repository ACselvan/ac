package com.e.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class viewhoroscope extends AppCompatActivity {
static ImageView iv;
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhoroscope);
        iv = findViewById(R.id.image);
        t = findViewById(R.id.t);

        String image = getIntent().getStringExtra("image");
        t.setText(image);

        Picasso.with(this).load(image).into(iv);
       // iv.setImageURI(Uri.parse(image));

    }
}
