package com.andre3.smartshopperslist.models;

/**
 * Created by andre3 on 10/1/16.
 */
public class StoreMdl {

    String storeName;
    String storeNo;
    String storeLcn;
    int storeId;

    public StoreMdl(String storeName, String storeNo, String storeLcn, int storeId) {
        this.storeName = storeName;
        this.storeNo = storeNo;
        this.storeLcn = storeLcn;
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreLcn() {
        return storeLcn;
    }

    public void setStoreLcn(String storeLcn) {
        this.storeLcn = storeLcn;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
