package com.bb.taold.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by Administrator on 2017/3/22.
 * 保存与读取地址对象的工具类
 */

public class AddressUtils {

    private static String FILENAME = "address";
    /**
     * desc:保存对象

     * @param context
     * @param key addressBean
     * @param obj 要保存的对象，只能保存实现了serializable的对象
     * modified:
     */
    public static void saveObject(final Context context,final String key ,final Object obj){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 保存对象
                    SharedPreferences.Editor sharedata = context.getSharedPreferences(FILENAME, 0).edit();
                    //先将序列化结果写到byte缓存中，其实就分配一个内存空间
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    ObjectOutputStream os=new ObjectOutputStream(bos);
                    //将对象序列化写入byte缓存
                    os.writeObject(obj);
                    //将序列化的数据转为16进制保存
                    String bytesToHexString = bytesToHexString(bos.toByteArray());
                    //保存该16进制数组
                    sharedata.putString(key, bytesToHexString);
                    sharedata.commit();
                    Log.e("tttttt", "--------------保存obj成功---------------");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("tttttt", "--------------保存obj失败---------------");
                }
            }
        }).run();

    }
    /**
     * desc:将数组转为16进制
     * @param bArray
     * @return
     * modified:
     */
    public static String bytesToHexString(byte[] bArray) {
        if(bArray == null){
            return null;
        }
        if(bArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * desc:获取保存的Object对象
     * @param context
     * @param key
     * @return
     * modified:
     */
    public static Object readObject(Context context,String key ){
        try {
            SharedPreferences sharedata = context.getSharedPreferences(FILENAME, 0);
            if (sharedata.contains(key)) {
                String string = sharedata.getString(key, "");
                if(TextUtils.isEmpty(string)){
                    return null;
                }else{
                    //将16进制的数据转为数组，准备反序列化
                    byte[] stringToBytes = StringToBytes(string);
                    ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
                    ObjectInputStream is=new ObjectInputStream(bis);
                    //返回反序列化得到的对象
                    Object readObject = is.readObject();
                    return readObject;
                }
            }
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return null;

    }
    /**
     * desc:将16进制的数据转为数组

     * @param data
     * @return
     * modified:
     */
    public static byte[] StringToBytes(String data){
        String hexString=data.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch2 = hex_char2-55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1+int_ch2;
            retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }


//    /**
//     * 存储对象
//     * @param context
//     * @param object
//     * @param key
//     */
//    public static void setObjectToShare(Context context, Object object,
//                                        String key) {
//// TODO Auto-generated method stub
//        SharedPreferences share = context.getSharedPreferences(FILENAME,Context.MODE_APPEND);
//        if (object == null) {
//            Editor editor = share.edit().remove(key);
//            editor.commit();
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(object);
//        } catch (IOException e) {
//            Log.d("tttttt","---------------fail111--------------");
//            e.printStackTrace();
//        }
//// 将对象放到OutputStream中
//// 将对象转换成byte数组，并将其进行base64编码
//        String objectStr = new String(Base64.encode(baos.toByteArray()));
//        try {
//            baos.close();
//            oos.close();
//        } catch (IOException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//            Log.d("tttttt","---------------fail222--------------");
//        }
//        SharedPreferences.Editor editor = share.edit();
//// 将编码后的字符串写到base64.xml文件中
//        editor.putString(key, objectStr);
//        if (editor.commit())
//        Log.d("tttttt","---------------success--------------");
//    }
//
//    /**
//     * 获取对象
//     * @param context
//     * @param key
//     * @return
//     */
//    public static Object getObjectFromShare(Context context, String key) {
//        SharedPreferences sharePre = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        try {
//            String wordBase64 = sharePre.getString(key, "");
//// 将base64格式字符串还原成byte数组
//            if (wordBase64 == null || wordBase64.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
//                return null;
//            }
//            byte[] objBytes = Base64.decode(wordBase64);
//            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
//            ObjectInputStream ois = new ObjectInputStream(bais);
//// 将byte数组转换成product对象
//            Object obj = ois.readObject();
//            bais.close();
//            ois.close();
//            return obj;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
