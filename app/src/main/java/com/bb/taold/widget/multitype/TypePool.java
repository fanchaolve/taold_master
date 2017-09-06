package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;

import com.bb.taold.widget.multitype.data.Item;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface TypePool {

    void register(@NonNull Class<? extends Item> clazz, @NonNull ItemViewProvider provider);

    int indexOf(@NonNull Class<? extends Item> clazz);

    @NonNull
    ArrayList<Class<? extends Item>> getContents();

    @NonNull
    ArrayList<ItemViewProvider> getProviders();

    @NonNull ItemViewProvider getProviderByIndex(int index);


    @NonNull <T extends ItemViewProvider> T getProviderByClass(@NonNull Class<? extends Item> clazz);


}
