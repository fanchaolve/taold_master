package com.bb.taold.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.bb.taold.api.RetrofitFactory;
import com.idcard.TRECAPIImpl;
import com.idcard.TStatus;
import com.idcard.TengineID;
import com.turui.bank.ocr.CaptureActivity;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.utils
 * <p>
 * 说明：扫描银行卡工具类
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/13
 * <p>
 * ==============================================
 */

public class CardNumScanUtil {

    public static final int RESULT_GET_OK = 1;//不能改

    private TengineID tengineID;
    private TRECAPIImpl engineDemp;

    private static CardNumScanUtil INSTANCE = null;

    private CardNumScanUtil() {
        tengineID = TengineID.TIDBANK;
        engineDemp=new TRECAPIImpl();
    }

    public static CardNumScanUtil getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new CardNumScanUtil();
        return INSTANCE;
    }

    public void doScan(){
        TStatus tStatus=engineDemp.TR_StartUP();
        if (tStatus == TStatus.TR_TIME_OUT ) {
            Toast.makeText(AppManager.getInstance().currentActivity(), "引擎过期", Toast.LENGTH_SHORT).show();
        }
        else  if (tStatus == TStatus.TR_FAIL) {
            Toast.makeText(AppManager.getInstance().currentActivity(), "引擎初始化失败", Toast.LENGTH_SHORT).show();
        }
        CaptureActivity.tengineID = TengineID.TIDBANK;
        CaptureActivity.ShowCopyRightTxt = "";
        Intent intent = new Intent(AppManager.getInstance().currentActivity(), CaptureActivity.class);
        intent.putExtra("engine", engineDemp);
        AppManager.getInstance().currentActivity().startActivityForResult(intent, RESULT_GET_OK);

    }

    public void doDestory(){
        try {
            engineDemp.TR_ClearUP();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }




}
