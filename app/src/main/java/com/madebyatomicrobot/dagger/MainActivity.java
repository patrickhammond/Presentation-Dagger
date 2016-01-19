package com.madebyatomicrobot.dagger;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> providers = locationManager.getAllProviders();
        boolean atLeastOneProviderEnabled = false;
        for (String provider : providers) {
            if (locationManager.isProviderEnabled(provider)) {
                locationManager.requestLocationUpdates(provider, 0, 0, this);
                atLeastOneProviderEnabled = true;
            }
        }

        if (!atLeastOneProviderEnabled) {
            findMainFragment().showMessage("No location providers enabled.");
        }
    }

    @Override
    protected void onPause() {
        locationManager.removeUpdates(this);
        super.onPause();
    }

    private void locationUpdated(Location location) {
        List<Location> locations = ((MainApplication) getApplication()).getAllReceivedLocations();
        locations.add(location);

        String message = String.format(
                "Total location updates: %d.\n\nYou are now at: %.2f, %.2f",
                locations.size(),
                location.getLatitude(),
                location.getLongitude());
        findMainFragment().showMessage(message);
    }

    public void onLocationChanged(Location location) {
        locationUpdated(location);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Don't care
    }

    public void onProviderEnabled(String provider) {
        // Don't care
    }

    public void onProviderDisabled(String provider) {
        // Don't care
    }

    private MainActivityFragment findMainFragment() {
        return (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }
}
