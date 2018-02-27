//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.goldautumn.sdk.activity.SplashActivity;
import com.goldautumn.sdk.dialog.ExitGameDialog;
import com.goldautumn.sdk.dialog.LoginDialog;
import com.goldautumn.sdk.dialog.PayDialog;
import com.goldautumn.sdk.dialog.ShareDialog;
import com.goldautumn.sdk.dialog.UserCenterDialog;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.floatview.GAGameFloat;
import com.goldautumn.sdk.minterface.Data.ShareData;
import com.goldautumn.sdk.minterface.GAGameTool.ConfigLoaderCallBack;
import com.goldautumn.sdk.pay.PayData;
import com.goldautumn.sdk.pay.WXpay;
import com.goldautumn.sdk.qqlogin.BaseUIListener;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GAGameSDK {
    private static Intent service;
    private static GAGameSDK.loginCallback mLoginCallback;
    private static GAGameSDK.initCallback mInitCallback;
    private static LoginDialog loginDialog;
    private static UserCenterDialog userDialog;
    private static ExitGameDialog exitDialog;
    private static GAGameSDK.logoutCallback mLogoutCallback;
    private static GAGameSDK.payCallback mPayCallback;
    private static GAGameSDK.ShareCallback shareCallback;
    private static ShareDialog shareDialog;
    private static boolean isInit = false;
    private static boolean isLogin = false;
    private static PayDialog mPayDialog;
    private static Context context;
    private static Activity activity;
    private static String appid;
    private static String appkey;
    private static WXpay wx;
    private static Tencent mTencent;
    public static IUiListener loginListener;
    static String[] ftpUrl = null;
    private static PayData mPaydata;
    public static IUiListener shareListener = new IUiListener() {
        public void onError(UiError e) {
            GAGameSDKLog.e("errorMsg:" + e.errorMessage);
            GAGameSDKLog.e("errorDetail:" + e.errorDetail);
            Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(GAGameSDK.context, "string", "text_share_error"), 0).show();
            GAGameSDK.shareDialog.dismiss();
            GAGameSDK.shareCallback.shareFail(e.errorMessage);
        }

        public void onComplete(Object json) {
            JSONObject response = (JSONObject)json;
            GAGameSDKLog.d("onComplete:" + response.toString());
            Toast.makeText(LoginDialog.Instance().getContext(), GetResId.getId(GAGameSDK.context, "string", "text_share_success"), 0).show();
            GAGameSDK.shareDialog.dismiss();
            GAGameSDK.shareCallback.shareSuccess();
        }

        public void onCancel() {
            GAGameSDKLog.e("onCancel");
            Toast.makeText(GAGameSDK.context, GetResId.getId(GAGameSDK.context, "string", "text_share_cancel"), 0).show();
            GAGameSDK.shareDialog.dismiss();
            GAGameSDK.shareCallback.shareCancel();
        }
    };

    public GAGameSDK() {
    }

    public static ShareDialog getshareDialog() {
        return shareDialog;
    }

    public static GAGameSDK.ShareCallback getShareCallback() {
        return shareCallback;
    }

    public static WXpay getWx() {
        return wx;
    }

    public static Tencent getQQ() {
        return mTencent;
    }

    public static PayDialog getmPayDialog() {
        return mPayDialog;
    }

    public static LoginDialog getloginDialog() {
        return loginDialog;
    }

    public static UserCenterDialog getuserDialog() {
        return userDialog;
    }

    public static ExitGameDialog getExitDialog() {
        return exitDialog;
    }

    public static boolean getisLogin() {
        return isLogin;
    }

    public static PayData getmPatdata() {
        return mPaydata;
    }

    public static void login(Context context, GAGameSDK.loginCallback loginCallback) {
        mLoginCallback = loginCallback;
        if(isInit) {
            if(!isLogin) {
                String isLogin = GAGameTool.getUsernameLogin(context, "isLogin", "sp_file_name");
                if(isLogin != null && isLogin.equals("1")) {
                    String userType = GAGameTool.getUsernameLogin(context, "usertype", "sp_file_name");
                    UserData.getPostData().setUserName(GAGameTool.getUsernameLogin(context, "username", "sp_file_name"));
                    UserData.getPostData().setPassWord(GAGameTool.getUsernameLogin(context, "password", "sp_file_name"));
                    LoginDialog.Instance().mHttpThread(context, 1, userType);
                } else {
                    loginDialog.show();
                }
            }
        } else {
            GAGameSDKLog.e("GAGameSDK Init is null");
        }

    }

    public static void setLoginResult(GAGameResult result) {
        if(result.isLogin()) {
            isLogin = result.isLogin();
            mLoginCallback.loginSuccess(result);
        } else {
            mLoginCallback.loginFail(result.getMessage());
        }

    }

    public void init(final int i, final Context context) {
        String buffStr = GAGameTool.getFromAssets(context, "GAGameSettings.txt");
        GAGameSDKLog.d(buffStr);
        if(buffStr.length() > 0) {
            try {
                JSONObject json = new JSONObject(buffStr);
                ftpUrl = new String[json.length()];
                Iterator<String> it = json.keys();

                for(int a = 0; it.hasNext(); ++a) {
                    ftpUrl[a] = json.getString((String)it.next());
                }
            } catch (JSONException var7) {
                var7.printStackTrace();
            }
        }

        GAGameTool.switchConfigDownloar(ftpUrl, new ConfigLoaderCallBack() {
            public void onSuesses() {
                GAGameSDKLog.i("startGetPayType");
                GAGameSDKLog.w(GAGameTool.isUIThread());
                GAGameTool.startGetPayType();
                GAGameResult result = new GAGameResult();
                result.setInit(true);
                GAGameSDK.setInitResult(result);
            }

            public void onFail() {
                GAGameSDKLog.e("switchConfigDownloar fail");
                int a = i;
                ++a;
                if(a > 5) {
                    GAGameResult result = new GAGameResult();
                    result.setInit(false);
                    GAGameSDK.setInitResult(result);
                } else if(GAGameSDK.ftpUrl != null) {
                    GAGameSDK.this.init(a, context);
                }

            }
        });
    }

    public static void init(Context _context, Activity _activity, String _appid, String _appkey, String wxId, String qqId, GAGameSDK.initCallback initCallback, GAGameSDK.logoutCallback logoutCallback) {
        GAGameSDKLog.w("init");
        GAGameSDKLog.w(GAGameTool.isUIThread());
        context = _context;
        activity = _activity;
        appid = _appid;
        appkey = _appkey;
        mLogoutCallback = logoutCallback;
        mInitCallback = initCallback;
        UserData.init();
        UserData.getPostData().init();
        UserData.getShowData().setAd_id(GAGameTool.getAdid(_context));
        if(!isInit) {
            GAGameSDKLog.i("openSplash");
            openSplash();
            Finaldata.setPackageName(GAGameTool.getPackageName(context));
            Finaldata.setAppid(appid);
            Finaldata.setAppkey(appkey);
            Finaldata.setmIMEI(GAGameTool.getIMEI(context));
            GAGameSDKLog.i("wx");
            wx = new WXpay();
            wx.setAPP_ID(wxId);
            mTencent = Tencent.createInstance(qqId, _context.getApplicationContext());
            loginListener = new BaseUIListener(_context);
            GAGameSDKLog.w("loginDialog");
            GAGameSDKLog.w(GAGameTool.isUIThread());
            if(loginDialog == null) {
                loginDialog = new LoginDialog(context, activity, 1);
            }

            GAGameSDKLog.w("userDialog");
            if(userDialog == null) {
                userDialog = new UserCenterDialog(context, activity, 5);
            }

            GAGameSDKLog.w("exitDialog");
            if(exitDialog == null) {
                exitDialog = new ExitGameDialog(context, activity);
            }
        }

    }

    public static void setInitResult(GAGameResult result) {
        if(result.isInit()) {
            if(!isInit) {
                mInitCallback.initSuccess();
            }

            isInit = result.isInit();
        } else {
            isInit = false;
            mInitCallback.initFail();
        }

    }

    public static void logout(Context context, Activity activity, GAGameSDK.logoutCallback logoutCallback) {
        mLogoutCallback = logoutCallback;
        mTencent.logout(context);
        logoutA(context);
        setlogoutResult();
    }

    public static void logoutA(Context context) {
        isLogin = false;
        GAGameFloat.Instance().setInitF(true);
        GAGameFloat.Instance().onPause();
        GAGameFloat.Instance().setLogout(false);
        LoginDialog.isShowFloatViewButton = true;
        GAGameTool.setUsernameLogin(context, "", "", "", "0", "sp_file_name");
    }

    public static void setlogoutResult() {
        mLogoutCallback.logoutSuccess();
    }

    public static void pay(Activity activity, Context context, String appid, String Item_Name, String price, String billNumber, String itemDetail, GAGameSDK.payCallback payCallback) {
        mPayCallback = payCallback;
        GAGameSDKLog.d("pay start.");
        GAGameSDKLog.d("appid=" + appid);
        GAGameSDKLog.d("item_name:" + Item_Name);
        GAGameSDKLog.d("price=" + price);
        GAGameSDKLog.d("billNumber=" + billNumber);
        GAGameSDKLog.d("itemDetail=" + itemDetail);
        if(isInit && isLogin) {
            context = context;
            String UID = UserData.getShowData().getUserId();
            mPaydata = new PayData();
            mPaydata.setPaydata(appid, Item_Name, price, billNumber, UID, itemDetail);
            mPayDialog = new PayDialog(context, activity);
            mPayDialog.show();
        }

    }

    public static void setPayResult(int result) {
        if(result == 1) {
            mPayCallback.paySuccess();
        } else if(result == 2) {
            mPayCallback.payFail();
        } else {
            mPayCallback.payCancel();
        }

    }

    private static void openWebView(String title, String url) {
    }

    public static void openBBS() {
        GAGameSDKLog.w("Open Web Activity, app_id=" + Finaldata.getAppid());
        GAGameSDKLog.w("Open Web Activity, app_key=" + Finaldata.getAppkey());
        GAGameSDKLog.w("Open Web Activity, user_id=" + UserData.getShowData().getUserId());
        GAGameSDKLog.w("Open Web Activity, user_token=" + UserData.getShowData().getToken());
        String parms = "?app_id=" + Finaldata.getAppid() + "&user_id=" + UserData.getShowData().getUserId() + "&user_token=" + UserData.getShowData().getToken();
        GAGameSDKLog.w("Open Web Activity, parms=" + parms);
        String sign = MD5Util.MD5(parms);
        GAGameSDKLog.w("Open Web Activity, sing=" + sign);
        String url = Finaldata.getBBSURLstr() + parms + "&sign=" + sign;
        GAGameSDKLog.w("Open Web Activity, url=" + url);
        openWebView("游戏论坛", url);
    }

    public static void openNotification() {
        openWebView("公告", Finaldata.getNotificationURLstr());
    }

    private static void openSplash() {
        Handler dialogHandler = new Handler(Looper.getMainLooper());
        dialogHandler.post(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setClass(GAGameSDK.context, SplashActivity.class);
                GAGameSDK.context.startActivity(intent);
            }
        });
    }

    public static void onResume() {
        if(GAGameResult.isFloatInitSuccess()) {
            GAGameSDKLog.i("GAGameSDK onResume");
            GAGameFloat.Instance().onResume();
        }

    }

    public static void onPause() {
        if(GAGameResult.isFloatInitSuccess()) {
            GAGameSDKLog.i("GAGameSDK onPause");
            GAGameFloat.Instance().onPause();
        }

    }

    public static void onDestroy(Context context) {
        GAGameSDKLog.i("GAGameSDK onDestroy");
        if(service != null) {
            context.stopService(service);
        }

        if(GAGameResult.isFloatInitSuccess()) {
            GAGameFloat.Instance().onDestroy();
        }

    }

    public static void onExit() {
        GAGameSDKLog.i("GAGameSDK Exit Game");
        exitDialog.show();
    }

    public static void share(Context context, Activity activity, ShareData shareData, GAGameSDK.ShareCallback shareCallback) {
        GAGameSDKLog.d("shareData:" + shareData.DataToString());
        UserData.getPostData().setShareData(shareData);
        shareCallback = shareCallback;
        shareDialog = new ShareDialog(context, activity);
        shareDialog.show();
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        GAGameSDKLog.d("requestCode:" + requestCode);
        if(requestCode != 11101 && requestCode != 10102) {
            if(requestCode == 10103) {
                Tencent.onActivityResultData(requestCode, resultCode, data, shareListener);
            }
        } else {
            GAGameSDKLog.d("tencent onActivityResult");
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

    }

    public interface ShareCallback {
        void shareSuccess();

        void shareFail(String var1);

        void shareCancel();
    }

    public interface initCallback {
        void initSuccess();

        void initFail();
    }

    public interface loginCallback {
        void loginSuccess(GAGameResult var1);

        void loginFail(String var1);
    }

    public interface logoutCallback {
        void logoutSuccess();
    }

    public interface payCallback {
        void paySuccess();

        void payFail();

        void payCancel();
    }
}
