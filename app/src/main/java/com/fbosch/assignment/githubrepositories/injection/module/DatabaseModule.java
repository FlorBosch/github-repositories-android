package com.fbosch.assignment.githubrepositories.injection.module;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.fbosch.assignment.githubrepositories.BuildConfig;
import com.fbosch.assignment.githubrepositories.data.local.RepositoryDao;
import com.fbosch.assignment.githubrepositories.data.local.RepositoryDatabase;
import com.fbosch.assignment.githubrepositories.injection.ApplicationContext;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DATABASE = "database_name";

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return BuildConfig.DATABASE_NAME;
    }

    @Provides
    @Singleton
    RepositoryDatabase provideRepositoryDatabase(@ApplicationContext Context context,
                                                 @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, RepositoryDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    RepositoryDao provideRepositoryDao(RepositoryDatabase repositoryDatabase) {
        return repositoryDatabase.repositoryDao();
    }

}
