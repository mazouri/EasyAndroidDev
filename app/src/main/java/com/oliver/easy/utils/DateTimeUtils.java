package com.oliver.easy.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by wangdong on 16-1-6.
 */
public class DateTimeUtils {

    public static final long MILLS_IN_TWO_MINU = 2 * 60 * 1000;
    public static final long MILLS_IN_ONE_HOUR = 60 * 60 * 1000;
    public static final long MILLS_IN_ONE_DAY = MILLS_IN_ONE_HOUR * 24;


    public static String calendarToString(Context context, String format, long date) {
        return DateFormat.format(format, date).toString();
    }

    public static long getCalendarZeroHour(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long getCalendarZeroMinute(Calendar cal) {
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long getCalendarAddOneHour(Calendar cal) {
        cal.add(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 是否为黑天，以6时18时为界
     * @param currentTime
     * @return
     */
    public static boolean isNight(int currentTime) {
        if (currentTime < 0 || currentTime > 24) {
            throw new IllegalArgumentException("time is error:" + currentTime);
        }
        return currentTime >= 18 || currentTime <= 6;
    }

    /**
     * 日期之前的周期次数，周期单位为天：比如完成一个事物需要时间为distance，则before-today天可以完成index次
     * @param today 当前日期时间戳
     * @param before 之前日期时间戳
     * @param distance 周期
     * @return
     */
    public static int getDistanceDay(long today, long before, int distance) {
        int index = (int) ((before - today) / MILLS_IN_ONE_DAY / distance);
        if (before < today) {
            index--;
        }
        return index;
    }

    /**
     * 日期之前的周期次数，周期单位为小时
     * @param today
     * @param before
     * @param distance
     * @return
     */
    public static int getDistanceHour(long today, long before, int distance) {
        int index = (int) ((before - today) / MILLS_IN_ONE_HOUR / distance);
        if (before < today) {
            index--;
        }
        return index;
    }

    /**
     * 求两个日期相差天数
     *
     * @param startDay
     *            起始日期，格式yyyy-MM-dd
     * @param endDay
     *            终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String startDay, String endDay) {
        return ((java.sql.Date.valueOf(endDay)).getTime() - (java.sql.Date
                .valueOf(startDay)).getTime())
                / (MILLS_IN_ONE_DAY);
    }

    /**
     * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数
     *
     * @return int
     */
    public static int getInterval(String beginMonth, String endMonth) {
        int intBeginYear = Integer.parseInt(beginMonth.substring(0, 4));
        int intBeginMonth = Integer.parseInt(beginMonth.substring(beginMonth
                .indexOf("-") + 1));
        int intEndYear = Integer.parseInt(endMonth.substring(0, 4));
        int intEndMonth = Integer.parseInt(endMonth.substring(endMonth
                .indexOf("-") + 1));

        return ((intEndYear - intBeginYear) * 12)
                + (intEndMonth - intBeginMonth) + 1;
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的天数，以格式"dd"返回.
     *
     * @return 当前月中的某天dd
     */
    public static String getCurrentDay() {
        return getFormatCurrentTime("dd");
    }

    /**
     * 根据给定的格式，返回时间字符串。
     * <p>
     * 格式参照类描绘中说明.
     *
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
     *
     * @param date
     *            指定的日期
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 取得指定年月日的日期对象.
     *
     * @param year
     *            年
     * @param month
     *            月注意是从1到12
     * @param day
     *            日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * 取得指定分隔符分割的年月日的日期对象.
     *
     * @param args
     *            格式为"yyyy-MM-dd"
     * @param split
     *            时间格式的间隔符，例如“-”，“/”
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String args, String split) {
        String[] temp = args.split(split);
        int year = new Integer(temp[0]).intValue();
        int month = new Integer(temp[1]).intValue();
        int day = new Integer(temp[2]).intValue();
        return getDateObj(year, month, day);
    }

    /**
     * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
     *
     * @param dateStr
     *            日期描述
     * @param pattern
     *            日期模式
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 取得当前Date对象.
     *
     * @return Date 当前Date对象.
     */
    public static Date getDateObj() {
        Calendar c = new GregorianCalendar();
        return c.getTime();
    }

    /**
     *
     * @return 当前月份有多少天；
     */
    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
        int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据指定的年月 返回指定月份（yyyy-MM）有多少天。
     *
     * @param time yyyy-MM
     * @return 天数，指定月份的天数。
     */
    public static int getDaysOfCurMonth(final String time) {
        String[] timeArray = time.split("-");
        int curyear = new Integer(timeArray[0]).intValue(); // 当前年份
        int curMonth = new Integer(timeArray[1]).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        if (curMonth == 12) {
            return mArray[0];
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     *  返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getChinaDayOfWeek(String date){
        String[] weeks = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int week = getDayOfWeek(date);
        return weeks[week-1];
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *            "yyyy/MM/dd",或者"yyyy-MM-dd"
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String date) {
        String[] temp = null;
        if (date.indexOf("/") > 0) {
            temp = date.split("/");
        }
        if (date.indexOf("-") > 0) {
            temp = date.split("-");
        }
        return getDayOfWeek(temp[0], temp[1], temp[2]);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param year
     * @param month
     *            month是从1开始的12结束
     * @param day
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date
     *            给定的日期对象
     * @param amount
     *            需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static Date getDateAdd(Date date, int amount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date
     *            给定的日期对象
     * @param amount
     *            需要添加的天数，如果是向前的天数，使用负数就可以.
     * @param format
     *            输出格式.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }

    /**
     * 获取两个时间串时间的差值，单位为秒
     *
     * @param startTime
     *            开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime
     *            结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static long getDiff(String startTime, String endTime) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 获取小时/分钟/秒
     *
     * @param second
     *            秒
     * @return 包含小时、分钟、秒的时间字符串，例如3小时23分钟13秒。
     */
    public static String getHour(long second) {
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;

        return hour + "小时" + minute + "分钟" + sec + "秒";
    }

    /**
     *  计算两天之间有多少个周末（这个周末，指星期六和星期天，一个周末返回结果为2，两个为4，以此类推。）
     * （此方法目前用于统计司机用车记录。）
     * @param startDate
     *            开始日期 ，格式"yyyy/MM/dd"
     * @param endDate
     *            结束日期 ，格式"yyyy/MM/dd"
     * @return int
     */
    public static int countWeekend(String startDate, String endDate) {
        int result = 0;
        Date sdate = null;
        Date edate = null;
        sdate = getDateObj(startDate, "/"); // 开始日期
        edate = getDateObj(endDate, "/");// 结束日期
        // 首先计算出都有那些日期，然后找出星期六星期天的日期
        int sumDays = Math.abs(getDiffDays(startDate, endDate));
        int dayOfWeek = 0;
        for (int i = 0; i <= sumDays; i++) {
            dayOfWeek = getDayOfWeek(getDateAdd(sdate, i)); // 计算每过一天的日期
            if (dayOfWeek == 1 || dayOfWeek == 7) { // 1 星期天 7星期六
                result++;
            }
        }
        return result;
    }

    /**
     * 返回两个日期之间相差多少天。
     *
     * @param startDate
     *            格式"yyyy/MM/dd"
     * @param endDate
     *            格式"yyyy/MM/dd"
     * @return 整数。
     */
    public static int getDiffDays(String startDate, String endDate) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date sDate = ft.parse(startDate + " 00:00:00");
            Date eDate = ft.parse(endDate + " 00:00:00");
            diff = eDate.getTime() - sDate.getTime();
            diff = diff / 86400000;// 1000*60*60*24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) diff;
    }

    /**
     * 根据传入的时间 和当前的时间进行比较.
     * @param microsecond
     * 1分钟=60*1000
     * 60分钟=1小时=60*60*1000
     * 10小时=24*60*60*1000
     * 5天=5*24*60*60*1000
     * @return
     */
    public static String getTimeConversion(long microsecond)
    {
        long mDurtionTime=System.currentTimeMillis()-microsecond;
        if(mDurtionTime<60*1000)
        {
            return String.valueOf(Math.abs(mDurtionTime/1000))+"秒前";
        }else if(mDurtionTime<60*60*1000){
            return String.valueOf(mDurtionTime/(60*1000))+"分钟前";
        }else if (mDurtionTime<24*60*60*1000) {
            return String.valueOf(mDurtionTime/(60*60*1000))+"小时前";
        }else if(mDurtionTime<10*24*60*60*1000)
        {
            return String.valueOf(mDurtionTime/(24*60*60*1000))+"天前";
        }else {
            return getDateTime(microsecond);
        }
    }

    /**
     * 返回指定时间字符串。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getDateTime(long microsecond) {
        return getFormatDateTime(new Date(microsecond), "yyyy-MM-dd HH:mm:ss");
    }

    public static final SimpleDateFormat dateformat = new SimpleDateFormat("yyMMdd");
    public static final SimpleDateFormat timeformat = new SimpleDateFormat("HHmmss");
    public static final SimpleDateFormat datetimeformat = new SimpleDateFormat("yyMMddHHmmss");


    private static final SimpleDateFormat formaterYMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formaterYMDChinese = new SimpleDateFormat("yyyy年MM月dd日");
    private static final SimpleDateFormat formaterYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formaterYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat formaterYMDHMSChinese = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private static final SimpleDateFormat formaterYMDHMChinese = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

    private static final SimpleDateFormat formaterH = new SimpleDateFormat("HH");
    private static final SimpleDateFormat formaterHM = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat formaterHMS = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat formaterHMSChinese = new SimpleDateFormat("HH小时 mm分 ss秒");

    private static final SimpleDateFormat formaterYMDHMSFile = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    private static final SimpleDateFormat formaterMD = new SimpleDateFormat("MM月dd日");
    private static final SimpleDateFormat formaterMDHM = new SimpleDateFormat("MM月dd日HH:mm");

    /**
     * "MM-dd"
     */
    public static String getFormatedDateMDHM(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterMDHM.format(data);
    }

    /**
     * "MM-dd"
     */
    public static String getFormatedDateMD(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterMD.format(data);
    }

    /**
     * "yyyy-MM-dd"
     */
    public static String getFormatedDateYMD(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMD.format(data);
    }

    /**
     * "yyyy年MM月dd日"
     */
    public static String getFormatedDateYMDChinese(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDChinese.format(data);
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"
     */
    public static String getFormatedDateYMDHMS(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDHMS.format(data);
    }

    /**
     * yyyy_MM_dd_HH_mm_ss
     */
    public static String getFormatedDateYMDHMSFile(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDHMSFile.format(data);
    }

    /**
     * "yyyy-MM-dd HH:mm"
     */
    public static String getFormatedDateYMDHM(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDHM.format(data);
    }

    /**
     * "yyyy年MM月dd日  HH时mm分ss秒"
     */
    public static String getFormatedDateYMDHMSChinese(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDHMSChinese.format(data);
    }

    /**
     * "yyyy年MM月dd日  HH时mm分"
     */
    public static String getFormatedDateYMDHMChinese(long time) {
        if (time < 1) {
            return "";
        }
        Date data = new Date(time);
        return formaterYMDHMChinese.format(data);
    }

    /**
     * "HH:mm:ss"
     */
    public static String getFormatedDataHMS(long time) {
        if (time < 1) {
            return "";
        }
        return formaterHMS.format(time);
    }

    /**
     * "HH"
     */
    public static String getFormatedDataH(long time) {
        if (time < 1) {
            return "";
        }
        return formaterH.format(time);
    }


    /**
     * "HH:mm"
     */
    public static String getFormatedDataHM(long time) {
        if (time < 1) {
            return "";
        }
        return formaterHM.format(time);
    }

    //如果以上格式均不满足需求，可用下面方法自行定义
    /**
     *
     * @param sdate 原始日期格式
     * @param format 格式化后日期格式
     * @return
     */
    public static String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.sql.Date date = java.sql.Date.valueOf(sdate);
        String dateString = formatter.format(date);

        return dateString;
    }

    /**
     * "HH小时 mm分 ss秒"
     */
    public static String getFormatedDataHMSChinese(long time) {
        if (time < 1) {
            return "";
        }
        return formaterHMSChinese.format(time);
    }

    public static String DATE_YMD = "yyyy/M/d";
    public static String DATE_HM = "H:m";

    @SuppressLint("SimpleDateFormat")
    // to :yMd
    private static String formatCalendarToString(Calendar calendar, String ymdFormat) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(ymdFormat);
            String date = dateFormat.format(calendar.getTime());
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatCalendarToStringId(Calendar calendar) {
        return formatCalendarToString(calendar, DATE_YMD);
    }

    public static String getFormatLongToStringDateId(Context context, long date) {
        return DateFormat.format(DATE_YMD, date).toString();
    }

    public static String getFormatLongToStringTimeId(Context context, long date) {
        return DateFormat.format(DATE_HM, date).toString();
    }

    public static String getFormatCalendarToString(Calendar calendar, String ymdFormat) {
        return formatCalendarToString(calendar, ymdFormat);
    }

    @SuppressLint("SimpleDateFormat")
    // to :yMd
    public static String formatDateStringToStringId(String date, String oldYmdFormat) {
        try {
            if (oldYmdFormat.equals(DATE_YMD)) {
                return date;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(oldYmdFormat);
            Date dateDate = dateFormat.parse(date);
            dateFormat.applyPattern(DATE_YMD);
            date = dateFormat.format(dateDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static Calendar formatDateStringToCalendar(String date, String ymdFormat, TimeZone timeZone) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(ymdFormat);
            // dateFormat.setTimeZone(timeZone);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(date));
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    // to :hour
    public static int formatTimeStringToHour(String time, String hmFormat) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(hmFormat);
            return dateFormat.parse(time).getHours();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 当前时间转化为如Jan 18(th),2015格式
     * @return
     */
    public String getFormatDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMM dd'(th)',yyyy", Locale.ENGLISH);
            Date date = new Date();
            String temp = dateFormat.format(date);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将类2015年12月12日格式的时间转化为如Jan 18th,2015格式
     * @param time
     * @return
     */
    public static String formatTimeByStr(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = dateFormat.parse(time);
            dateFormat.applyPattern("MMMMMM dd'th',yyyy");
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将类2015年12月12日格式的时间转化为如Jan 18th格式
     * @param time
     * @return
     */
    public static String formatTimeByStrWithoutYear(String time) {
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
            Date date = simpleDateFormat.parse(time);
            simpleDateFormat.applyPattern("MMMMMM dd'th'");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将类2015年12月12日格式的时间转化为如2015.12.12格式
     * @param time
     * @return
     */
    public static String formatTimeByYMDStr(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = dateFormat.parse(time);
            dateFormat.applyPattern("yyyy.MM.dd");
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断是否为闰年
     * @param year
     * @return
     */
    public final static boolean isLeapYear(int year) {
        return (year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    public static int getYear2JavaYear(int year) {
        return year;
    }

    public static int getDay2JavaDay(int day) {
        return day;
    }

    public static int getJavaYear2Year(int year) {
        return year;
    }

    /**
     *MONTH加1的原因： public static final int MONTH 指示月份的 get 和 set 的字段数字。
     * 这是一个特定于日历的值。在格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0；
     * 最后一个月取决于一年中的月份数。 简单来说，因为这个值的初始值是0，
     * 因此我们要用它来表示正确的月份时就需要加1。
     *
     * 也就是 int month = n.get(Calendar.MONTH)+1;
     *
     * @param month
     * @return
     */
    public static int getJavaMonth2Month(int month) {
        return month + 1;
    }
    public static int getMonth2JavaMonth(int month) {
        return month - 1;
    }

    public static int getJavaDay2Day(int day) {
        return day;
    }

    /**
     * WEEK减1的原因： public static final int DAY_OF_WEEK get 和 set 的字段数字，
     * 指示一个星期中的某天。该字段可取的值为 SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY 观察代码，
     * 你会发现其实week是用来确定随后的字符串week1该如何截取的，我们知道DAY_OF_WEEK是获取当前日期是一周中的第几天，
     * 而一周从周日算起，因此当我们取得这个值之后，为了要正确的截取出随后的字符串week1中的那个中文字符，因此它需要减1来达到目的。
     * 你只要跟踪一下程序运行，观察变量的赋值就会理解它这样编写的用意了。
     *
     * Calendar.SUNDAY == 1
     * Calendar.MONDAY == 2
     *
     * @param weekday
     * @return
     */
    public static int getJavaWeekday2Weekday(int weekday) {
        return weekday - 1;
    }
    public static int getWeekday2JavaWeekday(int weekday) {
        return weekday + 1;
    }
}
