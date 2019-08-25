package com.library.android.common.baseviewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.android.common.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgressLoadingHolder extends RecyclerView.ViewHolder {

    public ProgressLoadingHolder(Context context, ViewGroup root, boolean attachToRoot) {
        super(LayoutInflater.from(context).inflate(R.layout.layout_progress_bar, root, attachToRoot));
    }

    public ProgressLoadingHolder(@NonNull View itemView) {
        super(itemView);
    }
}
