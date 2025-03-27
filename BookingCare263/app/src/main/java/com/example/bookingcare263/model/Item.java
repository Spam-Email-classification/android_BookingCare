package com.example.bookingcare263.model;

import java.io.Serializable;

import java.io.Serializable;

public class Item implements Serializable {
    String name;
    int icon;
    String thongtin;


    public Item(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getThongtin() {
        return thongtin;
    }
    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

