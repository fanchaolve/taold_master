package com.bb.taold.utils.gps;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.bb.taold.bean.Location;
import com.bb.taold.listener.exts.Act1;


import java.text.DecimalFormat;
import java.util.List;



/**
 * 地图service
 */

public class LocationService {
    private LocationClient client = null;
    private LocationClientOption mOption,DIYoption;
    private Object  objLock = new Object();
    private Act1<Location> callback;

    /***
     *
     * @param locationContext
     */
    public LocationService(Context locationContext){
        synchronized (objLock) {
            if(client == null){
                client = new LocationClient(locationContext);
                client.setLocOption(getDefaultLocationClientOption());
                registerListener(new MyLocationListener());
            }
        }
    }

    /***
     *
     * @param listener
     * @return
     */

    public boolean registerListener(BDLocationListener listener){
        boolean isSuccess = false;
        if(listener != null){
            client.registerLocationListener(listener);
            isSuccess = true;
        }
        return  isSuccess;
    }

    public void unregisterListener(BDLocationListener listener){
        if(listener != null){
            client.unRegisterLocationListener(listener);
        }
    }

    /***
     *
     * @param option
     * @return isSuccessSetOption
     */
    public boolean setLocationOption(LocationClientOption option){
        boolean isSuccess = false;
        if(option != null){
            if(client.isStarted())
                client.stop();
            DIYoption = option;
            client.setLocOption(option);
            isSuccess = true;
        }
        return isSuccess;
    }

    public LocationClientOption getOption(){
        return DIYoption;
    }

    public BDLocation lastLocation(){
        return client.getLastKnownLocation();
    }

    /***
     *
     * @return DefaultLocationClientOption
     */
    public LocationClientOption getDefaultLocationClientOption(){
        if(mOption == null){
            mOption = new LocationClientOption();
//            mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
//            mOption.setScanSpan(1000*60*5);//3000//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
//            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
//            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
//            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//            mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//            mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//            mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//            mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用

            mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");
            //可选，默认gcj02，设置返回的定位结果坐标系
            int span=1000;
            mOption.setScanSpan(span);
            //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);
            //可选，设置是否需要地址信息，默认不需要
            mOption.setOpenGps(true);
            //可选，默认false,设置是否使用gps
            mOption.setLocationNotify(true);
            //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
            mOption.setIsNeedLocationDescribe(true);
            //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(true);
            //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.setIgnoreKillProcess(false);
            //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.SetIgnoreCacheException(false);
            //可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setEnableSimulateGps(false);
            //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        }
        return mOption;
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (callback != null){
                Location locationModel = new Location();
                locationModel.latitude = location.getLatitude();
                locationModel.lontitude = location.getLongitude();

                DecimalFormat format = new DecimalFormat("0.000000");
                //刷一遍缓存数据
//                Session.g_lat = format.format(locationModel.latitude);
//                Session.g_lng = format.format(locationModel.lontitude);
//                Session.save();

                callback.run(locationModel);
            }

            if (location.getLocType() == BDLocation.TypeGpsLocation){

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            Log.i("BaiduLocationApiDem", sb.toString());
        }


        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    public void start(Act1<Location> callback){
        this.callback = callback;
        synchronized (objLock) {
            if(client != null && !client.isStarted()){
                client.start();
            }
        }
    }
    public void stop(){
        synchronized (objLock) {
            if(client != null && client.isStarted()){
                client.stop();
            }
        }
    }
}
