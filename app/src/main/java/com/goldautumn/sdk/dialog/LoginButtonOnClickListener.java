//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.goldautumn.sdk.activity.ProtocolActivity;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Req;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LoginButtonOnClickListener implements OnClickListener {
    static int a = 1;
    private IWXAPI api;

    public LoginButtonOnClickListener() {
    }

    public void onClick(View v) {
        if(v.getId() == GetResId.getId(v.getContext(), "id", "welcome1_button_4")) {
            String username = GAGameTool.getUsernameLogin(LoginDialog.context, "username", "CreateVisitor_data");
            String password = GAGameTool.getUsernameLogin(LoginDialog.context, "password", "CreateVisitor_data");
            if(username.isEmpty() && username.equals("") && password.isEmpty() && password.equals("")) {
                LoginDialog.Instance().mHttpThread(v.getContext(), 3, "");
            } else {
                UserData.getPostData().setUserName(username);
                UserData.getPostData().setPassWord(password);
                LoginDialog.Instance().mHttpThread(v.getContext(), 1, "0");
            }
        } else if(v.getId() != GetResId.getId(v.getContext(), "id", "welcome1_button_1") && v.getId() != GetResId.getId(v.getContext(), "id", "registered_imagebutton_1")) {
            if(v.getId() == GetResId.getId(v.getContext(), "id", "login_button_reg")) {
                LoginDialog.Instance().init(v.getContext(), 3);
                LoginDialog.Instance().show();
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "login_button_1")) {
                UserData.getPostData().setUserName(LoginDialog.geteditUsername().getText().toString().trim());
                UserData.getPostData().setPassWord(LoginDialog.geteditPassword().getText().toString().trim());
                if(TextUtils.isEmpty(UserData.getPostData().getUserName()) || TextUtils.isEmpty(UserData.getPostData().getPassWord())) {
                    Toast.makeText(v.getContext(), GetResId.getId(v.getContext(), "string", "username_or_pws_nil"), 0).show();
                    return;
                }

                LoginDialog.Instance().mHttpThread(v.getContext(), 1, "1");
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "registered_button_1")) {
                UserData.getPostData().setUserName(LoginDialog.geteditUsername().getText().toString().trim());
                UserData.getPostData().setPassWord(LoginDialog.geteditPassword().getText().toString().trim());
                UserData.getPostData().setPassWord2(LoginDialog.geteditPassword2().getText().toString().trim());
                if(TextUtils.isEmpty(UserData.getPostData().getUserName()) || TextUtils.isEmpty(UserData.getPostData().getPassWord()) || TextUtils.isEmpty(UserData.getPostData().getPassWord2())) {
                    Toast.makeText(v.getContext(), GetResId.getId(v.getContext(), "string", "username_or_pws_nil"), 0).show();
                    return;
                }

                if(!UserData.getPostData().getPassWord().equals(UserData.getPostData().getPassWord2())) {
                    Toast.makeText(v.getContext(), GetResId.getId(v.getContext(), "string", "password_isfail"), 0).show();
                    LoginDialog.geteditPassword().setText("");
                    LoginDialog.geteditPassword2().setText("");
                    return;
                }

                LoginDialog.Instance().mHttpThread(v.getContext(), 2, "");
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "login_imagebutton_1")) {
                GAGameTool.immDismiss(v);
                LoginDialog.Instance().init(v.getContext(), 1);
                LoginDialog.Instance().show();
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "login_imagebutton_2")) {
                if(a == 1) {
                    a = 2;
                    GAGameTool.immDismiss(v);
                    LoginDialog.Instance().download();
                    LoginDialog.getlistViewLin().setVisibility(0);
                } else {
                    a = 1;
                    LoginDialog.getlistViewLin().setVisibility(8);
                }
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "tv_2")) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), ProtocolActivity.class);
                v.getContext().startActivity(intent);
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "login_button_forgotpassword")) {
                UserCenterDialog.Instance().init(LoginDialog.context, 6);
                UserCenterDialog.Instance().show();
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "welcome1_button_3")) {
                this.api = WXAPIFactory.createWXAPI(v.getContext(), GAGameSDK.getWx().getAPP_ID());
                if(!this.api.isWXAppInstalled()) {
                    Toast.makeText(v.getContext(), GetResId.getId(v.getContext(), "string", "text_pay_16"), 0).show();
                    return;
                }

                Req req = new Req();
                req.scope = "snsapi_userinfo";
                req.state = "none";
                this.api.sendReq(req);
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "welcome1_button_2") && GAGameSDK.getQQ() != null && !GAGameSDK.getQQ().isSessionValid()) {
                GAGameSDK.getQQ().login(LoginDialog.activity, "all", GAGameSDK.loginListener);
            }
        } else {
            LoginDialog.Instance().init(v.getContext(), 2);
            LoginDialog.Instance().show();
        }

    }
}
