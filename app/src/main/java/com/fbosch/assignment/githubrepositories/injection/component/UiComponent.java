package com.fbosch.assignment.githubrepositories.injection.component;

import android.app.Application;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.injection.module.DatabaseModule;
import com.fbosch.assignment.githubrepositories.injection.module.NetworkModule;
import com.fbosch.assignment.githubrepositories.injection.module.RepositoryModule;
import com.fbosch.assignment.githubrepositories.ui.repositorylist.RepositoryListActivity;
import com.fbosch.assignment.githubrepositories.injection.ApplicationContext;
import com.fbosch.assignment.githubrepositories.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class,
        DatabaseModule.class})
public interface UiComponent {

    @ApplicationContext
    Context context();
    Application application();

    void inject(RepositoryListActivity repositoryListActivity);
}