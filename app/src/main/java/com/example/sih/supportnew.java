package com.example.sih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class supportnew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supportnew);

        TextView userReport = (TextView) findViewById(R.id.textViewemail);
        userReport.setText(Html.fromHtml("<a href=\"aviralcool2110@gmail.com\">Email:aviralcool2110@gmail.com</a>"));
        userReport.setMovementMethod(LinkMovementMethod.getInstance());
        TextView phone=(TextView)findViewById(R.id.textViewphone);
        phone.setText(Html.fromHtml("<a href=\"+917007042761\">Phone:+917007042761</a>"));
        phone.setMovementMethod(LinkMovementMethod.getInstance());


    }
}
