package com.library.android.common.ui.baseui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.library.android.common.R;
import com.library.android.common.R2;
import com.library.android.common.listeners.Callbacks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements Callbacks.OnFragmentLoad {

    @BindView(R2.id.progressbar)
    ProgressBar progressbar;
    @BindView(R2.id.view_stub)
    ViewStub viewStub;
    Unbinder unbinder;
    private boolean hasViewStubInflated;
    private Bundle mSavedInstanceState;

    @Override
    public void onFragmentVisible() {

    }

    @Override
    public void onFragmentHide() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && viewStub != null && !hasViewStubInflated) {
            inflateViewStub();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_view_stub_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        viewStub.setLayoutResource(getLayoutId());
        mSavedInstanceState = savedInstanceState;

        if (getUserVisibleHint() && !hasViewStubInflated) {
            inflateViewStub();
        }
        return view;
    }

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void inflateViewStub() {
        if (viewStub != null && !hasViewStubInflated) {
            View inflatedView = viewStub.inflate();
            onViewStubInflated(inflatedView, mSavedInstanceState);
            onViewStubInflated();
        }
        initControllers();
        handleViews();
        setListeners();
        restoreValues(mSavedInstanceState);
    }

    //Bind the inflatedView for data binding
    public abstract void onViewStubInflated(View inflatedView, Bundle savedInstanceState);

    private void onViewStubInflated() {
        hasViewStubInflated = true;
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
}
