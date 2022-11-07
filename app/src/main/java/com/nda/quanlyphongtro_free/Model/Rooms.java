package com.nda.quanlyphongtro_free.Model;

public class Rooms {
    private int id;
    private String roomsName,roomPrice,roomsNote;

    public Rooms(int id,  String roomPrice, String roomsNote,String roomsName) {
        this.id = id;
        this.roomsName = roomsName;
        this.roomPrice = roomPrice;
        this.roomsNote = roomsNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomsName() {
        return roomsName;
    }

    public void setRoomsName(String roomsName) {
        this.roomsName = roomsName;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }



    public String getRoomsNote() {
        return roomsNote;
    }

    public void setRoomsNote(String roomsNote) {
        this.roomsNote = roomsNote;
    }
}
