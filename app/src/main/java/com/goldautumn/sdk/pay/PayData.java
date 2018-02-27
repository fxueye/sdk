//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import com.goldautumn.sdk.minterface.Data.BaseData;

public class PayData extends BaseData {
    private String appid;
    private String Item_Name;
    private String price;
    private String billNumber;
    private String UID;
    private String itemDetail;
    private int payType;

    public PayData() {
    }

    public String getAppid() {
        return this.appid;
    }

    public String getItem_Name() {
        return this.Item_Name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getBillNumber() {
        return this.billNumber;
    }

    public String getUID() {
        return this.UID;
    }

    public void setPaydata(String appid, String Item_Name, String price, String billNumber, String UID, String itemDetail) {
        this.appid = appid;
        this.Item_Name = Item_Name;
        this.price = price;
        this.billNumber = billNumber;
        this.UID = UID;
        this.itemDetail = itemDetail;
        this.SetData("itemName", Item_Name);
        this.SetData("amount", price);
        this.SetData("gameOrderId", billNumber);
        this.SetData("accountId", UID);
        this.SetData("itemDetail", itemDetail);
        this.SetData("itemPrice", price);
        this.SetData("itemNum", "1");
        this.SetData("itemId", "1");
    }

    public void setPayType(int payType) {
        this.payType = payType;
        this.SetData("payChannel", "" + payType);
    }
}
