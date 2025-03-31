package com.example.bookingcare263.model;

public class Cosoyte {
    String id;
    String name;
    String img;

    String diachi;
    String thongtin;

    public Cosoyte() {
    }

    public Cosoyte(String id, String name, String img, String diachi, String thongtin) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.diachi = diachi;
        this.thongtin = thongtin;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}
