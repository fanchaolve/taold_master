package com.bb.taold.activitiy.webview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.bb.taold.R;

/**
 * 类描述：
 * 创建时间：2017/9/14 0014
 *
 * @author chaochao
 */

public class WebViewActivity extends Activity{
    private WebView mWebView;
    private TextView mTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        initData();
    }

    private void initView() {
        mWebView = (WebView)findViewById(R.id.webview);
        mTvTitle = (TextView)findViewById(R.id.tv_webview_title);
    }


    private void initData() {

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_webview_back:
                finish();
                break;
        }
    }
}
