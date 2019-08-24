package com.example.android.emailapp.detail;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.emailapp.R;
import com.example.android.emailapp.baseui.BaseActivity;
import com.example.android.emailapp.databinding.ActivityOutlookDetailBinding;
import com.example.android.emailapp.pojos.OutlookMessage;
import com.library.android.common.utils.StringUtils;
import com.library.android.common.utils.Utils;
import com.livemymail.android.mailboxapp.activities.MainActivity;

import androidx.annotation.RequiresApi;

public class OutlookDetailActivity extends BaseActivity {

    private ActivityOutlookDetailBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_outlook_detail;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mBinding = ActivityOutlookDetailBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {

    }

    @Override
    public void handleViews() {
        setWebView();
        if (Utils.hasParcel(getIntent())) {
            if (Utils.getParcel(getIntent()) instanceof OutlookMessage) {
                OutlookMessage outlookMessage = (OutlookMessage) Utils.getParcel(getIntent());
                if (outlookMessage != null) {
                    if (outlookMessage.getBody() != null && StringUtils.isNotNullNotEmpty(outlookMessage.getBody().getContent())) {
                        /* mWebView.loadData(myHtmlString, "text/html", null);*/
                        mBinding.webView.loadData(outlookMessage.getBody().getContent(), "text/html", "UTF-8");
                    }
                }
            }
        }
    }

    private void setWebView() {
        WebSettings myWebSettings = mBinding.webView.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setUseWideViewPort(true);
        myWebSettings.setSupportZoom(true);
        myWebSettings.setBuiltInZoomControls(true);
        myWebSettings.setDisplayZoomControls(false);
        myWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebSettings.setDomStorageEnabled(true);
        mBinding.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mBinding.webView.setScrollbarFadingEnabled(true);
        mBinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(request.getUrl());
                startActivity(i);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mBinding.webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, int statuscode, String description, String url) {
                super.onReceivedError(view, statuscode, description, url);
                Log.e(MainActivity.TAG, String.format("code: %d\tdescription: %s\turl: %s", statuscode, description, url));
                mBinding.webView.setVisibility(View.GONE);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e(MainActivity.TAG, String.format("code: %d\tdescription: %s\turl: %s", error.getErrorCode(), error.getDescription(), request.getUrl()));
                mBinding.webView.setVisibility(View.GONE);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse response) {
                super.onReceivedHttpError(view, request, response);
                Log.e(MainActivity.TAG, String.format("code: %d\tdescription: %s\turl: %s", response.getStatusCode(), response.getReasonPhrase(), request.getUrl()));
                mBinding.webView.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
                Log.e(MainActivity.TAG, error.toString());
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }
}
