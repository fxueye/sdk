//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;
import android.os.Handler.Callback;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.SmallDialog.Builder;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.pay.GAGameSDKpay;
import com.goldautumn.sdk.utils.GetResId;

public class PayHandlerMessage implements Callback {
    public PayHandlerMessage() {
    }

    public boolean handleMessage(Message msg) {
        if(PayButtonOnClickListener.getdialog() != null) {
            PayButtonOnClickListener.getdialog().dismiss();
        }

        String message = (String)msg.obj;
        Toast.makeText(LoginDialog.context, message, 0).show();
        GAGameSDKLog.e("" + msg.what);
        switch(msg.what) {
            case 218:
                GAGameSDKLog.e("PAY_SUCCESS");
                GAGameSDK.getmPayDialog().dismiss();
                GAGameSDK.setPayResult(1);
                String diaLogMsg = "";
                if(!UserData.getShowData().getUserType().equals("1")) {
                    diaLogMsg = LoginDialog.context.getString(GetResId.getId(LoginDialog.context, "string", "dialog_paysuccess_msg1"));
                    this.showDialog(diaLogMsg, 1);
                } else if(UserData.getShowData().getPhone() == null || UserData.getShowData().getPhone().isEmpty()) {
                    diaLogMsg = LoginDialog.context.getString(GetResId.getId(LoginDialog.context, "string", "dialog_paysuccess_msg2"));
                    this.showDialog(diaLogMsg, 2);
                }
                break;
            case 219:
                GAGameSDKLog.e("PAY_FAIL");
                GAGameSDK.getmPayDialog().dismiss();
                if(GAGameSDKpay.getStatus().equals("1000")) {
                    if(!GAGameSDKpay.getPayResult().getResultStatus().equals("6001")) {
                        GAGameSDK.setPayResult(3);
                    } else {
                        GAGameSDK.setPayResult(2);
                    }
                } else {
                    GAGameSDK.setPayResult(2);
                }
                break;
            case 404:
                GAGameSDKLog.e("NETWORK_ERROR");
        }

        return false;
    }

    public void showDialog(String msg, final int type) {
        Builder builder = new Builder(LoginDialog.context);
        builder.setMessage(msg);
        builder.setNegativeButton(GetResId.getId(LoginDialog.context, "string", "dialog_del_no"), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(GetResId.getId(LoginDialog.context, "string", "dialog_del_yes_1"), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(type == 1) {
                    UserCenterDialog.Instance().init(LoginDialog.context, 10);
                    UserCenterDialog.Instance().show();
                } else {
                    UserCenterDialog.Instance().init(LoginDialog.context, 9);
                    UserCenterDialog.Instance().show();
                }

                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
