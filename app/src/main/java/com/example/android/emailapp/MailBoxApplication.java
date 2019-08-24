package com.example.android.emailapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.livemymail.android.mailboxapp.models.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

public class MailBoxApplication extends Application {

    private static Resources mResources;

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mResources = getResources();
        AndroidThreeTen.init(this);
        // DBFlow init
        FlowManager.init(new FlowConfig.Builder(this)
                .addDatabaseConfig(
                        new DatabaseConfig.Builder(AppDatabase.class)
                                .build())
                .build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Resources getAppResources() {
        return mResources;
    }

}