package com.nda.quanlyphongtro_free.Model;

import java.util.List;

public class Rooms {
    private String id;
    private String rName,rPrice,rFloorNumber, rBedRoomNumber, rLivingRoomNumber, rArea,
                    rLimitTenants, rDeposit, rGender, rDescription, rNoteToTenant;

    private List<Service> serviceList;


    public Rooms(){}
    public Rooms(String id, String rName, String rPrice, String rFloorNumber, String rBedRoomNumber, String rLivingRoomNumber,
                 String rArea, String rLimitTenants, String rDeposit, String rGender, List<Service> serviceList, String rDescription,
                 String rNoteToTenant) {
        this.id = id;
        this.rName = rName;
        this.rPrice = rPrice;
        this.rFloorNumber = rFloorNumber;
        this.rBedRoomNumber = rBedRoomNumber;
        this.rLivingRoomNumber = rLivingRoomNumber;
        this.rArea = rArea;
        this.rLimitTenants = rLimitTenants;
        this.rDeposit = rDeposit;
        this.rGender = rGender;
        this.serviceList = serviceList;
        this.rDescription = rDescription;
        this.rNoteToTenant = rNoteToTenant;
    }

    public String getId() {
        return id;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrPrice() {
        return rPrice;
    }

    public void setrPrice(String rPrice) {
        this.rPrice = rPrice;
    }

    public String getrFloorNumber() {
        return rFloorNumber;
    }

    public void setrFloorNumber(String rFloorNumber) {
        this.rFloorNumber = rFloorNumber;
    }

    public String getrBedRoomNumber() {
        return rBedRoomNumber;
    }

    public void setrBedRoomNumber(String rBedRoomNumber) {
        this.rBedRoomNumber = rBedRoomNumber;
    }

    public String getrLivingRoomNumber() {
        return rLivingRoomNumber;
    }

    public void setrLivingRoomNumber(String rLivingRoomNumber) {
        this.rLivingRoomNumber = rLivingRoomNumber;
    }

    public String getrArea() {
        return rArea;
    }

    public void setrArea(String rArea) {
        this.rArea = rArea;
    }

    public String getrLimitTenants() {
        return rLimitTenants;
    }

    public void setrLimitTenants(String rLimitTenants) {
        this.rLimitTenants = rLimitTenants;
    }

    public String getrDeposit() {
        return rDeposit;
    }

    public void setrDeposit(String rDeposit) {
        this.rDeposit = rDeposit;
    }

    public String getrGender() {
        return rGender;
    }

    public void setrGender(String rGender) {
        this.rGender = rGender;
    }


    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrNoteToTenant() {
        return rNoteToTenant;
    }

    public void setrNoteToTenant(String rNoteToTenant) {
        this.rNoteToTenant = rNoteToTenant;
    }
}
