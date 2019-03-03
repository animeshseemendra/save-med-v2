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
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class userreg extends AppCompatActivity {
    private EditText userinputEmail, userinputPassword,userinputName,userinputLocation,userinputPhone,userinputAddress;
    private Button userbtnreg;
    private ProgressBar userprogressBar;
    private DatabaseReference UsermDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userreg);
        UsermDatabase = FirebaseDatabase.getInstance().getReference();

        userinputAddress=(EditText) findViewById(R.id.useradd);
        userinputPhone = (EditText) findViewById(R.id.userphone);
        userbtnreg = (Button) findViewById(R.id.userregBtn);
        userinputEmail = (EditText) findViewById(R.id.useremail);
        userinputName=(EditText)findViewById(R.id.userregName);
        userinputLocation=(EditText)findViewById(R.id.userloc);
        userinputPassword = (EditText) findViewById(R.id.userregPassword);
        userprogressBar = (ProgressBar) findViewById(R.id.regProgressBar);

        userbtnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = userinputEmail.getText().toString().trim();
                final String password = userinputPassword.getText().toString().trim();
                final String Location=userinputLocation.getText().toString().trim().toLowerCase();
                final String Name=userinputName.getText().toString().trim();
                final String Address = userinputAddress.getText().toString().trim();
                final String Phone = userinputPhone.getText().toString();
                if (TextUtils.isEmpty(Phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

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

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                userprogressBar.setVisibility(View.VISIBLE);
                //create user
                writeNewUser(Name,Phone,email,password,Address,Location,0.0);
                startActivity(new Intent(userreg.this, frontpage.class));
                finish();

            }
        });
    }
    private void writeNewUser(String Name, String Phone,String email, String password ,String Address, String Location,double coin) {
        User user = new User( Name,Phone,email,password,Address,Location,coin);
        UsermDatabase.child("Users").child(Phone).setValue(user);
        Toast.makeText(userreg.this, "Registration Done", Toast.LENGTH_SHORT).show();
        userprogressBar.setVisibility(View.GONE);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        userprogressBar.setVisibility(View.GONE);
    }
}






