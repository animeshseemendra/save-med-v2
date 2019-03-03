package com.example.sih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       /* Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, "id-"+id, Toast.LENGTH_SHORT).show();*/
    }
}
