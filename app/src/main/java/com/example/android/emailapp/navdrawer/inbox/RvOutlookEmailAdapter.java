package com.example.android.emailapp.navdrawer.inbox;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.android.emailapp.R;
import com.example.android.emailapp.databinding.ItemEmailBinding;
import com.example.android.emailapp.pojos.OutlookMessage;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.Utils;
import com.library.android.common.utils.ViewUtils;

import org.threeten.bp.Instant;

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
public class RvOutlookEmailAdapter extends RecyclerView.Adapter {

    private List<OutlookMessage> outLookEmailList = new ArrayList<>();
    private Callbacks.AddEventCallBack eventCallBack;
    private Context context;
    public static final String TAG = RvOutlookEmailAdapter.class.getSimpleName();
    // This object helps you save/restore the open/close state of each view
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public RvOutlookEmailAdapter(Context context, List<OutlookMessage> outLookEmailList, Callbacks.AddEventCallBack eventCallBack) {
        this.context = context;
        this.outLookEmailList = outLookEmailList;
        this.eventCallBack = eventCallBack;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public List<OutlookMessage> getOutLookEmailList() {
        return outLookEmailList;
    }

    public void setOutLookEmailList(List<OutlookMessage> outLookEmailList) {
        this.outLookEmailList = outLookEmailList;
    }

    public Callbacks.AddEventCallBack getEventCallBack() {
        return eventCallBack;
    }

    public void setEventCallBack(Callbacks.AddEventCallBack eventCallBack) {
        this.eventCallBack = eventCallBack;
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
            OutlookMessage outlookMessage = outLookEmailList.get(position);
            // Save/restore the open/close state.
            // You need to provide a String id which uniquely defines the data object.
            viewBinderHelper.bind(itemViewHolder.mBinding.swipeLayout, outlookMessage.getId());
            if (outlookMessage.getSender() != null && outlookMessage.getSender().getEmailAddress() != null) {
                itemViewHolder.mBinding.txtFromPreview.setText(outlookMessage.getSender().getEmailAddress().getName().substring(0, 1).toUpperCase());
                itemViewHolder.mBinding.txtFrom.setText(outlookMessage.getSender().getEmailAddress().getName());
            }
            itemViewHolder.mBinding.txtSubject.setText(outlookMessage.getSubject());
            itemViewHolder.mBinding.txtSnippet.setText(outlookMessage.getBodyPreview());

            Instant receivedDateTime = Instant.parse(outlookMessage.getReceivedDateTime());
            itemViewHolder.mBinding.txtDate.setText(DateUtils.getRelativeDateTimeString(
                    context, receivedDateTime.toEpochMilli(), DateUtils.SECOND_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE));
        }
    }

    @Override
    public int getItemCount() {
        return outLookEmailList != null ? outLookEmailList.size() : 0;
    }

    public void addItems(List<OutlookMessage> emails) {
        this.outLookEmailList.addAll(emails);
        notifyDataSetChanged();
    }

    public void removeItem(int clickedPosition) {
        if (clickedPosition != -1 && Utils.isNotNullNotEmpty(outLookEmailList) && outLookEmailList.size() > clickedPosition) {
            outLookEmailList.remove(clickedPosition);
            notifyItemRemoved(clickedPosition);
            notifyItemRangeChanged(clickedPosition, getItemCount());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemEmailBinding mBinding;

        public ItemViewHolder(@NonNull View itemView, ItemEmailBinding mBinding) {
            super(itemView);
            this.mBinding = mBinding;
            ViewUtils.setOnClickListener(this, mBinding.layoutDelete, mBinding.cbtnDelete);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (clickedPosition != -1 && Utils.isNotNullNotEmpty(outLookEmailList)) {
                OutlookMessage outlookMessage = outLookEmailList.get(clickedPosition);
                if (outlookMessage != null && getEventCallBack() != null) {
                    getEventCallBack().onEventCallBack(v, clickedPosition, Utils.setParcel(new Intent(), outlookMessage));
                }
            }
        }
    }
}
