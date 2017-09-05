package com.fbosch.assignment.githubrepositories.injection.module;


import android.app.Application;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.data.RepositoryDataSource;
import com.fbosch.assignment.githubrepositories.data.local.Local;
import com.fbosch.assignment.githubrepositories.data.local.RepositoryLocalDataSource;
import com.fbosch.assignment.githubrepositories.data.network.Remote;
import com.fbosch.assignment.githubrepositories.data.network.RepositoryRemoteDataSource;
import com.fbosch.assignment.githubrepositories.injection.ApplicationContext;
import com.fbosch.assignment.githubrepositories.injection.GithubServiceMock;

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
    @Local
    @Singleton
    public RepositoryDataSource provideLocalDataSource(RepositoryLocalDataSource localDataSource) {
        return new GithubServiceMock();
    }

    @Provides
    @Remote
    @Singleton
    public RepositoryDataSource provideRemoteDataSource(RepositoryRemoteDataSource remoteDataSource) {
        return new GithubServiceMock();
    }

}