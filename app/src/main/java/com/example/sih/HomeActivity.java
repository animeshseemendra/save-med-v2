package com.example.sih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {



    Button profile,updatemed,report;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, "id-"+id, Toast.LENGTH_SHORT).show();
        updatemed = (Button)findViewById(R.id.upload);
        report=(Button)findViewById(R.id.support);
        profile=(Button)findViewById(R.id.profile);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        updatemed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomeActivity.this, updatemed.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( HomeActivity.this, supportnew.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( HomeActivity.this, profileuser.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                finish();
            }
        });



    }
}
