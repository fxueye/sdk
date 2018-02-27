//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

public class GAGameResult {
    private boolean isLogin = false;
    private String token = "";
    private String userId = "";
    private String message = "";
    private boolean isInit = false;
    private static boolean floatInitSuccess = false;

    public GAGameResult() {
    }

    public static GAGameResult Instance() {
        return GAGameResult.SingletonHandler.instance;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isInit() {
        return this.isInit;
    }

    public void setInit(boolean isInit) {
        this.isInit = isInit;
    }

    public static boolean isFloatInitSuccess() {
        return floatInitSuccess;
    }

    public static void setFloatInitSuccess(boolean afloatInitSuccess) {
        floatInitSuccess = afloatInitSuccess;
    }

    public void setLoginResult(boolean isLogin, String token, String userId, String message) {
        this.isLogin = isLogin;
        this.token = token;
        this.userId = userId;
        this.message = message;
    }

    private static class SingletonHandler {
        static final GAGameResult instance = new GAGameResult();

        private SingletonHandler() {
        }
    }
}
