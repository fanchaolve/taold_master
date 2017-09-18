package com.bb.taold.api;

import android.content.Context;

import com.bb.taold.bean.LoadRecordResponse;
import com.bb.taold.utils.DeviceUtils;

import retrofit2.Call;

/**
 * ApiServive实现类
 * Created by zhou on 2017/9/16.
 */

public class ApiServiveImpl {
    public ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);

    /**
     * 意见反馈
     */
    public Call<Result_Api<String>> memberCreateUserFeedback(Context context, String feedbackContent
    ) {
        String versionName = DeviceUtils.getCurr_VersionName(context);
        return service.memberCreateUserFeedback(DeviceUtils.getModel(), versionName, feedbackContent, DeviceUtils.getDeviceIdentification(context));
    }

}
