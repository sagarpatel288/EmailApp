package com.example.android.emailapp.baseui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.example.android.emailapp.R;
import com.library.android.common.baseconstants.BaseKeys;
import com.library.android.common.databinding.BaseViewStubLayoutBinding;
import com.library.android.common.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    BaseViewStubLayoutBinding mBinding;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.view_stub)
    ViewStub viewStub;

    private boolean hasStubInflated;
    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Normal method to inflate the layout
        setContentView(R.layout.base_view_stub_layout);
        ButterKnife.bind(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //Data binding to inflate the layout and binding views at the same time
//        binding = BaseViewStubLayoutBinding.inflate(getLayoutInflater());
        /*For fragments, listview or recyclerview adapter, we can use below method:
        binding = DataBindingUtil.setContentView(this, R.layout.base_activity_layout);*/
        viewStub.setLayoutResource(getLayoutId());

        if (!hasStubInflated) {
            View inflatedView = viewStub.inflate();
            //Abstract method
            onViewStubInflated(inflatedView, savedInstanceState);
            //Normal method to hide progress bar
            onViewStubInflated();
        }
        initControllers();
        handleViews();
        setListeners();
        restoreValues(savedInstanceState);
    }

    public abstract int getLayoutId();

    //Bind the inflatedView for data binding
    public abstract void onViewStubInflated(View inflatedView, Bundle savedInstanceState);

    private void onViewStubInflated() {
        hasStubInflated = true;
        hideProgressbar();
    }

    public abstract void initControllers();

    public abstract void handleViews();

    public abstract void setListeners();

    public abstract void restoreValues(Bundle savedInstanceState);

    public void hideProgressbar() {
        if (progressbar != null) {
            progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hasStubInflated = false;
    }

    public ViewModel getViewModel(@NonNull Class viewModelClass) {
        if (viewModelClass != null) {
            return ViewModelProviders.of(this).get(viewModelClass);
        } else {
            return null;
        }
    }

    public Intent getBaseIntent(String key, String value, Class targetClass) {
        mIntent = getBaseIntent();
        mIntent.putExtra(key, value);
        mIntent.setClass(this, targetClass);
        return mIntent;
    }

    public Intent getBaseIntent() {
        if (mIntent == null) {
            mIntent = new Intent();
        }
        mIntent.putExtra(BaseKeys.SOURCE_SCREEN, getClass().getSimpleName());
        return mIntent;
    }

    public Intent getBaseIntent(Object parcelableObject, String key, String value, int position, Class targetClass) {
        mIntent = getBaseIntent(parcelableObject, position, targetClass);
        mIntent.putExtra(key, value);
        return mIntent;
    }

    public Intent getBaseIntent(Object parcelableObject, int position, Class targetClass) {
        mIntent = getBaseIntent(parcelableObject, targetClass);
        mIntent.putExtra(BaseKeys.POSITION, position);
        return mIntent;
    }

    public Intent getBaseIntent(Object parcelableObject, Class targetClass) {
        mIntent = getBaseIntent();
        mIntent.putExtra(BaseKeys.PARCEL, (Parcelable) parcelableObject);
        mIntent.setClass(this, targetClass);
        return mIntent;
    }

    public String getSourceScreen() {
        if (Utils.hasSourceScreen(getIntent())) {
            return Utils.getSourceScreen(getIntent(), "");
        }
        return "";
    }
}
