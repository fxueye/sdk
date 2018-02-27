//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.goldautumn.sdk.minterface.Data.BaseData;
import com.goldautumn.sdk.utils.ApkUtils;
import com.goldautumn.sdk.utils.PatchUtils;
import com.goldautumn.sdk.utils.SignUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GAGameSDKUpdateManager {
    private Context mContext;
    private String platformName;
    private String appVersion;
    private String packageName;
    private String androidVersion;
    private int androidSDK;
    private String checkUrl;
    private String updateMsg = "发现最新游戏版本";
    private String apkUrl;
    private String patchUrl;
    private String curUrl;
    private String serverApkMd5Value;
    private Dialog noticeDialog;
    private Dialog downloadDialog;
    private static final String savePath;
    private static final String saveFileName;
    private static final String patchedFilePathName;
    private static final String apkPatchPath;
    private ProgressBar mProgress;
    private TextView mMsg;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private static final int UPDATE_YES = 1;
    private static final int UPDATE_NO = 0;
    private int progress;
    private int apkSize;
    private Thread downLoadThread;
    private Thread checkUpdateThread;
    private boolean interceptFlag = false;
    private ProgressDialog mProgressDialog;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 1:
                    GAGameSDKUpdateManager.this.mMsg.setText("安装包" + GAGameSDKUpdateManager.this.apkSize + "MB,下载" + GAGameSDKUpdateManager.this.progress + "%");
                    break;
                case 2:
                    GAGameSDKUpdateManager.this.mMsg.setText("下载完成");
                    if(!GAGameSDKUpdateManager.this.curUrl.isEmpty()) {
                        if(GAGameSDKUpdateManager.this.curUrl.equals(GAGameSDKUpdateManager.this.patchUrl)) {
                            GAGameSDKLog.i("curUrl:" + GAGameSDKUpdateManager.this.curUrl + "patchUrl:" + GAGameSDKUpdateManager.this.patchUrl);
                            (GAGameSDKUpdateManager.this.new PatchApkTask()).execute(new String[0]);
                        } else {
                            GAGameSDKUpdateManager.this.installApk(GAGameSDKUpdateManager.saveFileName);
                        }
                    }
            }

        }
    };
    private Handler checkHandler = new Handler() {
        public void handleMessage(Message msg) {
            GAGameSDKLog.i("Check Handler msg=" + msg.what);
            switch(msg.what) {
                case 1:
                    GAGameSDKUpdateManager.this.showNoticeDialog();
                case 0:
                default:
            }
        }
    };
    private Runnable mcheckUpdateRunnable = new Runnable() {
        public void run() {
            try {
                String checkUpdate = GAGameSDKUpdateManager.this.checkUrl + GAGameSDKUpdateManager.this.platformName + "/CheckVersion/?version=" + GAGameSDKUpdateManager.this.appVersion + "&packageName=" + GAGameSDKUpdateManager.this.packageName + "&AndroidVersion=" + GAGameSDKUpdateManager.this.androidVersion + "&AndroidSDK=" + GAGameSDKUpdateManager.this.androidSDK;
                GAGameSDKLog.i("Check update URL: url=" + checkUpdate);
                String response = GAGameTool.mHttpGet(checkUpdate);
                GAGameSDKLog.i("Apk Url=" + response);
                BaseData updateData = new BaseData();
                updateData.StringToData(response);
                int updateCode = updateData.GetInt("code");
                if(updateCode == 1) {
                    GAGameSDKUpdateManager.this.apkUrl = updateData.GetData("msg");
                    GAGameSDKUpdateManager.this.checkHandler.sendEmptyMessage(1);
                } else {
                    GAGameSDKUpdateManager.this.checkHandler.sendEmptyMessage(0);
                }
            } catch (Exception var5) {
                GAGameSDKLog.e("Check URL Exception:" + var5.toString());
            }

        }
    };
    private Runnable mdownApkRunnable = new Runnable() {
        public void run() {
            try {
                URL url = new URL(GAGameSDKUpdateManager.this.curUrl);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                GAGameSDKUpdateManager.this.apkSize = length / 1024 / 1024;
                InputStream is = conn.getInputStream();
                File file = new File(GAGameSDKUpdateManager.savePath);
                if(!file.exists()) {
                    file.mkdir();
                }

                String apkFile = !GAGameSDKUpdateManager.this.curUrl.isEmpty() && GAGameSDKUpdateManager.this.curUrl == GAGameSDKUpdateManager.this.patchUrl?GAGameSDKUpdateManager.apkPatchPath:GAGameSDKUpdateManager.saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);
                int count = 0;
                byte[] buf = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    GAGameSDKUpdateManager.this.progress = (int)((float)count / (float)length * 100.0F);
                    GAGameSDKUpdateManager.this.mHandler.sendEmptyMessage(1);
                    if(numread <= 0) {
                        GAGameSDKUpdateManager.this.mHandler.sendEmptyMessage(2);
                        break;
                    }

                    fos.write(buf, 0, numread);
                } while(!GAGameSDKUpdateManager.this.interceptFlag);

                fos.close();
                is.close();
            } catch (MalformedURLException var12) {
                var12.printStackTrace();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }
    };

    static {
        savePath = Environment.getExternalStorageDirectory() + File.separator + "temp" + File.separator;
        saveFileName = savePath + "GameUpdateRelease.apk";
        patchedFilePathName = savePath + "PatchedUpdateRelease.apk";
        apkPatchPath = savePath + "patch.apk";
    }

    public GAGameSDKUpdateManager(Context context, String platformName, String checkUrl) {
        this.mContext = context;
        this.androidSDK = VERSION.SDK_INT;
        this.androidVersion = VERSION.RELEASE;
        this.packageName = this.mContext.getPackageName();
        this.appVersion = this.getVersion(this.mContext);
        this.platformName = platformName;
        this.checkUrl = checkUrl;
        this.mProgressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialog.setProgressStyle(0);
        this.mProgressDialog.setMessage("doing..");
        this.mProgressDialog.setCancelable(false);
        GAGameSDKLog.i("Update manager init:checkUrl=" + this.checkUrl + ";platform=" + this.platformName + "appVersion=" + this.appVersion);
    }

    public void checkUpdateInfo() {
        this.checkUpdate();
    }

    public void downloadApk(String downloadURL) {
        if(downloadURL.startsWith("http")) {
            this.apkUrl = downloadURL;
            this.showNoticeDialog();
        }

    }

    private void showNoticeDialog() {
        Builder builder = new Builder(this.mContext);
        builder.setTitle("游戏版本更新");
        builder.setMessage(this.updateMsg);
        builder.setPositiveButton("下载", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GAGameSDKUpdateManager.this.showDownloadDialog();
            }
        });
        builder.setNegativeButton("以后再说", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        this.noticeDialog = builder.create();
        this.noticeDialog.show();
    }

    private void showDownloadDialog() {
        Builder builder = new Builder(this.mContext);
        builder.setTitle("软件版本更新");
        this.mMsg = new TextView(this.mContext);
        this.mMsg.setGravity(1);
        this.mMsg.setTextSize(18.0F);
        builder.setView(this.mMsg);
        builder.setNegativeButton("取消", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GAGameSDKUpdateManager.this.interceptFlag = true;
            }
        });
        this.downloadDialog = builder.create();
        this.downloadDialog.show();
        this.downloadApk();
    }

    private void checkUpdate() {
        this.checkUpdateThread = new Thread(this.mcheckUpdateRunnable);
        this.checkUpdateThread.start();
    }

    private void downloadApk() {
        this.downLoadThread = new Thread(this.mdownApkRunnable);
        this.downLoadThread.start();
    }

    private void installApk(String filePathName) {
        File apkfile = new File(filePathName);
        if(apkfile.exists()) {
            Intent i = new Intent("android.intent.action.VIEW");
            i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
            this.mContext.startActivity(i);
        }
    }

    public String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (Exception var3) {
            var3.printStackTrace();
            return "unknow";
        }
    }

    private void showShortToast(String text) {
        Toast.makeText(this.mContext, text, 0).show();
    }

    public class PatchApkTask extends AsyncTask<String, Void, Integer> {
        private static final int PATCH_SUCCESS = 1;
        private static final int PATCH_FAIL_SIGN = -1;
        private static final int PATCH_FAIL_ERROR = -2;
        private static final int PATCH_FAIL_GET_SOURCE = -3;
        private long mBeginTime;
        private long mEndTime;

        public PatchApkTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            GAGameSDKUpdateManager.this.mProgressDialog.show();
            this.mBeginTime = System.currentTimeMillis();
        }

        protected Integer doInBackground(String... arg0) {
            String oldApkSource = ApkUtils.getSourceApkPath(GAGameSDKUpdateManager.this.mContext, GAGameSDKUpdateManager.this.packageName);
            if(!TextUtils.isEmpty(oldApkSource)) {
                GAGameSDKLog.i("patch begin");
                Log.e("GDEGameApkUpdate", "patch begin");
                int patchResult = PatchUtils.patch(oldApkSource, GAGameSDKUpdateManager.patchedFilePathName, GAGameSDKUpdateManager.apkPatchPath);
                GAGameSDKLog.i("patch finished");
                if(patchResult == 0) {
                    String signatureNew = SignUtils.getUnInstalledApkSignature(GAGameSDKUpdateManager.patchedFilePathName);
                    String signatureSource = SignUtils.getInstalledApkSignature(GAGameSDKUpdateManager.this.mContext, GAGameSDKUpdateManager.this.packageName);
                    return !TextUtils.isEmpty(signatureNew) && !TextUtils.isEmpty(signatureSource) && signatureNew.equals(signatureSource)?Integer.valueOf(1):Integer.valueOf(-1);
                } else {
                    return Integer.valueOf(-2);
                }
            } else {
                return Integer.valueOf(-3);
            }
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(GAGameSDKUpdateManager.this.mProgressDialog.isShowing()) {
                GAGameSDKUpdateManager.this.mProgressDialog.dismiss();
            }

            this.mEndTime = System.currentTimeMillis();
            GAGameSDKUpdateManager.this.showShortToast("升级耗时: " + (this.mEndTime - this.mBeginTime) + "ms");
            switch(result.intValue()) {
                case -3:
                    GAGameSDKUpdateManager.this.showShortToast("无法获取packageName为" + GAGameSDKUpdateManager.this.packageName + "的源APK文件！");
                    break;
                case -2:
                    GAGameSDKUpdateManager.this.showShortToast("APK合成失败");
                    break;
                case -1:
                    GAGameSDKUpdateManager.this.showShortToast("APK合成失败，签名不一致");
                case 0:
                default:
                    break;
                case 1:
                    GAGameSDKUpdateManager.this.showShortToast("APK已合成成功：" + GAGameSDKUpdateManager.patchedFilePathName);

                    try {
                        String patchedApkMd5Value = MD5Util.getFileMD5String(new File(GAGameSDKUpdateManager.patchedFilePathName));
                        if(patchedApkMd5Value.equals(GAGameSDKUpdateManager.this.serverApkMd5Value)) {
                            GAGameSDKUpdateManager.this.installApk(GAGameSDKUpdateManager.patchedFilePathName);
                        } else {
                            GAGameSDKUpdateManager.this.curUrl = GAGameSDKUpdateManager.this.apkUrl;
                            GAGameSDKUpdateManager.this.showNoticeDialog();
                        }
                    } catch (IOException var3) {
                        var3.printStackTrace();
                    }

                    GAGameSDKUpdateManager.this.installApk(GAGameSDKUpdateManager.patchedFilePathName);
            }

        }
    }
}
