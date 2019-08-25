package com.library.android.common.adapters;

import android.content.Context;
import android.content.Intent;

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
public abstract class BaseAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List mList;
    private Callbacks.EventCallBack mEventCallBack;
    private Intent mIntent;
    private int mPaginationLimit = -1;

    public BaseAdapter(Context mContext, List mList, int mPaginationLimit, Callbacks.EventCallBack mEventCallBack) {
        this.mContext = mContext;
        this.mList = mList;
        this.mPaginationLimit = mPaginationLimit;
        this.mEventCallBack = mEventCallBack;
        setContext(mContext);
        setList(mList);
        setPaginationLimit(mPaginationLimit);
        setEventCallBack(mEventCallBack);
    }

    public BaseAdapter(Context mContext, List mList, Callbacks.EventCallBack mEventCallBack, Intent mIntent) {
        this.mContext = mContext;
        this.mList = mList;
        this.mEventCallBack = mEventCallBack;
        this.mIntent = mIntent;
        setContext(mContext);
        setList(mList);
        setEventCallBack(mEventCallBack);
        setAdapterIntent(mIntent);
    }

    public BaseAdapter(Context mContext, List mList, Callbacks.EventCallBack mEventCallBack) {
        this.mContext = mContext;
        this.mList = mList;
        this.mEventCallBack = mEventCallBack;
        setContext(mContext);
        setList(mList);
        setEventCallBack(mEventCallBack);
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
        return mList;
    }

    public void setList(List mList) {
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return getList() != null ? getList().size() : 0;
    }

    @SuppressWarnings("unchecked")
    public void addItems(List mList, boolean adjustLoadMore, int paginationLimit) {
        addList(mList, adjustLoadMore, paginationLimit);
    }

    @SuppressWarnings("unchecked")
    public void addList(List mList, boolean adjustLoadMore, int paginationLimit) {
        removeLoadMore();
        if (mList == null) {
            mList = new ArrayList();
        }
        this.mList.addAll(mList);
        if (adjustLoadMore && Utils.hasMore(mList, paginationLimit)) {
            this.mList.add(null);
        }
        notifyItemInserted(mList.size());
        notifyItemRangeChanged(mList.size(), getItemCount());
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

    public void removeLoadMore(List mList) {
        if (Utils.isNotNullNotEmpty(mList)) {
//            mList.removeAll(Collections.singletonList(null));
//            notifyDataSetChanged();
            int lastIndex = mList.size() - 1;
            if (mList.get(lastIndex) == null) {
                mList.remove(lastIndex);
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

}
