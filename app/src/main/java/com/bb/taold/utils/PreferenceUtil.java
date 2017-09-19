package com.bb.taold.utils;

import android.content.Context;

/**
 * Created by fancl
 * 存数据类
 */

public class PreferenceUtil {
    //基本保存信息文件名
    public static final String KBAS_BASE_PRE="taold_base";

    /****以下是保存字段****/

    //保存的默认登录用户
    public static final String SESSION="session";
    public static final String PHONE="phoneNum";



    /**
     *
     * 存数据到SP
     @param context
     @param savedKey 关键字
     @param value    保存的值
     2015年11月10日
     void
     */
    public synchronized static void saveSharedPreference(Context context, String savedKey, String value){
        context.getSharedPreferences(KBAS_BASE_PRE, 0).edit().putString(savedKey, value).commit();
    }

    /**
     *
     * 从SP取数据
     @param context
     @param getKey  获取的值的键
     @return
     2015年11月10日
     String
     */
    public synchronized static String getSharedPreference(Context context,String getKey){
        String value="";
        value=context.getSharedPreferences(KBAS_BASE_PRE, 0).getString(getKey, "");
        return value;

    }

    public synchronized static  boolean bSharedPreference(Context context,String getKey){
        return  context.getSharedPreferences(KBAS_BASE_PRE,0).getBoolean(getKey,false);

    }

    public synchronized static void saveBSharedPreference(Context context, String savedKey, boolean value){
        context.getSharedPreferences(KBAS_BASE_PRE, 0).edit().putBoolean(savedKey, value).commit();
    }
    
}
