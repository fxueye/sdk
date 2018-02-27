//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.goldautumn.sdk.utils.GetResId;

public class IngDialog extends Dialog {
    public IngDialog(Context context) {
        super(context);
    }

    public IngDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private ImageView imgV;

        public Builder(Context context) {
            this.context = context;
        }

        public IngDialog.Builder setMessage(String message) {
            return this;
        }

        public IngDialog.Builder setMessage(int message) {
            return this;
        }

        public IngDialog.Builder setContentView(View v) {
            return this;
        }

        @SuppressLint({"InflateParams"})
        public IngDialog create() {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
            IngDialog dialog = new IngDialog(this.context, GetResId.getId(this.context, "style", "login_dialog"));
            View layout = inflater.inflate(GetResId.getId(this.context, "layout", "gasdk_dialog_ing"), (ViewGroup)null);
            dialog.addContentView(layout, new LayoutParams(-1, -2));
            this.imgV = (ImageView)layout.findViewById(GetResId.getId(this.context, "id", "ing_img"));
            Animation operatingAnim = AnimationUtils.loadAnimation(this.context, GetResId.getId(this.context, "anim", "gasdk_dialog_ing_tip"));
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            this.imgV.clearAnimation();
            this.imgV.startAnimation(operatingAnim);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
