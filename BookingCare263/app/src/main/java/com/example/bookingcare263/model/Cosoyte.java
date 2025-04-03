package com.example.bookingcare263.model;

import java.io.Serializable;

public class Cosoyte implements Serializable {
    private int id;
    private String name;
    private String img;

    private String diachi;
    private String thongtin;
    private String masogiayphep;
    private String email;
    private String sdt;
    private String website;
    String chuyenkhoa;


    public Cosoyte() {
    }

    public Cosoyte( String name, String img, String diachi, String thongtin
            , String masogiayphep, String chuyenkhoa, String email, String sdt, String website) {
        this.name = name;
        this.img = img;
        this.diachi = diachi;
        this.thongtin = thongtin;
        this.masogiayphep = masogiayphep;
        this.website = website;
        this.sdt = sdt;
        this.email = email;
        this.chuyenkhoa = chuyenkhoa;

    }


    public Cosoyte(int id, String name, String img, String diachi, String thongtin
            , String masogiayphep, String chuyenkhoa,String email, String sdt, String website) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.diachi = diachi;
        this.thongtin = thongtin;
        this.masogiayphep = masogiayphep;
        this.website = website;
        this.sdt = sdt;
        this.email = email;
        this.chuyenkhoa = chuyenkhoa;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMasogiayphep() {
        return masogiayphep;
    }

    public String getChuyenkhoa() {
        return chuyenkhoa;
    }

    public void setChuyenkhoa(String chuyenkhoa) {
        this.chuyenkhoa = chuyenkhoa;
    }

    public void setMasogiayphep(String masogiayphep) {
        this.masogiayphep = masogiayphep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
