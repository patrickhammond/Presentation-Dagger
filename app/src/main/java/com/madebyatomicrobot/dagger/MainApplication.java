package com.madebyatomicrobot.dagger;

import android.app.Application;

public class MainApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .appModule(new AppModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
