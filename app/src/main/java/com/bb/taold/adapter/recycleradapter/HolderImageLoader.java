package com.bb.taold.adapter.recycleradapter;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载，这里稍微处理得复杂一些，因为考虑加载图片的第三方可能不太一样
 * 也可以不写这个类
 */
public abstract  class HolderImageLoader {
    private String mImagePath;

    public HolderImageLoader(String imagePath) {
        this.mImagePath = imagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public abstract void displayImage(Context context, ImageView imageView, String imagePath);
}
