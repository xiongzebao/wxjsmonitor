package cn.net.yto.baselibrary.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.net.yto.baselibrary.R;

public class WebViewActivity extends YTOToolBarActivity {

    WebView webView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webView);
    }

    @Override
    protected void initData() {
       String content =  getIntent().getStringExtra("content");
       content = " <head><style>img{ width:100% !important;}</style></head>"+content;//给图片设置一个样式，宽满屏
        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected String setTopTitle() {
        return null;
    }
}
