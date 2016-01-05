package com.oliver.easy.utils.log;

import android.util.Log;

import java.util.Hashtable;

/**
 * Created by wangdong on 16-1-5.
 */
public class EasyLogHelper {

    private static final String TAG = EasyLogHelper.class.getSimpleName();

    //private boolean mIsLoggerEnable = true;
    private String mClassName;

    private static Hashtable<String, EasyLogHelper> sLoggerTable;
    static {
        sLoggerTable = new Hashtable<String, EasyLogHelper>();
    }

    public static EasyLogHelper getLogger(String className) {
        EasyLogHelper logger = (EasyLogHelper)sLoggerTable.get(className);
        if(logger == null) {
            logger = new EasyLogHelper(className);
            sLoggerTable.put(className, logger);
        }
        return logger;
    }

    private EasyLogHelper (String name) {
        mClassName = name;
    }

    public void v(String log) {
        if(EasyLog.DEBUG){
            EasyLog.v(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
        }
    }

    public void d(String log) {
        if(EasyLog.DEBUG){
            EasyLog.d(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
        }
    }

    public void i(String log) {
        if(EasyLog.DEBUG){
            EasyLog.i(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
        }
    }

    public void i(String log, Throwable tr) {
        if(EasyLog.DEBUG){
            EasyLog.i(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
        }
    }

    public void w(String log) {
        if(EasyLog.DEBUG){
            EasyLog.w(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
        }
    }

    public void w(String log, Throwable tr) {
        if(EasyLog.DEBUG){
            EasyLog.w(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
        }
    }

    public void e(String log) {
        if(EasyLog.DEBUG){
            EasyLog.e(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
        }
    }

    public void e(String log, Throwable tr) {
        if(EasyLog.DEBUG){
            EasyLog.e(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
        }
    }
}
