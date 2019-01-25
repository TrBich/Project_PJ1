package com.example.congduy.landmonitoring;

import android.app.Activity;
import android.os.Bundle;

import com.paditech.core.BaseApplication;
import com.paditech.core.helper.FragmentHelper;

public class MainApplication extends BaseApplication implements AppConstant {

    @Override
    public void onCreate() {
        super.onCreate();
        FragmentHelper.setRoot(R.id.container);

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        app.loadFromCache();
        super.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
        app.saveToCache();
    }


}
