package com.tencent.tbs.demo.feature;

import android.os.Bundle;
import android.widget.Toast;
import com.tencent.smtt.sdk.QbSdk;

/**
 * <p>SDK中封装的系统WebView</p>
 * 进程强制系统内核接口（进程不可恢复X5） QbSdk.forceSysWebView()
 */
public class SystemWebViewActivity extends BaseWebViewActivity {

    protected static final String M_TAG = "SystemWebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTAG(M_TAG);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWebView() {
        QbSdk.forceSysWebView();
        super.initWebView();
        Toast.makeText(this, mWebView.getIsX5Core() ? "X5内核" : "SDK系统内核" , Toast.LENGTH_SHORT).show();
    }
}
