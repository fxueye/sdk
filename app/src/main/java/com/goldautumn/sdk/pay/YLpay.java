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
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.utils.GetResId;
import com.unionpay.UPPayAssistEx;

public class YLpay extends Activity {
    Context context;
    private Activity activity;
    public static final int PLUGIN_NEED_UPGRADE = 2;
    int i;

    public YLpay() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GAGameSDKLog.i("YLpay onCreate");
        ++this.i;
        GAGameSDKLog.i("i:" + this.i);
        Intent intent = this.getIntent();
        this.activity = this;
        if(this.activity == null) {
            GAGameSDKLog.e("error:activity is null");
        } else {
            this.context = this;
            this.startPayYL(intent.getStringExtra("getRust"));
        }
    }

    public void startPayYL(String getRust) {
        PayData strPaydata = new PayData();
        strPaydata.StringToData(getRust);
        GAGameSDKLog.e("status -----> " + GAGameSDKpay.getStatus());
        if(GAGameSDKpay.getStatus().intValue() == 1000) {
            GAGameSDKLog.i("tn:" + strPaydata.GetData("tn"));
            GAGameSDKLog.i("signMethod:" + strPaydata.GetData("signMethod"));
            UPPayAssistEx.startPay(this.activity, (String)null, (String)null, strPaydata.GetData("tn"), "00");
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PayButtonOnClickListener a = new PayButtonOnClickListener();
        int i = 0;
        if(PayButtonOnClickListener.getdialog() != null) {
            GAGameSDKLog.i("getdialog dismiss");
            PayButtonOnClickListener.getdialog().dismiss();
        }

        if(data == null) {
            GAGameSDKLog.e("支付失败");
            a.startTBresult("2", i);
            Toast.makeText(this.context, this.context.getText(GetResId.getId(this.context, "string", "text_pay_14")), 0).show();
            GAGameSDK.getmPayDialog().dismiss();
            GAGameSDK.setPayResult(2);
        } else {
            String str = data.getExtras().getString("pay_result");
            if(str.equalsIgnoreCase("success")) {
                GAGameSDKLog.e("支付成功");
                a.startTBresult("1", i);
                Toast.makeText(this.context, this.context.getText(GetResId.getId(this.context, "string", "text_pay_8")), 0).show();
                GAGameSDK.getmPayDialog().dismiss();
                GAGameSDK.setPayResult(1);
            } else if(str.equalsIgnoreCase("fail")) {
                GAGameSDKLog.e("支付失败");
                a.startTBresult("2", i);
                Toast.makeText(this.context, this.context.getText(GetResId.getId(this.context, "string", "text_pay_14")), 0).show();
                GAGameSDK.getmPayDialog().dismiss();
                GAGameSDK.setPayResult(2);
            } else if(str.equalsIgnoreCase("cancel")) {
                GAGameSDKLog.e("支付取消");
                a.startTBresult("4", i);
                Toast.makeText(this.context, this.context.getText(GetResId.getId(this.context, "string", "text_pay_17")), 0).show();
                GAGameSDK.getmPayDialog().dismiss();
                GAGameSDK.setPayResult(3);
            }

            this.finish();
        }
    }

    protected void onStart() {
        super.onStart();
        GAGameSDKLog.i("YLpay onStart");
    }

    protected void onRestart() {
        super.onRestart();
        GAGameSDKLog.i("YLpay onRestart");
    }

    protected void onPause() {
        super.onPause();
        GAGameSDKLog.i("YLpay onPause");
    }

    protected void onStop() {
        super.onStop();
        GAGameSDKLog.i("YLpay onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        GAGameSDKLog.i("YLpay onDestroy");
    }
}
