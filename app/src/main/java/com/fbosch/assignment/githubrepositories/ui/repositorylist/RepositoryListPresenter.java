package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.os.Bundle;

import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.ui.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RepositoryListPresenter extends BasePresenter<RepositoryListMvpView> {

    private static final String KEY_REPOSITORY_LIST = "repository_list";

    private List<Repository> repositories = new ArrayList<>();

    @Inject
    public RepositoryListPresenter() {
    }

    void restoreState(Bundle bundle) {
        assertViewAttached();
        if (bundle == null || !bundle.containsKey(KEY_REPOSITORY_LIST)) {
            loadRepositoryList();
            return;
        }
        showRepositoryList(bundle.getParcelableArrayList(KEY_REPOSITORY_LIST));
    }

    void saveState(Bundle bundle) {
        bundle.putParcelableArrayList(KEY_REPOSITORY_LIST, new ArrayList<>(this.repositories));
    }

    void loadRepositoryList() {
        assertViewAttached();
        List<Repository> reponse = new ArrayList<>();
        for (long i = 0; i < 30; i++) {
            reponse.add(new Repository(
                    i, "Name " + i, "Description", "Java", "12/03/2016", 2, 3));
        }
        showRepositoryList(reponse);
    }

    private void showRepositoryList(List<Repository> repositories) {
        this.repositories = new ArrayList<>(repositories);
        getView().displayRepositories(repositories);
    }
}
