package com.oliver.easy.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
/**
 * Created by wangdong on 16-1-11.
 *
 * 应用数据库导出工具类
 */
public class DatabaseExportUtils {

    private static final boolean DEBUG = true;
    private static final String TAG = "DatabaseExportUtils";

    /**
     * 开始导出数据 此操作比较耗时,建议在线程中进行
     *
     * @param context      上下文
     * @param targetFile   目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public boolean startExportDatabase(Context context, String targetFile,
                                       String databaseName) {
        if (DEBUG) {
        }
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            if (DEBUG) {
            }
            return false;
        }
        String sourceFilePath = Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/" + databaseName;
        String destFilePath = Environment.getExternalStorageDirectory()
                + (TextUtils.isEmpty(targetFile) ? (context.getPackageName() + ".db")
                : targetFile);
        boolean isCopySuccess = FileUtils
                .copyFile(sourceFilePath, destFilePath);
        if (DEBUG) {
            if (isCopySuccess) {

            } else {
            }
        }
        return isCopySuccess;
    }
}
