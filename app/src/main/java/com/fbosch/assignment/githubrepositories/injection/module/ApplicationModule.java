package com.fbosch.assignment.githubrepositories.injection.module;

import android.app.Application;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.injection.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

}