package com.example.congduy.landmonitoring.utils;

public class DateUtil {
    public static double convertTimeToHour(int hour,int min,int second){
        return hour + (double)min/60 + (double)second/3600;
    }
}
