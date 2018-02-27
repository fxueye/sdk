//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.util.Log;

public class GAGameSDKLog {
    public static final String TAG = "GAGame";
    private static boolean showLog = true;

    public static void setShowLog(boolean showLog) {
        showLog = showLog;
    }

    private GAGameSDKLog() {
    }

    public static void v(String msg) {
        if(showLog) {
            Log.v("GAGame", buildMessage(msg));
        }

    }

    public static void v(String msg, Throwable thr) {
        if(showLog) {
            Log.v("GAGame", buildMessage(msg), thr);
        }

    }

    public static void d(String msg) {
        if(showLog) {
            Log.d("GAGame", buildMessage(msg));
        }

    }

    public static void d(String msg, Throwable thr) {
        if(showLog) {
            Log.d("GAGame", buildMessage(msg), thr);
        }

    }

    public static void i(String msg) {
        if(showLog) {
            Log.i("GAGame", buildMessage(msg));
        }

    }

    public static void i(String msg, Throwable thr) {
        if(showLog) {
            Log.i("GAGame", buildMessage(msg), thr);
        }

    }

    public static void e(String msg) {
        if(showLog) {
            Log.e("GAGame", buildMessage(msg));
        }

    }

    public static void w(String msg) {
        if(showLog) {
            Log.w("GAGame", buildMessage(msg));
        }

    }

    public static void w(String msg, Throwable thr) {
        if(showLog) {
            Log.w("GAGame", buildMessage(msg), thr);
        }

    }

    public static void w(Throwable thr) {
        if(showLog) {
            Log.w("GAGame", buildMessage(""), thr);
        }

    }

    public static void e(String msg, Throwable thr) {
        if(showLog) {
            Log.e("GAGame", buildMessage(msg), thr);
        }

    }

    protected static String buildMessage(String msg) {
        StackTraceElement caller = (new Throwable()).fillInStackTrace().getStackTrace()[2];
        return caller.getClassName() + "." + caller.getMethodName() + "(): " + msg;
    }
}
