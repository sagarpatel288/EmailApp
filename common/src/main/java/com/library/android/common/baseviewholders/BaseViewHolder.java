package com.library.android.common.baseviewholders;

import android.view.View;
import android.widget.EditText;

import com.library.android.common.adapters.BaseAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sagar on 7/31/2019 at 12:39 AM
 * <p>
 * BaseViewHolder to be used in RecyclerView.Adapter$
 * <p>
 * Being used in {@link }
 *
 * @see
 * @since 1.0$
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView, BaseAdapter baseAdapter, ViewDataBinding mBinding) {
        super(itemView);
    }

    public abstract boolean isSelectableItem();

    public abstract boolean isSingleSelection();

    public abstract boolean isMultipleSelection();

    public abstract boolean isRadioSelection();

    public abstract List<View> getViewsToSetClickListener();

    public abstract List<EditText> getInputFieldsToSustainFocus();

    public abstract List<EditText> getInputFieldsToWatch();

}
