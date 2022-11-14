package com.nda.quanlyphongtro_free.Model;

public class HoaDon {
    private String id;
    private String hoaDonThang, rentHouse, rentRoom, ngayThanhToan, hanThanhToan, roomFee,
             note, totalServiceFee, noteServices;

    private boolean daThanhToan = false;

    public HoaDon(){}

    public HoaDon(String id, String hoaDonThang, String rentHouse, String rentRoom, String ngayThanhToan,
                  String hanThanhToan, String roomFee, String note, String totalServiceFee, String noteServices,
                  boolean daThanhToan) {
        this.id = id;
        this.hoaDonThang = hoaDonThang;
        this.rentHouse = rentHouse;
        this.rentRoom = rentRoom;
        this.ngayThanhToan = ngayThanhToan;
        this.hanThanhToan = hanThanhToan;
        this.roomFee = roomFee;
        this.note = note;
        this.totalServiceFee = totalServiceFee;
        this.noteServices = noteServices;
        this.daThanhToan = daThanhToan;
    }

    public String getId() {
        return id;
    }


    public String getHoaDonThang() {
        return hoaDonThang;
    }

    public void setHoaDonThang(String hoaDonThang) {
        this.hoaDonThang = hoaDonThang;
    }

    public String getRentHouse() {
        return rentHouse;
    }

    public void setRentHouse(String rentHouse) {
        this.rentHouse = rentHouse;
    }

    public String getRentRoom() {
        return rentRoom;
    }

    public void setRentRoom(String rentRoom) {
        this.rentRoom = rentRoom;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getHanThanhToan() {
        return hanThanhToan;
    }

    public void setHanThanhToan(String hanThanhToan) {
        this.hanThanhToan = hanThanhToan;
    }

    public String getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(String roomFee) {
        this.roomFee = roomFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(String totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public String getNoteServices() {
        return noteServices;
    }

    public void setNoteServices(String noteServices) {
        this.noteServices = noteServices;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }
}
