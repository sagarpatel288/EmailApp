package com.library.android.common.adapters;

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
    private List mList;

    public List getmList() {
        return mList;
    }

    public void setmList(List mList) {
        this.mList = mList;
    }

    @SuppressWarnings("unchecked")
    public void addList(List mList) {
        this.mList.addAll(mList);
    }

    @SuppressWarnings("unchecked")
    public void addItems(List mList) {
        this.mList.addAll(mList);
    }
}
