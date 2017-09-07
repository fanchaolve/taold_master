package com.bb.taold.api;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RetrofitFactory {

    private static final int DEFAULT_TIME_OUT = 30;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 30;
    private static final int DEFAULT_WRITE_TIME_OUT = 30;


    private static String TAG = RetrofitFactory.class.getSimpleName();


    private static RetrofitFactory INSTANCE = null;


    private Retrofit mRetrofit;


    private RetrofitFactory() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()

                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new BasicParamsInterceptor())
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .build();


        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, String>() {

                            @Override
                            public String convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                })
                .baseUrl(GenApiHashUrl.apiUrl)
                .build();

    }

    public static RetrofitFactory getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new RetrofitFactory();
        return INSTANCE;
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
