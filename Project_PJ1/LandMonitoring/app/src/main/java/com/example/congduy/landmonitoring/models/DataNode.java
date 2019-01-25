package com.example.congduy.landmonitoring.models;

import com.google.gson.annotations.SerializedName;

public class DataNode {
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public double getNhietdo() {
        return nhietdo;
    }

    public double getDoam() {
        return doam;
    }

    public double getPh() {
        return ph;
    }

    public double getDA_dat() {
        return DA_dat;
    }

    public double getPIR() {
        return PIR;
    }

    public double getEC_meter() {
        return EC_meter;
    }

    public double getAnhsang() {
        return anhsang;
    }

    public double getPin() {
        return Pin;
    }

    public String getDate() {
        return date;
    }

    @SerializedName("nhietdo")

    private double nhietdo;
    @SerializedName("doam")
    private double doam;
    @SerializedName("ph")
    private double ph;
    @SerializedName("DA_dat")
    private double DA_dat;
    @SerializedName("PIR")
    private double PIR;
    @SerializedName("EC_meter")
    private double EC_meter;
    @SerializedName("anhsang")
    private double anhsang;
    @SerializedName("Pin")
    private double Pin;
    @SerializedName("date")
    private String date;

}
