//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.share;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.Data.ShareData;

public class QQShare {
    public QQShare() {
    }

    public void share(Context context, Activity activity, ShareData data) {
        Bundle bundle = new Bundle();
        bundle.putString("targetUrl", data.GetData("share_url"));
        bundle.putString("title", data.GetData("share_title"));
        bundle.putString("imageUrl", data.GetData("share_img_url"));
        bundle.putString("summary", data.GetData("share_msg"));
        bundle.putString("appName", data.GetData("app_name"));
        GAGameSDK.getQQ().shareToQQ(activity, bundle, GAGameSDK.shareListener);
    }
}
