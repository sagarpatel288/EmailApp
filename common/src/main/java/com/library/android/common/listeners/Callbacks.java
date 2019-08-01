package com.library.android.common.listeners;


import android.content.Intent;
import android.view.View;

public abstract class Callbacks {

    public interface OnFragmentLoad {
        void onFragmentVisible();

        void onFragmentHide();
    }

    public interface SimpleCallBack {
        void eventCallBack(Intent intent);
    }

    public interface AddEventCallBack {
        void onEventCallBack(View view, int clickedPosition, Intent intent);
    }

    public interface NetworkConnectionListener {
        void onConnectionChanged(boolean isConnected);
    }
}
