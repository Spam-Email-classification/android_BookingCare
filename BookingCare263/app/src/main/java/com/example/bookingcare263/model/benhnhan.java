package com.example.bookingcare263.model;

public class benhnhan {
    String id, ten, sodienthoai, diachi, gioitinh, ngaysinh, benhlynen,img ;
    public benhnhan(){

    }

    public benhnhan(String id, String ten, String sodienthoai, String diachi,
                    String gioitinh, String ngaysinh, String benhlynen, String img) {
        this.id = id;
        this.ten = ten;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.benhlynen = benhlynen;
        this.img = img;
    }

    public benhnhan(String ten, String sodienthoai, String diachi,
                    String gioitinh, String ngaysinh, String benhlynen, String img) {
        this.ten = ten;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.benhlynen = benhlynen;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getBenhlynen() {
        return benhlynen;
    }

    public void setBenhlynen(String benhlynen) {
        this.benhlynen = benhlynen;
    }
}
