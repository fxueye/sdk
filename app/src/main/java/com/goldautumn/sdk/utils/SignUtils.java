//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.DisplayMetrics;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class SignUtils {
    public SignUtils() {
    }

    public static String getUnInstalledApkSignature(String apkPath) {
        String PATH_PackageParser = "android.content.pm.PackageParser";

        try {
            Class<?> pkgParserCls = Class.forName(PATH_PackageParser);
            Class[] typeArgs = new Class[]{String.class};
            Constructor<?> pkgParserCt = pkgParserCls.getConstructor(typeArgs);
            Object[] valueArgs = new Object[]{apkPath};
            Object pkgParser = pkgParserCt.newInstance(valueArgs);
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();
            typeArgs = new Class[]{File.class, String.class, DisplayMetrics.class, Integer.TYPE};
            Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage", typeArgs);
            valueArgs = new Object[]{new File(apkPath), apkPath, metrics, Integer.valueOf(64)};
            Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser, valueArgs);
            typeArgs = new Class[]{pkgParserPkg.getClass(), Integer.TYPE};
            Method pkgParser_collectCertificatesMtd = pkgParserCls.getDeclaredMethod("collectCertificates", typeArgs);
            valueArgs = new Object[]{pkgParserPkg, Integer.valueOf(64)};
            pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs);
            Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField("mSignatures");
            Signature[] info = (Signature[])packageInfoFld.get(pkgParserPkg);
            return info[0].toCharsString();
        } catch (Exception var13) {
            var13.printStackTrace();
            return null;
        }
    }

    public static String getInstalledApkSignature(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(64);
        Iterator iter = apps.iterator();

        while(iter.hasNext()) {
            PackageInfo packageinfo = (PackageInfo)iter.next();
            String thisName = packageinfo.packageName;
            if(thisName.equals(packageName)) {
                return packageinfo.signatures[0].toCharsString();
            }
        }

        return null;
    }
}
