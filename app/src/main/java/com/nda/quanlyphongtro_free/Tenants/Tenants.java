package com.nda.quanlyphongtro_free.Tenants;

public class Tenants {
    private int id;
    private String name,phonenumber,note;

    public Tenants(int id, String name, String phonenumber, String note) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
