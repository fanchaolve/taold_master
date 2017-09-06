package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;

import com.bb.taold.widget.multitype.data.Item;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface FlatTypeAdapter {


    @NonNull
    Class onFlattenClass(@NonNull Item item);

    @NonNull Item onFlattenItem(@NonNull final Item item);


}
