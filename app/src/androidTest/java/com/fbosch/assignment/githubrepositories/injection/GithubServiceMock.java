package com.fbosch.assignment.githubrepositories.injection;

import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.network.GithubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

public class GithubServiceMock implements GithubService {

    @Override
    public Observable<List<Repository>> getJakeWhartonRepositories(@Query("page") int page,
                                                                   @Query("per_page") int perPage) {
        return Observable.just(getRepositoryList(page, perPage));
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
