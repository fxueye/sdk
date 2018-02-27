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
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.goldautumn.sdk.dialog.IngDialog.Builder;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.AES;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameResult;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.pay.GetMessagePay;
import com.goldautumn.sdk.pay.PayData;
import com.goldautumn.sdk.pay.GetMessagePay.Message;
import com.goldautumn.sdk.utils.GetResId;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserCenterDialog extends Dialog implements DialogInterface {
    private View mDialogView;
    private LinearLayout backLin;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private TextView textV1;
    private TextView textV2;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private RelativeLayout forgot_re;
    private ListView mLv;
    private static IngDialog dialog;
    static PayDataListViewAdapter mAdapter;
    OnClickListener mButtonOnClickListener = new UserCenterButtonOnClickListener();

    public Button getbutton1() {
        return this.button1;
    }

    public Button getbutton2() {
        return this.button2;
    }

    public TextView getTextv1() {
        return this.textV1;
    }

    public EditText geteditText1() {
        return this.editText1;
    }

    public EditText geteditText2() {
        return this.editText2;
    }

    public EditText geteditText3() {
        return this.editText3;
    }

    public EditText geteditText4() {
        return this.editText4;
    }

    public RelativeLayout getforgot_re() {
        return this.forgot_re;
    }

    public static UserCenterDialog Instance() {
        return UserCenterDialog.SingletonHandler.instance;
    }

    public UserCenterDialog(Context context) {
        super(context);
    }

    public void show() {
        super.show();
    }

    public UserCenterDialog(Context context, Activity activity, int dialogStyleEnum) {
        super(context, GetResId.getId(context, "style", "login_dialog"));
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

    public void init(Context context, int dialogStyleEnum) {
        switch(dialogStyleEnum) {
            case 5:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_usercenter"), (ViewGroup)null);
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_1"));
                this.button2 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_2"));
                this.button3 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_3"));
                this.button4 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_4"));
                this.button5 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_5"));
                this.button6 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_button_6"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_phone_1"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "user_phone_2"));
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "center_imagebutton_1"));
                this.editText1.setHint(UserData.getShowData().getUserName());
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.button2.setOnClickListener(this.mButtonOnClickListener);
                this.button3.setOnClickListener(this.mButtonOnClickListener);
                this.button4.setOnClickListener(this.mButtonOnClickListener);
                this.button5.setOnClickListener(this.mButtonOnClickListener);
                this.button6.setOnClickListener(this.mButtonOnClickListener);
                if(GAGameSDK.getisLogin()) {
                    if(UserData.getShowData().getUserType().equals("1")) {
                        this.button1.setVisibility(8);
                        if(UserData.getShowData().getPhone() != null && !UserData.getShowData().getPhone().isEmpty()) {
                            this.editText2.setHint(UserData.getShowData().getPhone());
                            this.button2.setVisibility(8);
                        }
                    } else {
                        this.editText1.setHint(context.getText(GetResId.getId(context, "string", "text_username_text_2")) + ":" + UserData.getShowData().getUserName());
                        this.button2.setVisibility(8);
                        this.button3.setVisibility(8);
                        this.button4.setVisibility(8);
                    }

                    if(Finaldata.getShowReal()) {
                        if(UserData.getShowData().getShowReal().equals("true")) {
                            this.button4.setVisibility(8);
                        }
                    } else {
                        this.button4.setVisibility(8);
                    }
                }

                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 6:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_forgotpassword_1"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_imagebutton_1"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "login_username"));
                this.editText4 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_text_phone_user"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_button_1"));
                this.button2 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_button_2"));
                this.textV1 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_text_phone"));
                this.forgot_re = (RelativeLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_re"));
                this.forgot_re.setVisibility(8);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.button2.setOnClickListener(this.mButtonOnClickListener);
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 7:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_forgotpassword_2"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot2_imagebutton_1"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_username"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_password_1"));
                this.editText3 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot_password_2"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot2_button_1"));
                this.button2 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "forgot2_button_2"));
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.button2.setOnClickListener(this.mButtonOnClickListener);
                GAGameTool.buttonTime(context, context.getText(GetResId.getId(context, "string", "text_forgot_8")).toString().trim(), 115, this.button1);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 8:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_changepassword"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_imagebutton_1"));
                this.textV1 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_tv2"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_username"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_password_1"));
                this.editText3 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_password_2"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "change_button_1"));
                this.textV1.setText(UserData.getShowData().getUserName());
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
                break;
            case 9:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_bindphone"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_imagebutton_1"));
                this.textV1 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_tv2"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_phone"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_msg"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_button_1"));
                this.button2 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "bind_button_2"));
                this.textV1.setText(UserData.getShowData().getUserName());
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.button2.setOnClickListener(this.mButtonOnClickListener);
                this.button2.setBackgroundResource(GetResId.getId(context, "drawable", "gasdk_button_false"));
                this.button2.setClickable(false);
                this.setCancelable(false);
                this.setContentView(this.mDialogView);
                break;
            case 10:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_upgrade"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_imagebutton_1"));
                this.textV1 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_tv_2"));
                this.textV2 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_tv2"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_button_1"));
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_username"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_password_1"));
                this.editText3 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "upgrade_password_2"));
                this.textV2.setText(UserData.getShowData().getUserName());
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.textV1.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.setCancelable(false);
                this.setContentView(this.mDialogView);
                break;
            case 11:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_paydata"), (ViewGroup)null);
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "paydata_imagebutton_1"));
                this.mLv = (ListView)this.mDialogView.findViewById(GetResId.getId(context, "id", "paydata_lv"));
                this.textV1 = (TextView)this.mDialogView.findViewById(GetResId.getId(context, "id", "paydata_tv3"));
                this.startGetQueryOrder(context);
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.setCancelable(false);
                this.setContentView(this.mDialogView);
            case 12:
            default:
                break;
            case 13:
                this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_realname"), (ViewGroup)null);
                this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "real_username"));
                this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "real_password_1"));
                this.backLin = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "real_imagebutton_1"));
                this.button1 = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "real_button_1"));
                this.backLin.setOnClickListener(this.mButtonOnClickListener);
                this.button1.setOnClickListener(this.mButtonOnClickListener);
                this.setCanceledOnTouchOutside(false);
                this.setContentView(this.mDialogView);
        }

    }

    private void startGetQueryOrder(final Context context) {
        Builder ingDialog = new Builder(context);
        dialog = ingDialog.create();
        dialog.show();
        (new Thread(new Runnable() {
            public void run() {
                int i = 0;

                for(GAGameTool.bl = true; GAGameTool.bl; ++i) {
                    UserCenterDialog.this.queryOrder(context, i);
                }

            }
        })).start();
    }

    private void queryOrder(final Context context, int a) {
        PayData mPaydata = new PayData();
        mPaydata.SetData("accountId", GAGameResult.Instance().getUserId());
        mPaydata.SetData("token", UserData.getShowData().getToken());
        AES aes = new AES();
        String jsonStr = aes.encrypt(mPaydata.DataToString(), Finaldata.getAppkey());
        mPaydata = new PayData();
        mPaydata.SetData("appId", Finaldata.getAppid());
        mPaydata.SetData("data", jsonStr);
        String payURL = GAGameTool.getUrl(mPaydata.GetHashMap(), 2, "query_order_by_accountid", a);
        String getRust = GAGameTool.mHttpGet(payURL);
        mPaydata.StringToData(getRust);
        if(mPaydata.GetData("status").equals("1000") && !mPaydata.GetData("data").isEmpty()) {
            try {
                JSONArray jsonarr = new JSONArray(mPaydata.GetData("data"));
                GAGameSDKLog.e("a data----->" + mPaydata.GetData("data"));
                final GetMessagePay getmesg = new GetMessagePay();
                List<Message> list = new ArrayList();

                for(int i = jsonarr.length() - 1; i >= 0; --i) {
                    JSONObject temp = (JSONObject)jsonarr.get(i);
                    Message message = new Message();
                    message.setTime(temp.getString("create_datetime"));
                    message.setName(temp.getString("item_name"));
                    message.setPrice(temp.getString("item_price"));
                    message.setRuslt(temp.getString("status_desc"));
                    message.setStatus(temp.getString("status"));
                    list.add(message);
                    if(i == 0 && list != null && list.size() > 0) {
                        getmesg.setMessages(list);
                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.post(new Runnable() {
                            public void run() {
                                UserCenterDialog.dialog.dismiss();
                                UserCenterDialog.this.textV1.setVisibility(8);
                                UserCenterDialog.mAdapter = new PayDataListViewAdapter(getmesg.getMessages(), context);
                                UserCenterDialog.this.mLv.setAdapter(UserCenterDialog.mAdapter);
                                UserCenterDialog.this.mLv.setVisibility(0);
                            }
                        });
                    }
                }
            } catch (JSONException var15) {
                var15.printStackTrace();
            }
        } else {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                public void run() {
                    UserCenterDialog.dialog.dismiss();
                    UserCenterDialog.this.mLv.setVisibility(8);
                    UserCenterDialog.this.textV1.setVisibility(0);
                }
            });
        }

    }

    private static class SingletonHandler {
        static final UserCenterDialog instance = GAGameSDK.getuserDialog();

        private SingletonHandler() {
        }
    }
}
