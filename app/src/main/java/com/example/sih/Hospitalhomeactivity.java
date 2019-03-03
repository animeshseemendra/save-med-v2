package com.example.sih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Hospitalhomeactivity extends AppCompatActivity {



    Button ReqMed,nearby,sell;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitalhomeactivity);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this, "id-"+id, Toast.LENGTH_SHORT).show();
        nearby = (Button)findViewById(R.id.nearby);
        sell=(Button)findViewById(R.id.sell);
        ReqMed=(Button)findViewById(R.id.ReqMed);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( Hospitalhomeactivity.this, nearbymed.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                finish();
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( Hospitalhomeactivity.this, supportnew.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                finish();
            }
        });
        ReqMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( Hospitalhomeactivity.this, profileuser.class);
                intent2.putExtra("id", id);
                startActivity(intent2);
                finish();
            }
        });



    }
}
