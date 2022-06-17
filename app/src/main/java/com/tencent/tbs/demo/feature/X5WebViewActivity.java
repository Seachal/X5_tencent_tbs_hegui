package com.tencent.tbs.demo.feature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.ISelectionInterface;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.tbs.demo.R;

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
