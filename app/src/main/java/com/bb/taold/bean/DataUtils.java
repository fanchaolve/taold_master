package com.bb.taold.bean;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/14
 * <p>
 * ==============================================
 */

public class DataUtils {

    public static Map<String ,String > getMap(){
        Map<String ,String > map=new LinkedHashMap<>();
        map.put("初中及以下","10");
        map.put("专科","20");
        map.put("高中","30");
        map.put("本科","40");
        map.put("硕士","50");
        map.put("博士及以上","60");
        map.put("未知","99");
        return map;
    }

    public static Map<String ,String > getShipsMap(){
        Map<String ,String > map=new LinkedHashMap<>();
        map.put("父母","10");
        map.put("配偶","20");
        map.put("兄弟姐妹","30");
        map.put("朋友","70");
        map.put("同学","80");
        return map;
    }
}
