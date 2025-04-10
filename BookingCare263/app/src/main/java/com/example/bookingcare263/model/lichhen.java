package com.example.bookingcare263.model;

import java.io.Serializable;

public class lichhen implements Serializable {
    private String id;
    private String idbenhnhan;
    private String idnguoihen;
    private String ngayhenkham;
    private String khunggiokham;
    private  String trangthai;
    private String namebenhnhan;
    private String sdtbenhnhan;
    private String diachibenhnhan;
    private String avatar_ng;
    private String nameng_hen;



    public lichhen(String id, String idbenhnhan, String idbacsi, String ngayhenkham, String khunggiokham, String trangthai,
                   String namebenhnhan, String sdtbenhnhan, String diachibenhnhan,
                   String avatarbs, String namebs) {

        this.id = id;
        this.idbenhnhan = idbenhnhan;
        this.idnguoihen = idbacsi;
        this.ngayhenkham = ngayhenkham;
        this.khunggiokham = khunggiokham;
        this.trangthai = trangthai;
        this.namebenhnhan = namebenhnhan;
        this.sdtbenhnhan = sdtbenhnhan;
        this.diachibenhnhan = diachibenhnhan;
        this.avatar_ng = avatarbs;
        this.nameng_hen = namebs;

    }
    public lichhen( String idbenhnhan, String idbacsi, String ngayhenkham, String khunggiokham, String trangthai,
                    String namebenhnhan, String sdtbenhnhan, String diachibenhnhan, String avatarbs, String namebs) {
        this.idbenhnhan = idbenhnhan;
        this.idnguoihen = idbacsi;
        this.ngayhenkham = ngayhenkham;
        this.khunggiokham = khunggiokham;
        this.trangthai = trangthai;
        this.namebenhnhan = namebenhnhan;
        this.sdtbenhnhan = sdtbenhnhan;
        this.diachibenhnhan = diachibenhnhan;
        this.avatar_ng = avatarbs;
        this.nameng_hen = namebs;
    }

    public lichhen() {

    }

    public String getNamebs() {
        return nameng_hen;
    }

    public void setNamebs(String namebs) {
        this.nameng_hen = namebs;
    }

    public String getAvatarbs() {
        return avatar_ng;
    }

    public void setAvatarbs(String avatarbs) {
        this.avatar_ng = avatarbs;
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
        return idnguoihen;
    }

    public void setIdbacsi(String idbacsi) {
        this.idnguoihen = idbacsi;
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
