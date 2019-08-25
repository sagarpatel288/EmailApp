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
import com.library.android.common.adapters.BaseAdapter;
import com.library.android.common.baseconstants.BaseConstants;
import com.library.android.common.baseviewholders.ProgressLoadingHolder;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.StringUtils;
import com.library.android.common.utils.Utils;
import com.library.android.common.utils.ViewUtils;

import org.threeten.bp.Instant;

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
public class RvOutlookEmailAdapter extends BaseAdapter  {

    public static final String TAG = RvOutlookEmailAdapter.class.getSimpleName();
    // This object helps you save/restore the open/close state of each view
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public RvOutlookEmailAdapter(Context context, List<OutlookMessage> list, Callbacks.EventCallBack eventCallBack) {
        super(context, list, eventCallBack);
        viewBinderHelper.setOpenOnlyOne(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BaseConstants.ItemViewType.PROGRESS_BAR_CIRCULAR_LOADING) {
            return new ProgressLoadingHolder(getContext(), parent, false);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
            ItemEmailBinding mBinding = ItemEmailBinding.bind(view);
            return new ItemViewHolder(view, mBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != -1 && getList().get(position) != null && holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            OutlookMessage outlookMessage = (OutlookMessage) getList().get(position);
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
                    getContext(), receivedDateTime.toEpochMilli(), DateUtils.SECOND_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List getFilteredList(String query) {
        if (StringUtils.isNullOrEmpty(query)) {
            return getOriginalList();
        }
        List<OutlookMessage> originalList = getOriginalList();
        if (Utils.isNotNullNotEmpty(originalList)) {
            return Utils.getFilteredList(originalList, input -> input != null
                    && input.getSender() != null
                    && input.getSender().getEmailAddress() != null
                    && StringUtils.contains(input.getSender().getEmailAddress().getAddress(), query));
        }
        return getOriginalList();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemEmailBinding mBinding;

        ItemViewHolder(@NonNull View itemView, ItemEmailBinding mBinding) {
            super(itemView);
            this.mBinding = mBinding;
            ViewUtils.setOnClickListener(this, itemView, mBinding.layoutFrontMain, mBinding.lytItemParent, mBinding.layoutDelete, mBinding.cbtnDelete);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (clickedPosition != -1 && Utils.isNotNullNotEmpty(getList()) && getList().get(clickedPosition) != null) {
                OutlookMessage outlookMessage = (OutlookMessage) getList().get(clickedPosition);
                if (outlookMessage != null && getEventCallBack() != null) {
                    getEventCallBack().onEventCallBack(v, clickedPosition, Utils.setParcel(new Intent(), outlookMessage));
                }
            }
        }
    }
}
