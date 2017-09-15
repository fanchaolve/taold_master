package com.bb.taold.activitiy.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.utils.Constants;

/**
 * 类描述：
 * 创建时间：2017/9/14 0014
 *
 * @author chaochao
 */

public class WebViewActivity extends Activity{
    private WebView mWebView;
    private TextView mTvTitle;
    private String url;

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
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }


    private void initData() {
        Intent intent = getIntent();
        if(intent!=null){
            Bundle extras = intent.getExtras();
            if(extras!=null){
                this.url = extras.getString(Constants.WEBVIEW_URL);
            }else{
                this.url = intent.getStringExtra(Constants.WEBVIEW_URL);
            }
        }

        mWebView.setWebViewClient(new MWebviewClient());
        mWebView.setWebChromeClient(new MWebChromeClient());
        mWebView.loadUrl(url);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_webview_back:
                onBackPressed();
                break;
        }
    }

    class MWebviewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

    class MWebChromeClient extends WebChromeClient{
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if(!TextUtils.isEmpty(title)){
                if(title.length()>10){
                    title = title.substring(0,10)+"...";
                }
            }
            mTvTitle.setText(title);
        }
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            finish();
        }
    }
}
