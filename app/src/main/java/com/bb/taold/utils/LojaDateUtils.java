/*
 * Copyright (C) 2015 Jiejing.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bb.taold.utils;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * use this utils to async time with server and client
 * Created by Loja on 2014/8/24.
 * descript:
 */
@SuppressWarnings("unused")
public class LojaDateUtils {
    public static final SimpleDateFormat YYYY_MM_DD_EEE_HH_MM_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd (EEE) HH:mm",
            Locale.CHINA);

    public static final SimpleDateFormat EEE_D_MMM_YYYY_HH_MM_SS_Z_FORMAT = new SimpleDateFormat(
            "EEE, d MMM yyyy HH:mm:ss z",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd",
            Locale.CHINA);
    public static final SimpleDateFormat YY_MM_DD_FORMAT = new SimpleDateFormat("yy-MM-dd",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_CN_FORMAT = new SimpleDateFormat(
            "yyyy年MM月dd日",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_DOT_FORMAT2 = new SimpleDateFormat(
            "yyyy.MM.dd HH:mm",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_DOT_FORMAT = new SimpleDateFormat(
            "yyyy.MM.dd",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DOT_FORMAT = new SimpleDateFormat(
            "yyyy.MM",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_FORMAT = new SimpleDateFormat(
            "yyyy年MM月",
            Locale.CHINA);
    public static final SimpleDateFormat MM_DD_CN_FORMAT = new SimpleDateFormat("MM月dd日",
            Locale.CHINA);
    public static final SimpleDateFormat HH_MM_SS_FORMAT = new SimpleDateFormat("HH:mm:ss",
            Locale.CHINA);
    public static final SimpleDateFormat HH_MM_FORMAT = new SimpleDateFormat("HH:mm",
            Locale.CHINA);
    public static final SimpleDateFormat MM_SS_FORMAT = new SimpleDateFormat("mm:ss",
            Locale.CHINA);
    public static final SimpleDateFormat MM_CN_FORMAT = new SimpleDateFormat("MM月",
            Locale.CHINA);
    public static final SimpleDateFormat MM_DD_CN_FORMAT_S = new SimpleDateFormat("MM/dd",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_NOT_ALL_FORMAT = new SimpleDateFormat(
            "yyyyMM", Locale.CHINA);
    public static final SimpleDateFormat M_CN_FORMAT = new SimpleDateFormat("M月",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_AND_MM_FORMAT = new SimpleDateFormat(
            "yyyy.MM", Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_FORMAT2 = new SimpleDateFormat("yyyy/MM/dd",
            Locale.CHINA);
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_CN_FORMAT = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm",
            Locale.CHINA);
    private static final long MILLIS_DAY = 24 * 60 * 60 * 1000;
    private static final long MILLIS_MINUTES = 60 * 1000;
    private static long serverTime = System.currentTimeMillis();
    private static long detaTime = 0;

    public static long getServerTime() {
        return serverTime;
    }

    public static void setServerTime(long serverTime) {
        LojaDateUtils.serverTime = serverTime;
        long localTime = System.currentTimeMillis();
        detaTime = serverTime - localTime;
    }

    public static long getNow() {
        return System.currentTimeMillis() + detaTime;
    }


    public static Date getNowDate() {
        return new Date(getNow());
    }

    public static Date parse(String dateString) {
        try {
            return YYYY_MM_DD_HH_MM_SS_FORMAT.parse(dateString);
        } catch (ParseException ignore) {
            ignore.printStackTrace();
        }
        try {
            return YYYY_MM_DD_FORMAT.parse(dateString);
        } catch (ParseException ignore) {
            ignore.printStackTrace();
        }
        try {
            return YY_MM_DD_FORMAT.parse(dateString);
        } catch (ParseException ignore) {
            ignore.printStackTrace();
        }
        try {
            return YYYY_MM_DD_CN_FORMAT.parse(dateString);
        } catch (ParseException ignore) {
            ignore.printStackTrace();
        }

        return null;
    }

    public static Date parse(String dateString, SimpleDateFormat format) {
        try {
            return format.parse(dateString);
        } catch (ParseException ignore) {
            ignore.printStackTrace();
        }
        return null;
    }

    public static long parseToMillis(String dateString, SimpleDateFormat format) {
        try {
            Date date = parse(dateString, format);
            if (date == null) {
                return 0;
            }
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * use default format {@link #YYYY_MM_DD_HH_MM_SS_FORMAT}
     *
     * @return {@link #YYYY_MM_DD_HH_MM_SS_FORMAT}
     */
    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS_FORMAT);
    }

    public static String format(long dateMillis, SimpleDateFormat format) {
        if (dateMillis <= 0) {
            return "";
        }
        return format(new Date(dateMillis), format);
    }

    public static String format(Long dateMillis, SimpleDateFormat format) {
        if (dateMillis == null) {
            return "";
        }
        return format((long) dateMillis, format);
    }

    public static String format(Date date, SimpleDateFormat format) {
        try {
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "wrong time";
    }

    /**
     * 用于跟数据库比较实际的时候格式化当前时间
     *
     * @return {@link #YYYY_MM_DD_HH_MM_SS_FORMAT}
     */
    public static String getFormatNow() {
        Date now = new Date(getNow());
        return format(now);
    }

    /**
     * 获取从现在往回推算day天的日期
     */
    public static Date getPassedFromNowDate(int day) {
        return new Date(getNow() - MILLIS_DAY * day);
    }

    /**
     * 获取从现在往回推算n天的日期，n为0到maxDay的随机数
     *
     * @param maxDay 随机数上限
     */
    public static Date getRandomPassedFromNowDate(int maxDay) {
        int d = (int) (Math.random() * maxDay);
        return getPassedFromNowDate(d);
    }

    public static boolean isToday(Date date) {
        Date now = getNowDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(now);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return isSameDay(calendar1, calendar2);
    }

    /**
     * 获取当前时间往后延迟delay毫秒的Date
     */
    public static Date getNowDelay(int delay) {
        return new Date(getNow() + delay);
    }

    public static int getDeferenceDay(Date expiration) {
        Date now = new Date(getNow());
        return getDeferenceDay(now, expiration);
    }

    public static int getDeferenceDay(Date date, Date now) {
        return (int) Math.abs((date.getTime() - now.getTime()) / (MILLIS_DAY)) + 1;
    }


    /**
     * 获取当前时间往前beforeMillis毫秒的时间值
     *
     * @param prevMillis 毫秒数
     * @return yyyy-MM-dd HH:mm:ss格式的时间
     */
    public static String getFormatPrev(long prevMillis) {
        return format(new Date(getNow() - prevMillis));
    }

    /**
     * 获取距离截止时间延迟delayDays天的剩余天数
     *
     * @param expiration 截止时间
     * @param delayDays  延迟时间(天)
     * @return 剩余天数
     */
    public static int getIntervalDaysDelay(Date expiration, int delayDays) {
        return getIntervalDays(getNow(), expiration.getTime() + delayDays * MILLIS_DAY);
    }

    /**
     * 获取距离截止时间的剩余天数
     *
     * @param expiration 截止时间
     * @return 剩余天数
     */
    public static int getIntervalDays(Date expiration) {
        return getIntervalDays(getNow(), expiration.getTime());
    }

    public static int getIntervalDays(long time1, long time2) {
        if (time2 <= time1) {
            return 0;
        }
        long interval = time2 - time1;
        int days = (int) (interval / MILLIS_DAY);
        if (interval % MILLIS_DAY == 0) {
            return days;
        }
        return days + 1;
    }

    /**
     * 获取过去时间表述语
     *
     * @param date 过去时间点
     * @return 过去多长时间的描述
     */
    public static String getPassedTimeTip(Date date) {
        if (date == null) {
            return "刚才";
        }

        int minutes = (int) Math.abs((date.getTime() - System.currentTimeMillis()) / (60 * 1000.0f)) + 1;
        if (minutes < 5) {
            return "刚才";
        } else if (minutes < 60) {
            return (String.format("%d分钟前", minutes));
        } else {
            minutes = minutes / 60 + 1;
            if (minutes < 24) {
                return (String.format("%d小时前", minutes));
            } else {
                minutes = minutes / 24 + 1;
                return (String.format("%d天前", minutes));
            }
        }
    }

    /**
     * 获取date时间点前hours小时的时间
     */
    public static Date getPrevHourDate(int hours, Date date) {
        if (hours > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -hours);
            return calendar.getTime();
        }

        return null;
    }

    /**
     * 获取date时间点前days天的时间
     */
    public static Date getPrevDayDate(int days, Date date) {
        if (days > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -days);
            return calendar.getTime();
        }

        return null;
    }


    /**
     * 判定这两天是否为同一天
     */
    public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR) && calendar1.get(
                Calendar.YEAR) == calendar2.get(
                Calendar.YEAR);
    }

    /**
     * 判定这两天是否为同一天
     */
    public static boolean isSameDay(long time1, long time2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(time1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(time2);
        return isSameDay(calendar1, calendar2);
    }

    /**
     * 自定义上午
     * 凌晨0点到中午12点
     */
    public static boolean isMorning(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 0 && hour < 12;
    }

    /**
     * 自定义下午
     * 中午12点到18点
     */
    public static boolean isAfternoon(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 12 && hour < 18;
    }

    /**
     * 自定义晚上
     * 晚上18点到24点
     */
    public static boolean isNight(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 18 && hour < 24;
    }

    /**
     * 获取9：00格式的时间
     */
    public static String getTimeInDay(long time) {
        return format(time, HH_MM_FORMAT);
    }


    public static int getPassedYear(long jobBeginTime) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(jobBeginTime);
        int beginYear = calendar.get(Calendar.YEAR);
        return nowYear - beginYear + 1;
    }

    public static long getMinutes(long previewTime) {
        return previewTime / MILLIS_MINUTES;
    }

    public static String getComfortInterval(long beginTime, long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endTime - beginTime);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (year > 0) {
            return year + "年" + month + "个月";
        } else {
            return month + "个月";
        }
    }

    public static String getWeekString(int week) {
        if (week == Calendar.SUNDAY) {
            return "周日";
        } else if (week == Calendar.MONDAY) {
            return "周一";
        } else if (week == Calendar.TUESDAY) {
            return "周二";
        } else if (week == Calendar.WEDNESDAY) {
            return "周三";
        } else if (week == Calendar.THURSDAY) {
            return "周四";
        } else if (week == Calendar.FRIDAY) {
            return "周五";
        } else if (week == Calendar.SATURDAY) {
            return "周六";
        }
        return "星期";
    }


    public static long getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getNow());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    @NonNull public static Calendar getTodayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getNow());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static void clearBelowHour(@NonNull Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static long clearBelowHour(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        clearBelowHour(calendar);
        return calendar.getTimeInMillis();
    }

    public static long getCalendarDayId(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static long getCalendarMonthId(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH);
    }


    public static String formatSchedule(long beginTime, long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beginTime);
        int beginYear = calendar.get(Calendar.YEAR);
        int beginDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(endTime);
        if ((calendar.get(Calendar.YEAR) == beginYear) && (calendar.get(Calendar.DAY_OF_YEAR) == beginDay)) {
            return format(beginTime, YYYY_MM_DD_EEE_HH_MM_FORMAT) + "-" + format(endTime, HH_MM_FORMAT);
        }
        return format(beginTime, YYYY_MM_DD_EEE_HH_MM_FORMAT) + "-" + format(endTime, YYYY_MM_DD_EEE_HH_MM_FORMAT);
    }

    public static String formatSchedule2(long beginTime, long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beginTime);
        int beginYear = calendar.get(Calendar.YEAR);
        int beginDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(endTime);
        if ((calendar.get(Calendar.YEAR) == beginYear) && (calendar.get(Calendar.DAY_OF_YEAR) == beginDay)) {
            return format(beginTime, YYYY_MM_DD_FORMAT)
                    + "\n" + format(beginTime, HH_MM_FORMAT)
                    + "-" + format(endTime, HH_MM_FORMAT);
        }
        return format(beginTime, YYYY_MM_DD_HH_MM_FORMAT) + "\n-" + format(endTime, YYYY_MM_DD_HH_MM_FORMAT);
    }


    public static String formatScheduleShort(long beginTime, long endTime) {
        return format(beginTime, HH_MM_FORMAT)
                + "-" + format(endTime, HH_MM_FORMAT);
    }

    public static Calendar getTomorrowCalendar() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        tomorrow.set(Calendar.MILLISECOND, 0);
        return tomorrow;
    }

    public static int getOnlyMinutes(long beginTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beginTime);
        return calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY) * 60;
    }


    /**
     * 获取往回推N个月时间戳
     *
     * @return
     */
    public static long getMonthPassedFromNow(int month) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(getNow());
        calendar.add(Calendar.MONTH, -month);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long clearDateMillisecond(long time) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(time);
        clearBelowHour(calendar);
        return calendar.getTimeInMillis();
    }


    public static long monthOfLastDate(long time) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long monthOfFirstDate(long time) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    public static int getDateMonth(long time) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDateYear(long time) {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    public static int getWeekDay(int weekDay) {
        if (weekDay == 1) {
            return 8;
        }
        return weekDay;
    }
}
