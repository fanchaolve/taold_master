package com.bb.taold.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * 创建时间：2017/9/14 0014
 *
 * @author chaochao
 */

public class PayUtil {


    private Context context;
    private String merchantOutOrderNo = "";
    private String merid = "100100102";
    private String noncestr = "";
    private String orderMoney = "";
    private String orderTime = "";
    private Map<String,String> map;

    /**
     * @param context activity上下文
     * @param merchantOutOrderNo 商户订单号
     * @param merid 商户id
     * @param noncestr 随即参数
     * @param orderMoney 订单金额
     * @param orderTime 订单时间 yyyyMMddHHmmss
     */
    public PayUtil(Context context,String merchantOutOrderNo, String merid, String noncestr, String orderMoney, String orderTime){
        this.context = context;
        this.merchantOutOrderNo = merchantOutOrderNo;
        this.merid = merid;
        this.noncestr = noncestr;
        this.orderMoney = orderMoney;
        this.orderTime = orderTime;
    }
    public PayUtil(Map<String,String> map){
        this.map = map;
    }

    /**
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写,true:key转化成小写，false:不转化
     */
    private String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (!TextUtils.isEmpty(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    /**
     * 获取签名参数url
     * @return
     */
    public String getFinalPayUrl(){
        return Constants.EBJ_ALIPAY_URL+"?"+getSafeParams();
    }

    public void startPay(){
        Uri uri=Uri.parse("alipays://platformapi/startApp?appId=10000011&url="+Uri.encode(getFinalPayUrl()));
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    private Map<String,String> getParamsMap(){
        if(this.map!=null){
            return this.map;
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("merchantOutOrderNo",this.merchantOutOrderNo);
            map.put("merid",this.merid);
            map.put("noncestr",this.noncestr);
            map.put("orderMoney",this.orderMoney);
            map.put("orderTime",this.orderTime);
            return map;
        }
    }

    private String getSortParams(){
        Map<String,String> map = getParamsMap();
        String sortParams = null;
        if(map!=null){
            sortParams = formatUrlMap(map,false,false);
        }
        return sortParams;
    }

    private String getSortParamsWithKey(){
        return getSortParams()+"&key=bd3228d053dc4f02311ecc69";
    }

    private String getSignedParams(){

        return MD5Util.MD5(getSortParamsWithKey());
    }



    private String getSafeParams(){
        return getSortParams()+"&sign="+getSignedParams();
    }
}
