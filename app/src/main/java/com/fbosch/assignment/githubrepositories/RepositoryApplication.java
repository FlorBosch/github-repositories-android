package com.fbosch.assignment.githubrepositories;

import android.app.Application;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.injection.component.DaggerUiComponent;
import com.fbosch.assignment.githubrepositories.injection.component.UiComponent;
import com.fbosch.assignment.githubrepositories.injection.module.ApplicationModule;

import timber.log.Timber;


public class RepositoryApplication extends Application {

    private UiComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        applicationComponent = DaggerUiComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static RepositoryApplication get(Context context) {
        return (RepositoryApplication) context.getApplicationContext();
    }

    public UiComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(UiComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
