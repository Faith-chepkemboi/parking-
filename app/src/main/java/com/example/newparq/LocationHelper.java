package com.example.newparq;

public class LocationHelper {

    private double Longitude;
    private double Latitude;
    private double Jamaa;

    public LocationHelper(double longitude, double latitude, double jamaa) {
        Longitude = longitude;
        Latitude = latitude;
        Jamaa= jamaa;
    }

    public double getJamaa() {
        return Jamaa;
    }

    public void setJamaa(double jamaa) {
        Jamaa = jamaa;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
