package com.madebyatomicrobot.dagger;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
    private final Context application;

    public AndroidModule(Application application) {
        this.application = application;
    }

    @Provides
    public LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }
}
