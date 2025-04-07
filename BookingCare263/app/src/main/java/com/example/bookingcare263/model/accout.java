package com.example.bookingcare263.model;

public class accout {
    private String id, name, phone, pass,  as, status,token;


    public accout(String id, String name, String phone, String pass, String as,
                  String status, String token) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.as = as;
        this.status = status;
        this.token = token;

    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public accout(String name, String phone, String pass, String as, String status) {
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.as = as;
        this.status = status;
    }

    public accout() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }
}
