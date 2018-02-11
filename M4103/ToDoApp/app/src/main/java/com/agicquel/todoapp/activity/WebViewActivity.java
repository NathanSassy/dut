package com.agicquel.todoapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.agicquel.todoapp.R;

/**
 * Created by agicquel on 10/02/18.
 * Simple webview activity which allows to open a link given in the extra bundle
 */

public class WebViewActivity extends AppCompatActivity {
    public final static String URL_EXTRA = "URL_EXTRA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getExtras();
        if(extra == null || extra.getString(URL_EXTRA) == null)
            finish();
        String url = extra.getString(URL_EXTRA);

        getSupportActionBar().setTitle(getString(R.string.link_task));
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if(!url.contains("https://"))
            url = "https://" + url;
        webView.loadUrl(url);
        setContentView(webView);
    }
}
