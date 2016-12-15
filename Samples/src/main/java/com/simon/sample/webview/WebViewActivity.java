package com.simon.sample.webview;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.ToastUtil;
import com.simon.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {
    public static String TAG = WebViewActivity.class.getSimpleName();

    public static final String EXTRA_URL = "EXTRA_URL";

    @BindView (R.id.web_view)
    WebView webView;
    @BindView (R.id.web_view_btn_copy)
    Button webViewBtnCopy;

    private String url = "http://172.16.6.15:8005/wechat/attentionWechat.html";

    private ClipboardManager myClipboard;
    private ClipData myClip;

    public static void launch(Activity activity, String url) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        setWebConfig();
        initClip();
    }

    private void initClip() {
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    private void setWebConfig() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);

        //在JS上响应
        webView.addJavascriptInterface(new JSInterface(), "test");

        webView.loadUrl(url);
    }

    @OnClick (R.id.web_view_btn_copy)
    public void onClick() {
        myClip = ClipData.newPlainText("text", "剪贴板的内容");
        myClipboard.setPrimaryClip(myClip);
        ToastUtil.showShort(WebViewActivity.this, "剪贴成功");
    }

    class JSInterface {
        @JavascriptInterface
        public void showAndroid() {
            ToastUtil.showShort(WebViewActivity.this, "哈哈啊哈哈");
            startWeChat();
        }
    }

    /**
     * 启动微信
     */
    private void startWeChat() {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        startActivityForResult(intent, 0);
    }
}
