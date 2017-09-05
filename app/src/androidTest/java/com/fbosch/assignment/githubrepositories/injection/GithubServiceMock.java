package com.fbosch.assignment.githubrepositories.injection;

import com.fbosch.assignment.githubrepositories.data.RepositoryDataSource;
import com.fbosch.assignment.githubrepositories.data.model.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static com.fbosch.assignment.githubrepositories.util.Constants.ITEMS_PER_PAGE;

public class GithubServiceMock implements RepositoryDataSource {

    @Override
    public Flowable<List<Repository>> getRepositories(boolean forceRemote, int page) {
        return Flowable.just(getRepositoryList(page, ITEMS_PER_PAGE));
    }

    @Override
    public void saveRepository(Repository repository) {
    }

    public static List<Repository> getRepositoryList(int page, int perPage) {
        List<Repository> repositories = new ArrayList<>();
        for (long i = ((page - 1) * perPage); i < page * perPage; i++) {
            repositories.add(
                    new Repository(i, "Name " + i, "Description", "Java", "08/07/2017", 2, 3));
        }
        return repositories;
    }
}
