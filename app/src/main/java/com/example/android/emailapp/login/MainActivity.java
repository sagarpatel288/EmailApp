package com.example.android.emailapp.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.emailapp.R;
import com.example.android.emailapp.baseui.BaseActivity;
import com.example.android.emailapp.compose.ComposeActivity;
import com.example.android.emailapp.constants.AppKeys;
import com.example.android.emailapp.constants.JsonKeys;
import com.example.android.emailapp.databinding.ActivityMainBinding;
import com.example.android.emailapp.navdrawer.inbox.RvOutlookEmailAdapter;
import com.example.android.emailapp.pojos.OutlookAccess;
import com.example.android.emailapp.pojos.OutlookDetail;
import com.example.android.emailapp.pojos.OutlookMessage;
import com.example.android.emailapp.pojos.OutlookResponse;
import com.example.android.emailapp.rest.ApiClient;
import com.example.android.emailapp.rest.ApiInterface;
import com.library.android.common.utils.StringUtils;
import com.library.android.common.utils.Utils;
import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;

import static com.example.android.emailapp.constants.AppApi.REDIRECT_URI;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    /* Azure AD v2 Configs */
    /*Office 365 Mail API: https://outlook.office.com*/
    final static String[] SCOPES = {"https://graph.microsoft.com/User.Read",
            "https://graph.microsoft.com/Mail.ReadWrite",
            "https://graph.microsoft.com/Mail.Send"};

    /*https://graph.microsoft.com/v1.0/me/messages*/
    private static final String OUTLOOK_AUTH_URI = "https://login.microsoftonline.com/common/oauth2/v2.0/";
    private static final String CLIENT_ID = "5f620588-8746-4c7d-8bfd-ea5331a4b1d2";
    private static final String CLIENT_SECRET = "3*I/iUA3z+FL4+y35-4V[T+2EdtLY+qp";
    /*GET https://login.microsoftonline.com/common/oauth2/v2.0/
    authorize?client_id=<CLIENT ID>
    &redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
    &response_type=code
    &scope=openid+Mail.Read*/

    /* Azure AD Variables */
    private PublicClientApplication emailApp;
    private IAuthenticationResult authResult;
    private ActivityMainBinding mBinding;

    private String mAccessCode = "";
    private RvOutlookEmailAdapter mRvOutlookEmailAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mBinding = ActivityMainBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {
        configureAzure();
    }

    private void configureAzure() {
        /* Configure your sample app and save state for this activity */
        emailApp = new PublicClientApplication(
                this.getApplicationContext(),
                R.raw.auth_outlook);

        /* Attempt to get a user and acquireTokenSilent
         * If this fails we do an interactive request
         */
        emailApp.getAccounts(accounts -> {

            if (!accounts.isEmpty()) {
                /* This sample doesn't support multi-account scenarios, use the first account */
                emailApp.acquireTokenSilentAsync(SCOPES, accounts.get(0), getAuthSilentCallback());
            } /*else {
             *//* No accounts or >1 account *//*
            }*/
        });
    }

    /* Callback used in for silent acquireToken calls.
     * Looks if tokens are in the cache (refreshes if necessary and if we don't forceRefresh)
     * else errors that we need to do an interactive request.
     */
    private AuthenticationCallback getAuthSilentCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                /* Successfully got a token, call graph now */
                Log.d(TAG, "Successfully authenticated");

                /* Store the authResult */
                authResult = authenticationResult;

                /* call graph */
                callGraphAPI();

                /* update the UI to post call graph state */
                updateSuccessUI();
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());
                /* if (exception instanceof MsalClientException) {
                 *//* Exception inside MSAL, more info inside MsalError.java *//*
                } else if (exception instanceof MsalServiceException) {
                    *//* Exception when communicating with the STS, likely config issue *//*
                } else if (exception instanceof MsalUiRequiredException) {
                    *//* Tokens expired or no session, retry with interactive *//*
                }*/
            }

            @Override
            public void onCancel() {
                /* User cancelled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    private void callGraphAPI() {
        /* Make sure we have a token to send to graph */
       /* if (authResult.getAccessToken() == null) {
            return;
        }*/
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        /*headers.put("Authorization", "Bearer " + authResult.getAccessToken());*/
        Log.d(TAG, "callGraphAPI: accessToken: " + authResult.getAccessToken());
        Call<OutlookResponse> call = apiService.getAllMessages("Bearer " + authResult.getAccessToken());
        call.enqueue(new Callback<OutlookResponse>() {
            @Override
            public void onResponse(@NonNull Call<OutlookResponse> call, @NonNull retrofit2.Response<OutlookResponse> response) {
                if (response.body() != null) {
                    List<OutlookMessage> emails = response.body().getValues();
                    onGetOutLookEmailList(emails);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OutlookResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void updateSuccessUI() {

    }

    private void onGetOutLookEmailList(List<OutlookMessage> emails) {
        if (mRvOutlookEmailAdapter != null) {
            mRvOutlookEmailAdapter.addItems(emails);
        }
    }

    @Override
    public void handleViews() {
        setRecyclerView();
    }

    private void setRecyclerView() {
        mBinding.rvEmail.setVisibility(View.VISIBLE);
        mBinding.rvEmail.setNestedScrollingEnabled(false);
        mRvOutlookEmailAdapter = new RvOutlookEmailAdapter(this, new ArrayList<>(), this::onClickEmail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvEmail.setLayoutManager(linearLayoutManager);
        mBinding.rvEmail.setAdapter(mRvOutlookEmailAdapter);
    }

    private void onClickEmail(View view, int clickedPosition, Intent intent) {
        if (Utils.hasParcel(intent)) {
            if (Utils.getParcel(intent) instanceof OutlookDetail) {
                OutlookDetail clickedEmail = (OutlookDetail) Utils.getParcel(intent);
                Toast.makeText(this, "" + clickedEmail.getBodyPreview(), Toast.LENGTH_SHORT).show();
                deleteEmail(clickedPosition, clickedEmail);
            }
        }
    }

    private void deleteEmail(int clickedPosition, OutlookDetail clickedEmail) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.deleteOutlookEmail("Bearer " + authResult.getAccessToken(), clickedEmail.getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                if (mRvOutlookEmailAdapter != null) {
                    mRvOutlookEmailAdapter.removeItem(clickedPosition);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void setListeners() {
        mBinding.fabCompose.setOnClickListener(v -> onClickCompose());
    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }

    /* Use MSAL to acquireToken for the end-user
     * Callback will call Graph api w/ access token & update UI
     */
    private void onCallGraphClicked() {
        emailApp.acquireToken(this, SCOPES, getAuthInteractiveCallback());
    }

    private void onClickCompose() {
        if (authResult != null && StringUtils.isNotNullNotEmpty(authResult.getAccessToken())) {
            startActivity(getBaseIntent(AppKeys.ACCESS_TOKEN, authResult.getAccessToken(), ComposeActivity.class));
        } else {
            Toast.makeText(this, "Please verify first", Toast.LENGTH_SHORT).show();
        }
    }

    /* Callback used for interactive request.  If succeeds we use the access
     * token to call the Microsoft Graph. Does not check cache
     */
    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                /* Successfully got a token, call graph now */
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getIdToken());

                /* Store the auth result */
                authResult = authenticationResult;

                /* call graph */
                callGraphAPI();

                /* update the UI to post call graph state */
                updateSuccessUI();
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());
                /*  if (exception instanceof MsalClientException) {
                 *//* Exception inside MSAL, more info inside MsalError.java *//*
                } else if (exception instanceof MsalServiceException) {
                    *//* Exception when communicating with the STS, likely config issue *//*
                }*/
            }

            @Override
            public void onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    private void initializeWebView() {
        /*client_id: the client ID generated by registering the app.
client_secret: the client secret generated by registering the app.
code: the authorization code obtained in the prior step.
redirect_uri: this value must be the same as the value used in the authorization code request.
grant_type: the type of grant the app is using. For the Authorization Grant Flow, this should always be authorization_code.*/
        /*
Mail.Read Application Read mail in all mailboxes Yes
Mail.ReadBasic Application Read basic mail in all mailboxes Yes
Mail.ReadBasic.All Application Read basic mail in all mailboxes Yes
Mail.ReadWrite Application Read and write mail in all mailboxes Yes
Mail.Send*/
        String url = OUTLOOK_AUTH_URI
                + "authorize/?client_id="
                + CLIENT_ID
                + "&redirect_uri="
                + REDIRECT_URI
                + "&response_type=code"
                + "&scope=openid+Mail.ReadWrite"
                + "&prompt=consent";
        mBinding.webView.loadUrl(url);
        mBinding.webView.getSettings().setJavaScriptEnabled(true);
        mBinding.webView.setWebViewClient(new WebViewClient() {
            boolean authComplete = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /*?code=M35820dcf-c6dd-68d7-298a-43bf3c52bdaf*/
                if (url.contains("code=") && !authComplete) {
                    /*https://www.google.com/?code=M5bf036f1-f173-bed1-806b-2c13203ac9d6*/
                    mAccessCode = url;
                    // get the whole token after the '=' sign
                    if (StringUtils.isNotNullNotEmpty(mAccessCode)) {
                        mAccessCode = mAccessCode.substring(mAccessCode.lastIndexOf("=") + 1);
                        Log.i("", "CODE : " + mAccessCode);
                        authComplete = true;
                        mBinding.webView.setVisibility(View.GONE);
                        onGetAccessCode(mAccessCode);
                    }
                } else if (url.contains("?error")) {
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    mBinding.webView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void onGetAccessCode(String accessCode) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<OutlookAccess> call = apiService.getOutLookAccesToken(JsonKeys.AUTHORIZATION_CODE, accessCode, REDIRECT_URI, CLIENT_ID, CLIENT_SECRET);
        call.enqueue(new Callback<OutlookAccess>() {
            @Override
            public void onResponse(@NonNull Call<OutlookAccess> call, @NonNull retrofit2.Response<OutlookAccess> response) {
                if (response.body() != null) {
                    OutlookAccess outlookAccess = response.body();
                    onGetOutLookAccess(outlookAccess);
                    Log.d(MainActivity.class.getSimpleName(), "accessToken: " + outlookAccess.getAccessToken());
                }
            }

            @Override
            public void onFailure(@NonNull Call<OutlookAccess> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    //
    // Core Identity methods used by MSAL
    // ==================================
    // onCallGraphClicked() - attempts to get tokens for graph, if it succeeds calls graph & updates UI
    // onSignOutClicked() - Signs account out of the app & updates UI
    // callGraphAPI() - called on successful token acquisition which makes an HTTP request to graph
    //
    private void onGetOutLookAccess(OutlookAccess outlookAccess) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<OutlookResponse> call = apiService.getAllMessages("Bearer " + outlookAccess.getAccessToken());
        call.enqueue(new Callback<OutlookResponse>() {
            @Override
            public void onResponse(@NonNull Call<OutlookResponse> call, @NonNull retrofit2.Response<OutlookResponse> response) {
                if (response.body() != null) {
                    List<OutlookMessage> emails = response.body().getValues();
                    onGetOutLookEmailList(emails);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OutlookResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
