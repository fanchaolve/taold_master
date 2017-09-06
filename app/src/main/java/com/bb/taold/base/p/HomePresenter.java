package com.bb.taold.base.p;


import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.v.HomeContract;
import com.bb.taold.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl.
 */

public class HomePresenter extends HomeContract.Presenter {


    private List<Item> items;



    private int topicListPageIndex = 1;

    private int topicListPageSize = 10;

    private int item_PageSize = 5;

    private PostCallback postCallback;


    @Override
    public void onAttached() {
        items = new ArrayList<>();
        postCallback = new PostCallback<HomeContract.View>(mView) {

            @Override
            public void successCallback(Result_Api api) {

            }

            @Override
            public void failCallback() {

            }
        };

    }



}
