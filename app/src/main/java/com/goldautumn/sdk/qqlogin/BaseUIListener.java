//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.qqlogin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.LoginDialog;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

public class BaseUIListener implements IUiListener {
    private Context mContext;
    private String mScope;
    private boolean mIsCaneled;
    private static final int ON_COMPLETE = 0;
    private static final int ON_ERROR = 1;
    private static final int ON_CANCEL = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 0:
                    JSONObject response = (JSONObject)msg.obj;
                    BaseUIListener.initOpenidAndToken(response);
                    GAGameSDKLog.d("onComplete:" + response.toString());
                    break;
                case 1:
                    UiError e = (UiError)msg.obj;
                    GAGameSDKLog.e("errorMsg:" + e.errorMessage);
                    GAGameSDKLog.e("errorDetail:" + e.errorDetail);
                    Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(BaseUIListener.this.mContext, "string", "text_qwlogin_error"), 0).show();
                    break;
                case 2:
                    GAGameSDKLog.e("onCancel:");
                    Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(BaseUIListener.this.mContext, "string", "text_qwlogin_cancel"), 0).show();
            }

        }
    };

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");
            String openId = jsonObject.getString("openid");
            if(!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
                GAGameSDK.getQQ().setAccessToken(token, expires);
                GAGameSDK.getQQ().setOpenId(openId);
            }

            LoginDialog.Instance().hwLoginHttp(openId, token, "21");
        } catch (Exception var4) {
            ;
        }

    }

    public BaseUIListener(Context mContext) {
        this.mContext = mContext;
    }

    public BaseUIListener(Context mContext, String mScope) {
        this.mContext = mContext;
        this.mScope = mScope;
    }

    public void cancel() {
        this.mIsCaneled = true;
    }

    public void onComplete(Object response) {
        if(!this.mIsCaneled) {
            Message msg = this.mHandler.obtainMessage();
            msg.what = 0;
            msg.obj = response;
            this.mHandler.sendMessage(msg);
        }
    }

    public void onError(UiError e) {
        if(!this.mIsCaneled) {
            Message msg = this.mHandler.obtainMessage();
            msg.what = 1;
            msg.obj = e;
            this.mHandler.sendMessage(msg);
        }
    }

    public void onCancel() {
        if(!this.mIsCaneled) {
            Message msg = this.mHandler.obtainMessage();
            msg.what = 2;
            this.mHandler.sendMessage(msg);
        }
    }

    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
