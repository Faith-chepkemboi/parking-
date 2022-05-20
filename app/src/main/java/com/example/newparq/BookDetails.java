package com.example.newparq;

public class BookDetails {

    String minutes1;
    String minutes2;
    String minutes3;

    String sh1;
    String sh2;
    String sh3;



    public BookDetails(String minutes1, String minutes2, String minutes3, String sh1, String sh2, String sh3 ){
        this.minutes1=minutes1;
        this.minutes2=minutes2;
        this.minutes3=minutes3;

        this.sh1=sh1;
        this.sh2=sh2;
        this.sh3=sh3;

    }

    public String getMinutes1() {
        return minutes1;
    }
    public String getMinutes2() {
        return minutes2;
    }
    public String getMinutes3() {
        return minutes3;
    }

    public String getSh1() { return sh1;}
    public String getSh2() { return sh2;}
    public String getSh3() { return sh3;}


    }






