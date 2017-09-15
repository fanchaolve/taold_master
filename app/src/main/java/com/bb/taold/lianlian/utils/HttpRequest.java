/*
 * Copyright (C) 2015 The LianLianYT PAY SDK Source Project
 * All right reserved.
 * @Author kristain
 */
package com.bb.taold.lianlian.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * HTTP请求交易通信类
 * 
 * @author kristain
 *
 */
public class HttpRequest {

	private static final String TAG = "HttpRequest";

	public static JSONObject request(Context context, JSONObject bean, String url) {
		if (bean == null) {
			return null;
		}
		
		String response = request(url, bean);
	
		JSONObject result = generateJsonObject(response);
		return result;
	}
	
	public static JSONObject generateJsonObject(String response) {
		if(null == response) {
			return null;
		}
		JSONObject result = null;
		try {
			result = new JSONObject(response);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			try {
				return new JSONObject("{'ret_code':'" + "9998" + "','ret_msg':'"
						+ "格式错误" + "'}");
			} catch (JSONException e1) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
		
		return result;
	}
	
	private static String request(String url, JSONObject req) {
			String response = CustomURLConnection.post(url, req);
			
			return response;
	 }

	public static boolean isSuccess(JSONObject result) {
		if (result != null) {
			return result.optString("ret_code").equals("0000");
		}
		return false;
	}

}
