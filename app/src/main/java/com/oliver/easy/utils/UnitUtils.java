package com.oliver.easy.utils;

/**
 * Created by wangdong on 16-1-12.
 */
public class UnitUtils {

    //摄氏度转华氏度
    public static float c2f(float c) {
        float f = c*9/5+32;
        return f;
    }

    //华氏度转摄氏度
    public static float f2c(float f) {
        float c = (f-32)*5/9;
        return c;
    }

    //公里转英里
    public static double km2mi(double km) {
        double mi = km * 0.621;
        return mi;
    }

    //英里转公里
    public static double mi2km(double mi) {
        double km = mi / 0.621;
        return km;
    }

    //升转加仑
    public static double l2gal(double l) {
        double gal = l * 0.264;
        return gal;
    }

    //L/100km转mpg
    public static double l100km2mpg(double l100km) {
        double mpg = 235 / l100km;
        return mpg;
    }

    //mpg转L/100km
    public static double mpg2l100km(double mpg) {
        double l100km = 235 / mpg;
        return l100km;
    }
}
