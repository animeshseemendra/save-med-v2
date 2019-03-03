package com.example.sih;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String name;
    public String phone;
    public String email;
    public String password;
    public String address;
    public String city;
    public double coin;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String phone, String email, String password,String address,String city,double coin) {
        this.name = name;
        this.phone = phone;
        this.email=email;
        this.password=password;
        this.address=address;
        this.city=city;
        this.coin=coin;
    }

    //public String getID() {
    //  return ID;
    //}
    //public String getPassword() {
    //  return password;
    //}
}

