package com.nda.quanlyphongtro_free.Houses;

public class Houses {
    private int housesId;
    private String housesName,housesLocation;

    public Houses(int housesId, String housesName, String housesLocation) {
        this.housesId = housesId;
        this.housesName = housesName;
        this.housesLocation = housesLocation;
    }

    public int getHousesId() {
        return housesId;
    }

    public void setHousesId(int housesId) {
        this.housesId = housesId;
    }

    public String getHousesName() {
        return housesName;
    }

    public void setHousesName(String housesName) {
        this.housesName = housesName;
    }

    public String getHousesLocation() {
        return housesLocation;
    }

    public void setHousesLocation(String housesLocation) {
        this.housesLocation = housesLocation;
    }
}
