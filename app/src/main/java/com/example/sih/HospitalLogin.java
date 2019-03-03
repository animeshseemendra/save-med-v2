package com.example.sih;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalLogin extends AppCompatActivity {
    private EditText inputId, inputPassword;
    private Button btnlogin;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitallogin);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Hospitals");

        btnlogin = (Button) findViewById(R.id.login);
        inputId = (EditText) findViewById(R.id.Hid);
        inputPassword = (EditText) findViewById(R.id.logPassword);
        progressBar = (ProgressBar) findViewById(R.id.regProgressBar);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String hosId = inputId.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(hosId)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        checkData(dataSnapshot,hosId,password);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
    private void checkData(DataSnapshot dataSnapshot,String Id,String password)
    {
        String obj= null;
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.getKey().toString().equals(Id))
            {
                obj=ds.child("password").getValue().toString();
                if(obj.equals(password))
                {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HospitalLogin.this,  Hospitalhomeactivity.class);
                    intent.putExtra("id", Id);
                    startActivity(intent);
                    finish();

                }
                else
                {
                   Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                //Toast.makeText(getApplicationContext(), "Login Failed-else", Toast.LENGTH_SHORT).show();
            }
            //if(obj.ID.equals(Id) && obj.password.equals(password)){



        }


    }

}