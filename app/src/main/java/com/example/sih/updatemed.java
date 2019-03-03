package com.example.sih;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class updatemed extends AppCompatActivity {
    private EditText inputbatch, inputexpiry, inputmanufact, inputPhone;
    private EditText inputname;
    private DatabaseReference pharmDatabase,userDatabase,medDatabase,NGODatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatemed);
        pharmDatabase = FirebaseDatabase.getInstance().getReference();
        userDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
        NGODatabase=FirebaseDatabase.getInstance().getReference();
        medDatabase=FirebaseDatabase.getInstance().getReference();
        Button confirm;
        confirm = (Button) findViewById(R.id.confirm);
        inputbatch = (EditText) findViewById(R.id.batch);
        inputmanufact = (EditText) findViewById(R.id.man);
        inputPhone = (EditText) findViewById(R.id.userphone);
        inputexpiry = (EditText) findViewById(R.id.exp);
        inputname = (EditText) findViewById(R.id.medname);
        final String Id;

        Intent intent = getIntent();
        Id = intent.getStringExtra("id");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String batch = inputbatch.getText().toString().trim();
                final String man = inputmanufact.getText().toString().trim().toLowerCase();
                final String phone = inputPhone.getText().toString().trim().toLowerCase();
                final String expiry = inputexpiry.getText().toString().trim();
                final String ID = Id;
                int flag=0;
                final String name=inputname.getText().toString().trim().toLowerCase();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
               // String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
               if (TextUtils.isEmpty(batch)) {
                    Toast.makeText(getApplicationContext(), "Enter batch number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(man)) {
                    Toast.makeText(getApplicationContext(), "Enter manufacturer", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(expiry)) {
                    Toast.makeText(getApplicationContext(), "Enter Expiry Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ID)) {
                    Toast.makeText(getApplicationContext(), "ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(expiry.compareTo(date)>=0)
                {
                    Toast.makeText(getApplicationContext(), "The medicine is expired will be donated to an NGO", Toast.LENGTH_SHORT).show();
                    flag=1;

                }
                writeNewmed(batch, man, expiry, Id,name,flag);
                Intent intent = new Intent(updatemed.this,  HomeActivity.class);
                intent.putExtra("id", Id);
                startActivity(intent);
                finish();
            }
        });
        }
    private void writeNewmed (String batch, String man, String expiry, final String ID, String medname,int flag){
        final medinfo user= new medinfo(batch, man, expiry,medname);
        if(flag==1)
        {
            pharmDatabase.child("User_record").child(ID).push().setValue(user);
            userDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        if(ds.getKey().toString().equals(ID))
                        {
                            NGODatabase.child("NGO_Record").child(ds.child("city").getValue().toString()).child(ID).push().setValue(user);

                        }}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            pharmDatabase.child("User_record").child(ID).push().setValue(user);
            userDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (ds.getKey().toString().equals(ID)) {
                            medDatabase.child("Med_Record").child(ds.child("city").getValue().toString()).child(ID).push().setValue(user);

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        Toast.makeText(updatemed.this, "Details Uploaded", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume () {
        super.onResume();
    }
    }








