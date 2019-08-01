package com.library.android.common.ui.baseui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.library.android.common.R;
import com.library.android.common.R2;
import com.library.android.common.databinding.BaseViewStubLayoutBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    BaseViewStubLayoutBinding binding;

    @BindView(R2.id.progressbar)
    ProgressBar progressbar;
    @BindView(R2.id.view_stub)
    ViewStub viewStub;

    private boolean hasStubInflated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Normal method to inflate the layout
        setContentView(R.layout.base_view_stub_layout);
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

        ButterKnife.bind(this);
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
}
