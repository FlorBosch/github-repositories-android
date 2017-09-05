package com.fbosch.assignment.githubrepositories.data.network;


import com.fbosch.assignment.githubrepositories.data.model.Repository;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("/users/JakeWharton/repos")
    Flowable<List<Repository>> getJakeWhartonRepositories(@Query("page") final int page,
                                                          @Query("per_page") final int perPage);
}
