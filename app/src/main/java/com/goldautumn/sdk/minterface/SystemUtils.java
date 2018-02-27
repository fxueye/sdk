//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SystemUtils {
    public static final char SEPARATOR = '\u0002';
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    private static final int NETWORK_TYPE_WIFI = -101;
    private static final int NETWORK_CLASS_WIFI = -101;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    private static final int NETWORK_CLASS_2_G = 1;
    private static final int NETWORK_CLASS_3_G = 2;
    private static final int NETWORK_CLASS_4_G = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_HSPAP = 15;
    private static final FileFilter CPU_FILTER = new FileFilter() {
        public boolean accept(File pathname) {
            String path = pathname.getName();
            if(!path.startsWith("cpu")) {
                return false;
            } else {
                for(int i = 3; i < path.length(); ++i) {
                    if(path.charAt(i) < 48 || path.charAt(i) > 57) {
                        return false;
                    }
                }

                return true;
            }
        }
    };

    public SystemUtils() {
    }

    public static String getAppVersionName(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return "";
        } else {
            String versionName = "";

            try {
                PackageManager pm = context.getPackageManager();
                PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                versionName = pi.versionName;
                if(versionName == null || versionName.length() <= 0) {
                    return "";
                }
            } catch (Exception var4) {
                GAGameSDKLog.e(var4.toString());
            }

            return versionName;
        }
    }

    public static String getModel() {
        return Build.MODEL == null?"NAN":Build.MODEL;
    }

    public static String getProduct() {
        return Build.MANUFACTURER;
    }

    public static String getAndroidID(Activity activity) {
        return Secure.getString(activity.getContentResolver(), "android_id");
    }

    public static String getSystemVersion() {
        return VERSION.RELEASE;
    }

    public static int getScreenWidthSize(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return 0;
        } else {
            WindowManager wm = (WindowManager)context.getSystemService("window");
            int screenWidth = wm.getDefaultDisplay().getWidth();
            if(screenWidth != 0) {
                return screenWidth;
            } else {
                DisplayMetrics dm = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(dm);
                return dm != null?dm.widthPixels:0;
            }
        }
    }

    public static int getScreenHeightSize(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return 0;
        } else {
            WindowManager wm = (WindowManager)context.getSystemService("window");
            int screenHeight = wm.getDefaultDisplay().getHeight();
            if(screenHeight != 0) {
                return screenHeight;
            } else {
                DisplayMetrics dm = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(dm);
                return dm != null?dm.heightPixels:0;
            }
        }
    }

    public static float getDty(Context context) {
        DisplayMetrics metric = GAGameTool.getMetrics(context);
        return metric.density;
    }

    public static boolean getRootAhth() {
        boolean bool = false;

        try {
            if(!(new File("/system/bin/su")).exists() && !(new File("/system/xbin/su")).exists()) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception var2) {
            ;
        }

        return bool;
    }

    public static String getMemoryFilesPath(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return "";
        } else {
            return context.getFilesDir().getAbsolutePath();
        }
    }

    public static List<PackageInfo> getAllApps(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return null;
        } else {
            List<PackageInfo> apps = new ArrayList();
            PackageManager pManager = context.getPackageManager();
            List<PackageInfo> paklist = pManager.getInstalledPackages(0);

            for(int i = 0; i < paklist.size(); ++i) {
                PackageInfo pak = (PackageInfo)paklist.get(i);
                ApplicationInfo var10001 = pak.applicationInfo;
                if((pak.applicationInfo.flags & 1) <= 0) {
                    apps.add(pak);
                }
            }

            return apps;
        }
    }

    public static PackageInfo getPackageInfo(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return null;
        } else {
            PackageManager pManager = context.getPackageManager();

            try {
                PackageInfo info = pManager.getPackageInfo(context.getPackageName(), 0);
                return info;
            } catch (NameNotFoundException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static boolean isAppOnForeground(Context context) {
        if(context == null) {
            GAGameSDKLog.e("context is null");
            return false;
        } else {
            ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if(appProcesses == null) {
                return false;
            } else {
                Iterator var4 = appProcesses.iterator();

                RunningAppProcessInfo appProcess;
                do {
                    if(!var4.hasNext()) {
                        return false;
                    }

                    appProcess = (RunningAppProcessInfo)var4.next();
                } while(!appProcess.processName.equals(context.getPackageName()) || appProcess.importance != 100);

                return true;
            }
        }
    }

    public static String getTotalCpuUsage() {
        float cpuRate = 0.0F;

        try {
            float totalCpuTime1 = (float)getTotalCpuTime();
            float idle1 = (float)getIdleCpuTime();
            Thread.sleep(1000L);
            float totalCpuTime2 = (float)getTotalCpuTime();
            float idle2 = (float)getIdleCpuTime();
            cpuRate = 100.0F * (totalCpuTime2 - totalCpuTime1 - (idle2 - idle1)) / (totalCpuTime2 - totalCpuTime1);
        } catch (Exception var5) {
            GAGameSDKLog.e(var5.toString());
        }

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format((double)cpuRate) + "%";
    }

    private static long getTotalCpuTime() {
        String[] cpuInfos = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        long totalCpu = Long.parseLong(cpuInfos[2]) + Long.parseLong(cpuInfos[3]) + Long.parseLong(cpuInfos[4]) + Long.parseLong(cpuInfos[6]) + Long.parseLong(cpuInfos[5]) + Long.parseLong(cpuInfos[7]) + Long.parseLong(cpuInfos[8]);
        return totalCpu;
    }

    private static long getIdleCpuTime() {
        String[] cpuInfos = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        long IdleCpu = Long.parseLong(cpuInfos[4]);
        return IdleCpu;
    }

    public static int getCurCpuFreq() {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        } catch (FileNotFoundException var19) {
            GAGameSDKLog.e(var19.toString());
        } catch (IOException var20) {
            GAGameSDKLog.e(var20.toString());
        } finally {
            if(fr != null) {
                try {
                    fr.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

            if(br != null) {
                try {
                    br.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return result;
    }

    public static String getCPUType() {
        return Build.CPU_ABI;
    }

    public static long getMemoryTotalMB(Context context) {
        return getTotalMemoryKB() / 1024L;
    }

    private static long getTotalMemoryKB() {
        String str1 = "/proc/meminfo";
        long initial_memory = 0L;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            String str2 = localBufferedReader.readLine();
            String[] arrayOfString = str2.split("\\s+");
            initial_memory = Long.valueOf(arrayOfString[1]).longValue();
            localBufferedReader.close();
        } catch (IOException var7) {
            GAGameSDKLog.e(var7.toString());
        }

        return initial_memory;
    }

    public static long getMemoryUsageMB(Context context) {
        long total = getTotalMemoryKB();
        long avail = getAvailMemoryKB(context);
        return (total - avail) / 1024L;
    }

    private static long getAvailMemoryKB(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService("activity");
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem / 1024L;
    }

    public static String getWifiMac(Context context) {
        String macAddress = "";

        try {
            WifiManager wifi = (WifiManager)context.getSystemService("wifi");
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if(!TextUtils.isEmpty(wifiMac)) {
                macAddress = wifiMac;
            } else {
                macAddress = "Get Mac Value Error";
            }
        } catch (Exception var5) {
            GAGameSDKLog.e(var5.toString());
        }

        return macAddress;
    }

    public static String getIMEI(Context context) {
        String phoneIMEI = "";

        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            String imei = tm.getDeviceId();
            if(!TextUtils.isEmpty(imei)) {
                phoneIMEI = imei;
            } else {
                phoneIMEI = "Get IMEI value Error";
            }
        } catch (Exception var4) {
            GAGameSDKLog.e(var4.toString());
        }

        return phoneIMEI;
    }

    public static String getIMSI(Context context) {
        String phoneIMSI = "";

        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            String imsi = tm.getSubscriberId();
            if(!TextUtils.isEmpty(imsi)) {
                phoneIMSI = imsi;
            } else {
                phoneIMSI = "Get IMSI value Error";
            }
        } catch (Exception var4) {
            GAGameSDKLog.e(var4.toString());
        }

        return phoneIMSI;
    }

    public static String getPhoneSN(Context context) {
        String phoneSN = "";

        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            String sn = tm.getSimSerialNumber();
            if(!TextUtils.isEmpty(sn)) {
                phoneSN = sn;
            } else {
                phoneSN = "Get SimSerialNumber Error";
            }
        } catch (Exception var4) {
            GAGameSDKLog.e(var4.toString());
        }

        return phoneSN;
    }

    public static String getPhoneNO(Context context) {
        String phoneNO = "";

        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            String sn = tm.getLine1Number();
            if(!TextUtils.isEmpty(sn)) {
                phoneNO = sn;
            } else {
                phoneNO = "Get SimSerialNumber Error";
            }
        } catch (Exception var4) {
            GAGameSDKLog.e(var4.toString());
        }

        return phoneNO;
    }

    public static String getTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        return createGmtOffsetString(true, true, tz.getRawOffset());
    }

    public static String createGmtOffsetString(boolean includeGmt, boolean includeMinuteSeparator, int offsetMillis) {
        int offsetMinutes = offsetMillis / '\uea60';
        char sign = 43;
        if(offsetMinutes < 0) {
            sign = 45;
            offsetMinutes = -offsetMinutes;
        }

        StringBuilder builder = new StringBuilder(9);
        if(includeGmt) {
            builder.append("GMT");
        }

        builder.append(sign);
        appendNumber(builder, 2, offsetMinutes / 60);
        if(includeMinuteSeparator) {
            builder.append(':');
        }

        appendNumber(builder, 2, offsetMinutes % 60);
        return builder.toString();
    }

    private static void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);

        for(int i = 0; i < count - string.length(); ++i) {
            builder.append('0');
        }

        builder.append(string);
    }

    public static String getCurTime() {
        Calendar c = Calendar.getInstance();
        String strTime = "";
        strTime = strTime + c.get(1) + "_";
        strTime = strTime + (c.get(2) + 1) + "_";
        strTime = strTime + c.get(5) + "_";
        strTime = strTime + c.get(11) + "_";
        strTime = strTime + c.get(12) + "_";
        strTime = strTime + c.get(13);
        return strTime;
    }

    public static String getSimOperatorName(Activity activity) {
        TelephonyManager telManager = (TelephonyManager)activity.getSystemService("phone");
        String operator = telManager.getSimOperator();
        String opName = "";
        if(operator != null) {
            if(!operator.equals("46000") && !operator.equals("46002") && !operator.equals("46007")) {
                if(operator.equals("46001")) {
                    opName = "中国联通";
                } else if(operator.equals("46003")) {
                    opName = "中国电信";
                }
            } else {
                opName = "中国移动";
            }
        }

        return opName;
    }

    public static String getNetworkType(Activity activity) {
        int networkClass = getNetworkClass(activity);
        String type = "未知";
        switch(networkClass) {
            case -101:
                type = "Wi-Fi";
                break;
            case -1:
                type = "无";
                break;
            case 0:
                type = "未知";
                break;
            case 1:
                type = "2G";
                break;
            case 2:
                type = "3G";
                break;
            case 3:
                type = "4G";
        }

        return type;
    }

    private static int getNetworkClassByType(int networkType) {
        switch(networkType) {
            case -101:
                return -101;
            case -1:
                return -1;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 2;
            case 13:
                return 3;
            default:
                return 0;
        }
    }

    private static int getNetworkClass(Activity activity) {
        int networkType = 0;

        try {
            NetworkInfo network = ((ConnectivityManager)activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if(network != null && network.isAvailable() && network.isConnected()) {
                int type = network.getType();
                if(type == 1) {
                    networkType = -101;
                } else if(type == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService("phone");
                    networkType = telephonyManager.getNetworkType();
                }
            } else {
                networkType = -1;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return getNetworkClassByType(networkType);
    }

    public static String getWifiSsid(Activity activity) {
        String ssid = "";

        try {
            NetworkInfo network = ((ConnectivityManager)activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if(network != null && network.isAvailable() && network.isConnected()) {
                int type = network.getType();
                if(type == 1) {
                    WifiManager wifiManager = (WifiManager)activity.getSystemService("wifi");
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    if(wifiInfo != null) {
                        ssid = wifiInfo.getSSID();
                        if(ssid == null) {
                            ssid = "";
                        }

                        ssid = ssid.replaceAll("\"", "");
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return ssid;
    }

    public static String getPhoneIp() {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();

            while(en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface)en.nextElement();
                Enumeration enumIpAddr = intf.getInetAddresses();

                while(enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception var4) {
            GAGameSDKLog.e(var4.toString());
        }

        return "";
    }

    public static String getCurLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language;
    }

    public static int getNumberOfCPUCores() {
        if(VERSION.SDK_INT <= 10) {
            return 1;
        } else {
            int cores = 0;

            try {
                cores = (new File("/sys/devices/system/cpu/")).listFiles(CPU_FILTER).length;
            } catch (SecurityException var2) {
                var2.printStackTrace();
            } catch (NullPointerException var3) {
                var3.printStackTrace();
            }

            return cores;
        }
    }
}
