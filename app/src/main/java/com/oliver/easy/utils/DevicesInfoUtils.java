package com.oliver.easy.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.oliver.easy.application.EasyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;

/**
 * Created by wangdong on 16-1-6.
 */
public class DevicesInfoUtils {
    private static final int WIFI=1;
    private static final int CMWAP=2;
    private static final int CMNET=3;

    /**
     * @return mac address
     */
    public static String getMacAddress(Context c) {
        String mMacAddress = "";
        try {
            WifiManager wfm = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wfi = wfm.getConnectionInfo();

            if (null != wfi && !TextUtils.isEmpty(wfi.getMacAddress())) {
                mMacAddress = wfi.getMacAddress();
                mMacAddress = mMacAddress.replace(":", "");
                return mMacAddress;
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @return boolean isNetworkConnected if connected true , other wise return
     * false
     */
    public static boolean isNetworkConnected(Context context) {
        boolean isNetworkAvailable = false;
        ConnectivityManager cwjManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        cwjManager.getActiveNetworkInfo();
        if (cwjManager.getActiveNetworkInfo() != null) {
            isNetworkAvailable = cwjManager.getActiveNetworkInfo().isAvailable();
        }
        return isNetworkAvailable;
    }

    /**
     * @return IpAddress
     */
    /*public static String getIpAddress() {
        String ipAddress = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ipAddress = "";
        }
        return ipAddress;
    }*/

    public static String getLocalIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String ipAddress = int2ip(ip);
        if (ipAddress.equals("") || ipAddress == null) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前的网络状态  -1：没有网络  1：WIFI网络 2：wap网络 3：net网络
     * @param context
     * @return
     */
    public static int getAPNType(Context context){
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return netType;
        }
        int nType = networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = CMNET;
            }
            else{
                netType = CMWAP;
            }
        }
        else if(nType==ConnectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }

    // 获取ip地区
    public static String getIpCountry() {
        String ipCountry = "460";
        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) EasyApp
                    .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephonyManager != null) {
                String IMSI = mTelephonyManager.getSubscriberId();
                if (IMSI != null && !IMSI.equals("") && IMSI.length() >= 3) {
                    // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
                    ipCountry = IMSI.substring(0, 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipCountry;
    }

    // 获取ip运营商
    public static String getIpName() {
        String ipName = null;
        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) EasyApp
                    .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephonyManager != null) {
                String IMSI = mTelephonyManager.getSubscriberId();
                if (IMSI != null && !IMSI.equals("") && IMSI.length() >= 5) {
                    // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
                    ipName = IMSI.substring(3, 5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipName;
    }

    // 获取ip基站
    public static String getIpBaseStation() {
        TelephonyManager telMgr = (TelephonyManager) EasyApp
                .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        int cid = 0;
        int lac = 0;
        try {
            if (telMgr != null) {
                GsmCellLocation gc = (GsmCellLocation) telMgr.getCellLocation();
                if (null == gc) {
                    return "0_0";
                }
                cid = gc.getCid();
                lac = gc.getLac();
            }
        } catch (Exception e) {
            if (telMgr != null) {
                CdmaCellLocation location = (CdmaCellLocation) telMgr
                        .getCellLocation();
                if (null == location) {
                    return "0_0";
                }
                lac = location.getNetworkId();
                cid = location.getBaseStationId();
                cid /= 16;
            }
        }
        return lac + "_" + cid;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 获取屏幕长宽
    public static int[] getScreenSizeArray(Activity activity) {
        int[] size = new int[2];
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
            size[0] = widthPixels;
            size[1] = heightPixels;
        }
        return size;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    // 获取新分辨率
    public static String getNewLcd(Activity activity) {
        StringBuffer buffer = new StringBuffer();
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
        }
        buffer.append(heightPixels);
        buffer.append("*");
        buffer.append(widthPixels);
        return buffer.toString();
    }

    // 屏幕密度
    public static float getdensity(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return density;
    }

    // 获取手机型号
    public static String getLocalModel() {
        String model = android.os.Build.MODEL;
        if (model == null) {
            model = "";
        }
        return model;
    }

    // 获取手机系统版本
    public static String getLocalSystemVersion() {
        String version = android.os.Build.VERSION.RELEASE;
        if (version == null) {
            version = "";
        }
        return version;

    }

    // 获取手机厂商
    public static String getLocalManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer == null) {
            manufacturer = "";
        }
        return manufacturer;
    }

    // 判断设备是否越狱
    public static boolean getIsJailBreak() {
        for (String str : new String[] { "/system/bin/", "/system/xbin/",
                "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/" }) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    // 获取手机硬件属性
    public static String[] getTotalHardwareMessage() {
        String result[] = new String[3];
        String str1 = "/proc/cpuinfo";
        String str2 = null;
        FileReader localFileReader = null;
        BufferedReader localBufferedReader = null;
        try {
            localFileReader = new FileReader(str1);
            localBufferedReader = new BufferedReader(localFileReader);
            while ((str2 = localBufferedReader.readLine()) != null) {
                if (str2.contains("Processor")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[0] = arrayOfString[1];
                            if (result[0].length() > 32 && result[0] != null) {
                                result[0] = result[0].substring(0, 32);
                            }
                        }
                    }
                }
                if (str2.contains("Features")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[1] = arrayOfString[1];
                            if (result[1].length() > 50 && result[1] != null) {
                                result[1] = result[1].substring(0, 50);
                            }
                        }
                    }
                }
                if (str2.contains("Hardware")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[2] = arrayOfString[1];
                            if (result[2].length() > 32 && result[2] != null) {
                                result[2] = result[2].substring(0, 32);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (localBufferedReader != null) {
                try {
                    localBufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (localFileReader != null) {
                try {
                    localFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 获取时区
    public static String getTimeArea() {
        return String.valueOf(
                TimeZone.getDefault().getOffset(new Date().getTime() / 1000))
                .toString();
    }

    // 获取useragent
    public static String getUserAgent(Context context) {
        String userAgent = null;
        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            userAgent = settings.getUserAgentString();
        }
        return userAgent;
    }


}
