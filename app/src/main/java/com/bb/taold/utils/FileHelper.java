package com.bb.taold.utils;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/21.
 * 读写本地txt文件的帮助类
 */

public class FileHelper {
    //    private String str = "";
    private String fileName = "";
    private String filePath = "";

    public FileHelper(String fileName) {
//        this.str = str;
        this.fileName = fileName;

        creadFilePath(fileName);
    }

    /**
     * 判断文件是否存在
     *
     * @return
     */
    public boolean isExist() {
        creadFilePath(fileName);
        File file = new File(filePath);
        return file.exists() ? true : false;
    }

    /**
     * 创建文件路径
     */
    public void creadFilePath(String fileName) {
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) { // SD卡根目录的hello.text
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + fileName;
        } else { // 系统下载缓存根目录的hello.text
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + fileName;
        }
    }

    /**
     * 保存文件
     *
     * @param str
     */
    public void saveFile(String str) {
        filePath = Environment.getExternalStorageDirectory() + File.separator + fileName;
        try {
            File file = new File(filePath);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取字符串
     *
     * @return
     */
    public String readFile(String filename) {
        filePath = Environment.getExternalStorageDirectory() + File.separator + fileName;
        String reads = "";
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[8*1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (fis.read(b) != -1) {
                baos.write(b, 0, b.length);
            }
            baos.close();
            fis.close();
            reads = baos.toString();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reads;
        }

}
