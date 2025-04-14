package com.example.bookingcare263.model;

import java.io.Serializable;

public class Bacsi implements Serializable {
    private String id;
    private String name;
    private String chuyenkhoa;
    private String diachi;
    private String img;
    private String thongtin;
    private String GiaKham;

    private String sogiayphephanhnghe;
    private String email;
    private String sdt;
    private String imggiayphep;


    public  Bacsi(){

    }

    public Bacsi(String id, String name, String chuyenkhoa, String diachi, String img, String thongtin,
                 String GiaKham, String sogiayphephanhnghe, String email, String sdt, String imggiayphep) {
        this.id = id;
        this.name = name;
        this.chuyenkhoa = chuyenkhoa;
        this.diachi = diachi;
        this.img = img;
        this.thongtin = thongtin;
        this.GiaKham = GiaKham;
        this.sogiayphephanhnghe = sogiayphephanhnghe;
        this.email = email;
        this.sdt = sdt;
        this.imggiayphep = imggiayphep;
    }

    public Bacsi(String name, String chuyenkhoa, String diachi, String img, String thongtin,
                 String GiaKham, String sogiayphephanhnghe, String email, String sdt
                ,String imggiayphep) {

        this.name = name;
        this.chuyenkhoa = chuyenkhoa;
        this.diachi = diachi;
        this.img = img;
        this.thongtin = thongtin;
        this.GiaKham = GiaKham;
        this.sogiayphephanhnghe = sogiayphephanhnghe;
        this.email = email;
        this.sdt = sdt;
        this.imggiayphep = imggiayphep;
    }

    public String getImggiayphep() {
        return imggiayphep;
    }

    public void setImggiayphep(String imggiayphep) {
        this.imggiayphep = imggiayphep;
    }

    public String getSogiayphephanhnghe() {
        return sogiayphephanhnghe;
    }

    public void setSogiayphephanhnghe(String sogiayphephanhnghe) {
        this.sogiayphephanhnghe = sogiayphephanhnghe;
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

    public String getGiaKham() {
        return GiaKham;
    }

    public void setGiaKham(String giaKham) {
        GiaKham = giaKham;
    }
}
