package com.nda.quanlyphongtro_free.Houses.HouseDetail.Rooms.Payment;

public class Payments {
    private int paymentId;
    private String paymentPurchased,paymentStatus, paymentPurchaseDate, paymentNote;

    public Payments(int paymentId, String paymentPurchased,String paymentStatus, String paymentPurchaseDate, String paymentNote) {
        this.paymentId = paymentId;
        this.paymentPurchased = paymentPurchased;
        this.paymentStatus  = paymentStatus;
        this.paymentPurchaseDate = paymentPurchaseDate;
        this.paymentNote = paymentNote;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }



    public String getPaymentPurchased() {
        return paymentPurchased;
    }

    public void setPaymentPurchased(String paymentPurchased) {
        this.paymentPurchased = paymentPurchased;
    }

    public String getPaymentPurchaseDate() {
        return paymentPurchaseDate;
    }

    public void setPaymentPurchaseDate(String paymentPurchaseDate) {
        this.paymentPurchaseDate = paymentPurchaseDate;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }
}
