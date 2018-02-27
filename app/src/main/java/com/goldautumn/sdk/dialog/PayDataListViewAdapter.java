//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.goldautumn.sdk.pay.GetMessagePay.Message;
import com.goldautumn.sdk.utils.GetResId;
import java.text.DecimalFormat;
import java.util.List;

public class PayDataListViewAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    Context context;
    List<Message> list;
    DecimalFormat decimalFormat;

    public PayDataListViewAdapter(List<Message> list, Context context) {
        this.context = context;
        this.list = list;
        this.decimalFormat = new DecimalFormat("0.00");
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.list.size();
    }

    public Message getItem(int position) {
        return (Message)this.list.get(position);
    }

    public long getItemId(int position) {
        return (long)position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PayDataListViewAdapter.ViewHolder holder = null;
        if(convertView == null) {
            convertView = this.mInflater.inflate(GetResId.getId(this.context, "layout", "gasdk_paydata_item"), (ViewGroup)null);
            holder = new PayDataListViewAdapter.ViewHolder();
            holder.tv_time = (TextView)convertView.findViewById(GetResId.getId(this.context, "id", "paydata_time"));
            holder.tv_name = (TextView)convertView.findViewById(GetResId.getId(this.context, "id", "paydata_item_name"));
            holder.tv_price = (TextView)convertView.findViewById(GetResId.getId(this.context, "id", "paydata_price"));
            holder.tv_ruelt = (TextView)convertView.findViewById(GetResId.getId(this.context, "id", "paydata_ruslt"));
            convertView.setTag(holder);
        } else {
            holder = (PayDataListViewAdapter.ViewHolder)convertView.getTag();
        }

        Message msg = this.getItem(position);
        if(msg != null) {
            String price = this.decimalFormat.format(Double.parseDouble(msg.getPrice()));
            holder.tv_time.setText(msg.getTime());
            holder.tv_name.setText(msg.getName());
            holder.tv_price.setText(price);
            holder.tv_ruelt.setText(msg.getRuslt());
            if(msg.getStatus().equals("3")) {
                holder.tv_ruelt.setTextColor(this.context.getResources().getColor(GetResId.getId(this.context, "color", "paydata_result_yes")));
            } else {
                holder.tv_ruelt.setTextColor(this.context.getResources().getColor(GetResId.getId(this.context, "color", "paydata_result_no")));
            }
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_time;
        TextView tv_name;
        TextView tv_price;
        TextView tv_ruelt;

        ViewHolder() {
        }
    }
}
