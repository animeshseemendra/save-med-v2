package com.example.sih;

import android.content.Intent;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileuser extends AppCompatActivity {

    private TextView addname,addphone,addemail,addaddress,addcity;
    private DatabaseReference profileDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileuser);
        Intent intent = getIntent();
        final String Id = intent.getStringExtra("id");
        addname=(TextView)findViewById(R.id.profileusername);
        addphone=(TextView)findViewById(R.id.profileuserphone);
        addemail=(TextView)findViewById(R.id.profileuseremail);
        addaddress=(TextView)findViewById(R.id.profileuseraddress);
        addcity=(TextView)findViewById(R.id.profileusercity);
        profileDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        profileDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.getKey().toString().equals(Id))
                    {
                        addname.setText(ds.child("name").getValue().toString());
                        addphone.setText(ds.child("phone").getValue().toString());
                        addemail.setText(ds.child("email").getValue().toString());
                        addaddress.setText(ds.child("address").getValue().toString());
                        addcity.setText(ds.child("city").getValue().toString());

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
