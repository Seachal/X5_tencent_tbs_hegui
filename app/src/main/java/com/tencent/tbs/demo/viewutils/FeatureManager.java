package com.tencent.tbs.demo.viewutils;

import com.tencent.tbs.demo.feature.SystemWebViewActivity;
import com.tencent.tbs.demo.feature.VideoActivity;
import com.tencent.tbs.demo.feature.WebKitWebViewActivity;
import com.tencent.tbs.demo.feature.X5WebViewActivity;

public class FeatureManager {

    public static final int TYPE_ITEM = 0;
//    header
    public static final int TYPE_SEPARATOR = 1;

    public static final FeatureItem[] featureItems = {
            makeHeader("基础功能"),
            makeItem("X5 WebView", X5WebViewActivity.class),
            makeItem("SDK SysWebView", SystemWebViewActivity.class),
            makeItem("纯 Webkit WebView", WebKitWebViewActivity.class),
            makeHeader("网页具体功能"),
            makeItem("全屏播放", VideoActivity.class, "file:///android_asset/webpage/fullscreenVideo.html"),
            makeItem("JS交互", X5WebViewActivity.class, "file:///android_asset/webpage/JSExamplePage.html"),
            makeItem("TBS Debug", X5WebViewActivity.class, "file:///android_asset/webpage/coreLoadDebugPage.html")
    };

    public static class FeatureItem {
        public int type;
        public String featureName;
        public Class<?> featureActivity;
        public String url;

        public FeatureItem(int t, String name, Class<?> activity) {
            type = t;
            featureName = name;
            featureActivity = activity;
            url = null;
        }

        public FeatureItem(int t, String name, Class<?> activity, String turl) {
            type = t;
            featureName = name;
            featureActivity = activity;
            url = turl;
        }
    }

    private static FeatureItem makeItem(String name, Class<?> activity) {
        return new FeatureItem(TYPE_ITEM, name, activity);
    }

    private static FeatureItem makeItem(String name, Class<?> activity, String url) {
        return new FeatureItem(TYPE_ITEM, name, activity, url);
    }

    private static FeatureItem makeHeader(String header) {
        return new FeatureItem(TYPE_SEPARATOR, header, null);
    }

    public static Class<?> getActivity(int pos) {
        return featureItems[pos].featureActivity;
    }

    public static String getUrl(int pos) {
        return featureItems[pos].url;
    }

}
