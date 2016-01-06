package com.oliver.easy.utils;

import org.json.JSONObject;

/**
 * Created by wangdong on 16-1-6.
 */
public class JsonUtils {

    public static String getJSONString(JSONObject obj, String key) {
        String result = "";
        try {
            result = obj.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
