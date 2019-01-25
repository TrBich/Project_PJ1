package com.example.congduy.landmonitoring.network;

import com.example.congduy.landmonitoring.AppConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paditech.core.network.BaseClient;
import com.paditech.core.network.NetworkConfigImpl;

import java.util.concurrent.TimeUnit;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient extends BaseClient<AppService> implements AppConstant {
    public static AppClient newInstance() {
        return new AppClient();
    }

    @Override
    public AppService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getHostAddress())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AppService.class);
    }

    @Override
    protected Class<AppService> getServiceType() {
        return AppService.class;
    }

    @Override
    protected boolean isEnableDebug() {
        return isDebug;
    }

    @Override
    protected String getHostAddress() {
        return "http://192.168.1.13/Project1/";
    }

    @Override
    public NetworkConfigImpl onConfigsSetup(NetworkConfigImpl config) {
        config.setWriteTimeOut(120, TimeUnit.SECONDS);
        return super.onConfigsSetup(config);
    }

    @Override
    protected Converter.Factory getConverter() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return GsonConverterFactory.create(gson);
    }
}
