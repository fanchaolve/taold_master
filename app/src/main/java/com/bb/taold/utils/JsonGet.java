package com.bb.taold.utils;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by hp on 2016/8/9.
 */
public class JsonGet {

    private JSONObject jsonObject;

    public JsonGet(){
        jsonObject=new JSONObject();
    }

    public  JsonGet putopt(String key, Object value)  {

        try {
            jsonObject.putOpt(key,value);
        }catch (Exception e){
            Log.e("JsonGet","putopt():参数错误");
            e.getMessage();
        }


        return  this;
    }

    public String tostring(){
       return  jsonObject.toString();
    }
}
