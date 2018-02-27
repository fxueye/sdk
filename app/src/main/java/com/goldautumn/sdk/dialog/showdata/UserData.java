//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.dialog.showdata;

public class UserData {
    private static ShowData showData;
    private static PostData postData;

    public UserData() {
    }

    public static void init() {
        showData = new ShowData();
        postData = new PostData();
    }

    public static ShowData getShowData() {
        return showData;
    }

    public static void setShowData(ShowData showData) {
        showData = showData;
    }

    public static PostData getPostData() {
        return postData;
    }

    public static void setPostData(PostData postData) {
        postData = postData;
    }
}
