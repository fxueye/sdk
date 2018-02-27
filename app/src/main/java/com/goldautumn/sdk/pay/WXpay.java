//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import android.content.Context;
import android.os.Bundle;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX.Resp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXpay {
    private String app_id = "";
    private IWXAPI api;

    public WXpay() {
    }

    public String getAPP_ID() {
        return this.app_id;
    }

    public void setAPP_ID(String appId) {
        this.app_id = appId;
    }

    public void init(Context context) {
        GAGameSDKLog.d("app_id" + this.app_id);
        this.api = WXAPIFactory.createWXAPI(context, this.app_id);
    }

    public void requestWXreq(String text, String transaction) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        Req req = new Req();
        req.transaction = transaction;
        req.message = msg;
        this.api.sendReq(req);
    }

    public void requestWXresp(String text, Bundle bundle) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        WXMediaMessage msg = new WXMediaMessage();
        msg.description = text;
        Resp resp = new Resp();
        resp.transaction = (new com.tencent.mm.opensdk.modelmsg.GetMessageFromWX.Req(bundle)).transaction;
        resp.message = msg;
        this.api.sendResp(resp);
    }

    public void startPayWX(String getRust, Context context) {
        this.init(context);
        PayData paydata = new PayData();
        paydata.StringToData(getRust);
        if(GAGameSDKpay.getStatus().intValue() == 1000) {
            PayReq request = new PayReq();
            request.appId = paydata.GetData("appid");
            request.partnerId = paydata.GetData("partnerid");
            request.prepayId = paydata.GetData("prepayid");
            request.packageValue = paydata.GetData("package");
            request.nonceStr = paydata.GetData("noncestr");
            request.timeStamp = paydata.GetData("timestamp");
            request.sign = paydata.GetData("sign");
            GAGameSDKLog.i("request.appId:" + request.appId);
            GAGameSDKLog.i("request.partnerId:" + request.partnerId);
            GAGameSDKLog.i("request.prepayId:" + request.prepayId);
            GAGameSDKLog.i("request.packageValue:" + request.packageValue);
            GAGameSDKLog.i("request.nonceStr:" + request.nonceStr);
            GAGameSDKLog.i("request.timeStamp:" + request.timeStamp);
            GAGameSDKLog.i("request.sign:" + request.sign);
            this.api.sendReq(request);
        }

    }
}
