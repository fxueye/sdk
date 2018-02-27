//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.os.Looper;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.Data.ShareData;
import com.goldautumn.sdk.utils.GetResId;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WXShare {
    private IWXAPI api;

    public WXShare() {
    }

    public void wxShare(final Context context, final ShareData data) {
        (new Thread(new Runnable() {
            public void run() {
                Bitmap bm = WXShare.this.returnBitmap(data.GetData("share_img_url"));
                if(bm != null) {
                    final byte[] byteImg = WXShare.Bitmap2Bytes(bm);
                    GAGameSDKLog.d("b:" + byteImg.toString());
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        public void run() {
                            WXShare.this.share(context, data, byteImg);
                        }
                    });
                } else {
                    WXShare.this.share(context, data, (byte[])null);
                    GAGameSDKLog.e("bm is null");
                }

            }
        })).start();
    }

    public void share(Context context, ShareData data, byte[] b) {
        this.api = WXAPIFactory.createWXAPI(context, GAGameSDK.getWx().getAPP_ID());
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = data.GetData("share_url");
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = data.GetData("share_title");
        msg.description = data.GetData("share_msg");
        if(b != null) {
            msg.thumbData = b;
        } else {
            Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), GetResId.getId(context, "drawable", "app_icon"));
            msg.thumbData = Bitmap2Bytes(thumb);
        }

        Req req = new Req();
        req.transaction = this.buildTransaction("webpage");
        req.message = msg;
        req.scene = 1;
        GAGameSDKLog.d("api.sendReq(req);");
        this.api.sendReq(req);
    }

    private String buildTransaction(String type) {
        return type == null?String.valueOf(System.currentTimeMillis()):type + System.currentTimeMillis();
    }

    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException var7) {
            var7.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return bitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
