package com.bb.taold.widget;

/*
 * Copyright (C) 2009 Teleca Poland Sp. z o.o. <android@teleca.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.AsyncTask;

import com.bb.taold.base.v.BaseView;


/**
 * Wrapper around UserTask & ProgressDialog
 *
 * @author Lukasz Wisniewski
 */
public abstract class BaseAsyncTask<Input, Result> extends AsyncTask<Input, Integer, Result> {


    private BaseView view;

    private boolean mEnddismiss = true;//是否显示等待效果

    public BaseAsyncTask(BaseView view, boolean mEnddismiss) {
        this.view = view;
        this.mEnddismiss = mEnddismiss;
        view.initLoading();
    }

    public BaseAsyncTask() {
    }


    @Override
    public void onCancelled() {
        if (view != null && mEnddismiss) {
            view.dissmissLoading();
        }
        super.onCancelled();
    }

    @Override
    public void onPreExecute() {
        if (view != null && mEnddismiss) {
            view.showLoading();
        }
        super.onPreExecute();
    }

    @Override
    public abstract Result doInBackground(Input... params);

    @Override
    public void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (view != null && mEnddismiss) {
            view.dissmissLoading();
        }
        if (result != null)
            doStuffWithResult(result);
        else
            view.showMsg("不好意思,数据有点小问题请重试!!!");

    }


    /**
     * Very abstract function hopefully very meaningful name,
     * executed when result is other than null
     *
     * @param result
     * @return
     */
    public abstract void doStuffWithResult(Result result);

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

}

