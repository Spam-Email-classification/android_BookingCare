package com.example.bookingcare263.model;

import java.io.Serializable;

public class chuyenkhoa implements Serializable {

    String id;
    String tenchuyenkhoa;
    String img;
    String thongtin;

    public chuyenkhoa() {
    }

    public chuyenkhoa(String id, String tenchuyenkhoa, String img, String thongtin) {
        this.id = id;
        this.tenchuyenkhoa = tenchuyenkhoa;
        this.img = img;
        this.thongtin = thongtin;
    }

    public chuyenkhoa( String tenchuyenkhoa, String img, String thongtin) {
        this.tenchuyenkhoa = tenchuyenkhoa;
        this.img = img;
        this.thongtin = thongtin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenchuyenkhoa() {
        return tenchuyenkhoa;
    }

    public void setTenchuyenkhoa(String tenchuyenkhoa) {
        this.tenchuyenkhoa = tenchuyenkhoa;
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
