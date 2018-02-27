//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.goldautumn.sdk.dialog.IngDialog.Builder;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.AES;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.minterface.Data.PayRusltData;
import com.goldautumn.sdk.pay.GAGameSDKpay;
import com.goldautumn.sdk.pay.PayData;
import com.goldautumn.sdk.pay.TBpay;
import com.goldautumn.sdk.pay.WXpay;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class PayButtonOnClickListener implements OnClickListener, OnCheckedChangeListener {
    private static IngDialog dialog;
    private static String msg;
    private static String msg_type;
    private Context context;
    private static int payType;
    private PayData mPaydata;
    private String getRust;
    private boolean isClick;
    private IWXAPI api;
    private Handler mHandler = new Handler(new PayHandlerMessage());

    public PayButtonOnClickListener() {
    }

    public static IngDialog getdialog() {
        return dialog;
    }

    public static String getMsg() {
        return msg;
    }

    public static String getMsg_type() {
        return msg_type;
    }

    public static void setPayType(int payType) {
        payType = payType;
    }

    public void onClick(View v) {
        this.context = v.getContext();
        if(v.getId() == GetResId.getId(this.context, "id", "pay_button_1") && (payType == 1 || payType == 2 || payType == 3)) {
            this.mPaydata = GAGameSDK.getmPatdata();
            this.mPaydata.setPayType(payType);
            GAGameSDKLog.e("payType ------>" + payType);
            this.mHttpThread(payType, this.context);
        }

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        this.context = group.getContext();
        int radioButtonId = group.getCheckedRadioButtonId();
        if(radioButtonId == GetResId.getId(this.context, "id", "radio0")) {
            payType = 1;
            GAGameSDKLog.e("payType ------>" + payType);
        } else if(radioButtonId == GetResId.getId(this.context, "id", "radio1")) {
            payType = 2;
            GAGameSDKLog.e("payType ------>" + payType);
        } else if(radioButtonId == GetResId.getId(this.context, "id", "radio2")) {
            payType = 3;
            GAGameSDKLog.e("payType ------>" + payType);
        }

    }

    protected void mHttpThread(final int payType, final Context context) {
        Runnable mRunnable = null;
        mRunnable = new Runnable() {
            public void run() {
                if(payType == 2) {
                    PayButtonOnClickListener.this.api = WXAPIFactory.createWXAPI(context, GAGameSDK.getWx().getAPP_ID());
                    if(!PayButtonOnClickListener.this.api.isWXAppInstalled()) {
                        Toast.makeText(context, GetResId.getId(context, "string", "text_pay_16"), 0).show();
                        if(PayButtonOnClickListener.dialog != null) {
                            PayButtonOnClickListener.dialog.dismiss();
                        }

                        return;
                    }
                }

                PayButtonOnClickListener.this.startpay(context);
                int i;
                if(payType == 1) {
                    GAGameSDKLog.e("tb pay");
                    TBpay tb = new TBpay();
                    tb.startPayTB((Activity)context, PayButtonOnClickListener.this.getRust);
                    i = 0;
                    GAGameTool.bl = true;
                    PayButtonOnClickListener.this.isClick = true;

                    while(GAGameTool.bl) {
                        PayButtonOnClickListener.this.clickGet(10, i);
                        ++i;
                        if(i >= Finaldata.getPayURL().length) {
                            GAGameTool.bl = false;
                        }
                    }
                } else if(payType == 2) {
                    GAGameSDKLog.e("wx pay");
                    GAGameSDKLog.e("WXpay getRust---->" + PayButtonOnClickListener.this.getRust);
                    WXpay wx = GAGameSDK.getWx();
                    wx.startPayWX(PayButtonOnClickListener.this.getRust, context);
                    i = 0;
                    GAGameTool.bl = true;
                    PayButtonOnClickListener.this.isClick = true;

                    while(GAGameTool.bl) {
                        PayButtonOnClickListener.this.clickGetWX(i);
                        ++i;
                        if(i >= Finaldata.getPayURL().length) {
                            GAGameTool.bl = false;
                        }
                    }

                    if(PayButtonOnClickListener.dialog != null) {
                        PayButtonOnClickListener.dialog.dismiss();
                    }
                } else if(payType == 3) {
                    GAGameSDKLog.e("yl pay");
                }

            }
        };
        Builder ingDialog = new Builder(context);
        dialog = ingDialog.create();
        dialog.show();
        if(mRunnable != null) {
            (new Thread(mRunnable)).start();
        }

    }

    protected void startpay(Context context) {
        if(this.mPaydata.getAppid().isEmpty() && this.mPaydata.getBillNumber().isEmpty() && this.mPaydata.getItem_Name().isEmpty() && this.mPaydata.getPrice().isEmpty() && this.mPaydata.getUID().isEmpty()) {
            Message msg = this.mHandler.obtainMessage(219, context.getText(GetResId.getId(context, "string", "login_fail_username")));
            this.mHandler.sendMessage(msg);
        } else {
            GAGameSDKpay mPay = new GAGameSDKpay();
            int i = 0;

            for(GAGameTool.bl = true; Finaldata.getPayURL().length > i; ++i) {
                this.getRust = mPay.startPay(this.mPaydata.getAppid(), i);
            }
        }

    }

    public void startTBresult(String result, int i) {
        PayData mPaydata = GAGameSDK.getmPatdata();
        PayRusltData mPayRuslt = new PayRusltData();
        GAGameSDKLog.e("startTBresult channelOrderId:" + UserData.getPostData().getChannelOrderId());
        mPayRuslt.SetData("gameOrderId", UserData.getPostData().getChannelOrderId());
        mPayRuslt.SetData("orderStatus", result);
        AES aes = new AES();
        String jsonStr = aes.encrypt(mPayRuslt.DataToString(), Finaldata.getAppkey());
        mPayRuslt = new PayRusltData();
        mPayRuslt.SetData("data", jsonStr);
        mPayRuslt.SetData("appId", mPaydata.getAppid());
        String payURL = GAGameTool.getUrl(mPayRuslt.GetHashMap(), 2, "update_order_status_client", i);
        GAGameTool.mHttpGet(payURL);
    }

    public void clickGet(int code, int i) {
        int a = 0;
        int b = 0;
        if(code == 10) {
            a = 218;
            b = 219;
        } else if(code == 11) {
            a = 220;
            b = 221;
        }

        Message msg;
        if(GAGameSDKpay.getStatus().intValue() == 1000) {
            if(GAGameSDKpay.getPayResult().getResultStatus().equals("9000")) {
                this.startTBresult("1", i);
                if(this.isClick) {
                    msg = this.mHandler.obtainMessage(a, this.context.getText(GetResId.getId(this.context, "string", "text_pay_8")));
                    this.mHandler.sendMessage(msg);
                    this.isClick = false;
                }
            } else if(GAGameSDKpay.getPayResult().getResultStatus().equals("8000")) {
                this.startTBresult("3", i);
                if(this.isClick) {
                    msg = this.mHandler.obtainMessage(a, this.context.getText(GetResId.getId(this.context, "string", "text_pay_9")));
                    this.mHandler.sendMessage(msg);
                    this.isClick = false;
                }
            } else if(GAGameSDKpay.getPayResult().getResultStatus().equals("6001")) {
                this.startTBresult("4", i);
                if(this.isClick) {
                    msg = this.mHandler.obtainMessage(b, GAGameSDKpay.getPayResult().getMemo().toString());
                    this.mHandler.sendMessage(msg);
                    this.isClick = false;
                }
            } else {
                this.startTBresult("2", i);
                if(this.isClick) {
                    GAGameSDKLog.e("tb error:" + GAGameSDKpay.getPayResult().toString());
                    msg = this.mHandler.obtainMessage(b, GAGameSDKpay.getPayResult().getMemo().toString());
                    this.mHandler.sendMessage(msg);
                    this.isClick = false;
                }
            }
        } else if(GAGameSDKpay.getStatus().intValue() == 404) {
            msg = this.mHandler.obtainMessage(GAGameSDKpay.getStatus().intValue(), this.context.getText(GetResId.getId(this.context, "string", "network_error")));
            this.mHandler.sendMessage(msg);
        } else {
            msg = this.mHandler.obtainMessage(b, GAGameSDKpay.getMessage());
            this.mHandler.sendMessage(msg);
        }

    }

    public void clickGetWX(int i) {
        int b = 221;
        Message msg;
        if(GAGameSDKpay.getStatus().intValue() == 1000) {
            this.startTBresult("1", i);
            msg = this.mHandler.obtainMessage(GAGameSDKpay.getStatus().intValue(), GAGameSDKpay.getMessage());
            this.mHandler.sendMessage(msg);
        } else if(GAGameSDKpay.getStatus().intValue() == 404) {
            this.startTBresult("3", i);
            msg = this.mHandler.obtainMessage(GAGameSDKpay.getStatus().intValue(), this.context.getText(GetResId.getId(this.context, "string", "network_error")));
            this.mHandler.sendMessage(msg);
        } else {
            this.startTBresult("2", i);
            msg = this.mHandler.obtainMessage(b, GAGameSDKpay.getMessage());
            this.mHandler.sendMessage(msg);
        }

    }
}
