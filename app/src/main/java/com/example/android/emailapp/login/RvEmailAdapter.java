package com.example.android.emailapp.login;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.emailapp.R;
import com.example.android.emailapp.databinding.ItemEmailBinding;
import com.example.android.emailapp.pojos.OutlookDetail;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sagar on 7/31/2019 at 2:58 AM
 * <p>
 * $
 * <p>
 * Being used in {@link }
 *
 * @see
 * @since $
 */
public class RvEmailAdapter extends RecyclerView.Adapter {

    private List<OutlookDetail> outLookEmailList = new ArrayList<>();
    private Callbacks.AddEventCallBack addEventCallBack;

    public RvEmailAdapter(List<OutlookDetail> outLookEmailList, Callbacks.AddEventCallBack addEventCallBack) {
        this.outLookEmailList = outLookEmailList;
        this.addEventCallBack = addEventCallBack;
    }

    public List<OutlookDetail> getOutLookEmailList() {
        return outLookEmailList;
    }

    public void setOutLookEmailList(List<OutlookDetail> outLookEmailList) {
        this.outLookEmailList = outLookEmailList;
    }

    public Callbacks.AddEventCallBack getAddEventCallBack() {
        return addEventCallBack;
    }

    public void setAddEventCallBack(Callbacks.AddEventCallBack addEventCallBack) {
        this.addEventCallBack = addEventCallBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
        ItemEmailBinding mBinding = ItemEmailBinding.bind(view);
        return new ItemViewHolder(view, mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != -1 && holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            OutlookDetail outlookDetail = outLookEmailList.get(position);
            itemViewHolder.mBinding.ctvEmailTitle.setText(outlookDetail.getBodyPreview());
        }
    }

    @Override
    public int getItemCount() {
        return outLookEmailList != null ? outLookEmailList.size() : 0;
    }

    public void addItems(List<OutlookDetail> emails) {
        this.outLookEmailList.addAll(emails);
        notifyDataSetChanged();
    }

    public void removeItem(int clickedPosition) {
        if (clickedPosition != -1 && Utils.isNotNullNotEmpty(outLookEmailList) && outLookEmailList.size() > clickedPosition) {
            outLookEmailList.remove(clickedPosition);
            notifyItemRemoved(clickedPosition);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemEmailBinding mBinding;

        public ItemViewHolder(@NonNull View itemView, ItemEmailBinding mBinding) {
            super(itemView);
            this.mBinding = mBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (clickedPosition != -1 && Utils.isNotNullNotEmpty(outLookEmailList)) {
                OutlookDetail outlookDetail = outLookEmailList.get(clickedPosition);
                if (outlookDetail != null && getAddEventCallBack() != null) {
                    getAddEventCallBack().onEventCallBack(v, clickedPosition, Utils.setParcel(new Intent(), outlookDetail));
                }
            }
        }
    }
}
