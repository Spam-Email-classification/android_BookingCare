package com.example.bookingcare263.model;

public class accout {
    private String name, phone, pass, email,  as;

    public accout(String name, String phone, String pass, String as, String email) {
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.as = as;
        this.email = email;
    }

    public accout() {
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
