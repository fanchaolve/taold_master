package com.bb.taold.utils;

import android.util.Log;

import com.bb.taold.MyApplication;
import com.bb.taold.api.GenApiHashUrl;
import com.bb.taold.bean.Tostring_TreeMap;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.utils
 * <p>
 * 说明：封装了服务端的系统参数和应用参数
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/7
 * <p>
 * ==============================================
 */

public class ParamUtils {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    private Tostring_TreeMap<String, String> params;

    public ParamUtils() {
        params = new Tostring_TreeMap<>();
        params.put("app_key", Constants.APP_KEY);
        params.put("sign_method", "SHA1");
        params.put("format", "json");
        params.put("timestamp", System.currentTimeMillis() / 1000 + "");
        params.put("v", "1.0");
        String session=PreferenceUtil.getSharedPreference(MyApplication.getInstance(), PreferenceUtil.SESSION);
        if(session == null || "".equalsIgnoreCase(session)){

        }else {
            params.put("session",session);
        }

    }

    //get请求参数封装
    public Request get_method(Request request) {
        HttpUrl url = request.url();
        String url_param = url.encodedQuery();
        Request.Builder requestBuilder = request.newBuilder();
        String[] url_params = splist(url_param, "&");//参数切割
        iterator(url_params);
        String sign = SignUtils.sign(params, Constants.SECRET);
        params.put("sign", sign);
        Log.i("fancl", "sign:" + sign);
        Log.i("fancl", "get_method:" + params.toString());
        requestBuilder.method(request.method(), null);
        requestBuilder.url(GenApiHashUrl.apiUrl + url.encodedPath() + "?" + params.toString());
        return requestBuilder.build();

    }

    //post请求参数封装
    public Request post_method(Request request) {
        HttpUrl url = request.url();
        String url_param = url.encodedQuery();
        Request.Builder requestBuilder = request.newBuilder();
        String[] key_value = splist(url_param, "=");//method添加
        params.put(key_value[0], key_value[1]);//切出method 添加到map

        if (request.body() != null && request.body() instanceof FormBody) {//formencode
            FormBody formBody = (FormBody) request.body();
            for (int i = 0; i < formBody.size(); i++) {//post filed字段添加到map
                params.put(formBody.name(i), formBody.value(i));
            }
        }
        String sign = SignUtils.sign(params, Constants.SECRET);
        params.put("sign", sign);

        Log.i("fancl", "sign:" + sign);
        Log.i("fancl", "post_method:" + params.toString());

        RequestBody body = generateMultipartRequestBody(MEDIA_TYPE_JSON, params);
        requestBuilder.url(GenApiHashUrl.apiUrl + url.encodedPath());
        requestBuilder.method(request.method(), body);
        return requestBuilder.build();
    }


    /**
     * 生成类是表单的请求体
     */
    private RequestBody generateMultipartRequestBody(MediaType type, Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            String key = it2.next();
            builder.append(key);
            builder.append("=");
            builder.append(map.get(key));
            builder.append("&");
        }

        builder.deleteCharAt(builder.length() - 1);
        Log.d("info", builder.toString());
        RequestBody body = RequestBody.create(type, builder.toString().getBytes());

        return body;
    }

    //切割
    private String[] splist(String value, String key) {
        if (value != null && key != null && value.indexOf(key) > -1) {
            return value.split(key);
        }
        return new String[]{};
    }

    //遍历添加到map
    private void iterator(String[] its) {
        if (its.length > 0 && params != null) {
            for (String s : its) {
                if (s.indexOf("=") > -1) {
                    String[] p = s.split("=");
                    params.put(p[0], p[1]);
                }
            }
        }

    }


}
