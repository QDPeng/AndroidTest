package com.lsp.test.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/3.
 */
public class ToastUtil {

    public static void showShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
