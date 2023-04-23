package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {

    ImageView imgview;
    TextView desc;
    String img;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgview=findViewById(R.id.img_view);
        desc=findViewById(R.id.txt_view);
        getdata();
        setdata();

    }
    private void setdata()
    {
        Glide.with(getApplicationContext()).load(img).into(imgview);
        desc.setText(data);
    }
    private void getdata()
    {
        if(getIntent().hasExtra("data1") && getIntent().hasExtra("img1"))
        {
            data= getIntent().getStringExtra("data1");
            img= getIntent().getStringExtra("img1");
        }
        else
        {
            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
        }
    }
}