package com.fbosch.assignment.githubrepositories.injection.module;


import android.app.Application;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.injection.ApplicationContext;
import com.fbosch.assignment.githubrepositories.injection.GithubServiceMock;
import com.fbosch.assignment.githubrepositories.network.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationTestModule {

    protected final Application application;

    public ApplicationTestModule(Application application) {
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

    @Provides
    @Singleton
    GithubService provideGithubService() {
        return new GithubServiceMock();
    }

}