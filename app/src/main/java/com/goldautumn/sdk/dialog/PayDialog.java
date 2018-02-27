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
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.minterface.Finaldata;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.pay.PayData;
import com.goldautumn.sdk.utils.GetResId;
import java.text.DecimalFormat;

public class PayDialog extends Dialog implements DialogInterface {
    private View mDialogView;
    private EditText editText1;
    private EditText editText2;
    private EditText editText4;
    private RadioGroup mRadioGroup;
    private Button button;
    private LinearLayout imgButton;
    private RelativeLayout re1;
    private RelativeLayout re2;
    private RelativeLayout re3;
    private RadioButton rButton1;
    private RadioButton rButton2;
    private RadioButton rButton3;
    private View.OnClickListener mButtonOnClickListener = new PayButtonOnClickListener();
    OnCheckedChangeListener mOnCheckedChangeListener = new PayButtonOnClickListener();

    public PayDialog(Context context) {
        super(context);
    }

    public void cancel() {
        super.cancel();
    }

    public void show() {
        super.show();
    }

    public PayDialog(Context context, Activity activity) {
        super(context, GetResId.getId(context, "style", "login_dialog"));
        this.init(context);
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

    public void init(Context context) {
        this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_pay"), (ViewGroup)null);
        PayData mPatdata = GAGameSDK.getmPatdata();
        this.editText1 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_username"));
        this.editText2 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_name"));
        this.editText4 = (EditText)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_prices_1"));
        this.mRadioGroup = (RadioGroup)this.mDialogView.findViewById(GetResId.getId(context, "id", "radioGroup1"));
        this.button = (Button)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_button_1"));
        this.imgButton = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_imagebutton_1"));
        this.re1 = (RelativeLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_type_1"));
        this.re2 = (RelativeLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_type_2"));
        this.re3 = (RelativeLayout)this.mDialogView.findViewById(GetResId.getId(context, "id", "pay_type_3"));
        this.rButton1 = (RadioButton)this.mDialogView.findViewById(GetResId.getId(context, "id", "radio0"));
        this.rButton2 = (RadioButton)this.mDialogView.findViewById(GetResId.getId(context, "id", "radio1"));
        this.rButton3 = (RadioButton)this.mDialogView.findViewById(GetResId.getId(context, "id", "radio2"));
        this.re1.setVisibility(8);
        this.re2.setVisibility(8);
        this.re3.setVisibility(8);
        this.rButton1.setVisibility(8);
        this.rButton2.setVisibility(8);
        this.rButton3.setVisibility(8);
        this.editText1.setHint(UserData.getShowData().getUserName());
        this.editText2.setHint(mPatdata.getItem_Name());
        double douPrice = Double.valueOf(mPatdata.getPrice()).doubleValue();
        GAGameSDKLog.d("price=" + douPrice);
        DecimalFormat df = new DecimalFormat("#0.00");
        String strPrice = String.valueOf(df.format(douPrice / 100.0D));
        GAGameSDKLog.d("display price=" + strPrice);
        this.editText4.setHint("￥" + strPrice);
        String[] channels = Finaldata.getChannels();
        if(channels != null) {
            for(int i = 0; i < channels.length; ++i) {
                if(channels[i].equals("1")) {
                    this.re1.setVisibility(0);
                    this.rButton1.setVisibility(0);
                }

                if(channels[i].equals("2")) {
                    this.re2.setVisibility(0);
                    this.rButton2.setVisibility(0);
                }

                if(channels[i].equals("3")) {
                    this.re3.setVisibility(0);
                    this.rButton3.setVisibility(0);
                }
            }

            if(this.re1.getVisibility() == 0) {
                PayButtonOnClickListener.setPayType(1);
            } else if(this.re2.getVisibility() == 0) {
                PayButtonOnClickListener.setPayType(2);
                this.rButton2.setChecked(true);
            } else if(this.re3.getVisibility() == 0) {
                PayButtonOnClickListener.setPayType(3);
                this.rButton3.setChecked(true);
            }
        }

        this.button.setOnClickListener(this.mButtonOnClickListener);
        this.imgButton.setOnClickListener(this.mButtonOnClickListener);
        this.mRadioGroup.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        this.setCanceledOnTouchOutside(false);
        this.setContentView(this.mDialogView);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4 && event.getRepeatCount() == 0) {
            GAGameSDK.getmPayDialog().dismiss();
            GAGameSDK.setPayResult(3);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
