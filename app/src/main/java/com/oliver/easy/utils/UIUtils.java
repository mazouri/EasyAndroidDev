package com.oliver.easy.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

/**
 * Created by wangdong on 16-1-6.
 */
public class UIUtils {

    /**
     * activity之间的切换
     * @param context
     * @param klass
     */
    public static void startActivityByClass(Context context, Class<?> klass) {
        Intent intent = new Intent();
        intent.setClass(context, klass);
        context.startActivity(intent);
    }

    /**
     * 判断是否touch到view区域
     * @param view
     * @param touchX
     * @param touchY
     * @return
     */
    public static boolean touchInViewRect(View view, int touchX, int touchY) {
        if(view == null)
            return false;

        Rect viewRect = new Rect();
        view.getDrawingRect(viewRect);

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        viewRect.left = location[0];
        viewRect.top = location[1];
        viewRect.right += location[0];
        viewRect.bottom += location[1];

        return viewRect.contains(touchX, touchY);
    }

    public static boolean isApplicationShown(Context context, String packageName) {
        ComponentName cn = getTopActivity(context);
        if(cn != null && !TextUtils.isEmpty(packageName)) {
            return cn.getPackageName().equals(packageName);
        }
        return false;
    }

    public static ComponentName getTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (tasks != null) {
            return tasks.get(0).topActivity;
        }
        return null;
    }

    /**
     *
     * @param context
     * @return
     */
    public static boolean isForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 获取状态栏的高度
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 判断是否为连击
     *
     * @return  boolean
     */
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static void setViewLayoutParamsHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    public static void swapViewGroupChildren(ViewGroup viewGroup, View firstView, View secondView) {
        int firstIndex = viewGroup.indexOfChild(firstView);
        int secondIndex = viewGroup.indexOfChild(secondView);
        if (firstIndex < secondIndex) {
            viewGroup.removeViewAt(secondIndex);
            viewGroup.removeViewAt(firstIndex);
            viewGroup.addView(secondView, firstIndex);
            viewGroup.addView(firstView, secondIndex);
        } else {
            viewGroup.removeViewAt(firstIndex);
            viewGroup.removeViewAt(secondIndex);
            viewGroup.addView(firstView, secondIndex);
            viewGroup.addView(secondView, firstIndex);
        }
    }

    public static void postOnPreDraw(final View view, final Runnable runnable) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                runnable.run();
                return true;
            }
        });
    }
}
