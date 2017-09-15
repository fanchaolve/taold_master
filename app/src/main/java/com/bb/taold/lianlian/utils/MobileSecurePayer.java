
package com.bb.taold.lianlian.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.yintong.android.app.IPayService;
import com.yintong.android.app.IRemoteServiceCallback;
import com.yintong.secure.service.PayService;

import org.json.JSONException;
import org.json.JSONObject;

public class MobileSecurePayer {

    Integer lock = 0;
    IPayService payService = null;
    boolean mbPaying = false;
    static final String TAG = "MobileSecurePayer";
    
    boolean mCAPTCHA_Switch = false;

    Activity mActivity = null;
    
    LLPayConstants.PAY_MODE mPayMode = LLPayConstants.PAY_MODE.PAY_COMMON;

    private boolean isTestMode = false;

    Handler mCallbackHandler = null;
    int mCallbackWhat = 0;

    public void setCallbackHandler(final Handler callbackHandler, final int callbackWhat){
        mCallbackHandler = callbackHandler;
        mCallbackWhat = callbackWhat;
    }

    // 和安全支付服务建立连接
    private ServiceConnection mSecurePayConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            try {
                //
                // wake up the binder to continue.
                // 获得通信通道
                synchronized (lock) {
                    payService = IPayService.Stub.asInterface(service);
                    lock.notify();
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            payService = null;
        }
    };

    /**
     * 向银通支付发送预授权支付请求
     * 
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */
    public boolean payPreAuth(String strOrderInfo, final Handler callback,
                              final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "2", false, isTest);

    }


    /**
     * 向银通支付发送游易付支付请求
     *
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */
    public boolean payTravle(String strOrderInfo, final Handler callback,
                             final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "3", false, isTest);

    }

    /**
     * 向银通支付发送分期付单独签约请求
     * 
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean payRepaySign(String strOrderInfo, final Handler callback,
                                final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "6", true, isTest);

    }

    /**
     * 向银通支付发送单独签约请求
     * 
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean paySign(String strOrderInfo, final Handler callback,
                           final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "1", true, isTest);

    }
    
    /**
     * 向银通支付发送基金支付单独签约请求
     * 
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean payFundSign(String strOrderInfo, final Handler callback,
                               final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "7", true, isTest);

    }
    
    /**
     * 向银通支付发送基金支付请求
     * 认证支付
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean payFund(String strOrderInfo, final Handler callback,
                           final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "7", false, isTest);

    }
    
    /**
     * 向银通支付发送认证支付请求
     * 认证支付
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean payAuth(String strOrderInfo, final Handler callback,
                           final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "1", false, isTest);

    }
    
    
    /**
     * 向银通支付发送快捷支付请求
     *
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */
    public boolean pay(String strOrderInfo, final Handler callback,
                       final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "0", false, isTest);

    }

    /**
     * 向银通支付发送分期付支付请求
     * 分期付
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param isTest 是否是测试环境，true为测试环境，但不推荐使用。
     * @return
     */

    public boolean payRepayment(String strOrderInfo, final Handler callback,
                                final int myWhat, final Activity activity, boolean isTest) {

        return pay(strOrderInfo, callback, myWhat, activity, "6", false, isTest);

    }
    
    /**
     * 向银通支付发送支付请求
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param pay_product 0:快捷支付 1:认证支付 2:预授权支付 3:游易付 4:随心付
     * @param signCard 单独签约标识
     * @param isTest 测试环境
     * @return
     */
    public boolean pay(String strOrderInfo, final Handler callback,
                       final int myWhat, final Activity activity, String pay_product, boolean signCard,
                       boolean isTest) {
    	return pay(strOrderInfo, callback, myWhat, activity, pay_product, signCard, isTest, LLPayConstants.PAY_MODE.PAY_COMMON);
    }

    /**
     * 向银通支付发送支付请求
     * @param strOrderInfo 订单信息
     * @param callback 回调handler
     * @param myWhat 回调信息
     * @param activity 目标activity
     * @param pay_product 0:快捷支付 1:认证支付 2:预授权支付 3:游易付 4:随心付
     * @param signCard 单独签约标识
     * @param isTest 测试环境
     * @return
     */
    public boolean pay(String strOrderInfo, final Handler callback,
                       final int myWhat, final Activity activity, String pay_product, boolean signCard,
                       boolean isTest, final LLPayConstants.PAY_MODE pay_mode) {
        if (mbPaying)
            return false;
        mbPaying = true;
        
        mPayMode = pay_mode;

        try {
            if (isTest) {
                strOrderInfo = new JSONObject(strOrderInfo).put("test_mode", "1").toString();
            }
            if (signCard) {
                strOrderInfo = new JSONObject(strOrderInfo).put("sign_mode", "1").toString();
            }
            strOrderInfo = new JSONObject(strOrderInfo).put("pay_product", pay_product).toString();
            
			switch (pay_mode) {
			case PAY_APPLY:
				strOrderInfo = new JSONObject(strOrderInfo).put("pay_mode", "1").toString();
			case PAY_VERIFY:
				strOrderInfo = new JSONObject(strOrderInfo).put("pay_mode", "2").toString();
			default:
				strOrderInfo = new JSONObject(strOrderInfo).put("pay_mode", "0").toString();
			}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //
        mActivity = activity;

        // bind the service.
        // 绑定服务
        if (payService == null) {
            // 绑定安全支付服务需要获取上下文环境，
            // 如果绑定不成功使用mActivity.getApplicationContext().bindService
            // 解绑时同理
            mActivity.getApplicationContext().bindService(
                    new Intent(activity, PayService.class),
                    mSecurePayConnection, Context.BIND_AUTO_CREATE);
        }
        // else ok.

        final String payinfo = strOrderInfo;

        // 实例一个线程来进行支付
        new Thread(new Runnable() {
            public void run() {
                try {
                    // wait for the service bind operation to completely
                    // finished.
                    // Note: this is important,otherwise the next
                    // payService.Pay()
                    // will fail.
                    // 等待安全支付服务绑定操作结束
                    // 注意：这里很重要，否则payService.pay()方法会失败
                    synchronized (lock) {
                        if (payService == null)
                            lock.wait();
                    }

                    // register a Callback for the service.
                    // 为安全支付服务注册一个回调
                    payService.registerCallback(mCallback);

                    // call the MobileSecurePay service.
                    // 调用安全支付服务的pay方法
                    String strRet = null;
//                    if(mPayMode == PAY_MODE.PAYMODE_TOKEN_SIGN) {
//                    	strRet = payService.payApply(payinfo);
//                    } else if(mPayMode == PAY_MODE.PAY_VERIFY) {
//                    	strRet = payService.payVerify(payinfo);
//                    } else {
//                    	strRet = payService.pay(payinfo);
//                    }
                    strRet = payService.pay(payinfo);
                    //String strRet = payService.test();
                    BaseHelper.log(TAG, "服务端支付结果：" + strRet);

                    // set the flag to indicate that we have finished.
                    // unregister the Callback, and unbind the service.
                    // 将mbPaying置为false，表示支付结束
                    // 移除回调的注册，解绑安全支付服务
                    mbPaying = false;
                    payService.unregisterCallback(mCallback);
                    mActivity.getApplicationContext().unbindService(
                            mSecurePayConnection);

                    // send the result back to caller.
                    // 发送交易结果
                    payCallback(strRet, myWhat, callback);
                } catch (Exception e) {
                    e.printStackTrace();

                    // send the result back to caller.
                    // 发送交易结果
                    payCallback(e.toString(), myWhat, callback);
                }
            }
        }).start();

        return true;
    }

    /**
     * 组织四要素支付的单独签约功能报文，并调用服务
     * @param orderInfo
     * @param activity
     * @return
     */
    public boolean doTokenSign(JSONObject orderInfo, final Activity activity){

        if (mbPaying){
            return false;
        }
        mbPaying = true;

        mActivity = activity;

        try {
            if (isTestMode) {
                orderInfo.put("test_mode", "1");
            }
            orderInfo.put("sign_mode", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        startPayService(orderInfo);

        return true;
    }

    public boolean doPay(JSONObject orderInfo, final Activity activity){

        if (mbPaying){
            return false;
        }
        mbPaying = true;

        mActivity = activity;

        try {
            if (isTestMode) {
                orderInfo.put("test_mode", "1");
            }
            //orderInfo.put("sign_mode", "1");

            // 设置paymode为tokensign方式
            //orderInfo.put("pay_mode", "tokensign");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        startPayService(orderInfo);

        return true;
    }

    private void startPayService(final JSONObject orderInfo) {

        // bind the service.
        // 绑定服务
        if (payService == null) {
            // 绑定安全支付服务需要获取上下文环境，
            // 如果绑定不成功使用mActivity.getApplicationContext().bindService
            // 解绑时同理
            mActivity.getApplicationContext().bindService(
                    new Intent(mActivity, PayService.class),
                    mSecurePayConnection, Context.BIND_AUTO_CREATE);
        }
        // else ok.

        final String payinfo = orderInfo.toString();
        // 实例一个线程来进行支付
        new Thread(new Runnable() {
            public void run() {
                try {
                    // wait for the service bind operation to completely
                    // finished.
                    // Note: this is important,otherwise the next
                    // payService.Pay()
                    // will fail.
                    // 等待安全支付服务绑定操作结束
                    // 注意：这里很重要，否则payService.pay()方法会失败
                    synchronized (lock) {
                        if (payService == null)
                            lock.wait();
                    }

                    // register a Callback for the service.
                    // 为安全支付服务注册一个回调
                    payService.registerCallback(mCallback);

                    // call the MobileSecurePay service.
                    // 调用安全支付服务的pay方法
                    String strRet = null;

                    strRet = payService.pay(payinfo);
                    //String strRet = payService.test();
                    BaseHelper.log(TAG, "服务端支付结果：" + strRet);

                    // set the flag to indicate that we have finished.
                    // unregister the Callback, and unbind the service.
                    // 将mbPaying置为false，表示支付结束
                    // 移除回调的注册，解绑安全支付服务
                    mbPaying = false;
                    payService.unregisterCallback(mCallback);
                    mActivity.getApplicationContext().unbindService(mSecurePayConnection);

                    payCallback(strRet, mCallbackWhat, mCallbackHandler);


                } catch (Exception e) {
                    e.printStackTrace();

                    // send the result back to caller.
                    // 发送交易结果
                    payCallback(e.toString(), mCallbackWhat, mCallbackHandler);
                }
            }
        }).start();
    }

    private void payCallback(String strRet, int myWhat, Handler callback) {
        // send the result back to caller.
        // 发送交易结果
        Message msg = new Message();
        msg.what = myWhat;
        msg.obj = strRet;
        callback.sendMessage(msg);
    }

    public void setCAPTCHA_Switch(boolean active) {
    	mCAPTCHA_Switch = active;
    }

    /**
     * This implementation is used to receive callbacks from the remote service.
     * 实现安全支付的回调
     */
    private IRemoteServiceCallback mCallback = new IRemoteServiceCallback.Stub() {
        /**
         * This is called by the remote service regularly to tell us about new
         * values. Note that IPC calls are dispatched through a thread pool
         * running in each process, so the code executing here will NOT be
         * running in our main thread like most other things -- so, to update
         * the UI, we need to use a Handler to hop over there. 通过IPC机制启动安全支付服务
         */
        public void startActivity(String packageName, String className,
                                  int iCallingPid, Bundle bundle) throws RemoteException {
            Intent intent = new Intent(Intent.ACTION_MAIN, null);

            if (bundle == null)
                bundle = new Bundle();
            // else ok.

            try {
                bundle.putInt("CallingPid", iCallingPid);
                bundle.putBoolean("INTENT_BUNDLE_ISAUTOGETAUTHCODE", mCAPTCHA_Switch);
                intent.putExtras(bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }

            intent.setClassName(packageName, className);
            mActivity.startActivity(intent);
        }

        /**
         * when the msp loading dialog gone, call back this method.
         */
        @Override
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        /**
         * when the current trade is finished or cancelled, call back this
         * method.
         */
        @Override
        public void payEnd(boolean arg0, String arg1) throws RemoteException {

        }

    };

    public boolean isTestMode() {
        return isTestMode;
    }

    public void setTestMode(boolean testMode) {
        isTestMode = testMode;
    }
}
