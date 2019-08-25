package com.library.android.common.adapters;

import android.content.Context;
import android.content.Intent;
import android.widget.Filter;
import android.widget.Filterable;

import com.library.android.common.baseconstants.BaseConstants;
import com.library.android.common.listeners.Callbacks;
import com.library.android.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sagar on 7/31/2019 at 12:42 AM
 * <p>
 * A RecyclerView.Adapter to be used $
 * <p>
 * Being used in {@link }
 *
 * @see
 * @since 1.0$
 */
public abstract class BaseAdapter extends RecyclerView.Adapter implements Filterable {

    private Context mContext;
    private List mFilteredList;
    private List mOriginalList;
    private Callbacks.EventCallBack mEventCallBack;
    private Intent mIntent;
    private int mPaginationLimit = -1;

    public BaseAdapter(Context mContext, List mFilteredList, int mPaginationLimit, Callbacks.EventCallBack mEventCallBack) {
        this.mContext = mContext;
        this.mFilteredList = mFilteredList;
        this.mPaginationLimit = mPaginationLimit;
        this.mEventCallBack = mEventCallBack;
        setContext(mContext);
        setList(mFilteredList);
        setOriginalList(mFilteredList);
        setPaginationLimit(mPaginationLimit);
        setEventCallBack(mEventCallBack);
    }

    public BaseAdapter(Context mContext, List mFilteredList, Callbacks.EventCallBack mEventCallBack, Intent mIntent) {
        this.mContext = mContext;
        this.mFilteredList = mFilteredList;
        this.mEventCallBack = mEventCallBack;
        this.mIntent = mIntent;
        setContext(mContext);
        setList(mFilteredList);
        setOriginalList(mFilteredList);
        setEventCallBack(mEventCallBack);
        setAdapterIntent(mIntent);
    }

    public BaseAdapter(Context mContext, List mFilteredList, Callbacks.EventCallBack mEventCallBack) {
        this.mContext = mContext;
        this.mFilteredList = mFilteredList;
        this.mEventCallBack = mEventCallBack;
        setContext(mContext);
        setList(mFilteredList);
        setOriginalList(mFilteredList);
        setEventCallBack(mEventCallBack);
    }

    public List getOriginalList() {
        return mOriginalList;
    }

    public void setOriginalList(List mOriginalList) {
        this.mOriginalList = mOriginalList;
    }

    public int getPaginationLimit() {
        return mPaginationLimit;
    }

    public void setPaginationLimit(int mPaginationLimit) {
        this.mPaginationLimit = mPaginationLimit;
    }

    public String getString(int stringResId) {
        return getContext() != null ? getContext().getString(stringResId) : "";
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public Callbacks.EventCallBack getEventCallBack() {
        return mEventCallBack;
    }

    public void setEventCallBack(Callbacks.EventCallBack mEventCallBack) {
        this.mEventCallBack = mEventCallBack;
    }

    public Intent getAdapterIntent() {
        return mIntent;
    }

    public void setAdapterIntent(Intent mIntent) {
        this.mIntent = mIntent;
    }

    @Override
    public int getItemViewType(int position) {
        if (Utils.isNotNullNotEmpty(getList()) && Utils.hasElement(getList(), position) && getList().get(position) == null) {
            return BaseConstants.ItemViewType.PROGRESS_BAR_CIRCULAR_LOADING;
        } else {
            return BaseConstants.ItemViewType.ITEM_VIEW_TYPE;
        }
    }

    public List getList() {
        return mFilteredList;
    }

    public void setList(List mFilteredList) {
        this.mFilteredList = mFilteredList;
    }

    @Override
    public int getItemCount() {
        return getList() != null ? getList().size() : 0;
    }

    @SuppressWarnings("unchecked")
    public void addItems(List mFilteredList, boolean adjustLoadMore, int paginationLimit) {
        addList(mFilteredList, adjustLoadMore, paginationLimit);
    }

    @SuppressWarnings("unchecked")
    public void addList(List mFilteredList, boolean adjustLoadMore, int paginationLimit) {
        removeLoadMore();
        if (mFilteredList == null) {
            mFilteredList = new ArrayList();
        }
        this.mFilteredList.addAll(mFilteredList);
        setOriginalList(this.mFilteredList);
        if (adjustLoadMore && Utils.hasMore(mFilteredList, paginationLimit)) {
            this.mFilteredList.add(null);
        }
        notifyItemInserted(mFilteredList.size());
        notifyItemRangeChanged(mFilteredList.size(), getItemCount());
    }

    public void removeLoadMore() {
        if (Utils.isNotNullNotEmpty(getList())) {
//            getList().removeAll(Collections.singletonList(null));
//            notifyDataSetChanged();
            int lastIndex = getList().size() - 1;
            if (getList().get(lastIndex) == null) {
                getList().remove(lastIndex);
                notifyItemRemoved(lastIndex);
                notifyItemRangeChanged(lastIndex, getItemCount());
            }
        }
    }

    public void removeLoadMore(List mFilteredList) {
        if (Utils.isNotNullNotEmpty(mFilteredList)) {
//            mFilteredList.removeAll(Collections.singletonList(null));
//            notifyDataSetChanged();
            int lastIndex = mFilteredList.size() - 1;
            if (mFilteredList.get(lastIndex) == null) {
                mFilteredList.remove(lastIndex);
                notifyItemRemoved(lastIndex);
                notifyItemRangeChanged(lastIndex, getItemCount());
            }
        }
    }

    public void removeItem(int position) {
        if (position != -1 && Utils.isNotNullNotEmpty(getList()) && getList().size() > position) {
            getList().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                FilterResults filterResults = new FilterResults();
                mFilteredList = getFilteredList(charString);
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public abstract List getFilteredList(String query);
}
