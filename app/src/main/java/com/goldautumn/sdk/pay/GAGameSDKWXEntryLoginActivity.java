//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.LoginDialog;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class GAGameSDKWXEntryLoginActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private Context context = this;
    Handler mainHandler = new Handler(Looper.getMainLooper());

    public GAGameSDKWXEntryLoginActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        GAGameSDKLog.d("GAGameSDKWXEntryLoginActivity");
        super.onCreate(savedInstanceState);
        WXpay wx = GAGameSDK.getWx();
        this.api = WXAPIFactory.createWXAPI(this, wx.getAPP_ID());
        this.api.handleIntent(this.getIntent(), this);
    }

    public void onReq(BaseReq arg0) {
        GAGameSDKLog.d("onReq:" + arg0.toString());
    }

    public void onResp(BaseResp resp) {
        GAGameSDKLog.d("onResp:" + resp.errCode);
        GAGameSDKLog.d("resp.getType():" + resp.getType());
        switch(resp.errCode) {
            case -4:
                switch(resp.getType()) {
                    case 1:
                        GAGameSDKLog.e("login denied");
                        this.finish();
                        return;
                    case 2:
                        GAGameSDKLog.e("resp.errCode:" + resp.errCode);
                        GAGameSDKLog.e("resp.errStr:" + resp.errStr);
                        GAGameSDK.getshareDialog().dismiss();
                        GAGameSDK.getShareCallback().shareFail(resp.errStr);
                        this.finish();
                        this.mainHandler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(LoginDialog.Instance().getContext(), "string", "text_share_error"), 0).show();
                            }
                        });
                        return;
                    default:
                        return;
                }
            case -3:
            case -1:
            default:
                GAGameSDKLog.e("发送返回code:" + resp.errCode + "errStr:" + resp.errStr);
                this.finish();
                break;
            case -2:
                switch(resp.getType()) {
                    case 1:
                        GAGameSDKLog.e("login cancel");
                        this.finish();
                        return;
                    case 2:
                        GAGameSDK.getshareDialog().dismiss();
                        GAGameSDK.getShareCallback().shareCancel();
                        this.finish();
                        this.mainHandler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(LoginDialog.Instance().getContext(), "string", "text_share_cancel"), 0).show();
                            }
                        });
                        return;
                    default:
                        return;
                }
            case 0:
                switch(resp.getType()) {
                    case 1:
                        String code = ((Resp)resp).code;
                        GAGameSDKLog.e("login success:code:" + code);
                        GAGameSDKLog.e("login success:openId:" + resp.openId);
                        LoginDialog.Instance().hwLoginHttp("", code, "22");
                        this.finish();
                        break;
                    case 2:
                        GAGameSDKLog.d("share success");
                        GAGameSDK.getshareDialog().dismiss();
                        GAGameSDK.getShareCallback().shareSuccess();
                        this.finish();
                        this.mainHandler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(LoginDialog.Instance().getContext(), "string", "text_share_success"), 0).show();
                            }
                        });
                }
        }

    }
}
