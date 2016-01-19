package com.madebyatomicrobot.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        AndroidModule.class,
        AppModule.class
})
@Singleton
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
