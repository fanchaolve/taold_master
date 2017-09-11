package com.bb.taold.activitiy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class DemoActivity extends BaseActivity{

    @BindView(R.id.iv_jp)
    ImageView iv_jp;

    @BindView(R.id.button)
    Button button;



    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateHeadTask(bitmap);
                iv_jp.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void initListener() {

    }

    Bitmap bitmap;
    @Override
    public void initdata() {
        View viewGroup= LayoutInflater.from(DemoActivity.this).inflate(R.layout.signature2, null);
        bitmap=getBitmapByView(viewGroup);

    }

    public static Bitmap getBitmapByView(View  view) {
        view.measure(View.MeasureSpec.makeMeasureSpec
                (0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }








    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initLocationService() {

    }




    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        super.onStop();
//        // TODO Auto-generated method stub

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @SuppressLint({"SimpleDateFormat", "SdCardPath"})
    private String getFileName() {
        String saveDir = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            saveDir = "/mnt/sdcard/kalai";
        } else {
            saveDir = this.getFilesDir().getAbsolutePath();
        }

        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdir(); // 创建文件夹
        }
        // 用日期作为文件名，确保唯一性
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String fileName = saveDir + "/" + formatter.format(date) + ".jpg";
        return fileName;
    }

    private void UpdateHeadTask(Bitmap bitmap) {
        final File avatarFile = new File(getFileName());
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatarFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
