package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;

import com.bb.taold.widget.multitype.data.Item;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */

public class GlobalMultiTypePool {

    private static MultiTypePool pool=new MultiTypePool();

    public static void register(@NonNull Class<? extends Item> clazz, @NonNull ItemViewProvider provider) {
        pool.register(clazz,provider);
    }

    public static int indexOf(@NonNull final Class<? extends Item> clazz) {
        return pool.indexOf(clazz);
    }

    @NonNull public static ArrayList<Class<? extends Item>> getContents() {
        return pool.getContents();
    }

    @NonNull public static ArrayList<ItemViewProvider> getProviders() {
        return pool.getProviders();
    }


    @NonNull public static ItemViewProvider getProviderByIndex(int index) {
        return pool.getProviderByIndex(index);
    }

    @NonNull public static <T extends ItemViewProvider> T getProviderByClass(
            @NonNull Class<? extends Item> clazz) {
        return pool.getProviderByClass(clazz);
    }

    public static MultiTypePool getPool(){
        return  pool;
    }


}
