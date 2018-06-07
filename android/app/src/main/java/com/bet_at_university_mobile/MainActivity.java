package com.bet_at_university_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     webView = (WebView) findViewById(R.id.webView);
     webView.loadUrl("http://10.0.2.2:4200/");
//     webView.loadUrl("http://betatuniversity.ddns.net/angular2");

     webView.getSettings().setJavaScriptEnabled(true);
     webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
     webView.setWebViewClient(new WebViewClient());
    }
}
