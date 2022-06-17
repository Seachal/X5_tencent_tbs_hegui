package com.tencent.tbs.demo.feature;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.ISelectionInterface;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.tbs.demo.R;

public class X5FeatureActivity extends BaseWebViewActivity {

    private static final String M_TAG = "X5FeatureActivity";

    private final Activity fActivity = this;
    private final Context fContext = this;

    private PopupMenu menu = null;
    private View menuPosition = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setTAG(M_TAG);
        super.onCreate(savedInstanceState);
        menuPosition = findViewById(R.id.select_popup_view);
        menu = new PopupMenu(this, menuPosition);
        menu.getMenuInflater().inflate(R.menu.website_menu, menu.getMenu());

    }

    @Override
    protected void initWebView() {
        super.initWebView();
        Toast.makeText(this, mWebView.getIsX5Core() ?
                "X5内核: " + QbSdk.getTbsVersion(this) : "SDK系统内核" , Toast.LENGTH_SHORT).show();
        initWebViewClientExtension();
    }

    private void initWebViewClientExtension() {
        if (mWebView == null || mWebView.getX5WebViewExtension() == null) {
            return;
        }
        // textZoom:100表示正常，120表示文字放大1.2倍
        mWebView.getSettings().setTextZoom(120);

        // 设置滚动条样式
        mWebView.getX5WebViewExtension().setVerticalTrackDrawable(this.getDrawable(R.drawable.ic_line_x));
        mWebView.getX5WebViewExtension().setVerticalScrollBarEnabled(true);
        
    }

}
