package com.oliver.easy.utils;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by wangdong on 16-1-6.
 */
public class ResourceUtils {

    /**
     * 获取array.xml中的资源数组
     * @param context
     * @param arrayId
     * @return
     */
    public static int[] getResourceIdsFromArray(Context context, int arrayId) {
        TypedArray ar = context.getResources().obtainTypedArray(arrayId);
        int[] ids = new int[ar.length()];
        for(int index=0; index<ar.length(); index++) {
            ids[index] = ar.getResourceId(index, 0);
        }
        ar.recycle();
        return ids;
    }
}
