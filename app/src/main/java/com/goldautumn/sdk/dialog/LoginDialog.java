//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.goldautumn.sdk.dialog.IngDialog.Builder;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.floatview.GAGameFloat;
import com.goldautumn.sdk.minterface.AES;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.minterface.GetMessage;
import com.goldautumn.sdk.minterface.Data.LoginOrRegisterData;
import com.goldautumn.sdk.utils.GetResId;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginDialog extends Dialog implements DialogInterface {
    static LoginUsernameListViewAdapter mAdapter;
    static Context context;
    static Activity activity;
    String[] usernames;
    String[] passwords;
    public static boolean isShowFloatViewButton = true;
    private static IngDialog dialog;
    private View mDialogView;
    private static Button welcome_mButton1;
    private static Button welcome_mButton2;
    private static Button login_mButton1;
    private ImageView welcome_mButton3;
    private ImageView login_mImageButton2;
    private static ListView lv;
    private static LinearLayout login_mImageButton1;
    private static LinearLayout listViewLin;
    private static LinearLayout passwordLin;
    private static EditText editUsername;
    private static EditText editPassword;
    private static EditText editPassword2;
    private TextView protocolButton;
    private TextView forgotPasswordButton;
    private TextView regButton;
    private Handler mHandler = new Handler(new LoginHandlerMessage());
    private View.OnClickListener mButtonOnClickListener = new LoginButtonOnClickListener();

    public static EditText geteditUsername() {
        return editUsername;
    }

    public static EditText geteditPassword() {
        return editPassword;
    }

    public static EditText geteditPassword2() {
        return editPassword2;
    }

    public static LinearLayout getlistViewLin() {
        return listViewLin;
    }

    public static LinearLayout getpasswordLin() {
        return passwordLin;
    }

    public static Button getlogin_mButton1() {
        return login_mButton1;
    }

    public static ListView getlv() {
        return lv;
    }

    public static IngDialog getdialog() {
        return dialog;
    }

    public static LoginDialog Instance() {
        return LoginDialog.SingletonHandler.instance;
    }

    public LoginDialog(Context context) {
        super(context);
    }

    public void show() {
        super.show();
    }

    public LoginDialog(Context context, Activity activity, int dialogStyleEnum) {
        super(context, GetResId.getId(context, "style", "login_dialog"));
        GAGameSDKLog.i("LoginDialog: begin");
        GAGameSDKLog.w(GAGameTool.isUIThread());
        activity = activity;
        context = context;
        GAGameSDKLog.i("LoginDialog: init");
        this.init(context, dialogStyleEnum);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    void init(Context context, int dialogStyleEnum) {
        switch(dialogStyleEnum) {
            case 1:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_welcome1"), (ViewGroup)null);
                welcome_mButton1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "welcome1_button_1"));
                welcome_mButton2 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "welcome1_button_4"));
                this.welcome_mButton3 = (ImageView)this.mDialogView.findViewById(GetResId.getId(context, "id", "welcome1_button_2"));
                this.login_mImageButton2 = (ImageView)this.mDialogView.findViewById(GetResId.getId(context, "id", "welcome1_button_3"));
                welcome_mButton1.setOnClickListener(this.mButtonOnClickListener);
                welcome_mButton2.setOnClickListener(this.mButtonOnClickListener);
                this.welcome_mButton3.setOnClickListener(this.mButtonOnClickListener);
                this.login_mImageButton2.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 2:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_login"), (ViewGroup)null);
                login_mButton1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_button_1"));
                login_mImageButton1 = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_imagebutton_1"));
                this.login_mImageButton2 = (ImageView)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_imagebutton_2"));
                listViewLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "listview_line"));
                passwordLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_edit_lin_2"));
                editUsername = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_username"));
                editPassword = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_password"));
                lv = (ListView)this.mDialogView.findViewById(GetResId.getId(context, "id", "lv"));
                this.forgotPasswordButton = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_button_forgotpassword"));
                this.regButton = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_button_reg"));
                login_mButton1.setOnClickListener(this.mButtonOnClickListener);
                login_mImageButton1.setOnClickListener(this.mButtonOnClickListener);
                this.login_mImageButton2.setOnClickListener(this.mButtonOnClickListener);
                this.forgotPasswordButton.setOnClickListener(this.mButtonOnClickListener);
                this.regButton.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 3:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_registered"), (ViewGroup)null);
                login_mImageButton1 = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "registered_imagebutton_1"));
                login_mButton1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "registered_button_1"));
                editUsername = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "registered_username"));
                editPassword = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "registered_password_1"));
                editPassword2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "registered_password_2"));
                this.protocolButton = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "tv_2"));
                login_mImageButton1.setOnClickListener(this.mButtonOnClickListener);
                login_mButton1.setOnClickListener(this.mButtonOnClickListener);
                this.protocolButton.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
        }

        this.getWindow().setLayout(-1, -2);
    }

    protected void download() {
        this.usernames = new String[5];
        String[] mUsernames = GAGameTool.getSharedPreference("usernames", context);
        int i;
        if(mUsernames != null) {
            for(i = 0; i <= mUsernames.length - 1; ++i) {
                this.usernames[i] = mUsernames[i];
            }
        }

        if(this.usernames != null) {
            for(i = 0; i <= this.usernames.length - 1; ++i) {
                if(this.usernames[i] == null || this.usernames[i].equals("null")) {
                    this.usernames[i] = "";
                }
            }
        }

        mAdapter = new LoginUsernameListViewAdapter(this.usernames, context);
        lv.setAdapter(mAdapter);
    }

    public void mHttpThread(final Context context, int code, final String userType) {
        Runnable mRunnable = null;
        if(code == 1) {
            mRunnable = new Runnable() {
                public void run() {
                    LoginDialog.this.startLogin(UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), context, userType);
                }
            };
        } else if(code == 2) {
            mRunnable = new Runnable() {
                public void run() {
                    LoginDialog.this.startRegistered(UserData.getPostData().getUserName(), UserData.getPostData().getPassWord(), context);
                }
            };
        } else if(code == 3) {
            mRunnable = new Runnable() {
                public void run() {
                    LoginDialog.this.startCreateVisitorID();
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

    public void startLogin(String username, String password, Context context, String userType) {
        Message msg;
        switch(GAGameTool.checkUsernameorPassword(username, password)) {
            case 1:
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    GetMessage.LoginOrRegister(username, password, 1, userType, i);
                }

                this.clickGet(1);
                break;
            case 2:
                msg = this.mHandler.obtainMessage(101, context.getText(GetResId.getId(context, "string", "login_fail_username")));
                this.mHandler.sendMessage(msg);
                break;
            case 3:
                msg = this.mHandler.obtainMessage(101, context.getText(GetResId.getId(context, "string", "login_fail_password")));
                this.mHandler.sendMessage(msg);
        }

    }

    public void startRegistered(String username, String password, Context context) {
        Message msg;
        switch(GAGameTool.checkUsernameorPassword(username, password)) {
            case 1:
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    GetMessage.LoginOrRegister(username, password, 2, "", i);
                }

                this.clickGet(2);
                break;
            case 2:
                msg = this.mHandler.obtainMessage(202, context.getText(GetResId.getId(context, "string", "login_fail_username")));
                this.mHandler.sendMessage(msg);
                break;
            case 3:
                msg = this.mHandler.obtainMessage(202, context.getText(GetResId.getId(context, "string", "login_fail_password")));
                this.mHandler.sendMessage(msg);
        }

    }

    public void startCreateVisitorID() {
        int i = 0;

        for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
            GetMessage.LoginOrRegister("", "", 3, "", i);
        }

        this.clickGet(3);
    }

    public void hwLoginHttp(final String uid, final String token, final String type) {
        Runnable mRunnable = new Runnable() {
            public void run() {
                LoginDialog.this.hwStartLogin(uid, token, type);
            }
        };
        Builder ingDialog = new Builder(context);
        dialog = ingDialog.create();
        dialog.show();
        if(mRunnable != null) {
            (new Thread(mRunnable)).start();
        }

    }

    public void hwStartLogin(String uid, String token, String type) {
        try {
            int i = 0;
            LoginOrRegisterData data = new LoginOrRegisterData();
            data.SetData("appid", Finaldata.getAppid());
            JSONObject json = new JSONObject();
            JSONObject dataJson = new JSONObject();
            json.put("usertype", type);
            json.put("ad_id", UserData.getShowData().getAd_id());
            dataJson.put("uid", uid);
            dataJson.put("token", token);
            json.put("tpdata", dataJson.toString());
            GAGameSDKLog.e("data:" + json.toString());
            AES aes = new AES();
            String jsonStr = aes.encrypt(json.toString(), Finaldata.getAppkey());
            data.SetData("data", jsonStr);
            data.StringToData(GAGameTool.mHttpGet(GAGameTool.getUrl(data.GetHashMap(), 1, "tplogin", i)));
            String status = data.GetData("status");
            String message = data.GetData("message");
            data.StringToData(data.GetData("data").toString());
            String tokenID = data.GetData("tokenID");
            String account_id = data.GetData("account_id");
            if(tokenID != null && account_id != null) {
                UserData.getShowData().setUserId(account_id);
                UserData.getShowData().setToken(tokenID);
            }

            this.clickHWLogin(status, message);
        } catch (JSONException var15) {
            var15.printStackTrace();
        }

    }

    private void clickHWLogin(String status, String message) {
        int a = 100;
        int b = 101;
        int mStatus;
        if(!status.isEmpty() && status != null) {
            mStatus = Integer.parseInt(status);
        } else {
            mStatus = 404;
        }

        Message msg;
        if(mStatus == 0) {
            msg = this.mHandler.obtainMessage(a, message);
            this.mHandler.sendMessage(msg);
        } else if(mStatus == 404) {
            msg = this.mHandler.obtainMessage(mStatus, context.getText(GetResId.getId(context, "string", "network_error")));
            this.mHandler.sendMessage(msg);
        } else {
            msg = this.mHandler.obtainMessage(b, message);
            this.mHandler.sendMessage(msg);
        }

    }

    public void clickGet(int code) {
        int a = 0;
        int b = 0;
        if(code == 1) {
            a = 100;
            b = 101;
        } else if(code == 2) {
            a = 203;
            b = 202;
        } else if(code == 3) {
            a = 204;
            b = 205;
        }

        Message msg;
        if(GetMessage.getStatus() == 0) {
            msg = this.mHandler.obtainMessage(a, GetMessage.getMessage());
            this.mHandler.sendMessage(msg);
        } else if(GetMessage.getStatus() == 404) {
            msg = this.mHandler.obtainMessage(GetMessage.getStatus(), context.getText(GetResId.getId(context, "string", "network_error")));
            this.mHandler.sendMessage(msg);
        } else {
            msg = this.mHandler.obtainMessage(b, GetMessage.getMessage());
            this.mHandler.sendMessage(msg);
        }

    }

    public static void showFloatViewButton() {
        GAGameSDKLog.e("showFloatViewButton: start");
        isShowFloatViewButton = false;
        boolean isScreen = GAGameTool.isScreenOriatationPortrait(context);
        DisplayMetrics metric = GAGameTool.getMetrics(context);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        GAGameSDKLog.e("showFloatViewButton: Call GAGameFloat onCreate");
        GAGameFloat.Instance().onCreate(context, activity, width, height, isScreen);
    }

    private static class SingletonHandler {
        static final LoginDialog instance = GAGameSDK.getloginDialog();

        private SingletonHandler() {
        }
    }
}
