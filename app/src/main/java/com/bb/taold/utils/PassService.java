package com.bb.taold.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.authreal.api.AuthBuilder;
import com.authreal.api.OnResultListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhucheng'an on 2017/9/8.
 * Package Name com.bb.taold.utils
 * Class Name PassService
 */

public class PassService {

    private Activity mContext;

    public PassService(Activity mContext) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
    }

    //跳转到活体检查界面
    void jumpToLiveNess() {

        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        final BaseModule result = controller2.getImgId(passBean.getOutMerchantNo());
//                        mContext.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mService.disProgressDialog();
//                                if (result == null) {
//                                    Toast.makeText(mContext, "活体检测失败", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//                                if (!result.getError().equals("00")) {
//                                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//
//                                AuthBuilder mAuthBuilder = new AuthBuilder(result.getMsg(), UICommon.authKey, Common.URL_YOUDUN, new OnResultListener() {
//                                    @Override
//                                    public void onResult(final String s) {
//
//                                        //判断是否认证成功
//                                        if (!controller2.isEmpty(s)) {
//                                            try {
//                                                Log.i("活体检测数据", s);
//                                                final JSONObject json = new JSONObject(s);
//
//                                                if (json.has("be_idcard")) {
//                                                    Double be_idcard = json.optDouble("be_idcard");
//                                                    Double config_be_idcard = Double.valueOf(passBean.getValueAuth());
//                                                    if (be_idcard >= config_be_idcard) {
//                                                        Intent intent = new Intent(mContext, OcrActivity.class);
//                                                        intent.putExtra("person_value", s);
//                                                        mContext.startActivity(intent);
//                                                    } else {
//                                                        UICommon.showTip("人脸识别未通过");
//                                                    }
//                                                }else {
//                                                    UICommon.showTip("人脸识别未通过");
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        } else {
//                                            Toast.makeText(mContext, "身份认证失败", Toast.LENGTH_SHORT).show();
//                                        }
//
//
//                                    }
//
//                                });
//
//                                mAuthBuilder.faceAuth(mContext);
//                            }
//                        });
                    }
                }).start();

            }

        });

    }

}
