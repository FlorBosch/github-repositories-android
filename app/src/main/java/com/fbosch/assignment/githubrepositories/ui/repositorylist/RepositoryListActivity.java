package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.os.Bundle;

import com.fbosch.assignment.githubrepositories.R;
import com.fbosch.assignment.githubrepositories.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class RepositoryListActivity extends BaseActivity implements RepositoryListMvpView {

    @Inject
    RepositoryListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
