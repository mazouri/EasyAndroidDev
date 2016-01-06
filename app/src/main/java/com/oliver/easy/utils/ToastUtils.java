package com.oliver.easy.utils;

import android.content.Context;
import android.widget.Toast;

import com.oliver.easy.application.EasyApp;

/**
 * Created by wangdong on 16-1-6.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(String toast) {
        if (null == mToast) {
            Context appContext = EasyApp.getInstance()
                    .getApplicationContext();
            mToast = Toast.makeText(appContext, toast, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(toast);
        }
        mToast.show();
    }

    public static void showToast(int stringID) {
        Context appContext = EasyApp.getInstance()
                .getApplicationContext();
        showToast(appContext.getString(stringID));
    }
}
