package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bb.taold.widget.multitype.data.Item;

/**
 * Created by Administrator on 2016/11/22.
 */

public abstract class ItemViewProvider<C extends Item, V extends RecyclerView.ViewHolder> {


    @NonNull
    protected abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull V holder, @NonNull C c);

    //获取当前的pos;
    protected void getCurrentPositon(int position) {

    }

}
