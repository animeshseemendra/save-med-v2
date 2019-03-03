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

public class UserLogin extends AppCompatActivity {
    private EditText inputId, inputPassword;
    private Button userbtnlogin;
    private ProgressBar progressBar;
    private DatabaseReference userDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        userbtnlogin = (Button) findViewById(R.id.userlogin);
        inputId = (EditText) findViewById(R.id.userHid);
        inputPassword = (EditText) findViewById(R.id.userlogPassword);
        progressBar = (ProgressBar) findViewById(R.id.userregProgressBar);
        userbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Id = inputId.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Id)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                userDatabase.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        checkData(dataSnapshot,Id,password);

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
                    Intent intent = new Intent(UserLogin.this,  HomeActivity.class);
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
                Toast.makeText(getApplicationContext(), "Login Failed-else", Toast.LENGTH_SHORT).show();
            }
            //if(obj.ID.equals(Id) && obj.password.equals(password)){



        }

    }

}