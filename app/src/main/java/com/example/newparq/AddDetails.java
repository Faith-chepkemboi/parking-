package com.example.newparq;

public class AddDetails {

    String PhoneN;
    String Charges;
    String Nslots;
    String location;

    public AddDetails() {
    }

    ;

    public AddDetails(String  editTextAphone , String editTextAcharges, String  editTextNslots, String editTextLocation) {
        this.PhoneN = editTextAphone;
        this.Charges = editTextAcharges;
        this.Nslots= editTextNslots;
        this.location = editTextLocation;
    }

    public String getAphone() {
        return PhoneN;
    }

    public void setAphone(String Aphone) {
        this.PhoneN = Aphone;
    }

    public String getAcharge() {
        return Charges;
    }

    public void setAcharge(String Acharge) {
        this.Charges= Acharge;
    }


    public String getAslot() {
        return Nslots;
    }

    public void setAslot(String Aslot) {
        this.Nslots = Aslot;
    }

    public String getLocation() {
        return Nslots;
    }

    public void setLocation(String Location) {
        this.location= Location;

    }
}

