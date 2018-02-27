//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.floatview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.goldautumn.sdk.dialog.UserCenterDialog;
import com.goldautumn.sdk.minterface.GAGameResult;
import com.goldautumn.sdk.minterface.GAGameSDK;
import com.goldautumn.sdk.minterface.GAGameSDKLog;
import com.goldautumn.sdk.minterface.GAGameTool;
import com.goldautumn.sdk.utils.GetResId;

public class GAGameFloat {
    RelativeLayout mFloatLayout;
    LayoutParams wmParams;
    volatile boolean stop = true;
    WindowManager mWindowManager;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    private float mStartX;
    private float mStartY;
    private Context context;
    private Context context1;
    private int width;
    private int height;
    private int statusBarHeight;
    private boolean rightORlift = true;
    ImageView mFloatImg;
    ImageView button1;
    ImageView button2;
    ImageView mFloatBack;
    boolean bl = true;
    private boolean logout;
    private boolean initF = true;
    Thread mThread;
    Runnable mRunnable = new Runnable() {
        public void run() {
            try {
                GAGameSDKLog.e("float thread go");
                Thread.sleep(5000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                public void run() {
                    if(GAGameFloat.this.stop) {
                        if(GAGameFloat.this.bl) {
                            if(GAGameFloat.this.rightORlift) {
                                GAGameFloat.this.mFloatImg.setImageResource(GetResId.getId(GAGameFloat.this.context, "drawable", "gasdk_float_right"));
                            } else {
                                GAGameFloat.this.mFloatImg.setImageResource(GetResId.getId(GAGameFloat.this.context, "drawable", "gasdk_float_left"));
                            }

                            GAGameFloat.this.mFloatImg.setImageAlpha(122);
                        }
                    } else {
                        GAGameFloat.this.stop = true;
                    }

                }
            });
        }
    };
    OnClickListener mClickListener = new OnClickListener() {
        public void onClick(View v) {
            if(v.getId() == GetResId.getId(GAGameFloat.this.context, "id", "float_img")) {
                if(GAGameFloat.this.bl) {
                    if(GAGameFloat.this.mThread != null) {
                        GAGameFloat.this.mThread.interrupt();
                        GAGameFloat.this.mThread = null;
                    }

                    GAGameFloat.this.mFloatImg.setImageAlpha(255);
                    GAGameFloat.this.mFloatBack.setVisibility(0);
                    GAGameFloat.this.button1.setVisibility(0);
                    GAGameFloat.this.button2.setVisibility(0);
                    GAGameFloat.this.bl = false;
                } else {
                    GAGameFloat.this.mFloatBack.setVisibility(8);
                    GAGameFloat.this.button1.setVisibility(8);
                    GAGameFloat.this.button2.setVisibility(8);
                    GAGameFloat.this.bl = true;
                    GAGameFloat.this.mThread = new Thread(GAGameFloat.this.mRunnable);
                    GAGameFloat.this.mThread.start();
                }
            } else if(v.getId() == GetResId.getId(GAGameFloat.this.context, "id", "float_button1")) {
                UserCenterDialog.Instance().init(GAGameFloat.this.context1, 5);
                UserCenterDialog.Instance().show();
                GAGameFloat.this.mFloatBack.setVisibility(8);
                GAGameFloat.this.button1.setVisibility(8);
                GAGameFloat.this.button2.setVisibility(8);
                GAGameFloat.this.bl = true;
                GAGameFloat.this.mThread = new Thread(GAGameFloat.this.mRunnable);
                GAGameFloat.this.mThread.start();
            } else if(v.getId() == GetResId.getId(GAGameFloat.this.context, "id", "float_button2")) {
                GAGameFloat.this.mFloatBack.setVisibility(8);
                GAGameFloat.this.button1.setVisibility(8);
                GAGameFloat.this.button2.setVisibility(8);
                GAGameFloat.this.bl = true;
                GAGameSDK.logoutA(GAGameFloat.this.context);
                GAGameSDK.setlogoutResult();
            }

        }
    };

    public GAGameFloat() {
    }

    public static GAGameFloat Instance() {
        return GAGameFloat.SingletonHandler.instance;
    }

    public void setInitF(boolean bl) {
        this.initF = bl;
    }

    public void onCreate(Context context, Activity activity, int width, int height, boolean isScreen) {
        GAGameSDKLog.e("GAGameFloat onCreate: start");
        this.context = context.getApplicationContext();
        this.context1 = context;
        this.rightORlift = true;
        this.statusBarHeight = GAGameTool.getStatusHeight(context, activity);
        if(isScreen) {
            this.width = width;
            this.height = height;
        } else {
            this.width = height;
            this.height = width;
        }

        GAGameSDKLog.e("GAGameFloat onCreate: createFloatView");
        this.createFloatView();
    }

    private void createFloatView() {
        GAGameSDKLog.e("GAGameFloat createFloatView: start");
        this.wmParams = new LayoutParams();
        this.mWindowManager = (WindowManager)this.context.getApplicationContext().getSystemService("window");
        if(VERSION.SDK_INT > 18) {
            this.wmParams.type = 2005;
        } else {
            this.wmParams.type = 2003;
        }

        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 51;
        this.wmParams.x = 0;
        this.wmParams.y = 0;
        this.wmParams.width = -2;
        this.wmParams.height = -2;
        this.logout = true;
        GAGameSDKLog.e("GAGameFloat createFloatView: show");
        this.show(this.rightORlift);
    }

    public void show(boolean rightORlift) {
        GAGameSDKLog.e("GAGameFloat createFloatView: show");
        if(this.mWindowManager != null && this.mFloatLayout != null) {
            this.mWindowManager.removeView(this.mFloatLayout);
        }

        LayoutInflater inflater = LayoutInflater.from(this.context);
        GAGameSDKLog.e("GAGameFloat createFloatView: rightORlift");
        if(rightORlift) {
            this.mFloatLayout = (RelativeLayout)inflater.inflate(GetResId.getId(this.context, "layout", "gasdk_dialog_float_rigth"), (ViewGroup)null);
        } else {
            this.mFloatLayout = (RelativeLayout)inflater.inflate(GetResId.getId(this.context, "layout", "gasdk_dialog_float"), (ViewGroup)null);
        }

        GAGameSDKLog.e("GAGameFloat createFloatView: addView");

        try {
            this.mWindowManager.addView(this.mFloatLayout, this.wmParams);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        GAGameSDKLog.e("GAGameFloat createFloatView: setFloatInitSuccess");
        GAGameResult.setFloatInitSuccess(true);
        this.mFloatImg = (ImageView)this.mFloatLayout.findViewById(GetResId.getId(this.context, "id", "float_img"));
        this.mFloatBack = (ImageView)this.mFloatLayout.findViewById(GetResId.getId(this.context, "id", "float_back_img"));
        this.button1 = (ImageView)this.mFloatLayout.findViewById(GetResId.getId(this.context, "id", "float_button1"));
        this.button2 = (ImageView)this.mFloatLayout.findViewById(GetResId.getId(this.context, "id", "float_button2"));
        this.button1.setOnClickListener(this.mClickListener);
        this.button2.setOnClickListener(this.mClickListener);
        this.mFloatLayout.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        if(this.initF) {
            this.initUpdateViewPostition();
            this.initF = false;
        }

        this.mFloatImg.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                GAGameFloat.this.x = event.getRawX();
                GAGameFloat.this.y = event.getRawY() - (float)GAGameFloat.this.statusBarHeight;
                switch(event.getAction()) {
                    case 0:
                        GAGameFloat.this.mFloatImg.setImageResource(GetResId.getId(GAGameFloat.this.context, "drawable", "gasdk_float_1"));
                        GAGameFloat.this.mTouchStartX = event.getX();
                        GAGameFloat.this.mTouchStartY = event.getY();
                        GAGameFloat.this.mStartX = GAGameFloat.this.x;
                        GAGameFloat.this.mStartY = GAGameFloat.this.y;
                        break;
                    case 1:
                        GAGameFloat.this.mFloatImg.setImageResource(GetResId.getId(GAGameFloat.this.context, "drawable", "gasdk_float_1"));
                        GAGameFloat var10000 = GAGameFloat.this;
                        GAGameFloat.this.mTouchStartY = 0.0F;
                        var10000.mTouchStartX = 0.0F;
                        if(GAGameFloat.this.x - GAGameFloat.this.mStartX < 5.0F && GAGameFloat.this.y - GAGameFloat.this.mStartY < 5.0F && GAGameFloat.this.y - GAGameFloat.this.mStartY > -5.0F && GAGameFloat.this.x - GAGameFloat.this.mStartX > -5.0F) {
                            GAGameSDKLog.e("x" + GAGameFloat.this.x);
                            GAGameSDKLog.e("mStartX" + GAGameFloat.this.mStartX);
                            GAGameSDKLog.e("x - mStartX" + (GAGameFloat.this.x - GAGameFloat.this.mStartX));
                            GAGameSDKLog.e("y" + GAGameFloat.this.y);
                            GAGameSDKLog.e("mStartY" + GAGameFloat.this.mStartY);
                            GAGameSDKLog.e("y - mStartY" + (GAGameFloat.this.y - GAGameFloat.this.mStartY));
                            GAGameFloat.this.mClickListener.onClick(GAGameFloat.this.mFloatImg);
                            GAGameFloat.this.onClickUpdateViewPosition(event);
                        } else if(GAGameFloat.this.bl) {
                            GAGameFloat.this.updateViewPositionA(event);
                            if(GAGameFloat.this.mThread != null) {
                                GAGameFloat.this.mThread.interrupt();
                                GAGameFloat.this.mThread = null;
                            }

                            GAGameFloat.this.mThread = new Thread(GAGameFloat.this.mRunnable);
                            GAGameFloat.this.mThread.start();
                        }
                        break;
                    case 2:
                        if(GAGameFloat.this.bl) {
                            GAGameFloat.this.stop = false;
                            GAGameFloat.this.updateViewPosition();
                        }
                }

                return true;
            }
        });
        this.mThread = new Thread(this.mRunnable);
        this.mThread.start();
    }

    private void initUpdateViewPostition() {
        this.wmParams.x = this.height;
        this.wmParams.y = this.width / 2;
        this.mWindowManager.updateViewLayout(this.mFloatLayout, this.wmParams);
    }

    private void updateViewPosition() {
        this.wmParams.x = (int)(this.x - this.mTouchStartX);
        this.wmParams.y = (int)(this.y - this.mTouchStartY);
        GAGameSDKLog.e("wmParams.x:" + this.wmParams.x);
        GAGameSDKLog.e("wmParams.y:" + this.wmParams.y);
        this.mWindowManager.updateViewLayout(this.mFloatLayout, this.wmParams);
    }

    private void updateViewPositionA(MotionEvent event) {
        this.wmParams.x = (int)(this.x - this.mTouchStartX);
        if(GAGameTool.isScreenOriatationPortrait(this.context)) {
            if(this.x < (float)(this.width / 2)) {
                this.wmParams.x = 0;
                this.rightORlift = false;
            } else {
                if(this.width > this.height) {
                    this.wmParams.x = this.width;
                } else {
                    this.wmParams.x = this.height;
                }

                this.rightORlift = true;
            }
        } else if(this.x < (float)(this.height / 2)) {
            this.wmParams.x = 0;
            this.rightORlift = false;
        } else {
            if(this.width > this.height) {
                this.wmParams.x = this.width;
            } else {
                this.wmParams.x = this.height;
            }

            this.rightORlift = true;
        }

        this.show(this.rightORlift);
        this.mWindowManager.updateViewLayout(this.mFloatLayout, this.wmParams);
    }

    private void onClickUpdateViewPosition(MotionEvent event) {
        this.wmParams.x = (int)(this.x - this.mTouchStartX);
        if(GAGameTool.isScreenOriatationPortrait(this.context)) {
            if(this.x < (float)(this.width / 2)) {
                this.wmParams.x = 0;
                this.rightORlift = false;
            } else {
                if(this.width > this.height) {
                    this.wmParams.x = this.width;
                } else {
                    this.wmParams.x = this.height;
                }

                this.rightORlift = true;
            }
        } else if(this.x < (float)(this.height / 2)) {
            this.wmParams.x = 0;
            this.rightORlift = false;
        } else {
            if(this.width > this.height) {
                this.wmParams.x = this.width;
            } else {
                this.wmParams.x = this.height;
            }

            this.rightORlift = true;
        }

        this.mWindowManager.updateViewLayout(this.mFloatLayout, this.wmParams);
    }

    public void onDestroy() {
        if(this.mWindowManager != null) {
            this.mWindowManager.removeView(this.mFloatLayout);
        }

    }

    public void onPause() {
        GAGameSDKLog.e("Float onPause");
        if(this.mFloatImg != null) {
            GAGameSDKLog.e("Float onPause if");
            this.mFloatImg.setVisibility(8);
            this.mFloatBack.setVisibility(8);
            this.button1.setVisibility(8);
            this.button2.setVisibility(8);
        }

    }

    public void onResume() {
        GAGameSDKLog.e("Float onResume");
        if(this.logout) {
            if(this.mFloatImg != null) {
                GAGameSDKLog.e("Float onResume 1");
                this.mFloatImg.setVisibility(0);
            }

            if(!this.bl) {
                GAGameSDKLog.e("Float onResume 3");
                this.mFloatBack.setVisibility(0);
                this.button1.setVisibility(0);
                this.button2.setVisibility(0);
            }
        }

    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    private static class SingletonHandler {
        static final GAGameFloat instance = new GAGameFloat();

        private SingletonHandler() {
        }
    }
}
