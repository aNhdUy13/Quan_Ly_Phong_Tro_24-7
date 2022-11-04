package com.nda.quanlyphongtro_free.Model;

import java.util.List;

public class Houses {
    private String hId;
    private String hName, hFloorsNumber, hFee, hDescription, hAddress, hTinhThanhPho,
            hQuanHuyen,hOpenTime, hCloseTime, hBaoSoNgayChuyen, hNote;

    private List<Service> serviceList;

    public Houses(){}
    public Houses(String hId, String hName, String hFloorsNumber, String hFee, String hDescription,
                  String hAddress, String hTinhThanhPho, String hQuanHuyen, List<Service> serviceList,
                  String hOpenTime, String hCloseTime, String hBaoSoNgayChuyen, String hNote) {
        this.hId = hId;
        this.hName = hName;
        this.hFloorsNumber = hFloorsNumber;
        this.hFee = hFee;
        this.hDescription = hDescription;
        this.hAddress = hAddress;
        this.hTinhThanhPho = hTinhThanhPho;
        this.hQuanHuyen = hQuanHuyen;
        this.hOpenTime = hOpenTime;
        this.hCloseTime = hCloseTime;
        this.hBaoSoNgayChuyen = hBaoSoNgayChuyen;
        this.hNote = hNote;
        this.serviceList = serviceList;
    }

    public String gethId() {
        return hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethFloorsNumber() {
        return hFloorsNumber;
    }

    public void sethFloorsNumber(String hFloorsNumber) {
        this.hFloorsNumber = hFloorsNumber;
    }

    public String gethFee() {
        return hFee;
    }

    public void sethFee(String hFee) {
        this.hFee = hFee;
    }

    public String gethDescription() {
        return hDescription;
    }

    public void sethDescription(String hDescription) {
        this.hDescription = hDescription;
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String gethTinhThanhPho() {
        return hTinhThanhPho;
    }

    public void sethTinhThanhPho(String hTinhThanhPho) {
        this.hTinhThanhPho = hTinhThanhPho;
    }

    public String gethQuanHuyen() {
        return hQuanHuyen;
    }

    public void sethQuanHuyen(String hQuanHuyen) {
        this.hQuanHuyen = hQuanHuyen;
    }

    public String gethOpenTime() {
        return hOpenTime;
    }

    public void sethOpenTime(String hOpenTime) {
        this.hOpenTime = hOpenTime;
    }

    public String gethCloseTime() {
        return hCloseTime;
    }

    public void sethCloseTime(String hCloseTime) {
        this.hCloseTime = hCloseTime;
    }

    public String gethBaoSoNgayChuyen() {
        return hBaoSoNgayChuyen;
    }

    public void sethBaoSoNgayChuyen(String hBaoSoNgayChuyen) {
        this.hBaoSoNgayChuyen = hBaoSoNgayChuyen;
    }

    public String gethNote() {
        return hNote;
    }

    public void sethNote(String hNote) {
        this.hNote = hNote;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
