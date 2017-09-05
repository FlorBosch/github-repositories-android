package com.fbosch.assignment.githubrepositories.injection.module;

import com.fbosch.assignment.githubrepositories.data.RepositoryDataSource;
import com.fbosch.assignment.githubrepositories.data.local.Local;
import com.fbosch.assignment.githubrepositories.data.local.RepositoryLocalDataSource;
import com.fbosch.assignment.githubrepositories.data.network.Remote;
import com.fbosch.assignment.githubrepositories.data.network.RepositoryRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RepositoryModule {

  @Provides
  @Local
  @Singleton
  public RepositoryDataSource provideLocalDataSource(RepositoryLocalDataSource localDataSource) {
    return localDataSource;
  }

  @Provides
  @Remote
  @Singleton
  public RepositoryDataSource provideRemoteDataSource(RepositoryRemoteDataSource remoteDataSource) {
    return remoteDataSource;
  }

}
