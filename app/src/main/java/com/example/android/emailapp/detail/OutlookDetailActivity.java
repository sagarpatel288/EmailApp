package com.example.android.emailapp.detail;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.android.emailapp.compose.ComposeActivity;
import com.example.android.emailapp.constants.AppUrls;
import com.example.android.emailapp.constants.JsonKeys;
import com.example.android.emailapp.databinding.ActivityOutlookDetailBinding;
import com.example.android.emailapp.pojos.OutlookMessage;
import com.example.android.emailapp.rest.ApiClient;
import com.example.android.emailapp.rest.ApiInterface;
import com.library.android.common.utils.StringUtils;
import com.library.android.common.utils.Utils;
import com.livemymail.android.mailboxapp.activities.MainActivity;
import com.squareup.okhttp.ResponseBody;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import retrofit2.Call;
import retrofit2.Callback;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class OutlookDetailActivity extends BaseActivity {

    private ActivityOutlookDetailBinding mBinding;
    private OutlookMessage mOutlookMessage;
    private String mAccessToken = "";
    private int mPosition = -1;

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

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
        setSupportActionBar(mBinding.toolbar);
        setWebView();
        if (Utils.hasKeyValue(getIntent(), JsonKeys.ACCESS_TOKEN)) {
            setAccessToken(Utils.getKeyValue(getIntent(), JsonKeys.ACCESS_TOKEN, ""));
        }
        if (Utils.hasPosition(getIntent())) {
            setPosition(Utils.getPosition(getIntent(), -1));
        }
        if (Utils.hasParcel(getIntent())) {
            if (Utils.getParcel(getIntent()) instanceof OutlookMessage) {
                OutlookMessage outlookMessage = (OutlookMessage) Utils.getParcel(getIntent());
                if (outlookMessage != null) {
                    setOutlookMessage(outlookMessage);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.outlook_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            deleteEmail();
            return true;
        } else if (id == R.id.reply) {
            replyEmail();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteEmail() {
        if (getOutlookMessage() != null && StringUtils.isNotNullNotEmpty(getAccessToken(), getOutlookMessage().getId())) {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseBody> call = apiService.deleteOutlookEmail("Bearer " + getAccessToken(), getOutlookMessage().getId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                    Intent intent = Utils.setApi(getIntent(), AppUrls.OutlookUrls.DELETE_EMAIL);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    makeText(OutlookDetailActivity.this, "something went wrong! Please try again...", LENGTH_SHORT).show();
                }
            });
        }
    }

    private void replyEmail() {
        startActivity(getBaseIntent(getOutlookMessage(), JsonKeys.ACCESS_TOKEN, getAccessToken(), getPosition(), ComposeActivity.class));
    }

    public OutlookMessage getOutlookMessage() {
        return mOutlookMessage;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public void setOutlookMessage(OutlookMessage mOutlookMessage) {
        this.mOutlookMessage = mOutlookMessage;
    }
}
