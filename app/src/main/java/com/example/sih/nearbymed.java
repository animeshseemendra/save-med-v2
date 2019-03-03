package com.example.sih;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class nearbymed extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearbymed);


        final String id;
        final DatabaseReference nearbyDatabase;
        final LinearLayout buttoncont=(LinearLayout)findViewById(R.id.buttoncontainer);

        DatabaseReference pharmdetDatabase;

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pharmdetDatabase= FirebaseDatabase.getInstance().getReference().child("Hospitals");
        nearbyDatabase = FirebaseDatabase.getInstance().getReference().child("Med_Record");
        pharmdetDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String location;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                   if(ds.getKey().equals(id))
                    {
                        location =ds.child("location").getValue().toString();
                        further(location,id,buttoncont,nearbyDatabase);
                    }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void further(final String location, final String Id, final LinearLayout buttoncont, DatabaseReference nearbyDatabase)
    {
        nearbyDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.getKey().equals(location))
                    {   int i=1;

                        for(DataSnapshot ds2 : ds.getChildren()) {
                            if(ds2.getKey()!=null)
                            {
                                for(DataSnapshot ds3:ds2.getChildren())
                                {
                                    Toast.makeText(getApplicationContext(), ds3.getKey(), Toast.LENGTH_SHORT).show();
                                    Button button = new Button(nearbymed.this);
                                    button.setText(i+" .Name : "+ds3.child("medname").getValue().toString()+", Expiry "+ds3.child("expiry").getValue().toString()+",Contact :,"+ds2.getKey());
                                    button.setBackgroundColor(Color.TRANSPARENT);
                                    buttoncont.addView(button);
                                    i=i+1;


                                }
                            }}
                    }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
