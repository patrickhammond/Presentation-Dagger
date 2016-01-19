package com.madebyatomicrobot.dagger;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

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

    // See http://blog.sqisland.com/2015/12/mock-application-in-espresso.html for alternative
    // approaches if this doesn't jive for you.
    @VisibleForTesting
    void setComponent(ApplicationComponent component) {
        this.component = component;
    }
}
