package com.madebyatomicrobot.dagger;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class MainApplicationRule extends DaggerMockRule<ApplicationComponent> {
    public MainApplicationRule() {
        super(ApplicationComponent.class,
                new AndroidModule(null),
                new AppModule());

        set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
            @Override
            public void setComponent(ApplicationComponent component) {
                Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
                Context context = instrumentation.getTargetContext();
                MainApplication app = (MainApplication) context.getApplicationContext();
                app.setComponent(component);
            }
        });
    }
}