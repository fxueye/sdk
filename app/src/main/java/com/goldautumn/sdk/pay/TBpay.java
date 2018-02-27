//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.pay;

import android.app.Activity;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.app.EnvUtils.EnvEnum;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class TBpay {
    private static String payInfo;

    public TBpay() {
    }

    public void startPayTB(Activity activity, String getRust) {
        PayData strPaydata = new PayData();
        strPaydata.StringToData(getRust);
        GAGameSDKLog.e("status -----> " + GAGameSDKpay.getStatus());
        if(GAGameSDKpay.getStatus().intValue() == 1000) {
            GAGameSDKLog.e("sign------>" + strPaydata.GetData("sign"));

            try {
                payInfo = strPaydata.GetData("param");
                payInfo = payInfo + "&sign=" + URLEncoder.encode(strPaydata.GetData("sign"), "utf-8");
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            }

            EnvUtils.setEnv(EnvEnum.SANDBOX);
            PayTask alipay = new PayTask(activity);
            GAGameSDKLog.e("payInfo---->" + payInfo);
            Map<String, String> result = alipay.payV2(payInfo, true);
            GAGameSDKpay.setPayResult(new TBPayResult(result));
        }

    }
}
