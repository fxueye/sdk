package com.goldautumn.sdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolActivity extends Activity {
    private TextView tvButton3;
    private TextView tvButton4;
    private TextView tvButton5;
    private TextView tvBady;
    private TextView tvTitle;
    private List<TextView> tvList;
    private ImageView imgButton_back;
    Context context = this;
    OnClickListener tvButtonOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            if(v.getId() == GetResId.getId(ProtocolActivity.this.context, "id", "img_button_back")) {
                ProtocolActivity.this.finish();
            }

            for(int i = 0; i < ProtocolActivity.this.tvList.size(); ++i) {
                if(((TextView)ProtocolActivity.this.tvList.get(i)).getId() == v.getId()) {
                    ((TextView)ProtocolActivity.this.tvList.get(i)).setTextColor(ProtocolActivity.this.getResources().getColor(17170443));
                    ((TextView)ProtocolActivity.this.tvList.get(i)).setBackground(ProtocolActivity.this.getResources().getDrawable(GetResId.getId(ProtocolActivity.this.context, "drawable", "gasdk_protocol_button_click")));
                    ProtocolActivity.this.tvTitle.setText(((TextView)ProtocolActivity.this.tvList.get(i)).getText().toString());
                    if(((TextView)ProtocolActivity.this.tvList.get(i)).getId() == ProtocolActivity.this.tvButton3.getId()) {
                        ProtocolActivity.this.tvBady.setText(GetResId.getId(ProtocolActivity.this.context, "string", "notice"));
                    } else {
                        ProtocolActivity.this.tvBady.setText(((TextView)ProtocolActivity.this.tvList.get(i)).getText().toString());
                    }
                } else {
                    ((TextView)ProtocolActivity.this.tvList.get(i)).setTextColor(ProtocolActivity.this.getResources().getColor(17170444));
                    ((TextView)ProtocolActivity.this.tvList.get(i)).setBackground(ProtocolActivity.this.getResources().getDrawable(GetResId.getId(ProtocolActivity.this.context, "drawable", "gasdk_protocol_button")));
                }
            }

        }
    };

    public ProtocolActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(GetResId.getId(this.context, "layout", "gasdk_protocol"));
        GAGameSDKLog.i("in ProtocolActivity");
        if(GAGameTool.isScreenOriatationPortrait(this.context)) {
            this.setRequestedOrientation(1);
        } else {
            this.setRequestedOrientation(0);
        }

        this.tvButton3 = (TextView)this.findViewById(GetResId.getId(this.context, "id", "protocol_buton_3"));
        this.tvButton4 = (TextView)this.findViewById(GetResId.getId(this.context, "id", "protocol_buton_4"));
        this.tvButton5 = (TextView)this.findViewById(GetResId.getId(this.context, "id", "protocol_buton_5"));
        this.imgButton_back = (ImageView)this.findViewById(GetResId.getId(this.context, "id", "img_button_back"));
        this.tvTitle = (TextView)this.findViewById(GetResId.getId(this.context, "id", "protocol_bady_title_tv"));
        this.tvBady = (TextView)this.findViewById(GetResId.getId(this.context, "id", "protocol_body_tv"));
        this.tvButton3.setTextColor(this.getResources().getColor(17170443));
        this.tvButton3.setBackground(this.getResources().getDrawable(GetResId.getId(this.context, "drawable", "gasdk_protocol_button_click")));
        this.tvTitle.setText(this.tvButton3.getText().toString());
        this.tvBady.setText(GetResId.getId(this.context, "string", "notice"));
        this.tvList = new ArrayList();
        this.tvList.add(this.tvButton3);
        this.tvList.add(this.tvButton4);
        this.tvList.add(this.tvButton5);
        this.tvButton3.setOnClickListener(this.tvButtonOnClickListener);
        this.tvButton4.setOnClickListener(this.tvButtonOnClickListener);
        this.tvButton5.setOnClickListener(this.tvButtonOnClickListener);
        this.imgButton_back.setOnClickListener(this.tvButtonOnClickListener);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
