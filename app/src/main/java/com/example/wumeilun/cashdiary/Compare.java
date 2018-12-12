package com.example.wumeilun.cashdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Compare extends AppCompatActivity {
    private WebView Income_data;
    private WebView Expense_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
        Income_data = (WebView) findViewById(R.id.Income_data);
        Expense_data = (WebView) findViewById(R.id.Expense_data);
        WebSettings webSettings1 = Income_data.getSettings();
        WebSettings webSettings2 = Expense_data.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings2.setJavaScriptEnabled(true);
        Income_data.loadUrl("https://www.bls.gov/news.release/realer.htm");
        Expense_data.loadUrl("https://www.bls.gov/news.release/cesan.nr0.htm");
    }

}