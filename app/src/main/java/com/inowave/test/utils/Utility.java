package com.inowave.test.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;

import java.util.Map;

public class Utility {

    public static final String OBJECT_EXTRAS = "com.inowave.test.object";

    public static void launchActivity(Context mContext, Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(mContext, mClass);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public static boolean isEmpty(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Map<String, String> getUserHeader() {

        @SuppressLint({"NewApi", "LocalSuppress"}) Map<String, String> headerMap = new ArrayMap<>();
        headerMap.put("Accept", "application/vnd.github.v3+json");
        return headerMap;
    }


}
