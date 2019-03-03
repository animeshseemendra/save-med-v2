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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HospitalReg extends AppCompatActivity {
    private EditText inputEmail, inputPassword,inputID,inputName,inputLocation;
    private Button btnreg;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitalreg);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnreg = (Button) findViewById(R.id.regBtn);
        inputEmail = (EditText) findViewById(R.id.email);
        inputID = (EditText) findViewById(R.id.regID);
        inputName=(EditText)findViewById(R.id.regName);
        inputLocation=(EditText)findViewById(R.id.loc);
        inputPassword = (EditText) findViewById(R.id.regPassword);
        progressBar = (ProgressBar) findViewById(R.id.regProgressBar);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String Location=inputLocation.getText().toString().trim().toLowerCase();
                final String Name=inputName.getText().toString().trim();
                final String ID=inputID.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Location)) {
                    Toast.makeText(getApplicationContext(), "Enter Location!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(getApplicationContext(), "Name of the hospital!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ID)) {
                    Toast.makeText(getApplicationContext(), "ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //create user
                                writeNewhospital(Name,email,password,ID,Location);
                startActivity(new Intent(HospitalReg.this, frontpage.class));
                finish();

            }
        });
    }
    private void writeNewhospital(String Name , String email, String password,String ID, String Location) {
        Hospital user = new Hospital(Name, email,password,ID,Location);

        mDatabase.child("Hospitals").child(ID).setValue(user);
        Toast.makeText(HospitalReg.this, "Registration Done", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}






