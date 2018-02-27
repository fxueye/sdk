//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.AES;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;

public class GAGameSDKpay {
    private static String status;
    private static String message;
    private static TBPayResult payResult;

    public GAGameSDKpay() {
    }

    public static TBPayResult getPayResult() {
        return payResult;
    }

    public static void setPayResult(TBPayResult payResult) {
        payResult = payResult;
    }

    public static Integer getStatus() {
        return !status.isEmpty() && status != null?Integer.valueOf(Integer.parseInt(status)):Integer.valueOf(404);
    }

    public static void setStatus(String status) {
        status = status;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        message = message;
    }

    public String startPay(String appid, int i) {
        PayData mPatdata = GAGameSDK.getmPatdata();
        AES aes = new AES();
        String jsonStr = aes.encrypt(mPatdata.DataToString(), Finaldata.getAppkey());
        PayData strPaydata = new PayData();
        strPaydata.SetData("data", jsonStr);
        strPaydata.SetData("appId", appid);
        String payURL = GAGameTool.getUrl(strPaydata.GetHashMap(), 2, "create_order", i);
        GAGameSDKLog.e("URL------>" + payURL);
        String getRust = GAGameTool.mHttpGet(payURL);
        strPaydata = new PayData();
        strPaydata.StringToData(getRust);
        PayData channelData = new PayData();
        channelData.StringToData(strPaydata.GetData("data"));
        setStatus(strPaydata.GetData("status"));
        setMessage(strPaydata.GetData("message"));
        GAGameSDKLog.i("GalaxySDKpay channelOrderId:" + channelData.GetData("channelOrderId"));
        UserData.getPostData().setChannelOrderId(channelData.GetData("channelOrderId"));
        return strPaydata.GetData("data");
    }
}
