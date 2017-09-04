package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.os.Bundle;

import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.network.GithubService;
import com.fbosch.assignment.githubrepositories.ui.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

import static com.fbosch.assignment.githubrepositories.util.NetworkUtil.call;

public class RepositoryListPresenter extends BasePresenter<RepositoryListMvpView> {

    public static final String KEY_REPOSITORY_LIST = "repository_list";

    private List<Repository> repositories = new ArrayList<>();

    private GithubService githubService;

    @Inject
    public RepositoryListPresenter(GithubService service) {
        this.githubService = service;
    }

    public void restoreState(Bundle bundle) {
        assertViewAttached();
        if (bundle == null || !bundle.containsKey(KEY_REPOSITORY_LIST)) {
            loadRepositoryList();
            return;
        }
        showRepositoryList(bundle.getParcelableArrayList(KEY_REPOSITORY_LIST));
    }

    public void saveState(Bundle bundle) {
        bundle.putParcelableArrayList(KEY_REPOSITORY_LIST, new ArrayList<>(this.repositories));
    }

    public void loadRepositoryList() {
        assertViewAttached();
        addSubscription(call(githubService.getJakeWhartonRepositories(1, 15))
                .subscribe(this::showRepositoryList,
                        throwable -> {
                            Timber.e(throwable.getMessage());
                            getView().onError(throwable.getMessage());
                        })
        );
    }

    private void showRepositoryList(List<Repository> repositories) {
        this.repositories = new ArrayList<>(repositories);
        getView().displayRepositories(repositories);
    }
}
