package com.lsp.test.utils;

import android.util.Log;

/**
 * Created by Administrator on 2015/10/3.
 */
public class LogUtil {
    public static boolean isDebug = true;
    public static final String TAG = "fc51";

    public static void i(String title, String info) {
        if (isDebug)
            Log.i(TAG, title + "--->" + info);
    }

    public static void e(String title, String info) {
        if (isDebug)
            Log.e(TAG, title + "--->" + info);
    }
}
