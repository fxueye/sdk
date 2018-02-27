//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.goldautumn.sdk.dialog.SmallDialog.Builder;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;

public class LoginUsernameListViewAdapter extends BaseAdapter {
    String[] usernames;
    LayoutInflater mInflater;
    Context context;

    public LoginUsernameListViewAdapter(String[] usernames, Context context) {
        this.usernames = usernames;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        int a = 0;

        for(int i = 0; i < this.usernames.length; ++i) {
            if(!this.usernames[i].isEmpty()) {
                a = i + 1;
            }
        }

        return a;
    }

    public Object getItem(int position) {
        return this.usernames[position];
    }

    public long getItemId(int position) {
        return (long)position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LoginUsernameListViewAdapter.ViewHolder holder = null;
        if(convertView == null) {
            convertView = this.mInflater.inflate(GetResId.getId(this.context, "layout", "gasdk_item"), (ViewGroup)null);
            holder = new LoginUsernameListViewAdapter.ViewHolder();
            holder.tv_username = (TextView)convertView.findViewById(GetResId.getId(this.context, "id", "login_listview_username"));
            holder.img_del = (ImageView)convertView.findViewById(GetResId.getId(this.context, "id", "login_listview_del"));
            convertView.setTag(holder);
        } else {
            holder = (LoginUsernameListViewAdapter.ViewHolder)convertView.getTag();
        }

        String username = (String)this.getItem(position);
        if(username != null && !username.isEmpty()) {
            holder.tv_username.setText(username);
        }

        holder.tv_username.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    public void run() {
                        LoginDialog.geteditUsername().setText(LoginUsernameListViewAdapter.this.usernames[position]);
                        LoginButtonOnClickListener.a = 1;
                        LoginDialog.getlistViewLin().setVisibility(8);
                    }
                });
            }
        });
        holder.img_del.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Builder builder = new Builder(LoginUsernameListViewAdapter.this.context);
                builder.setMessage(GetResId.getId(LoginUsernameListViewAdapter.this.context, "string", "dialog_del"));
                builder.setNegativeButton(GetResId.getId(LoginUsernameListViewAdapter.this.context, "string", "dialog_del_no"), new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(GetResId.getId(LoginUsernameListViewAdapter.this.context, "string", "dialog_del_yes"), new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = position; i <= LoginUsernameListViewAdapter.this.usernames.length - 2; ++i) {
                            LoginUsernameListViewAdapter.this.usernames[i] = LoginUsernameListViewAdapter.this.usernames[i + 1];
                        }

                        LoginUsernameListViewAdapter.this.usernames[LoginUsernameListViewAdapter.this.usernames.length - 1] = "";
                        GAGameTool.setSharedPreference("usernames", LoginUsernameListViewAdapter.this.usernames, LoginUsernameListViewAdapter.this.context);
                        LoginDialog.mAdapter = new LoginUsernameListViewAdapter(LoginUsernameListViewAdapter.this.usernames, LoginUsernameListViewAdapter.this.context);
                        LoginDialog.getlv().setAdapter(LoginDialog.mAdapter);
                        dialog.dismiss();
                        LoginDialog.getpasswordLin().setVisibility(0);
                        LoginDialog.getlogin_mButton1().setVisibility(0);
                        LoginDialog.getlistViewLin().setVisibility(8);
                        LoginButtonOnClickListener.a = 1;
                    }
                });
                builder.create().show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_username;
        ImageView img_del;

        ViewHolder() {
        }
    }
}
