//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import com.goldautumn.sdk.dialog.UserCenterButtonOnClickListener;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.Data.LoginOrRegisterData;
import com.goldautumn.sdk.minterface.Data.UserCenterData;
import org.json.JSONException;
import org.json.JSONObject;

public class GetMessage {
    private static String status;
    private static String message;
    private static String mData;
    private static JSONObject json = null;

    public GetMessage() {
    }

    public static void LoginOrRegister(String username, String password, int code, String userType, int i) {
        LoginOrRegisterData data = new LoginOrRegisterData();
        data.SetData("appid", Finaldata.getAppid());
        AES aes;
        String passWord;
        String account_id;
        String tokenID;
        if(code == 3) {
            json = new JSONObject();

            try {
                json.put("machine_id", Finaldata.getmIMEI());
                json.put("timestamp", GAGameTool.getCurTime());
                json.put("ad_id", UserData.getShowData().getAd_id());
                aes = new AES();
                passWord = aes.encrypt(json.toString(), Finaldata.getAppkey());
                data.SetData("data", passWord);
                json = null;
            } catch (JSONException var12) {
                var12.printStackTrace();
            }

            data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, "createVisitorID", i)));
            status = data.GetData("status");
            message = data.GetData("message");
            data.StringToData(data.GetData("data").toString());
            String visitorID = data.GetData("visitorID");
            passWord = data.GetData("password");
            account_id = data.GetData("account_id");
            UserData.getPostData().setVisitorID(visitorID);
            UserData.getPostData().setPassWord(passWord);
            UserData.getPostData().setUserId(account_id);
        } else {
            try {
                if(json == null) {
                    json = new JSONObject();
                    if(code != 2 && userType != null) {
                        json.put("usertype", userType);
                        json.put("ad_id", UserData.getShowData().getAd_id());
                        UserData.getPostData().setUserType(userType);
                    }
                }

                json.put("username", username);
                json.put("password", password);
            } catch (JSONException var11) {
                var11.printStackTrace();
            }

            aes = new AES();
            passWord = aes.encrypt(json.toString(), Finaldata.getAppkey());
            data.SetData("data", passWord);
            json = null;
            if(code == 1) {
                data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, "login", i)));
                status = data.GetData("status");
                message = data.GetData("message");
                data.StringToData(data.GetData("data").toString());
                tokenID = data.GetData("tokenID");
                account_id = data.GetData("account_id");
                data.StringToData(data.GetData("identify"));
                String showReal = data.GetData("isValid");
                UserData.getShowData().setToken(account_id);
                UserData.getShowData().setUserId(account_id);
                UserData.getShowData().setShowReal(showReal);
            } else if(code == 2) {
                data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, "register", i)));
                status = data.GetData("status");
                message = data.GetData("message");
                data.StringToData(data.GetData("data").toString());
                account_id = data.GetData("account_id");
                UserData.getShowData().setUserId(account_id);
            }
        }

    }

    public static void userCenterHttp(String username, String password, int code, String values, int i) {
        UserCenterData data = new UserCenterData();
        data.SetData("appid", Finaldata.getAppid());
        json = new JSONObject();
        if(code == 4) {
            try {
                json.put("username", username);
            } catch (JSONException var13) {
                var13.printStackTrace();
            }
        } else if(code == 5) {
            try {
                json.put("token", UserData.getShowData().getToken());
                json.put("username", username);
                json.put("phone", UserData.getPostData().getBindPhone());
                json.put("msg_type", UserCenterButtonOnClickListener.getMsg_type());
            } catch (JSONException var12) {
                var12.printStackTrace();
            }
        } else if(code == 6) {
            try {
                json.put("username", username);
                json.put("password", password);
                json.put("phone", UserData.getPostData().getBindPhone());
                json.put("msg", UserData.getPostData().getBindMsg());
            } catch (JSONException var11) {
                var11.printStackTrace();
            }
        } else if(code == 7) {
            try {
                json.put("token", UserData.getShowData().getToken());
                json.put("username", UserData.getShowData().getUserName());
                json.put("old_password", username);
                json.put("new_password", password);
            } catch (JSONException var10) {
                var10.printStackTrace();
            }
        } else if(code == 8) {
            try {
                json.put("token", UserData.getShowData().getToken());
                json.put("username", username);
                json.put("phone", UserData.getPostData().getBindPhone());
                json.put("msg", UserData.getPostData().getBindMsg());
                GAGameSDKLog.e("phone---------->" + UserData.getPostData().getBindPhone());
            } catch (JSONException var9) {
                var9.printStackTrace();
            }
        } else if(code == 9) {
            try {
                json.put("token", UserData.getShowData().getToken());
                json.put("username", username);
                json.put("password", password);
                json.put("visitorID", UserData.getShowData().getUserName());
            } catch (JSONException var8) {
                var8.printStackTrace();
            }
        }

        AES aes = new AES();
        String jsonStr = aes.encrypt(json.toString(), Finaldata.getAppkey());
        data.SetData("data", jsonStr);
        json = null;
        data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, values, i)));
        status = data.GetData("status");
        message = data.GetData("message");
        if(code == 4) {
            data.StringToData(data.GetData("data").toString());
            if(UserData.getShowData().getPhoneType().equals("1")) {
                UserData.getShowData().setPhone(data.GetData("phone"));
            } else {
                UserData.getPostData().setBindPhone(data.GetData("phone"));
                UserData.getShowData().setAuthmethodPhone(data.GetData("phone"));
            }
        } else {
            mData = data.GetData("data");
        }

    }

    public static void real(String values, int i) {
        UserCenterData data = new UserCenterData();
        data.SetData("appid", Finaldata.getAppid());
        json = new JSONObject();

        try {
            json.put("token", UserData.getShowData().getToken());
            json.put("username", UserData.getShowData().getUserName());
            json.put("identify", UserData.getPostData().getPeopleId());
            json.put("peoplename", UserData.getPostData().getPeopleName());
        } catch (JSONException var7) {
            var7.printStackTrace();
        }

        AES aes = new AES();
        String jsonStr = aes.encrypt(json.toString(), Finaldata.getAppkey());
        data.SetData("data", jsonStr);
        json = null;
        data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, values, i)));
        status = data.GetData("status");
        message = data.GetData("message");
        if(getStatus() == 0) {
            data.StringToData(data.GetData("data"));
            String showReal = data.GetData("isValid");
            String isAdult = data.GetData("isAdult");
            UserData.getShowData().setShowReal(showReal);
        }

    }

    public static int getStatus() {
        return !status.isEmpty() && status != null?Integer.parseInt(status):404;
    }

    public static String getMessage() {
        return message;
    }

    public static String getmData() {
        return mData;
    }
}
