//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Finaldata {
    public static final String SHARE_URL = "share_url";
    public static final String SHARE_TITLE = "share_title";
    public static final String SHARE_MSG = "share_msg";
    public static final String SHARE_IMG_URL = "share_img_url";
    public static final String SHARE_TYPE = "share_type";
    public static final String APP_NAME = "app_name";
    public static final String FIELD = "User-Agent";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0";
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String CREATEVISITORID = "createVisitorID";
    public static final String CREATE_PASSWORD = "createPassword";
    public static final String SENDMSG = "sendMsg";
    public static final String RESTPASSWORD = "resetPassword";
    public static final String AUTHMETHOD = "getAuthMethod";
    public static final String CHANGEPASSWORD = "changePassword";
    public static final String BINDPHONE = "bindPhone";
    public static final String VISITORUPDATA = "visitorUpdate";
    public static final String PAYURL = "create_order";
    public static final String REAL = "update_identify";
    public static final String TPLOGIN = "tplogin";
    public static final String PAYRUSLT = "update_order_status_client";
    public static final String PAYJL = "query_order_by_accountid";
    public static final String PAYTYPE = "get_pay_channel";
    public static final String EVENT = "send_event_count";
    public static final String PACKNAME_KEY = "packname";
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String USERTYPE_KEY = "usertype";
    public static final String PEOPLENAME_KEY = "peoplename";
    public static final String PEOPLEID_KEY = "identify";
    public static final String MACHINE_ID_KEY = "machine_id";
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String APPID_KEY = "appid";
    public static final String APPID_KEY_1 = "appId";
    public static final String PHONE_KEY = "phone";
    public static final String MSG_TYPE_KEY = "msg_type";
    public static final String MSG_KEY = "msg";
    public static final String OLD_PASSWORD_KEY = "old_password";
    public static final String NEW_PASSWORD_KEY = "new_password";
    public static final String GAMEORDERID_KEY = "gameOrderId";
    public static final String ORDERSTATUS_KEY = "orderStatus";
    public static final String DATA = "data";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String VISITORID = "visitorID";
    public static final String SIGN = "sign";
    public static final String SP_FILE_NAME = "sp_file_name";
    public static final String CREATEVISITOR_DATA = "CreateVisitor_data";
    public static final String AES = "AES";
    public static final String UTF8 = "utf-8";
    public static final String USERTYPE_TOURISTS = "0";
    public static final String USERTYPE_FORMAL = "1";
    public static final String RMB = "￥";
    public static final String CNT_1 = "cnt_1";
    public static final String CNT_2 = "cnt_2";
    public static final String CNT_3 = "cnt_3";
    public static final String CNT_4 = "cnt_4";
    public static final int LOGIN_CODE = 1;
    public static final int REGISTER_CODE = 2;
    public static final int CREATEVISITORID_CODE = 3;
    public static final int AUTHMETHOD_CODE = 4;
    public static final int SENDMSG_CODE = 5;
    public static final int RESETPASSWORD_CODE = 6;
    public static final int CHANGEPASSWORD_CODE = 7;
    public static final int BINDPHONE_CODE = 8;
    public static final int VISITORUPDATA_CODE = 9;
    public static final int TB_PAY_CODE = 10;
    public static final int WX_PAY_CODE = 11;
    public static final int TB_PAY_RUSLT = 11;
    public static final int TB_PAY_JL = 12;
    public static final int UI_PAY_TYPE = 13;
    public static final int EVENT_CODE = 14;
    public static final int REAL_CODE = 17;
    public static final int SUCCESS = 0;
    public static final int SUCCESS1 = 1000;
    public static final int NETWORK_ERROR = 404;
    public static final int LOGIN_FAIL = 101;
    public static final int LOGIN_SUCCESS = 100;
    public static final int REGISTERED_FAIL = 202;
    public static final int REGISTERED_SUCCESS = 203;
    public static final int CREATEVISITORID_SUCCESS = 204;
    public static final int CREATEVISITORID_FAIL = 205;
    public static final int AUTHMETHOD_SUCCESS = 206;
    public static final int AUTHMETHOD_FAIL = 207;
    public static final int SENDMSG_SUCCESS = 208;
    public static final int SENDMSG_FAIL = 209;
    public static final int RESETPASSWORD_SUCCESS = 210;
    public static final int RESETPASSWORD_FAIL = 211;
    public static final int CHANGEPASSWORD_SUCCESS = 212;
    public static final int CHANGEPASSWORD_FAIL = 213;
    public static final int BINDPHONE_SUCCESS = 214;
    public static final int BINDPHONE_FAIL = 215;
    public static final int VISITORUPDATA_SUCCESS = 216;
    public static final int VISITORUPDATA_FAIL = 217;
    public static final int TB_PAY_SUCCESS = 218;
    public static final int TB_PAY_FAIL = 219;
    public static final int WX_PAY_SUCCESS = 220;
    public static final int WX_PAY_FAIL = 221;
    public static final int REAL_SUCCESS = 226;
    public static final int REAL_FAIL = 227;
    public static final int TB_PAY_TYPE = 1;
    public static final int WX_PAY_TYPE = 2;
    public static final int YL_PAY_TYPE = 3;
    public static final String TB_CANCEL_CODE = "6001";
    public static final String TB_SUCCESS_CODE = "9000";
    public static final String TB_ING_CODE = "8000";
    private static String packageName;
    private static String appid;
    private static String appkey;
    private static String mIMEI;
    private static String[] channels;
    private static String[] updateURL;
    private static String[] payURL;
    private static String[] userURL;
    private static String[] eventURL;
    private static String[] bbsURL;
    private static String[] notificationURL;
    private static String updateURLstr;
    private static String payURLstr;
    private static String userURLstr;
    private static String eventURLstr;
    private static String bbsURLstr;
    private static String notificationURLstr;
    private static boolean showReal;
    public static final int URL_USER_CODE = 1;
    public static final int URL_PAY_CODE = 2;
    public static final int URL_EVENT_CODE = 3;

    public Finaldata() {
    }

    public static boolean getShowReal() {
        return showReal;
    }

    public static String getUpdateURLstr() {
        return updateURLstr;
    }

    public static String getPayURLstr() {
        return payURLstr;
    }

    public static String getUserURLstr() {
        return userURLstr;
    }

    public static String getEventURLstr() {
        return eventURLstr;
    }

    public static String getBBSURLstr() {
        return bbsURLstr;
    }

    public static String getNotificationURLstr() {
        return notificationURLstr;
    }

    public static String[] getPayURL() {
        return payURL;
    }

    public static String[] getUserURL() {
        return userURL;
    }

    public static String[] getEventURL() {
        return eventURL;
    }

    public static String[] bbsEventURL() {
        return bbsURL;
    }

    public static String[] notificationEventURL() {
        return notificationURL;
    }

    public static void setPayUserURL(String buffStr) {
        try {
            if(buffStr.length() > 0) {
                GAGameSDKLog.i("setPayUserURL start");
                JSONObject mJson = new JSONObject(buffStr);
                String updateUrl = mJson.getString("update");
                String payUrl = mJson.getString("billing");
                String userUrl = mJson.getString("user");
                String event = mJson.getString("event");
                String bbs = mJson.getString("bbs");
                String notification = mJson.getString("notification");
                String real = mJson.getString("showReal");
                JSONArray updateJsonarr = new JSONArray(updateUrl);
                JSONArray payJsonarr = new JSONArray(payUrl);
                JSONArray userJsonarr = new JSONArray(userUrl);
                JSONArray eventJsonarr = new JSONArray(event);
                JSONArray bbsJsonarr = new JSONArray(bbs);
                JSONArray notificationJsonarr = new JSONArray(notification);
                updateURL = new String[updateJsonarr.length()];
                payURL = new String[payJsonarr.length()];
                userURL = new String[userJsonarr.length()];
                eventURL = new String[eventJsonarr.length()];
                bbsURL = new String[bbsJsonarr.length()];
                notificationURL = new String[notificationJsonarr.length()];
                if(real.equals("1")) {
                    showReal = true;
                } else {
                    showReal = false;
                }

                int i;
                JSONObject temp;
                for(i = 0; i < updateJsonarr.length(); ++i) {
                    temp = (JSONObject)updateJsonarr.get(i);
                    updateURL[i] = temp.getString("url");
                    GAGameSDKLog.i("updateURL[" + i + "]:" + updateURL[i]);
                }

                for(i = 0; i < payJsonarr.length(); ++i) {
                    temp = (JSONObject)payJsonarr.get(i);
                    payURL[i] = temp.getString("url");
                    GAGameSDKLog.i("payURL[" + i + "]:" + payURL[i]);
                }

                for(i = 0; i < userJsonarr.length(); ++i) {
                    temp = (JSONObject)userJsonarr.get(i);
                    userURL[i] = temp.getString("url");
                    GAGameSDKLog.i("userURL[" + i + "]:" + userURL[i]);
                }

                for(i = 0; i < eventJsonarr.length(); ++i) {
                    temp = (JSONObject)eventJsonarr.get(i);
                    eventURL[i] = temp.getString("url");
                    GAGameSDKLog.i("eventURL[" + i + "]:" + eventURL[i]);
                }

                for(i = 0; i < bbsJsonarr.length(); ++i) {
                    temp = (JSONObject)bbsJsonarr.get(i);
                    bbsURL[i] = temp.getString("url");
                    GAGameSDKLog.i("bbsURL[" + i + "]:" + bbsURL[i]);
                }

                for(i = 0; i < notificationJsonarr.length(); ++i) {
                    temp = (JSONObject)notificationJsonarr.get(i);
                    notificationURL[i] = temp.getString("url");
                    GAGameSDKLog.i("notificationURL[" + i + "]:" + notificationURL[i]);
                }

                updateURLstr = updateURL[0] + "/update/";
                payURLstr = payURL[0] + "/pay/";
                userURLstr = userURL[0] + "/users/";
                eventURLstr = eventURL[0] + "/event/";
                bbsURLstr = bbsURL[0];
                notificationURLstr = notificationURL[0];
                GAGameSDKLog.i("setUrlSuccess");
            }
        } catch (JSONException var17) {
            var17.printStackTrace();
        }

    }

    public static String[] getChannels() {
        return channels;
    }

    public static void setChannels(String[] channels) {
        channels = channels;
    }

    public static String getmIMEI() {
        return mIMEI;
    }

    public static void setmIMEI(String mIMEI) {
        mIMEI = mIMEI;
    }

    public static String getAppkey() {
        return appkey;
    }

    public static void setAppkey(String appkey) {
        appkey = appkey;
    }

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        appid = appid;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        packageName = packageName;
    }
}
