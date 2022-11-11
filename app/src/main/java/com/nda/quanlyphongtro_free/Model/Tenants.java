package com.nda.quanlyphongtro_free.Model;

public class Tenants {
    private String id, rentHouseId, rentRoomId;
    private String tName, tPhoneNumber, tRentHouse, tRentRoom, tEmail,
            tDob, tNoiSinh, tSoCMND, tNgayCapCMND, tNoiCapCMND;

    public Tenants(){}
    public Tenants(String id, String rentHouseId, String rentRoomId, String tName, String tPhoneNumber,
                   String tRentHouse, String tRentRoom,
                   String tEmail, String tDob, String tNoiSinh, String tSoCMND, String tNgayCapCMND,
                   String tNoiCapCMND) {
        this.id = id;
        this.rentHouseId = rentHouseId;
        this.rentRoomId = rentRoomId;
        this.tName = tName;
        this.tPhoneNumber = tPhoneNumber;
        this.tRentHouse = tRentHouse;
        this.tRentRoom = tRentRoom;
        this.tEmail = tEmail;
        this.tDob = tDob;
        this.tNoiSinh = tNoiSinh;
        this.tSoCMND = tSoCMND;
        this.tNgayCapCMND = tNgayCapCMND;
        this.tNoiCapCMND = tNoiCapCMND;
    }

    public String getId() {
        return id;
    }

    public String getRentHouseId() {
        return rentHouseId;
    }


    public String getRentRoomId() {
        return rentRoomId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPhoneNumber() {
        return tPhoneNumber;
    }

    public void settPhoneNumber(String tPhoneNumber) {
        this.tPhoneNumber = tPhoneNumber;
    }

    public String gettRentHouse() {
        return tRentHouse;
    }

    public void settRentHouse(String tRentHouse) {
        this.tRentHouse = tRentHouse;
    }

    public String gettRentRoom() {
        return tRentRoom;
    }

    public void settRentRoom(String tRentRoom) {
        this.tRentRoom = tRentRoom;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettDob() {
        return tDob;
    }

    public void settDob(String tDob) {
        this.tDob = tDob;
    }

    public String gettNoiSinh() {
        return tNoiSinh;
    }

    public void settNoiSinh(String tNoiSinh) {
        this.tNoiSinh = tNoiSinh;
    }

    public String gettSoCMND() {
        return tSoCMND;
    }

    public void settSoCMND(String tSoCMND) {
        this.tSoCMND = tSoCMND;
    }

    public String gettNgayCapCMND() {
        return tNgayCapCMND;
    }

    public void settNgayCapCMND(String tNgayCapCMND) {
        this.tNgayCapCMND = tNgayCapCMND;
    }

    public String gettNoiCapCMND() {
        return tNoiCapCMND;
    }

    public void settNoiCapCMND(String tNoiCapCMND) {
        this.tNoiCapCMND = tNoiCapCMND;
    }
}
