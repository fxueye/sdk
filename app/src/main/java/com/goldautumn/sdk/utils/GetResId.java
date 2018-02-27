//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.utils;

import android.content.Context;

public class GetResId {
    public GetResId() {
    }

    public static int getId(Context paramContext, String paramString1, String paramString2) {
        return paramContext.getResources().getIdentifier(paramString2, paramString1, paramContext.getPackageName());
    }
}
