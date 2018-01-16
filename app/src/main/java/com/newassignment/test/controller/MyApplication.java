package com.newassignment.test.controller;

import android.app.Application;

import com.newassignment.test.dagger.ApiComponent;
import com.newassignment.test.dagger.ApiModule;
import com.newassignment.test.dagger.AppModule;
import com.newassignment.test.dagger.DaggerApiComponent;


public class MyApplication extends Application {

    private ApiComponent mApiComponent;
    String apiurl="http://api.openweathermap.org/data/2.5/";

    @Override
    public void onCreate() {
        super.onCreate();
        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(apiurl))
                .build();


    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}