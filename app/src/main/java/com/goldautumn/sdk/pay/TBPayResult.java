//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import android.text.TextUtils;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import java.util.Iterator;
import java.util.Map;

public class TBPayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public TBPayResult(Map<String, String> rawResult) {
        if(rawResult != null) {
            Iterator var3 = rawResult.keySet().iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                if(TextUtils.equals(key, "resultStatus")) {
                    this.resultStatus = (String)rawResult.get(key);
                    GAGameSDKLog.d("resultStatus:" + this.resultStatus);
                } else if(TextUtils.equals(key, "result")) {
                    this.result = (String)rawResult.get(key);
                    GAGameSDKLog.d("result:" + this.result);
                } else if(TextUtils.equals(key, "memo")) {
                    this.memo = (String)rawResult.get(key);
                    GAGameSDKLog.d("memo:" + this.memo);
                }
            }

        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }
}
