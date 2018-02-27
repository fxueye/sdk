//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.os.Message;
import android.os.Handler.Callback;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;

public class UserCenterHandlerMessage implements Callback {
    public UserCenterHandlerMessage() {
    }

    public boolean handleMessage(Message msg) {
        if(UserCenterButtonOnClickListener.getdialog() != null) {
            UserCenterButtonOnClickListener.getdialog().dismiss();
        }

        String message = (String)msg.obj;
        Toast.makeText(LoginDialog.context, message, 0).show();
        switch(msg.what) {
            case 206:
                GAGameSDKLog.d("UserCenterHandlerMessage: AUTHMETHOD_SUCCESS");
                if(!UserData.getShowData().getPhoneType().equals("1")) {
                    UserCenterDialog.Instance().getbutton1().setClickable(false);
                    UserCenterDialog.Instance().getbutton1().setBackgroundResource(GetResId.getId(LoginDialog.context, "drawable", "gasdk_button_false"));
                    UserCenterDialog.Instance().getforgot_re().setVisibility(0);
                    UserCenterDialog.Instance().getTextv1().setText(UserData.getShowData().getAuthmethodPhone());
                }
                break;
            case 207:
                GAGameSDKLog.d("UserCenterHandlerMessage: AUTHMETHOD_FAIL");
                break;
            case 208:
                GAGameSDKLog.d("UserCenterHandlerMessage: SENDMSG_SUCCESS");
                GAGameTool.buttonTime(UserCenterDialog.Instance().getContext(), UserCenterDialog.Instance().getContext().getText(GetResId.getId(LoginDialog.context, "string", "text_forgot_8")).toString().trim(), 115, UserCenterDialog.Instance().getbutton1());
                if(UserCenterButtonOnClickListener.getMsg_type() == "3") {
                    UserCenterDialog.Instance().init(UserCenterDialog.Instance().getContext(), 7);
                    UserCenterDialog.Instance().show();
                } else if(UserCenterButtonOnClickListener.getMsg_type() == "1") {
                    UserCenterDialog.Instance().getbutton2().setBackgroundResource(GetResId.getId(LoginDialog.context, "drawable", "gasdk_button_rel"));
                    UserCenterDialog.Instance().getbutton2().setClickable(true);
                }
                break;
            case 209:
                GAGameSDKLog.d("UserCenterHandlerMessage: SENDMSG_FAIL");
                break;
            case 210:
                GAGameSDKLog.d("UserCenterHandlerMessage: RESETPASSWORD_SUCCESS");
                UserCenterDialog.Instance().dismiss();
                break;
            case 211:
                GAGameSDKLog.d("UserCenterHandlerMessage: RESETPASSWORD_FAIL");
                break;
            case 212:
                GAGameSDKLog.d("UserCenterHandlerMessage: CHANGEPASSWORD_SUCCESS");
                UserCenterDialog.Instance().init(UserCenterDialog.Instance().getContext(), 5);
                GAGameTool.setUsernameLogin(LoginDialog.context, UserData.getShowData().getUserName(), UserData.getPostData().getPassWord(), "1", "1", "sp_file_name");
                UserData.getPostData().init();
                UserCenterDialog.Instance().dismiss();
                break;
            case 213:
                UserCenterDialog.Instance().geteditText1().setText("");
                UserCenterDialog.Instance().geteditText2().setText("");
                UserCenterDialog.Instance().geteditText3().setText("");
                break;
            case 214:
                UserCenterDialog.Instance().init(UserCenterDialog.Instance().getContext(), 5);
                UserData.getShowData().setPhone(UserData.getPostData().getBindPhone());
                UserData.getPostData().init();
                UserCenterDialog.Instance().dismiss();
            case 215:
            case 217:
            default:
                break;
            case 216:
                UserData.getShowData().setUserName(UserData.getPostData().getUserName());
                UserData.getShowData().setPassword(UserData.getPostData().getPassWord());
                GAGameTool.setUsernamesorPassword(UserData.getPostData().getUserName(), LoginDialog.context);
                GAGameTool.setUsernameLogin(LoginDialog.context, UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), "1", "1", "sp_file_name");
                GAGameTool.setUsernameLogin(LoginDialog.context, "", "", "", "", "CreateVisitor_data");
                UserData.getShowData().setUserType("1");
                UserCenterDialog.Instance().init(UserCenterDialog.Instance().getContext(), 5);
                UserData.getPostData().init();
                UserCenterDialog.Instance().dismiss();
                break;
            case 226:
                UserCenterDialog.Instance().dismiss();
                break;
            case 227:
                UserCenterDialog.Instance().dismiss();
                break;
            case 404:
                GAGameSDKLog.d("UserCenterHandlerMessage: NETWORK ERROR");
        }

        return false;
    }
}
