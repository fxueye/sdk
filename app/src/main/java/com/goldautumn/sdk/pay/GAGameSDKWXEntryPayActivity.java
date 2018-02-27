//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.PayButtonOnClickListener;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class GAGameSDKWXEntryPayActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private Context context = this;

    public GAGameSDKWXEntryPayActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        GAGameSDKLog.d("GAGameSDKWXEntryPayActivity");
        super.onCreate(savedInstanceState);
        WXpay wx = GAGameSDK.getWx();
        this.api = WXAPIFactory.createWXAPI(this, wx.getAPP_ID());
        this.api.handleIntent(this.getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq arg0) {
    }

    public void onResp(BaseResp resp) {
        if(PayButtonOnClickListener.getdialog() != null) {
            PayButtonOnClickListener.getdialog().dismiss();
        }

        PayButtonOnClickListener a = new PayButtonOnClickListener();
        int i = 0;
        GAGameTool.bl = true;

        for(boolean bb = true; Finaldata.getPayURL().length > i; ++i) {
            switch(resp.errCode) {
                case -4:
                    GAGameSDKLog.e("发送被拒绝");
                    a.startTBresult("2", i);
                    if(bb) {
                        Toast.makeText(this.context, String.valueOf(resp.errCode) + this.context.getText(GetResId.getId(this.context, "string", "text_pay_14")), 0).show();
                        GAGameSDK.getmPayDialog().dismiss();
                        GAGameSDK.setPayResult(2);
                        bb = false;
                    }
                    break;
                case -3:
                case -1:
                default:
                    GAGameSDKLog.e("发送返回code:" + resp.errCode + "errStr:" + resp.errStr);
                    a.startTBresult("2", i);
                    if(bb) {
                        Toast.makeText(this.context, String.valueOf(resp.errCode) + this.context.getText(GetResId.getId(this.context, "string", "text_pay_14")), 0).show();
                        GAGameSDK.getmPayDialog().dismiss();
                        GAGameSDK.setPayResult(2);
                        bb = false;
                    }
                    break;
                case -2:
                    GAGameSDKLog.e("发送取消");
                    a.startTBresult("4", i);
                    if(bb) {
                        Toast.makeText(this.context, resp.errCode + resp.errStr, 0).show();
                        GAGameSDK.getmPayDialog().dismiss();
                        GAGameSDK.setPayResult(3);
                        bb = false;
                    }
                    break;
                case 0:
                    GAGameSDKLog.e("发送成功");
                    a.startTBresult("1", i);
                    if(bb) {
                        Toast.makeText(this.context, String.valueOf(resp.errCode) + this.context.getText(GetResId.getId(this.context, "string", "text_pay_8")), 0).show();
                        GAGameSDK.getmPayDialog().dismiss();
                        GAGameSDK.setPayResult(1);
                        bb = false;
                    }
            }
        }

        this.finish();
    }
}
