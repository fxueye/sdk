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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.goldautumn.sdk.dialog.showdata.UserData;
import com.goldautumn.sdk.share.QQShare;
import com.goldautumn.sdk.share.WXShare;
import com.goldautumn.sdk.utils.GetResId;

public class ShareDialog extends Dialog implements DialogInterface {
    private View mDialogView;
    private ImageView imgButton1;
    private ImageView imgButton2;
    private Context mContext;
    private Activity mActivity;

    public ShareDialog(Context context) {
        super(context);
    }

    public void show() {
        super.show();
    }

    public ShareDialog(Context context, Activity activity) {
        super(context, GetResId.getId(context, "style", "login_dialog"));
        this.mContext = context;
        this.mActivity = activity;
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
        this.mDialogView = View.inflate(context, GetResId.getId(context, "layout", "gasdk_dialog_share"), (ViewGroup)null);
        this.imgButton1 = (ImageView)this.mDialogView.findViewById(GetResId.getId(context, "id", "share_wx"));
        this.imgButton2 = (ImageView)this.mDialogView.findViewById(GetResId.getId(context, "id", "share_qq"));
        this.imgButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                WXShare wxShare = new WXShare();
                wxShare.wxShare(ShareDialog.this.mContext, UserData.getPostData().getShareData());
            }
        });
        this.imgButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                QQShare qqShare = new QQShare();
                qqShare.share(ShareDialog.this.mContext, ShareDialog.this.mActivity, UserData.getPostData().getShareData());
            }
        });
        this.setCanceledOnTouchOutside(false);
        this.setContentView(this.mDialogView);
        this.getWindow().setLayout(-1, -2);
    }
}
