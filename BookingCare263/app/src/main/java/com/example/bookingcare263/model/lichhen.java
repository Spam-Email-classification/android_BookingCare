package com.example.bookingcare263.model;

import java.io.Serializable;

public class lichhen implements Serializable {
    private String id;
    private String idbenhnhan;
    private String idbacsi;
    private String ngayhenkham;
    private String khunggiokham;
    private  String trangthai;
    private String namebenhnhan;
    private String sdtbenhnhan;
    private String diachibenhnhan;
    private String avatarbs;
    private String namebs;

    public lichhen(String id, String idbenhnhan, String idbacsi, String ngayhenkham, String khunggiokham, String trangthai,
                   String namebenhnhan, String sdtbenhnhan, String diachibenhnhan,
                   String avatarbs, String namebs) {

        this.id = id;
        this.idbenhnhan = idbenhnhan;
        this.idbacsi = idbacsi;
        this.ngayhenkham = ngayhenkham;
        this.khunggiokham = khunggiokham;
        this.trangthai = trangthai;
        this.namebenhnhan = namebenhnhan;
        this.sdtbenhnhan = sdtbenhnhan;
        this.diachibenhnhan = diachibenhnhan;
        this.avatarbs = avatarbs;
        this.namebs = namebs;
    }
    public lichhen( String idbenhnhan, String idbacsi, String ngayhenkham, String khunggiokham, String trangthai,
                    String namebenhnhan, String sdtbenhnhan, String diachibenhnhan, String avatarbs, String namebs) {
        this.idbenhnhan = idbenhnhan;
        this.idbacsi = idbacsi;
        this.ngayhenkham = ngayhenkham;
        this.khunggiokham = khunggiokham;
        this.trangthai = trangthai;
        this.namebenhnhan = namebenhnhan;
        this.sdtbenhnhan = sdtbenhnhan;
        this.diachibenhnhan = diachibenhnhan;
        this.avatarbs = avatarbs;
        this.namebs = namebs;
    }

    public String getNamebs() {
        return namebs;
    }

    public void setNamebs(String namebs) {
        this.namebs = namebs;
    }

    public String getAvatarbs() {
        return avatarbs;
    }

    public void setAvatarbs(String avatarbs) {
        this.avatarbs = avatarbs;
    }

    public String getSdtbenhnhan() {
        return sdtbenhnhan;
    }

    public void setSdtbenhnhan(String sdtbenhnhan) {
        this.sdtbenhnhan = sdtbenhnhan;
    }

    public String getDiachibenhnhan() {
        return diachibenhnhan;
    }

    public void setDiachibenhnhan(String diachibenhnhan) {
        this.diachibenhnhan = diachibenhnhan;
    }

    public String getId() {
        return id;
    }

    public String getNamebenhnhan() {
        return namebenhnhan;
    }

    public void setNamebenhnhan(String namebenhnhan) {
        this.namebenhnhan = namebenhnhan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdbenhnhan() {
        return idbenhnhan;
    }

    public void setIdbenhnhan(String idbenhnhan) {
        this.idbenhnhan = idbenhnhan;
    }

    public String getIdbacsi() {
        return idbacsi;
    }

    public void setIdbacsi(String idbacsi) {
        this.idbacsi = idbacsi;
    }

    public String getNgayhenkham() {
        return ngayhenkham;
    }

    public void setNgayhenkham(String ngayhenkham) {
        this.ngayhenkham = ngayhenkham;
    }

    public String getKhunggiokham() {
        return khunggiokham;
    }

    public void setKhunggiokham(String khunggiokham) {
        this.khunggiokham = khunggiokham;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
