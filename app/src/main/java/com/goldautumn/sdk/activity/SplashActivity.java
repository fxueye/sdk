package com.goldautumn.sdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.View;
import com.goldautumn.sdk.dialog.IngDialog;
import com.goldautumn.sdk.dialog.IngDialog.Builder;
import com.goldautumn.sdk.minterface.GAGameResult;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.minterface.GAGameTool.ConfigLoaderCallBack;
import com.goldautumn.sdk.utils.GetResId;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends Activity {
    private long m_dwSplashTime = 2000L;
    private boolean m_bPaused = false;
    private boolean m_bSplashActive = true;
    IngDialog dialog;
    Context context = this;
    String[] ftpUrl = null;

    public SplashActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(VERSION.SDK_INT < 16) {
            this.getWindow().setFlags(1024, 1024);
        } else {
            View decorView = this.getWindow().getDecorView();
            int uiOptions = 6;
            decorView.setSystemUiVisibility(uiOptions);
        }

        this.setContentView(GetResId.getId(this.context, "layout", "gasdk_splash"));
        Builder ingDialog = new Builder(this.context);
        this.dialog = ingDialog.create();
        this.dialog.show();
        this.init(0);
    }

    public void init(final int i) {
        String buffStr = GAGameTool.getFromAssets(this, "GAGameSettings.txt");
        GAGameSDKLog.d(buffStr);
        if(buffStr.length() > 0) {
            try {
                JSONObject json = new JSONObject(buffStr);
                this.ftpUrl = new String[json.length()];
                Iterator<String> it = json.keys();

                for(int a = 0; it.hasNext(); ++a) {
                    String urlStr = json.getString((String)it.next());
                    this.ftpUrl[a] = urlStr;
                }
            } catch (JSONException var7) {
                var7.printStackTrace();
            }
        }

        GAGameTool.switchConfigDownloar(this.ftpUrl, new ConfigLoaderCallBack() {
            public void onSuesses() {
                GAGameSDKLog.i("startGetPayType");
                GAGameSDKLog.w(GAGameTool.isUIThread());
                GAGameTool.startGetPayType();
                GAGameResult result = new GAGameResult();
                result.setInit(true);
                GAGameSDK.setInitResult(result);
                SplashActivity.this.finash();
            }

            public void onFail() {
                GAGameSDKLog.e("switchConfigDownloar fail");
                int a = i;
                ++a;
                if(a > 5) {
                    GAGameResult result = new GAGameResult();
                    result.setInit(false);
                    GAGameSDK.setInitResult(result);
                    SplashActivity.this.finash();
                } else if(SplashActivity.this.ftpUrl != null) {
                    SplashActivity.this.init(a);
                }

            }
        });
    }

    public void finash() {
        Handler mHadnler = new Handler(Looper.getMainLooper());
        mHadnler.post(new Runnable() {
            public void run() {
                SplashActivity.this.dialog.dismiss();
                SplashActivity.this.finish();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        this.m_bPaused = true;
    }

    protected void onResume() {
        super.onResume();
        this.m_bPaused = false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        switch(keyCode) {
            case 4:
                Process.killProcess(Process.myPid());
                break;
            case 82:
                this.m_bSplashActive = false;
        }

        return true;
    }
}
