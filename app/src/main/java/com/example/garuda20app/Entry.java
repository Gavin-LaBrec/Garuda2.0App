package com.example.garuda20app;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Entry {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entry(String TITLE_COL, String PAY_COL, String LOCATION_COL, String TIME_COL, String DESCRIPTION_COL) {
        this.title = TITLE_COL;
        this.pay = PAY_COL;
        this.location = LOCATION_COL;
        this.time = TIME_COL;
        this.description = DESCRIPTION_COL;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "title=" + title +
                ", pay='" + pay + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getpay() {
        return pay;
    }

//    public void setPay(String pay) {
//        this.pay = pay;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    private String title;
    private String pay;
    private String location;
    private String time;
    private String description;

}