package com.oliver.easy.utils;

import android.util.Base64;

public class EncodeUtils
{
    public EncodeUtils(){
    }

    public static String decode(String a){
        return new String(Base64.decode(a, Base64.DEFAULT));
    }

    public static String encode(String a){
        byte[] n = a.getBytes();
        return Base64.encodeToString(n, Base64.DEFAULT);
    }
}
