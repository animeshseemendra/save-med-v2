package com.example.sih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class frontpage extends AppCompatActivity {
    Button govt;
    Button user;
    Button hosp;
    TextView donatetext;
    //image button
    private ImageView imagebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);
        //image buttton id
        donatetext=(TextView)findViewById(R.id.textdonate);
        imagebtn = (ImageView)findViewById(R.id.imv);
        govt = (Button)findViewById(R.id.govt);
        user=(Button)findViewById(R.id.user);
        hosp=(Button)findViewById(R.id.hospital);

        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frontpage.this, MainActivity.class));
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frontpage.this, UserMainActivity.class));
            }
        });
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frontpage.this, FAQ.class));
            }
        });
        donatetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frontpage.this, donate.class));
            }
        });



    }
}