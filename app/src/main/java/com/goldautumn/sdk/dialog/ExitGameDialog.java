//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.goldautumn.sdk.utils.GetResId;

public class ExitGameDialog extends Dialog implements DialogInterface {
    static Context context;
    static Activity activity;
    private View mDialogView;
    private static TextView exit_mButton1;
    private static TextView exit_mButton2;
    private LinearLayout exit_mImageButton;
    private ImageView exit_image;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == GetResId.getId(v.getContext(), "id", "dialog_exit_back_btn")) {
                ExitGameDialog.this.closeExitGameDialog();
            } else if(v.getId() == GetResId.getId(v.getContext(), "id", "dialog_exit_yes_btn")) {
                System.exit(0);
            }
        }
    };
    public ExitGameDialog(Context _context, Activity _activity) {
        super(_context, GetResId.getId(_context, "style", "exit_dialog"));
        context = _context;
        activity = _activity;
        this.mDialogView = View.inflate(context, GetResId.getId(_context, "layout", "gasdk_dialog_exit_game"), (ViewGroup)null);
        this.exit_image = (ImageView)this.mDialogView.findViewById(GetResId.getId(_context, "id", "dia_exit_img"));
        this.exit_mImageButton = (LinearLayout)this.mDialogView.findViewById(GetResId.getId(_context, "id", "dia_exit_imgbutton"));
        exit_mButton1 = (TextView)this.mDialogView.findViewById(GetResId.getId(_context, "id", "dialog_exit_back_btn"));
        exit_mButton2 = (TextView)this.mDialogView.findViewById(GetResId.getId(_context, "id", "dialog_exit_yes_btn"));
        exit_mButton1.setOnClickListener(mOnClickListener);
        exit_mButton2.setOnClickListener(mOnClickListener);
        this.exit_mImageButton.setOnClickListener(mOnClickListener);
        this.setCanceledOnTouchOutside(false);
        this.setContentView(this.mDialogView);
    }

    private void closeExitGameDialog() {
        this.hide();
    }
}
