package com.example.sih;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class medinfo {

    public String batch;
    public String manu;
    public String expiry;
    public String medname;

    public medinfo() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public medinfo(String batch, String manu, String expiry, String medname) {
        this.batch = batch;
        this.manu = manu;
        this.expiry=expiry;
        this.medname=medname;
    }

    //public String getID() {
    //  return ID;
    //}
    //public String getPassword() {
    //  return password;
    //}
}

