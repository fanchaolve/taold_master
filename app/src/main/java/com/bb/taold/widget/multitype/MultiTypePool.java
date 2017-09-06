package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bb.taold.widget.multitype.data.Item;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MultiTypePool implements TypePool {


    private  final String TAG=MultiTypePool.class.getSimpleName();


    protected ArrayList<Class<? extends Item>> contents;

    protected ArrayList<ItemViewProvider> providers;


    public MultiTypePool() {

        this.contents = new ArrayList<>();
        this.providers=new ArrayList<>();

    }

    @Override
    public void register(@NonNull Class<? extends Item> clazz, @NonNull ItemViewProvider provider) {
        if(!contents.contains(clazz)){
            contents.add(clazz);
            providers.add(provider);
        }
        else {
            int index=indexOf(clazz);
            providers.set(index,provider);
            Log.w(TAG,"You have registered the "+ clazz.getSimpleName() +"type."+"It will override the original provider.");
        }


    }

    @Override
    public int indexOf(@NonNull Class<? extends Item> clazz) {
        int index = contents.indexOf(clazz);
        if(index>=0){
            return index;
        }

        for(int i=0;i<contents.size();i++){
            if(contents.get(i).isAssignableFrom(clazz)){
                return i;
            }
        }
        return index;
    }

    @NonNull
    @Override
    public ArrayList<Class<? extends Item>> getContents() {
        return contents;
    }

    @NonNull
    @Override
    public ArrayList<ItemViewProvider> getProviders() {
        return providers;
    }

    @NonNull
    @Override
    public ItemViewProvider getProviderByIndex(int index) {
        return providers.get(index);
    }

    @NonNull
    @Override
    public <T extends ItemViewProvider> T getProviderByClass(@NonNull Class<? extends Item> clazz) {
        return (T)getProviderByIndex(indexOf(clazz));
    }
}
