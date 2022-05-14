package com.example.newparq;

public class ReadWriteUserDetails {
    public String  phoneTxt,identyNumberTxt;


    //constructor
    public ReadWriteUserDetails(){};

    public ReadWriteUserDetails( String phone, String identyNumber){
        this.phoneTxt=phone;
        this.identyNumberTxt=identyNumber;
    }
}
