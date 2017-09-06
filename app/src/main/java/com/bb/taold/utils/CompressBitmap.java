package com.bb.taold.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/5.
 */

public class CompressBitmap {
    /*
   * 二次采样图片,并写入文件*/
    public static void compressBitmapInThread(final String string,final FileOutputStream fos)
    {
        //final Bitmap bitmap;
        new Thread(new Runnable() {
            @Override
            public void run() {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds=true;
                BitmapFactory.decodeFile(string,options);
                options.inSampleSize=3;
                options.inPreferredConfig= Bitmap.Config.RGB_565;
                options.inJustDecodeBounds=false;
                Bitmap bitmap = BitmapFactory.decodeFile(string, options);
                bitmap.compress(Bitmap.CompressFormat.JPEG,80,fos);
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*
   * 二次采样图片,并写入文件*/
    public static Bitmap compressBitmap(final String string,final FileOutputStream fos,int size)
    {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds=true;
                BitmapFactory.decodeFile(string,options);
                options.inSampleSize=size;
                options.inPreferredConfig= Bitmap.Config.RGB_565;
                options.inJustDecodeBounds=false;
                Bitmap bitmap = BitmapFactory.decodeFile(string, options);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        return bitmap;
    }
    /*
   * 二次采样图片,不写入文件*/
    public static Bitmap compressBitmapOnly(final String string,int size)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(string,options);
        options.inSampleSize=size;
        options.inPreferredConfig= Bitmap.Config.RGB_565;
        options.inJustDecodeBounds=false;
        Bitmap bitmap = BitmapFactory.decodeFile(string, options);
        return bitmap;
    }
}
