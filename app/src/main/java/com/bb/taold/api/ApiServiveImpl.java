package com.bb.taold.api;

import android.content.Context;

import com.bb.taold.bean.BandCardResult;
import com.bb.taold.bean.LoadRecordResponse;
import com.bb.taold.bean.MessageResult;
import com.bb.taold.utils.DeviceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;

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

    public Call<Result_Api<List<MessageResult>>> queryMessageInfo() {
        return service.queryMessageInfo();
    }

    /**
     * 连连协议
     *
     * @param llAgreeNo
     * @param cardNo
     * @return
     */
    public Call<Result_Api<String>> updateAgreeNo(String llAgreeNo, String cardNo) {
        return service.updateAgreeNo(llAgreeNo, cardNo);

    }

    public Call<Result_Api<BandCardResult>> getLLToken(
            String cardNo, String mobile) {
        return service.getLLToken("0", cardNo, mobile);

    }


}
