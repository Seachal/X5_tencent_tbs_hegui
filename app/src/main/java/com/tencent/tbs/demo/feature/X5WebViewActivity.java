package com.tencent.tbs.demo.feature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tencent.smtt.sdk.QbSdk;

/**
 *x5
 */
public class X5WebViewActivity extends BaseWebViewActivity {

    private static final String M_TAG = "X5WebViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setTAG(M_TAG);
        super.onCreate(savedInstanceState);
        startDefinedUrl();
    }

    @Override
    protected void initWebView() {
        super.initWebView();
        Toast.makeText(this, mWebView.getIsX5Core() ?
                "X5内核: " + QbSdk.getTbsVersion(this) : "SDK系统内核" , Toast.LENGTH_SHORT).show();
        Log.i("X5Web_X5Log", ""+QbSdk.getTbsVersion(this));
    }

    private void startDefinedUrl() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("url");
            if (mWebView != null) {
                mWebView.loadUrl(url);
            }
        } else {
            Log.i(M_TAG, "Intent is null");
        }
    }
}
