package com.fbosch.assignment.githubrepositories.data;

import com.fbosch.assignment.githubrepositories.data.local.Local;
import com.fbosch.assignment.githubrepositories.data.model.Repository;
import com.fbosch.assignment.githubrepositories.data.network.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GithubRepositoriesRepository implements RepositoryDataSource {

    private RepositoryDataSource remoteDataSource;
    private RepositoryDataSource localDataSource;

    @Inject
    public GithubRepositoriesRepository(@Local RepositoryDataSource localDataSource,
                                        @Remote RepositoryDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Flowable<List<Repository>> getRepositories(boolean forceRemote, int page) {
        if (!forceRemote) {
            return localDataSource.getRepositories(false, page);
        }
        return remoteDataSource.getRepositories(true, page)
                .doOnEach(notification -> {
                    if (notification.isOnNext()) {
                        saveDataToLocal(notification.getValue());
                    }
                });
    }

    @Override
    public void saveRepository(Repository repository) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    private void saveDataToLocal(List<Repository> repositories) {
        if (repositories == null) {
            return;
        }
        for (Repository repository : repositories) {
            localDataSource.saveRepository(repository);
        }
    }

}
