package com.example.bookingcare263.model;

import java.io.Serializable;

public class Bacsi implements Serializable {
    private String id;
    private String name;
    private String chuyenkhoa;
    private String diachi;
    private String img;
    private String thongtin;
    private int GiaKham;


    public  Bacsi(){

    }
    public Bacsi(String id, String name, String chuyenkhoa, String diachi, String img, String thongtin, int GiaKham) {
        this.id = id;
        this.name = name;
        this.chuyenkhoa = chuyenkhoa;
        this.diachi = diachi;
        this.img = img;
        this.thongtin = thongtin;

        this.GiaKham = GiaKham;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChuyenkhoa() {
        return chuyenkhoa;
    }

    public void setChuyenkhoa(String chuyenkhoa) {
        this.chuyenkhoa = chuyenkhoa;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public int getGiaKham() {
        return GiaKham;
    }

    public void setGiaKham(int giaKham) {
        GiaKham = giaKham;
    }
}
