package com.example.android.emailapp.compose;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.emailapp.R;
import com.example.android.emailapp.baseui.BaseActivity;
import com.example.android.emailapp.constants.AppKeys;
import com.example.android.emailapp.constants.JsonKeys;
import com.example.android.emailapp.databinding.ActivityComposeBinding;
import com.example.android.emailapp.pojos.Body;
import com.example.android.emailapp.pojos.OutlookEmailAddress;
import com.example.android.emailapp.pojos.OutlookMessage;
import com.example.android.emailapp.pojos.ToRecipient;
import com.example.android.emailapp.rest.ApiClient;
import com.example.android.emailapp.rest.ApiInterface;
import com.google.gson.JsonObject;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.EditTextUtils;
import com.library.android.common.utils.Utils;
import com.microsoft.services.outlook.BodyType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;

public class ComposeActivity extends BaseActivity {

    private static final String TAG = ComposeActivity.class.getSimpleName();
    private ActivityComposeBinding mBinding;
    private OutlookMessage mMessage;
    private String accessToken = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_compose;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mBinding = ActivityComposeBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {
        if (getIntent() != null && getIntent().hasExtra(AppKeys.ACCESS_TOKEN)) {
            accessToken = getIntent().getStringExtra(AppKeys.ACCESS_TOKEN);
        }
    }

    @Override
    public void handleViews() {

    }

    @Override
    public void setListeners() {
        setEditTextChangeListener();
        mBinding.mbtnSend.setOnClickListener(v -> onClickSend());
    }

    private void setEditTextChangeListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onTextChange(s, String.valueOf(s));
            }
        };
//        ViewUtils.addTextWatcher(textWatcher, mBinding.etToRecepient, mBinding.etCcBcc, mBinding.etSubject, mBinding.etDescriptionBody);
    }

    private void onClickSend() {
        prepareMessage(intent -> {
            sendEmail();
        });
    }

    private void onTextChange(Editable editable, String newText) {
       /* if (editable == mBinding.etToRecepient.getEditableText()) {

        }*/
    }

    private void prepareMessage(Callbacks.SimpleCallBack simpleCallBack) {
        OutlookMessage message = new OutlookMessage();

        List<ToRecipient> recipientList = new ArrayList<>();
        OutlookEmailAddress emailAddress = new OutlookEmailAddress();
        emailAddress.setAddress(EditTextUtils.getString(mBinding.etToRecepient));
        ToRecipient recipient = new ToRecipient();
        recipient.setEmailAddress(emailAddress);
        recipientList.add(recipient);
        message.setToRecipients(recipientList);

        message.setSubject(EditTextUtils.getString(mBinding.etSubject));

        Body itemBody = new Body();
        itemBody.setContentType(String.valueOf(BodyType.Text));
        itemBody.setContent(EditTextUtils.getString(mBinding.etDescriptionBody));
        message.setBody(itemBody);

        setMessage(message);
        simpleCallBack.eventCallBack(new Intent());
    }

    private void sendEmail() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiService.sendEmail("Bearer " + accessToken, getPostDataSendEmail());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                Log.d(TAG, "onResponse: " + response.body());
                Toast.makeText(ComposeActivity.this, "sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(ComposeActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private JsonObject getPostDataSendEmail() {
        JsonObject postData = new JsonObject();
        JsonObject message = Utils.getJsonObject(getMessage());
        postData.add(JsonKeys.MESSAGE, message);
        return postData;
    }

    public OutlookMessage getMessage() {
        return mMessage;
    }

    public void setMessage(OutlookMessage mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }
}
