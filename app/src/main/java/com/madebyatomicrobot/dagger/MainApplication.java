package com.madebyatomicrobot.dagger;

import android.app.Application;
import android.location.Location;

import java.util.LinkedList;
import java.util.List;

public class MainApplication extends Application {

    private List<Location> allReceivedLocations = new LinkedList<>();

    public List<Location> getAllReceivedLocations() {
        return allReceivedLocations;
    }
}
