package com.example.sih;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class HospitalDetails {

    public String name;
    public String email;
    public String password;
    public String ID;
    public String location;


    public HospitalDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public HospitalDetails(String name, String email,String password, String ID, String location) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.ID=ID;
        this.location=location;
    }

    public String getID() {
      return ID;
    }
    public String getPassword() {
      return password;
    }
}

