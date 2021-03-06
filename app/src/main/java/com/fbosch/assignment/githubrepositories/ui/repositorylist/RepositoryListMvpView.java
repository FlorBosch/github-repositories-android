package com.fbosch.assignment.githubrepositories.ui.repositorylist;


import android.support.annotation.StringRes;

import com.fbosch.assignment.githubrepositories.data.model.Repository;
import com.fbosch.assignment.githubrepositories.ui.MvpView;

import java.util.List;

public interface RepositoryListMvpView extends MvpView {

    void displayRepositories(List<Repository> repositories);
    void loadMoreRepositories(List<Repository> repositories);
    void onError(@StringRes int message);
    void onNetworkError();

}
