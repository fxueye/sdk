//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import com.goldautumn.sdk.minterface.Data.PayTypeData;
import com.goldautumn.sdk.utils.GetResId;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GAGameTool {
    private static int a;
    private static final int TIMEOUT_CONNECTION = 5000;
    private static InputMethodManager imm;
    private static long lastClickTime;
    public static boolean bl;

    public GAGameTool() {
    }

    public static boolean checkPeopleName(String name) {
        boolean b = false;
        String msgReg = "^[一-龥]+";
        if(name != null) {
            Pattern pattern = Pattern.compile(msgReg);
            Matcher matcher = pattern.matcher(name);
            b = matcher.matches();
        }

        return b;
    }

    public static void buttonTime(final Context context, final String str, final int time, final Button button) {
        (new Thread(new Runnable() {
            public void run() {
                (new Handler(Looper.getMainLooper())).post(new Runnable() {
                    public void run() {
                        button.setBackgroundResource(GetResId.getId(context, "drawable", "gasdk_button_false"));
                    }
                });
                button.setClickable(false);
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int i = time;
                    public void run() {
                        --this.i;
                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.post(new Runnable() {
                            public void run() {
                                button.setText("" + i);
                                if(i == 0) {
                                    button.setBackgroundResource(GetResId.getId(context, "drawable", "gasdk_button3_rel"));
                                    button.setText(str);
                                    button.setClickable(true);
                                    timer.cancel();
                                }

                            }
                        });
                    }
                }, 1000L, 1000L);
            }
        })).start();
    }

    public static int checkUsernameorPassword(String username, String password) {
        int check = 1;
        String usernameReg = "^[a-zA-Z0-9]{5,20}$";
        String passwordReg = "^[a-zA-Z0-9]{6,20}$";
        if(username != null) {
            Pattern pattern = Pattern.compile(usernameReg);
            Matcher matcher = pattern.matcher(username);
            boolean b = matcher.matches();
            if(!b) {
                check = 2;
            } else if(password != null) {
                pattern = Pattern.compile(passwordReg);
                matcher = pattern.matcher(password);
                b = matcher.matches();
                if(!b) {
                    check = 3;
                }
            }
        }

        return check;
    }

    public static boolean checkMsg(String msg) {
        boolean b = false;
        String msgReg = "^[0-9]{6,6}$";
        if(msg != null) {
            Pattern pattern = Pattern.compile(msgReg);
            Matcher matcher = pattern.matcher(msg);
            b = matcher.matches();
        }

        return b;
    }

    public static boolean checkPhone(String phone) {
        boolean b = false;
        String msgReg = "^[0-9]{11,11}$";
        if(phone != null) {
            Pattern pattern = Pattern.compile(msgReg);
            Matcher matcher = pattern.matcher(phone);
            b = matcher.matches();
        }

        return b;
    }

    public static void setUsernameLogin(Context context, String username, String password, String userType, String isLogin, String spKey) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context) + spKey, '耀');
        Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        if(spKey.equals("sp_file_name")) {
            editor.putString("usertype", userType);
            editor.putString("isLogin", isLogin);
        }

        editor.commit();
    }

    public static String getUsernameLogin(Context context, String key, String spKey) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context) + spKey, '耀');
        String value = "";
        value = sp.getString(key, "");
        return value;
    }

    public static void setUsernamesorPassword(String username, Context context) {
        String[] mUsernames = new String[5];
        String[] usernames = getSharedPreference("usernames", context);
        boolean bl = false;
        if(usernames != null) {
            int i;
            for(i = 0; i <= usernames.length - 1; ++i) {
                mUsernames[i] = usernames[i];
            }

            for(i = 0; i <= usernames.length - 1; ++i) {
                if(usernames[i].equals(username)) {
                    a = i;
                    bl = true;
                    break;
                }
            }

            if(bl) {
                for(i = a; i > 0; --i) {
                    mUsernames[i] = mUsernames[i - 1];
                }

                mUsernames[0] = username;
            } else if(mUsernames != null) {
                if(mUsernames[mUsernames.length - 1] != null && !mUsernames[mUsernames.length - 1].isEmpty() && !mUsernames[mUsernames.length - 1].equals("null")) {
                    for(i = mUsernames.length - 1; i > 0; --i) {
                        mUsernames[i] = mUsernames[i - 1];
                    }

                    mUsernames[0] = username;
                } else {
                    for(i = 0; i <= mUsernames.length - 1; ++i) {
                        if(mUsernames[i] == null || mUsernames[i].isEmpty() || mUsernames[i].equals("null")) {
                            for(int j = i; j > 0; --j) {
                                mUsernames[j] = mUsernames[j - 1];
                            }

                            mUsernames[0] = username;
                            break;
                        }
                    }
                }
            }

            setSharedPreference("usernames", mUsernames, context);
        }

    }

    public static void setSharedPreference(String key, String[] values, Context context) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context) + "usernames", 0);
        if(values != null && values.length > 0) {
            String[] var9 = values;
            int var8 = values.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                String value = var9[var7];
                str = str + value;
                str = str + regularEx;
            }

            Editor et = sp.edit();
            et.putString(key, str);
            et.commit();
        }

    }

    public static String[] getSharedPreference(String key, Context context) {
        String regularEx = "#";
        String[] str = null;
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context) + "usernames", 0);
        String values = sp.getString(key, "");
        str = values.split(regularEx);
        return str;
    }

    public static void immDismiss(View v) {
        imm = (InputMethodManager)v.getContext().getSystemService("input_method");
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = ((Activity)context).getWindowManager();
        wm.getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static int getStatusHeight(Context context, Activity activity) {
        int statusHeight = 0;

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return statusHeight;
    }

    public static String getPackageName(Context context) {
        String packageNames = "";

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            packageNames = info.packageName;
        } catch (NameNotFoundException var3) {
            var3.printStackTrace();
        }

        return packageNames;
    }

    public static String getFromAssets(Context context, String _in_fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(_in_fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";

            String Result;
            for(Result = ""; (line = bufReader.readLine()) != null; Result = Result + line) {
                ;
            }

            return Result;
        } catch (Exception var6) {
            var6.printStackTrace();
            return "";
        }
    }

    public static String getIMEI(Context context) {
        String phoneIMEI = "";

        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
            @SuppressLint("MissingPermission")
            String imei = tm.getDeviceId();
            if(!TextUtils.isEmpty(imei)) {
                phoneIMEI = imei;
            } else {
                phoneIMEI = "Get IMEI value Error";
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return phoneIMEI;
    }

    public static String getUrl(HashMap<String, String> params, int code, String values, int i) {
        GAGameSDKLog.d("getUrl: code=" + code + "i=" + i);
        String url = "";
        if(code == 1) {
            GAGameSDKLog.d("getUrl: getUserURL=" + Finaldata.getUserURL().toString());
            if(i >= Finaldata.getUserURL().length) {
                bl = false;
            } else {
                url = Finaldata.getUserURL()[i] + "/users/" + values;
                GAGameSDKLog.d("getUrl: code=" + code + ", url=" + url);
            }

            GAGameSDKLog.e("URL_USER_CODE:" + url);
        } else if(code == 2) {
            if(i >= Finaldata.getPayURL().length) {
                bl = false;
            } else {
                url = Finaldata.getPayURL()[i] + "/pay/" + values;
            }

            GAGameSDKLog.e("URL_PAY_CODE:" + url);
        } else if(code == 3) {
            if(i >= Finaldata.getEventURL().length) {
                bl = false;
            } else {
                url = Finaldata.getEventURL()[i] + "/event/" + values;
            }

            GAGameSDKLog.e("URL_EVENT_CODE:" + url);
        }

        if(params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;

            while(it.hasNext()) {
                String key = (String)it.next();
                String value = (String)params.get(key);
                if(sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }

                sb.append(key);
                sb.append("=");
                sb.append(value);
            }

            url = url + sb.toString();
        }

        return url;
    }

    public static String mHttpGet(String url) {
        return mHttpGet(url, 0);
    }

    public static String mHttpGet(String url, int retry) {
        GAGameSDKLog.d("mHttpGet: start");
        GAGameSDKLog.d("mHttpGet: url=" + url);
        String result = "";
        URL myUrl = null;
        URLConnection rulConnection = null;
        HttpURLConnection httpUrlConnection = null;
        url = url.trim().replace(" ", "");
        int time = 0;
        bl = true;

        do {
            GAGameSDKLog.d("mHttpGet: url=" + url + ", retry=" + time);

            try {
                if(time != 0) {
                    Thread.sleep(1000L);
                }

                myUrl = new URL(url);
                rulConnection = myUrl.openConnection();
                httpUrlConnection = (HttpURLConnection)rulConnection;
                httpUrlConnection.setDoOutput(false);
                httpUrlConnection.setDoInput(true);
                httpUrlConnection.setUseCaches(false);
                httpUrlConnection.setRequestMethod("GET");
                httpUrlConnection.setReadTimeout(5000);
                httpUrlConnection.setConnectTimeout(5000);
                httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
                if(httpUrlConnection.getResponseCode() == 200) {
                    bl = false;
                    GAGameSDKLog.d("mHttpGet: url=" + url + ", retry=" + time + ", ResponseCode=200");
                    InputStream is = httpUrlConnection.getInputStream();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }

                    is.close();
                    os.close();
                    result = new String(os.toByteArray());
                    GAGameSDKLog.d("mHttpGet: url=" + url + ", retry=" + time + ", result=" + result);
                    break;
                }

                GAGameSDKLog.e("URLget getResponseCode:" + httpUrlConnection.getResponseCode());
            } catch (Exception var14) {
                GAGameSDKLog.e("mHttpGet: url=" + url + ", msg=" + var14.getMessage());
                var14.printStackTrace();
            } finally {
                ++time;
            }
        } while(time < retry);

        return result;
    }

    public static String getCurTime() {
        Calendar c = Calendar.getInstance();
        String strTime = "";
        strTime = strTime + c.get(1) + "-";
        strTime = strTime + (c.get(2) + 1) + "-";
        strTime = strTime + c.get(5) + " ";
        strTime = strTime + c.get(11) + ":";
        strTime = strTime + c.get(12) + ":";
        strTime = strTime + c.get(13);
        return strTime;
    }

    public static String isUIThread() {
        return Looper.myLooper() != Looper.getMainLooper()?"Not UI Thread!!!!":"UI Thread!!!!";
    }

    public static void startGetPayType() {
        GAGameSDKLog.w(isUIThread());
        int ia = 0;
        bl = true;

        PayTypeData params;
        String getRust;
        for(getRust = ""; bl; ++ia) {
            params = new PayTypeData();
            params.SetData("appId", Finaldata.getAppid());
            String url = getUrl(params.GetHashMap(), 2, "get_pay_channel", ia);
            GAGameSDKLog.i("url=" + url);
            getRust = mHttpGet(url);
        }

        params = new PayTypeData();
        params.StringToData(getRust);
        if(params.GetData("status").equals("1000")) {
            PayTypeData data = new PayTypeData();
            data.StringToData(params.GetData("data"));
            String channel = data.GetData("channel");
            String[] channels = channel.split(",");
            Finaldata.setChannels(channels);

            for(int i = 0; i < channels.length; ++i) {
                GAGameSDKLog.e("channels(" + i + ")---->" + channels[i]);
            }
        }

        GAGameSDKLog.i("pay channel ok");
    }

    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if(time - lastClickTime < 500L) {
            return false;
        } else {
            lastClickTime = time;
            return true;
        }
    }

    public static String getAdid(Context context) {
        String android_gid = "";

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            android_gid = "" + appInfo.metaData.get("android_gid");
            GAGameSDKLog.d("android_gid:" + android_gid);
            return android_gid;
        } catch (Exception var4) {
            var4.printStackTrace();
            return "0";
        }
    }

    public static void switchConfigDownloar(final String[] url, final GAGameTool.ConfigLoaderCallBack callBack) {
        (new Thread(new Runnable() {
            public void run() {
                String controllerMessage = "";
                if(url != null) {
                    int i = 0;
                    GAGameTool.bl = true;

                    while(GAGameTool.bl) {
                        String switchConfigUrl = url[i];
                        GAGameSDKLog.i("url=" + switchConfigUrl);
                        controllerMessage = GAGameTool.mHttpGet(switchConfigUrl);
                        ++i;
                        if(i == url.length) {
                            GAGameTool.bl = false;
                        }
                    }
                }

                Finaldata.setPayUserURL(controllerMessage);
                if(Finaldata.getPayURL() != null) {
                    callBack.onSuesses();
                } else {
                    callBack.onFail();
                }

            }
        })).start();
    }

    public interface ConfigLoaderCallBack {
        void onSuesses();

        void onFail();
    }
}
