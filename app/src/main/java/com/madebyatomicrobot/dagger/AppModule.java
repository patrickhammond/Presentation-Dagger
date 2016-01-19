package com.madebyatomicrobot.dagger;

import android.location.Location;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private List<Location> allLocations = new LinkedList<>();

    @Provides
    public List<Location> provideLocations() {
        return allLocations;
    }
}
