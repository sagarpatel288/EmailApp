package com.library.android.common.ui.baseui;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * Created by Sagar on 7/31/2019 at 10:29 AM
 * <p>
 * $
 * <p>
 * Being used in {@link }
 *
 * @see
 * @since 1.0$
 */
public class MyApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
