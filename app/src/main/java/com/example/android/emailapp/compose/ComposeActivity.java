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
import com.example.android.emailapp.rest.ApiClient;
import com.example.android.emailapp.rest.ApiInterface;
import com.google.gson.JsonObject;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.EditTextUtils;
import com.microsoft.services.outlook.BodyType;
import com.microsoft.services.outlook.EmailAddress;
import com.microsoft.services.outlook.ItemBody;
import com.microsoft.services.outlook.Message;
import com.microsoft.services.outlook.Recipient;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;

public class ComposeActivity extends BaseActivity {

    private static final String TAG = ComposeActivity.class.getSimpleName();
    private ActivityComposeBinding mBinding;
    private Message mMessage;
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
        Message message = new Message();

        List<Recipient> recipientList = new ArrayList<>();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setAddress(EditTextUtils.getString(mBinding.etToRecepient));
        Recipient recipient = new Recipient();
        recipient.setEmailAddress(emailAddress);
        recipientList.add(recipient);
        message.setToRecipients(recipientList);

        message.setSubject(EditTextUtils.getString(mBinding.etSubject));

        ItemBody itemBody = new ItemBody();
        itemBody.setContentType(BodyType.Text);
        itemBody.setContent(EditTextUtils.getString(mBinding.etDescriptionBody));
        message.setBody(itemBody);

        setMessage(message);
        simpleCallBack.eventCallBack(new Intent());
    }

    private void sendEmail() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.sendEmail("Bearer " + accessToken, getPostDataSendEmail());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: " + response.body());
                Toast.makeText(ComposeActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private JsonObject getPostDataSendEmail() {
        JsonObject postData = new JsonObject();
        postData.add(JsonKeys.MESSAGE, getMessage());
        return postData;
    }

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }
}
