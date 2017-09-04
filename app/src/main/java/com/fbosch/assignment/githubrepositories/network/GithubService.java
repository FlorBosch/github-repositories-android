package com.fbosch.assignment.githubrepositories.network;


import com.fbosch.assignment.githubrepositories.model.Repository;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubService {

    @GET("/users/JakeWharton/repos")
    Observable<List<Repository>> getJakeWhartonRepositories(@Query("page") final int page,
                                                            @Query("per_page") final int perPage);
}
