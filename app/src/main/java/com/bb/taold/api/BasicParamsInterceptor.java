package com.bb.taold.api;

import android.util.Log;

import com.bb.taold.bean.Tostring_TreeMap;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.MD5Util;
import com.bb.taold.utils.ParamUtils;
import com.bb.taold.utils.SignUtils;
import com.bb.taold.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
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


    //public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        ParamUtils paramUtils = new ParamUtils();
        if (request.method().equalsIgnoreCase("GET")) {//get 请求
            request = paramUtils.get_method(request);
        } else {
            request = paramUtils.post_method(request);
        }


//        if (request.body() instanceof FormBody) {
//            FormBody oldFormBody = (FormBody) request.body();
//            for (int i = 0; i < oldFormBody.size(); i++) {
//               params.put(oldFormBody.encodedName(i),oldFormBody.encodedValue(i));
//            }
//        }

//        String sign= SignUtils.sign(params,"lU9ZCkpxiL9PW86QevPXhs");
//
//        params.put("sign",sign);
//        RequestBody body = generateMultipartRequestBody(MEDIA_TYPE_JSON, params);
//        requestBuilder.method(request.method(), null);
//        requestBuilder.url(GenApiHashUrl.apiUrl+url.encodedPath()+"?"+params.toString());
//        request = requestBuilder.build();

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
