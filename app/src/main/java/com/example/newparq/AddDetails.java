package com.example.newparq;

public class AddDetails {

    String vehicleplate;
    String vehicletype;
    String Duration;
    String Location;

    public AddDetails(String vehicleplate, String vehicletype, String duration, String location) {
        this.vehicleplate = vehicleplate;
        this.vehicletype = vehicletype;
        this.Duration = duration;
        this.Location = location;
    }

    public String getVehicleplate() {
        return vehicleplate;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public String getDuration() {
        return Duration;
    }

    public String getLocation() {
        return Location;
    }
}

