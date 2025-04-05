package com.example.bookingcare263.model;

public class Baiviet {
    String id;
    String iduser;
    String titile;
    String content;
    String timestamp;
    String img;

    public Baiviet() {
    }

    public Baiviet(String id, String iduser,  String content, String timestamp,String title,
                   String img) {
        this.id = id;
        this.iduser = iduser;
        this.titile = title;
        this.timestamp = timestamp;
        this.content = content;
        this.img = img;



    }


    public Baiviet(String iduser,  String content, String timestamp, String titile, String img) {
        this.iduser = iduser;
        this.content = content;
        this.timestamp = timestamp;
        this.titile = titile;
        this.img = img;

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
