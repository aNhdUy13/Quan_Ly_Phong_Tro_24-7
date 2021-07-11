package com.nda.quanlyphongtro_free.Contract;

public class Contract {
    private int contractID;
    private byte[] imgContract;

    public Contract(int contractID, byte[] imgContract) {
        this.contractID = contractID;
        this.imgContract = imgContract;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public byte[] getImgContract() {
        return imgContract;
    }

    public void setImgContract(byte[] imgContract) {
        this.imgContract = imgContract;
    }
}
