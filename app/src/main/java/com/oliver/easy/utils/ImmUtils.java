package com.oliver.easy.utils;

import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wangdong on 16-1-6.
 */
public class ImmUtils {

    public static boolean isInputMethodOpen(InputMethodManager imm) {
        return imm.isActive();
    }

    public static void openInputMethod(View view, InputMethodManager imm) {
        if (!isInputMethodOpen(imm)) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    public static void hideInputMethod(IBinder token, InputMethodManager imm) {
        if(null == token || null == imm){
            return;
        }
        if (isInputMethodOpen(imm)) {
            imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
