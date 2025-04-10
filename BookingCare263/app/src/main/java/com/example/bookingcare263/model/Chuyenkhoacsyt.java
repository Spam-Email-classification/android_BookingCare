package com.example.bookingcare263.model;

import java.io.Serializable;

public class Chuyenkhoacsyt implements Serializable {
    String id;
    String sdtcsyt;
    String tenkhoa;
    String diachi;
    String giakham;
    String img;
    String thongtin;

    public Chuyenkhoacsyt() {
    }

    public Chuyenkhoacsyt(String id, String idcsyt, String tenkhoa, String diachi, String giakham, String img, String thongtin) {
        this.id = id;
        this.sdtcsyt = idcsyt;
        this.tenkhoa = tenkhoa;
        this.diachi = diachi;
        this.giakham = giakham;
        this.img = img;
        this.thongtin = thongtin;
    }

    public Chuyenkhoacsyt(String idcsyt, String tenkhoa, String diachi, String giakham, String img, String thongtin) {
        this.sdtcsyt = idcsyt;
        this.tenkhoa = tenkhoa;
        this.diachi = diachi;
        this.giakham = giakham;
        this.img = img;
        this.thongtin = thongtin;
    }

    public String getSdtcsyt() {
        return sdtcsyt;
    }

    public void setSdtcsyt(String sdtcsyt) {
        this.sdtcsyt = sdtcsyt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGiakham() {
        return giakham;
    }

    public void setGiakham(String giakham) {
        this.giakham = giakham;
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
}
