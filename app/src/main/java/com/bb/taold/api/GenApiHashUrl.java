package com.bb.taold.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class GenApiHashUrl {
    private static GenApiHashUrl INSTANCE = null;
    public static final String apiUrl = "http://59.110.228.218:5555";
    public static final String md5_key = "md5_key";
    public static final String uploadUrl = "";


    public static GenApiHashUrl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenApiHashUrl();
        }
        return INSTANCE;
    }

    private GenApiHashUrl() {
    }

    /**
     * 测试服务器网络是否正常
     *
     * @param host     服务器地址
     * @param port     服务器端口号
     * @param overtime 超时时长
     * @return boolean
     */
    public boolean TestServerNet(String host, int port, int overtime) {
        boolean bol = false;
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(host, port);
            server.connect(address, overtime);
            bol = true;
        } catch (IOException e) {
            e.printStackTrace();
            bol = false;
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bol;
    }


    /**
     * 下载图片资源
     *
     * @param urlStr
     * @return Bitmap
     */
    public Bitmap downloadPic(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        Bitmap bitmap = null;


        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30 * 1000);
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    // 读取字符流
    public static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }


    /**
     * 获取指定URL的响应字符串
     *
     * @return
     */
    public String Http_Post(String params) {
        HttpURLConnection conn = null; //连接对象
        String resultData = "";
        try {
            Log.i("info", "jsonObject=" + params);
            //params = URLEncoder.encode(params, "UTF-8");

            byte[] data = params.getBytes();

            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //这是请求方式为POST
            conn.setRequestMethod("POST");
            // 设置请求的头
            conn.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 设置请求的头
            conn.setRequestProperty("Content-Length",
                    String.valueOf(data.length));
            // 设置请求的头
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

            //获取输出流
            OutputStream os = conn.getOutputStream();
            os.write(data);
            os.flush();

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                resultData = new String(baos.toByteArray(), "UTF-8");
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.i("sean", "----Http_Post:" + resultData);
        return resultData;
    }
}
