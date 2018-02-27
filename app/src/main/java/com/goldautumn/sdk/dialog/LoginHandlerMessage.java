//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.os.Message;
import android.os.Handler.Callback;
import android.widget.Toast;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameResult;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.minterface.GetMessage;
import com.goldautumn.sdk.utils.GetResId;

public class LoginHandlerMessage implements Callback {
    public LoginHandlerMessage() {
    }

    public boolean handleMessage(Message msg) {
        GAGameResult result = new GAGameResult();
        if(LoginDialog.getdialog() != null) {
            LoginDialog.getdialog().dismiss();
        }

        String message = (String)msg.obj;
        Toast.makeText(LoginDialog.context, message, 0).show();
        switch(msg.what) {
            case 100:
                GAGameSDKLog.e("LoginhandlerMessage: LOGIN_SUCCESS");
                (new Thread(new Runnable() {
                    public void run() {
                        GAGameSDKLog.e("LoginhandlerMessage: Thread AUTHMETHOD Start");
                        int i = 0;

                        for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                            UserData.getShowData().setPhoneType("1");
                            GetMessage.userCenterHttp(UserData.getPostData().getUserName(), "", 4, "getAuthMethod", i);
                        }

                        GAGameSDKLog.e("LoginhandlerMessage: Thread AUTHMETHOD End");
                    }
                })).start();
                if(UserData.getPostData().getUserType().equals("1")) {
                    GAGameSDKLog.e("LoginhandlerMessage: setUsernamesorPassword");
                    GAGameTool.setUsernamesorPassword(UserData.getPostData().getUserName(), LoginDialog.context);
                    GAGameSDKLog.e("LoginhandlerMessage: setUsernameLogin");
                    GAGameTool.setUsernameLogin(LoginDialog.context, UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), "1", "1", "sp_file_name");
                } else {
                    GAGameSDKLog.e("LoginhandlerMessage: setUsernameLogin");
                    GAGameTool.setUsernameLogin(LoginDialog.context, UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), "0", "1", "sp_file_name");
                }

                GAGameSDKLog.e("LoginhandlerMessage: result.setLoginResult");
                result.setLoginResult(true, UserData.getShowData().getToken(), UserData.getShowData().getUserId(), message);
                GAGameSDKLog.e("LoginhandlerMessage: setUserId");
                GAGameResult.Instance().setUserId(result.getUserId());
                if(LoginDialog.isShowFloatViewButton) {
                    GAGameSDKLog.e("LoginhandlerMessage: showFloatViewButton");
                    LoginDialog.showFloatViewButton();
                }

                GAGameSDKLog.e("LoginhandlerMessage: init");
                GAGameSDKLog.e("LoginhandlerMessage: dismiss");
                UserData.getShowData().setUserName(UserData.getPostData().getUserName());
                UserData.getShowData().setUserType(UserData.getPostData().getUserType());
                UserData.getPostData().init();
                GAGameSDKLog.e("LoginhandlerMessage: GAGameSDK.setUserId");
                GAGameSDK.setLoginResult(result);
                LoginDialog.Instance().init(LoginDialog.context, 1);
                LoginDialog.Instance().dismiss();
                if(UserData.getShowData().getUserType().equals("1") && Finaldata.getShowReal() && UserData.getShowData().getShowReal().equals("false")) {
                    UserData.getShowData().setResult(result);
                    UserCenterDialog.Instance().init(LoginDialog.context, 13);
                    UserCenterDialog.Instance().show();
                    return false;
                }
                break;
            case 101:
                if(LoginDialog.geteditPassword() != null) {
                    LoginDialog.geteditPassword().setText("");
                }

                if(GAGameSDK.getQQ() != null) {
                    GAGameSDK.getQQ().logout(LoginDialog.context);
                }

                LoginDialog.Instance().show();
                result.setLoginResult(false, "", "", message);
                GAGameSDK.setLoginResult(result);
                break;
            case 202:
                LoginDialog.geteditPassword().setText("");
                LoginDialog.geteditPassword2().setText("");
                break;
            case 203:
                LoginDialog.Instance().mHttpThread(LoginDialog.context, 1, "1");
                break;
            case 204:
                GAGameTool.setUsernameLogin(LoginDialog.context, UserData.getPostData().getVisitorID(), UserData.getPostData().getPassWord(), "", "", "CreateVisitor_data");
                UserData.getPostData().setUserName(UserData.getPostData().getVisitorID());
                UserData.getPostData().setPassWord(UserData.getPostData().getPassWord());
                LoginDialog.Instance().mHttpThread(LoginDialog.context, 1, "0");
            case 205:
            default:
                break;
            case 404:
                result.setLoginResult(false, "", "", LoginDialog.context.getText(GetResId.getId(LoginDialog.context, "string", "network_error")).toString());
                GAGameSDK.setLoginResult(result);
        }

        return false;
    }
}
