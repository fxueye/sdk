//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.goldautumn.sdk.activity.ProtocolActivity;
import com.goldautumn.sdk.dialog.IngDialog.Builder;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.minterface.GetMessage;
import com.goldautumn.sdk.utils.GetResId;
import com.goldautumn.sdk.utils.IDCardValidate;
import java.text.ParseException;

public class UserCenterButtonOnClickListener implements OnClickListener {
    private static IngDialog dialog;
    private static String msg_type;
    private Context context;
    private boolean bl = true;
    private Handler mHandler = new Handler(new UserCenterHandlerMessage());

    public UserCenterButtonOnClickListener() {
    }

    public static IngDialog getdialog() {
        return dialog;
    }

    public static String getMsg_type() {
        return msg_type;
    }

    public void onClick(View v) {
        this.context = v.getContext();
        if(v.getId() == GetResId.getId(this.context, "id", "user_button_3")) {
            UserCenterDialog.Instance().init(v.getContext(), 8);
            UserCenterDialog.Instance().show();
        } else if(v.getId() == GetResId.getId(this.context, "id", "user_button_4")) {
            UserCenterDialog.Instance().init(LoginDialog.context, 13);
            UserCenterDialog.Instance().show();
        } else if(v.getId() == GetResId.getId(this.context, "id", "user_button_2")) {
            UserCenterDialog.Instance().init(v.getContext(), 9);
            UserCenterDialog.Instance().show();
        } else if(v.getId() == GetResId.getId(this.context, "id", "user_button_1")) {
            UserCenterDialog.Instance().init(v.getContext(), 10);
            UserCenterDialog.Instance().show();
        } else if(v.getId() == GetResId.getId(this.context, "id", "user_button_5")) {
            GAGameSDK.logoutA(this.context);
            GAGameSDK.setlogoutResult();
            UserCenterDialog.Instance().dismiss();
        } else if(v.getId() == GetResId.getId(this.context, "id", "user_button_6")) {
            UserCenterDialog.Instance().init(this.context, 11);
            UserCenterDialog.Instance().show();
        } else {
            String peopleName;
            if(v.getId() == GetResId.getId(this.context, "id", "forgot_button_1")) {
                peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                UserData.getPostData().setUserName(peopleName);
                UserData.getShowData().setPhoneType("2");
                this.mHttpThread(4, v.getContext());
            } else if(v.getId() == GetResId.getId(this.context, "id", "forgot_button_2")) {
                peopleName = UserCenterDialog.Instance().geteditText4().getText().toString().trim();
                UserData.getPostData().setBindPhone(peopleName);
                msg_type = "3";
                this.mHttpThread(5, this.context);
            } else if(v.getId() == GetResId.getId(this.context, "id", "forgot2_button_1")) {
                msg_type = "3";
                this.mHttpThread(5, this.context);
                GAGameTool.buttonTime(this.context, this.context.getText(GetResId.getId(this.context, "string", "text_forgot_8")).toString().trim(), 115, UserCenterDialog.Instance().getbutton1());
            } else {
                String peopleId;
                String msg;
                if(v.getId() == GetResId.getId(this.context, "id", "forgot2_button_2")) {
                    peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                    peopleId = UserCenterDialog.Instance().geteditText2().getText().toString().trim();
                    msg = UserCenterDialog.Instance().geteditText3().getText().toString().trim();
                    if(TextUtils.isEmpty(peopleName) || TextUtils.isEmpty(peopleId) || TextUtils.isEmpty(msg)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "msg_or_pws_nil"), 0).show();
                        return;
                    }

                    if(!peopleId.equals(msg)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "password_isfail"), 0).show();
                        UserCenterDialog.Instance().geteditText2().setText("");
                        UserCenterDialog.Instance().geteditText3().setText("");
                        return;
                    }

                    UserData.getPostData().setBindMsg(peopleName);
                    UserData.getPostData().setPassWord(peopleId);
                    this.mHttpThread(6, this.context);
                } else if(v.getId() == GetResId.getId(this.context, "id", "change_button_1")) {
                    peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                    peopleId = UserCenterDialog.Instance().geteditText2().getText().toString().trim();
                    msg = UserCenterDialog.Instance().geteditText3().getText().toString().trim();
                    if(TextUtils.isEmpty(peopleName) || TextUtils.isEmpty(peopleId) || TextUtils.isEmpty(msg)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "pws_or_newpws_nil"), 0).show();
                        return;
                    }

                    if(!peopleId.equals(msg)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "password_isfail"), 0).show();
                        UserCenterDialog.Instance().geteditText2().setText("");
                        UserCenterDialog.Instance().geteditText3().setText("");
                        return;
                    }

                    UserData.getPostData().setUserName(peopleName);
                    UserData.getPostData().setPassWord(peopleId);
                    this.mHttpThread(7, this.context);
                } else if(v.getId() == GetResId.getId(this.context, "id", "bind_button_1")) {
                    peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                    peopleId = UserCenterDialog.Instance().getTextv1().getText().toString().trim();
                    if(peopleName != null && peopleName.isEmpty() && !GAGameTool.checkPhone(peopleName)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "phone_nil"), 0).show();
                        return;
                    }

                    if(peopleId.isEmpty()) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "text_bind_7"), 0).show();
                        return;
                    }

                    msg_type = "1";
                    UserData.getPostData().setBindPhone(peopleName);
                    UserData.getPostData().setUserName(peopleId);
                    this.mHttpThread(5, this.context);
                } else if(v.getId() == GetResId.getId(this.context, "id", "bind_button_2")) {
                    peopleName = UserCenterDialog.Instance().geteditText2().getText().toString().trim();
                    GAGameSDKLog.e("msg---->" + peopleName);
                    if(peopleName.isEmpty()) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "msg_nil"), 0).show();
                        return;
                    }

                    if(GAGameTool.checkMsg(peopleName)) {
                        UserData.getPostData().setBindMsg(peopleName);
                        this.mHttpThread(8, this.context);
                    } else {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "msg_fail"), 0).show();
                    }
                } else if(v.getId() == GetResId.getId(this.context, "id", "upgrade_tv_2")) {
                    Intent intent = new Intent();
                    intent.setClass(v.getContext(), ProtocolActivity.class);
                    intent.addFlags(268435456);
                    v.getContext().startActivity(intent);
                } else if(v.getId() == GetResId.getId(this.context, "id", "upgrade_button_1")) {
                    peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                    peopleId = UserCenterDialog.Instance().geteditText2().getText().toString().trim();
                    msg = UserCenterDialog.Instance().geteditText3().getText().toString().trim();
                    if(peopleName.isEmpty() || peopleId.isEmpty() || msg.isEmpty()) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "username_or_pws_nil"), 0).show();
                        return;
                    }

                    if(!peopleId.equals(msg)) {
                        Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "password_isfail"), 0).show();
                        UserCenterDialog.Instance().geteditText2().setText("");
                        UserCenterDialog.Instance().geteditText3().setText("");
                        return;
                    }

                    UserData.getPostData().setUserName(peopleName);
                    UserData.getPostData().setPassWord(peopleId);
                    this.mHttpThread(9, this.context);
                } else if(v.getId() == GetResId.getId(this.context, "id", "forgot_imagebutton_1")) {
                    if(this.bl) {
                        UserCenterDialog.Instance().dismiss();
                    } else {
                        this.bl = true;
                        UserCenterDialog.Instance().init(v.getContext(), 5);
                        UserCenterDialog.Instance().show();
                    }
                } else if(v.getId() == GetResId.getId(this.context, "id", "forgot2_imagebutton_1")) {
                    UserCenterDialog.Instance().init(v.getContext(), 6);
                    UserCenterDialog.Instance().show();
                } else if(v.getId() != GetResId.getId(this.context, "id", "change_imagebutton_1") && v.getId() != GetResId.getId(this.context, "id", "paydata_imagebutton_1") && v.getId() != GetResId.getId(this.context, "id", "bind_imagebutton_1") && v.getId() != GetResId.getId(this.context, "id", "upgrade_imagebutton_1") && v.getId() != GetResId.getId(this.context, "id", "real_imagebutton_1")) {
                    if(v.getId() == GetResId.getId(this.context, "id", "center_imagebutton_1")) {
                        UserCenterDialog.Instance().dismiss();
                    } else if(v.getId() == GetResId.getId(this.context, "id", "real_button_1")) {
                        peopleName = UserCenterDialog.Instance().geteditText1().getText().toString().trim();
                        peopleId = UserCenterDialog.Instance().geteditText2().getText().toString().trim();
                        peopleId = peopleId.toUpperCase();
                        if(!GAGameTool.checkPeopleName(peopleName)) {
                            Toast.makeText(v.getContext(), GetResId.getId(this.context, "string", "peoplename_nil"), 0).show();
                            return;
                        }

                        try {
                            msg = IDCardValidate.IDCardValidate(peopleId);
                            if(!msg.equals("")) {
                                Toast.makeText(v.getContext(), msg, 0).show();
                                return;
                            }
                        } catch (NotFoundException var5) {
                            var5.printStackTrace();
                        } catch (ParseException var6) {
                            var6.printStackTrace();
                        }

                        UserData.getPostData().setPeopleName(peopleName);
                        UserData.getPostData().setPeopleId(peopleId);
                        this.mHttpThread(17, this.context);
                    }
                } else {
                    UserCenterDialog.Instance().init(v.getContext(), 5);
                    UserCenterDialog.Instance().show();
                }
            }
        }

    }

    protected void mHttpThread(int code, final Context context) {
        Runnable mRunnable = null;
        if(code == 4) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startAuthMethod(context);
                }
            };
        } else if(code == 5) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startSendMessage(context);
                }
            };
        } else if(code == 6) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startResetPassword(context);
                }
            };
        } else if(code == 7) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startchangePassword(context);
                }
            };
        } else if(code == 8) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startbindPhone(context);
                }
            };
        } else if(code == 9) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startvisitorUpdate(context);
                }
            };
        } else if(code == 17) {
            mRunnable = new Runnable() {
                public void run() {
                    UserCenterButtonOnClickListener.this.startReal(context);
                }
            };
        }

        Builder ingDialog = new Builder(context);
        dialog = ingDialog.create();
        dialog.show();
        if(mRunnable != null) {
            (new Thread(mRunnable)).start();
        }

    }

    protected void startReal(Context context) {
        GAGameSDKLog.d("startReal: start");
        int i = 0;

        for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
            GetMessage.real("update_identify", i);
        }

        this.clickGet(17);
    }

    protected void startAuthMethod(Context context) {
        GAGameSDKLog.d("startAuthMethod: start");
        String username = UserData.getPostData().getUserName();
        Message msg;
        if(username.isEmpty()) {
            msg = this.mHandler.obtainMessage(0, context.getText(GetResId.getId(context, "string", "username_nil")));
            this.mHandler.sendMessage(msg);
        } else if(GAGameTool.checkUsernameorPassword(username, "") == 2) {
            msg = this.mHandler.obtainMessage(0, context.getText(GetResId.getId(context, "string", "login_fail_username")));
            this.mHandler.sendMessage(msg);
        } else {
            GAGameSDKLog.d("startAuthMethod: http get start");
            int i = 0;

            for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                GAGameSDKLog.d("startAuthMethod: i=" + i);
                GetMessage.userCenterHttp(username, "", 4, "getAuthMethod", i);
            }

            GAGameSDKLog.d("startAuthMethod, clickGet");
            this.clickGet(4);
        }

    }

    protected void startSendMessage(Context context) {
        int i = 0;

        for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
            GetMessage.userCenterHttp(UserData.getPostData().getUserName(), "", 5, "sendMsg", i);
        }

        this.clickGet(5);
    }

    protected void startResetPassword(Context context) {
        String msg = UserData.getPostData().getBindMsg();
        String username = UserData.getPostData().getUserName();
        String password = UserData.getPostData().getPassWord();
        Message msg1;
        if(GAGameTool.checkMsg(msg)) {
            if(GAGameTool.checkUsernameorPassword(username, password) != 3) {
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    GetMessage.userCenterHttp(username, password, 6, "resetPassword", i);
                }

                this.clickGet(6);
            } else {
                msg1 = this.mHandler.obtainMessage(211, context.getText(GetResId.getId(context, "string", "login_fail_password")));
                this.mHandler.sendMessage(msg1);
            }
        } else {
            msg1 = this.mHandler.obtainMessage(211, context.getText(GetResId.getId(context, "string", "msg_fail")));
            this.mHandler.sendMessage(msg1);
        }

    }

    protected void startchangePassword(Context context) {
        Message msg;
        if(GAGameTool.checkUsernameorPassword(UserData.getPostData().getUserName(), UserData.getPostData().getUserName()) != 3 && GAGameTool.checkUsernameorPassword(UserData.getPostData().getPassWord(), UserData.getPostData().getPassWord()) != 3) {
            if(UserData.getPostData().getUserName().equals(UserData.getPostData().getPassWord())) {
                msg = this.mHandler.obtainMessage(213, context.getText(GetResId.getId(context, "string", "text_change_8")));
                this.mHandler.sendMessage(msg);
            } else {
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    GetMessage.userCenterHttp(UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), 7, "changePassword", i);
                }

                this.clickGet(7);
            }
        } else {
            msg = this.mHandler.obtainMessage(213, context.getText(GetResId.getId(context, "string", "login_fail_password")));
            this.mHandler.sendMessage(msg);
        }

    }

    protected void startbindPhone(Context context) {
        int i = 0;

        for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
            GetMessage.userCenterHttp(UserData.getShowData().getUserName(), "", 8, "bindPhone", i);
        }

        this.clickGet(8);
    }

    protected void startvisitorUpdate(Context context) {
        Message msg;
        switch(GAGameTool.checkUsernameorPassword(UserData.getPostData().getUserName(), UserData.getPostData().getPassWord())) {
            case 1:
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    GetMessage.userCenterHttp(UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), 9, "visitorUpdate", i);
                }

                this.clickGet(9);
                break;
            case 2:
                msg = this.mHandler.obtainMessage(217, context.getText(GetResId.getId(context, "string", "login_fail_username")));
                this.mHandler.sendMessage(msg);
                break;
            case 3:
                msg = this.mHandler.obtainMessage(217, context.getText(GetResId.getId(context, "string", "login_fail_password")));
                this.mHandler.sendMessage(msg);
        }

    }

    protected void clickGet(int code) {
        int a = 0;
        int b = 0;
        if(code == 4) {
            a = 206;
            b = 207;
        } else if(code == 5) {
            a = 208;
            b = 209;
        } else if(code == 6) {
            a = 210;
            b = 211;
        } else if(code == 7) {
            a = 212;
            b = 213;
        } else if(code == 8) {
            a = 214;
            b = 215;
        } else if(code == 9) {
            a = 216;
            b = 217;
        } else if(code == 17) {
            a = 226;
            b = 227;
        }

        Message msg;
        if(GetMessage.getStatus() == 0) {
            GAGameSDKLog.d("clickGet: GetMessage.getStatus() = SUCCESS");
            msg = this.mHandler.obtainMessage(a, GetMessage.getMessage());
            this.mHandler.sendMessage(msg);
        } else if(GetMessage.getStatus() == 404) {
            GAGameSDKLog.d("clickGet: GetMessage.getStatus() = NETWORK_ERROR");
            msg = this.mHandler.obtainMessage(GetMessage.getStatus(), this.context.getText(GetResId.getId(this.context, "string", "network_error")));
            this.mHandler.sendMessage(msg);
        } else {
            GAGameSDKLog.d("clickGet: GetMessage.getStatus()=" + GetMessage.getStatus());
            msg = this.mHandler.obtainMessage(b, GetMessage.getMessage());
            this.mHandler.sendMessage(msg);
        }

    }
}
