package com.tencent.tbs.demo.feature;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.sdk.WebChromeClient;

/**
 * 全屏实现需要对 WebChromeClient 中的 onShowCustomView 和 onHideCustomView 进行覆写
 * AndroidManifest 中需要增加全屏旋转相关的属性设置
 */
public class VideoActivity extends X5WebViewActivity {

    private WindowManager windowManager;
    private View fullScreenLayer;

    @Override
    protected void onResume() {
        super.onResume();
        // 如果之前处于全屏状态，重新进入后需要再次调用全屏
        if (fullScreenLayer != null) {
            fullScreen(fullScreenLayer);
        }
    }

    @Override
    protected void initWebView() {
        windowManager = getWindowManager();
        super.initWebView();
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                // view 为内核生成的全屏视图，需要添加到相应的布局位置（如：全屏幕）
                // customViewCallback 用于主动控制全屏退出
                windowManager.addView(view, new LayoutParams(LayoutParams.TYPE_APPLICATION));
                fullScreen(view);
                fullScreenLayer = view;
            }

            @Override
            public void onHideCustomView() {
                windowManager.removeViewImmediate(fullScreenLayer);
                fullScreenLayer = null;
            }
        });

        mWebView.loadUrl("file:///android_asset/webpage/fullscreenVideo.html");
    }

    private void fullScreen(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
