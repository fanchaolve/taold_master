package com.bb.taold.utils;

import android.content.Context;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }


    /**
     * 分转换为元.
     *
     * @param fen 分
     * @return 元
     */
    public static String fromFenToYuan(final String fen) {
        String yuan = "";
        final int MULTIPLIER = 100;
//        Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
//        Matcher matcher = pattern.matcher(fen);
//        if (matcher.matches()) {
        yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
//        }
        return yuan;
    }

    /**
     * 分转换为元.
     *
     * @param fen 分
     * @return 元
     */
    public static BigDecimal fromFenToYuanBd(final String fen) {
        final int MULTIPLIER = 100;
        return new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2);
    }


    /**
     * 元转换为分.
     *
     * @param yuan 元
     * @return 分
     */
    public static String fromYuanToFen(final String yuan) {
        String fen = "";
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");
        Matcher matcher = pattern.matcher(yuan);
        if (matcher.matches()) {
            try {
                NumberFormat format = NumberFormat.getInstance();
                Number number = format.parse(yuan);
                double temp = number.doubleValue() * 100.0;
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
                format.setGroupingUsed(false);
                // 设置返回数的小数部分所允许的最大位数
                format.setMaximumFractionDigits(0);
                fen = format.format(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("参数格式不正确!");
        }
        return fen;
    }

    /**
     * 元转换为分.
     *
     * @param yuan 元
     * @return 分
     */
    public static BigDecimal fromYuanToFen2(final String yuan) {
        final int MULTIPLIER = 100;
        return new BigDecimal(yuan).multiply(new BigDecimal(MULTIPLIER)).setScale(2, BigDecimal.ROUND_DOWN);
    }
}