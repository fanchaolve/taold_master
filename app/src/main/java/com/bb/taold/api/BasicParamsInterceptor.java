package com.bb.taold.api;

import android.util.Log;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * Created by Administrator on 2016/12/1.
 */

public class BasicParamsInterceptor implements Interceptor {



    //public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        HttpUrl url = request.url();
        String params = url.encodedQuery();





//
//        JSONObject jsonObject = new JSONObject();
//        if (request.body() instanceof FormBody) {
//            FormBody oldFormBody = (FormBody) request.body();
//            for (int i = 0; i < oldFormBody.size(); i++) {
//                try {
//                    jsonObject.put(oldFormBody.name(i), oldFormBody.value(i));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            try {
//                jsonObject = new JSONObject(bodyToString(request.body()));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        String sign = MD5Util.MD5(jsonObject.toString() + GenApiHashUrl.md5_key).toLowerCase();
////        treeMap.put("input", jsonObject.toString());
////        treeMap.put("sign", sign);
////        treeMap.put("method",params.substring(7,params.indexOf('&')));
//
//        JSONObject js = new JSONObject();
//        try {
//            js.put("input", jsonObject.toString());
//            js.put("sign", sign);
//            js.put("method", params.substring(7, params.indexOf('&')));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, js.toString().getBytes());
//        Log.i("OkHttp", js.toString());
//        // RequestBody body = generateMultipartRequestBody(MEDIA_TYPE_JSON, treeMap);
//        requestBuilder.method(request.method(), body);
//        request = requestBuilder.build();
//        Log.i("OkHttp", request.headers().toString());




        return chain.proceed(request);
    }

    /**
     * 生成类是表单的请求体
     */
    protected RequestBody generateMultipartRequestBody(MediaType type, Map<String, String> map) {
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

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
