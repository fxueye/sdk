//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;

public class ApkUtils {
    public ApkUtils() {
    }

    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;

        try {
            pm.getPackageInfo(packageName, 1);
            installed = true;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return installed;
    }

    public static String getSourceApkPath(Context context, String packageName) {
        if(TextUtils.isEmpty(packageName)) {
            return null;
        } else {
            try {
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
                return appInfo.sourceDir;
            } catch (NameNotFoundException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static void installApk(Context context, String apkPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
