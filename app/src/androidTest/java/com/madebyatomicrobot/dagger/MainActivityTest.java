package com.madebyatomicrobot.dagger;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.mockito.Mockito.*;

public class MainActivityTest {

    @Rule public MainApplicationRule applicationRule = new MainApplicationRule();

    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Mock LocationManager locationManager;

    @Test
    public void testNoEnabledProviders() {
        when(locationManager.getAllProviders()).thenReturn(Arrays.<String>asList());

        activityRule.launchActivity(null);

        onView(withId(R.id.message)).check(matches(withText("No location providers enabled.")));
    }

    @Test
    public void testProviderNeverUpdates() {
        when(locationManager.getAllProviders()).thenReturn(Arrays.asList("test"));
        when(locationManager.isProviderEnabled("test")).thenReturn(true);

        activityRule.launchActivity(null);

        onView(withId(R.id.message)).check(matches(withText("Waiting for a location...")));
    }

    @Test
    public void testLocationUpdate() {
        when(locationManager.getAllProviders()).thenReturn(Arrays.asList("test"));
        when(locationManager.isProviderEnabled("test")).thenReturn(true);

        final Location location = new Location("test");
        location.setLatitude(100f);
        location.setLongitude(-50f);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                LocationListener callback = (LocationListener) invocation.getArguments()[3];
                callback.onLocationChanged(location);
                return null;
            }
        }).when(locationManager).requestLocationUpdates(eq("test"), anyLong(), anyFloat(), any(LocationListener.class));

        activityRule.launchActivity(null);

        onView(withId(R.id.message)).check(matches(withText("Total location updates: 1.  You are now at: 100.00, -50.00")));
    }

    @Test
    public void testMultipleLocationUpdate() {
        when(locationManager.getAllProviders()).thenReturn(Arrays.asList("test"));
        when(locationManager.isProviderEnabled("test")).thenReturn(true);

        final Location location = new Location("test");
        location.setLatitude(100f);
        location.setLongitude(-50f);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                LocationListener callback = (LocationListener) invocation.getArguments()[3];
                callback.onLocationChanged(location);
                callback.onLocationChanged(location);
                callback.onLocationChanged(location);
                return null;
            }
        }).when(locationManager).requestLocationUpdates(eq("test"), anyLong(), anyFloat(), any(LocationListener.class));

        activityRule.launchActivity(null);

        onView(withId(R.id.message)).check(matches(withText("Total location updates: 3.  You are now at: 100.00, -50.00")));
    }
}
