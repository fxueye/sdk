//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.goldautumn.sdk.utils.GetResId;

public class SmallDialog extends Dialog {
    public SmallDialog(Context context) {
        super(context);
    }

    public SmallDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public SmallDialog.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public SmallDialog.Builder setMessage(int message) {
            this.message = (String)this.context.getText(message);
            return this;
        }

        public SmallDialog.Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public SmallDialog.Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String)this.context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public SmallDialog.Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public SmallDialog.Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String)this.context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public SmallDialog.Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public SmallDialog create() {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
            final SmallDialog dialog = new SmallDialog(this.context, GetResId.getId(this.context, "style", "login_dialog"));
            View layout = inflater.inflate(GetResId.getId(this.context, "layout", "gasdk_dialog_normal"), (ViewGroup)null);
            dialog.addContentView(layout, new LayoutParams(-1, -2));
            if(this.positiveButtonText != null) {
                ((TextView)layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_no"))).setText(this.positiveButtonText);
                if(this.positiveButtonClickListener != null) {
                    ((TextView)layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_no"))).setOnClickListener(new android.view.View.OnClickListener() {
                        public void onClick(View v) {
                            Builder.this.positiveButtonClickListener.onClick(dialog, -1);
                        }
                    });
                }
            } else {
                layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_no")).setVisibility(8);
            }

            if(this.negativeButtonText != null) {
                ((TextView)layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_yes"))).setText(this.negativeButtonText);
                if(this.negativeButtonClickListener != null) {
                    ((TextView)layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_yes"))).setOnClickListener(new android.view.View.OnClickListener() {
                        public void onClick(View v) {
                            Builder.this.negativeButtonClickListener.onClick(dialog, -2);
                        }
                    });
                }
            } else {
                layout.findViewById(GetResId.getId(this.context, "id", "dialog_del_yes")).setVisibility(8);
            }

            if(this.message != null) {
                ((TextView)layout.findViewById(GetResId.getId(this.context, "id", "dia_tv"))).setText(this.message);
            } else if(this.contentView != null) {
                ((LinearLayout)layout.findViewById(GetResId.getId(this.context, "id", "content"))).removeAllViews();
                ((LinearLayout)layout.findViewById(GetResId.getId(this.context, "id", "content"))).addView(this.contentView, new LayoutParams(-1, -1));
            }

            dialog.setContentView(layout);
            return dialog;
        }
    }
}
