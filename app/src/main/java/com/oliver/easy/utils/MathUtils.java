package com.oliver.easy.utils;

import java.text.DecimalFormat;

/**
 * Created by wangdong on 16-1-12.
 */
public class MathUtils {

    public static String getDoubleDecimalFormat(String format, double value) {
        DecimalFormat decimalFormat=new DecimalFormat(format);   //比如".00" 返回两位小数
        return decimalFormat.format(value);
    }
}
