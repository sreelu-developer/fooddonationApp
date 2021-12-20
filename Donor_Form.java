package com.example.fooddonationapp;

import android.widget.EditText;

public class Donor_Form {

    // variables for storing our data.
    private String NameofNgo, NoPackets, Address, TimeDuration,Datelimit,Uid;

    public Donor_Form(EditText nameofNgo, String nopackets, String timeduration, String datelimit, String ngoid) {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Donor_Form(String nameofNgo, String noPackets, String address, String timeDuration, String datelimit, String Uid) {
        this.NameofNgo = nameofNgo;
        this.NoPackets = noPackets;
        this.Address = address;
        this.TimeDuration = timeDuration;
        this.Uid=Uid;
        this.Datelimit=datelimit;
    }

    // getter methods for all variables.
    public String getNameofgNgo() {
        return NameofNgo;
    }

    public void setNameofNgo(String NameofNgo) {
        this.NameofNgo = NameofNgo;
    }

    public String getNoPackets() {
        return NoPackets;
    }

    // setter method for all variables.
    public void setNoPackets(String noPackets) {
        this.NoPackets = noPackets;
    }

    public String getPlace() {
        return Address;
    }

    public void setPlace(String Address) {
        this.Address = Address;
    }


    public String getTimeDuration() {
        return TimeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.TimeDuration = timeDuration;
    }
    public String getUid() {
        return Uid;
    }

    public void setUid(String datelimit) {
        this.Uid = Uid;
    }

    public String getDatelimit() {
        return Datelimit;
    }

    public void setDatelimit(String datelimit) {
        this.Datelimit = datelimit;
    }


}




