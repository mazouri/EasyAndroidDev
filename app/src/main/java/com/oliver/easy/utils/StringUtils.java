package com.oliver.easy.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangdong on 16-1-6.
 */
public class StringUtils {

    /**
     * 是否为手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 是否为邮箱地址
     * @param email
     * @return
     */
    public static boolean isEmailAddress(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 截取字符串，取“=”后面的内容
     * @param src 初始字符串，类：year=2015
     * @return 截取后的内容 2015
     */
    public static String cutString(String src) {
        if ((src.trim() == "") || (src == null))
            return "";

        return src.substring(src.indexOf("=") + 1);
    }

    public static int safeInteger(String intValue) {
        int value = 0;
        try {
            value = Integer.parseInt(intValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    // 判断是否转int类型
    public static boolean isFormatInteger(String str) {
        if (str != null && !str.equals("") && isGigital(str)) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     *            字符串
     * @return 如果字符串是数字返回ture，反正false
     */
    public static boolean isGigital(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isGigital = pattern.matcher(str);
        if (!isGigital.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是不是float型
     */
    public static boolean isFloat(String str) {
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        Matcher isFloat = pattern.matcher(str);
        if (isFloat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param url
     *            保存文件的文字
     * @return 文件名
     */
    public static String getFileName(String url) {
        String fileName = null;
        if (url != null && url.contains("/")) {
            String[] data = url.split("/");
            fileName = data[data.length - 1];
        }
        return fileName;
    }

    /**
     * @param style
     *            类型
     * @return 用逗号，或者分号截取字符串前两个(这个方法用于类型的字符串截取)
     */
    public static String get2InString(String style) {
        Pattern pattern = Pattern.compile("[,;]");
        String[] actors = pattern.split(style);
        StringBuffer buffer = new StringBuffer();
        if (actors.length <= 1) {
            buffer.append(actors[0]);
        } else if (actors.length == 2) {
            buffer.append(actors[0]);
            buffer.append(",");
            buffer.append(actors[1]);
        } else if (actors.length >= 3) {
            buffer.append(actors[0]);
            buffer.append(",");
            buffer.append(actors[1]);
            buffer.append(",");
            buffer.append(actors[2]);
        }
        return buffer.toString();
    }

    /**
     * @param str
     *            字符串
     * @return 字符串转化MD5
     */
    public static String calcMd5(String str) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(str.getBytes());
            return toHexString(algorithm.digest(), "");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param file
     *            文件
     * @return 文件转换MD5
     */
    public static String calcMd5(File file) {
        FileInputStream in = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer;
            byteBuffer = ch
                    .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            algorithm.update(byteBuffer);
            return toHexString(algorithm.digest(), "");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 上面的辅助类
    public static String toHexString(byte[] bytes, String separator) {
        StringBuilder hexString = new StringBuilder();
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        for (byte b : bytes) {
            hexString.append(hexDigits[b >> 4 & 0xf]);
            hexString.append(hexDigits[b & 0xf]);
        }
        return hexString.toString();
    }

    // 去掉字符串中的空格、回车、换行符、制表符
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String after = m.replaceAll("");
            return after;
        } else {
            return null;
        }
    }

    // 换域名
    public static String replaceRealmName(String newRealmName,
                                          String oldRealmName, String source) {
        if (oldRealmName == null) {
            return source;
        }
        StringBuffer bf = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(oldRealmName)) != -1) {
            bf.append(source.substring(0, index) + newRealmName);
            source = source.substring(index + oldRealmName.length());
            index = source.indexOf(oldRealmName);
        }
        bf.append(source);
        return bf.toString();
    }

    // 把字符串转换成UTF-8的格式
    public static String stringToUTF(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 把字符串转换成GBK的格式
    public static String stringToGBK(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLDecoder.decode(str, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 把字符串编码成GBK的格式
    public static String stringUTF8ToGBK(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(str, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取有颜色的文字，这里默认为橙色
     *
     * @param str
     *            文字内容
     * @return
     */
    public static String getHtmlColorString(String color, String str) {
        StringBuffer sb = new StringBuffer();
        sb.append("<font color='" + color + "'>");
        sb.append(str);
        sb.append("</font>");
        return sb.toString();
    }

    /**
     * 根据分数不同返回不同颜色的文字
     *
     * @return
     */
    public static String getRatingColorString(String vote) {
        StringBuffer sb = new StringBuffer();
        if (vote == null || vote.equals("") || !isFloat(vote)) {
            vote = "0";
        }
        int score = (int) Float.parseFloat(vote);
        switch (score) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append("<font color='" + "#f3ad07" + "'>");
                break;
            case 7:
                sb.append("<font color='" + "#ef8203" + "'>");
                break;
            case 8:
                sb.append("<font color='" + "#ff7510" + "'>");
                break;
            default:
                sb.append("<font color='" + "#fe4223" + "'>");
                break;
        }
        sb.append(vote);
        sb.append("</font>");
        return sb.toString();
    }

    // bt字节参考量
    private static final float SIZE_BT = 1024L;
    // KB字节参考量
    private static final float SIZE_KB = SIZE_BT * 1024.0f;
    // MB字节参考量
    private static final float SIZE_MB = SIZE_KB * 1024.0f;
    // GB字节参考量
    private static final float SIZE_GB = SIZE_MB * 1024.0f;
    // TB字节参考量
    private static final float SIZE_TB=SIZE_GB * 1024.0f;
    // BigDecimal四舍五入精度为2
    private static final int SACLE = 2;

    // 根据传入的字节数，返回对应的字符串
    public static String getReadableSize(long length) {
        if (length >= 0 && length < SIZE_BT) {
            // Math.round四舍五入
            return (double) (Math.round(length * 10) / 10.0) + "B";
        } else if (length >= SIZE_BT && length < SIZE_KB) {
            // //length/SIZE_BT+"KB";
            return (double) (Math.round((length / SIZE_BT) * 10) / 10.0) + "KB";
        } else if (length >= SIZE_KB && length < SIZE_MB) {
            // length/SIZE_KB+"MB";
            return (double) (Math.round((length / SIZE_KB) * 10) / 10.0) + "MB";
        } else if (length >= SIZE_MB && length < SIZE_GB) {
            // bigdecimal这个对象进行数据相互除
            BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "GB";
        } else {
            // bigdecimal这个对象进行数据相互除
            BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "TB";
        }
    }

    /**
     * 给数组元素之间添加链接符
     * 比如：array = {one, two, three, four}, separator = "<"
     *
     *     结果为字符串：one<two<three<four
     * @param array
     * @param separator
     * @param <T>
     * @return
     */
    public static <T> String join(T[] array, String separator) {
        StringBuilder sb = new StringBuilder();
        if(array != null && array.length > 0) {
            for(T item : array) {
                if(sb.length()>0 && !TextUtils.isEmpty(separator)) {
                    sb.append(separator);
                }
                sb.append(String.valueOf(item));
            }
        }
        return sb.toString();
    }

    /**
     * 按连接符分割整型字符串
     * 比如：one<two<three<four, separator = "<"
     *
     *     结果为数组：{one, two, three, four}
     *
     * @param idsText
     * @param separator
     * @return
     */
    public static Integer[] parseIds(String idsText, String separator) {
        List<Integer> ids = new ArrayList<Integer>();
        if(!TextUtils.isEmpty(idsText) && !TextUtils.isEmpty(separator)) {
            String[] segs = idsText.split(separator);
            try {
                for(String seg : segs) {
                    ids.add(Integer.valueOf(seg));
                }
            } catch(Exception e) {
                e.printStackTrace();
                ids.clear();
            }
        }
        return ids.size() > 0 ? ids.toArray(new Integer[ids.size()]) : null;
    }

    /**
     * is null or its length is 0
     *
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str    str
     * @return if string is null or its size is 0, return true, else return
     * false.
     */
    public static boolean isEmpty(CharSequence str) {

        return (str == null || str.length() == 0);
    }

    /**
     * is null or its length is 0 or it is made by space
     *
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str   str
     * @return if string is null or its size is 0 or it is made by space, return
     * true, else return false.
     */
    public static boolean isBlank(String str) {

        return (str == null || str.trim().length() == 0);
    }


}
