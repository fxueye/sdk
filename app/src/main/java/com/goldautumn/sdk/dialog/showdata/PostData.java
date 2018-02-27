//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog.showdata;

import com.goldautumn.sdk.minterface.Data.ShareData;

public class PostData {
    private String bindPhone;
    private String bindMsg;
    private String userName;
    private String passWord;
    private String passWord2;
    private String visitorID;
    private String userType;
    private String userId;
    private String channelOrderId;
    private String peopleName;
    private String peopleId;
    private ShareData shareData;

    public PostData() {
    }

    public ShareData getShareData() {
        return this.shareData;
    }

    public void setShareData(ShareData shareData) {
        this.shareData = shareData;
    }

    public String getPeopleName() {
        return this.peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleId() {
        return this.peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public void init() {
        this.bindPhone = "";
        this.bindMsg = "";
        this.userName = "";
        this.passWord = "";
        this.passWord2 = "";
        this.visitorID = "";
        this.userType = "";
        this.userId = "";
    }

    public String getChannelOrderId() {
        return this.channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord2() {
        return this.passWord2;
    }

    public void setPassWord2(String passWord2) {
        this.passWord2 = passWord2;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVisitorID() {
        return this.visitorID;
    }

    public void setVisitorID(String visitorID) {
        this.visitorID = visitorID;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBindPhone() {
        return this.bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getBindMsg() {
        return this.bindMsg;
    }

    public void setBindMsg(String bindMsg) {
        this.bindMsg = bindMsg;
    }
}
