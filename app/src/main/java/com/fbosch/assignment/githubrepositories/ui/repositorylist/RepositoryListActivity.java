package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fbosch.assignment.githubrepositories.R;
import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.ui.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryListActivity extends BaseActivity implements RepositoryListMvpView {

    @Inject
    RepositoryListPresenter presenter;

    @BindView(R.id.swipe_list)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.repository_list)
    RecyclerView listView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RepositoryListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
        toolbar.setTitle(R.string.app_name);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setHasFixedSize(true);
        presenter.restoreState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void displayRepositories(List<Repository> repositories) {
        swipeRefresh.setRefreshing(false);
        adapter = new RepositoryListViewAdapter(repositories);
        listView.swapAdapter(adapter, false);
    }

    @Override
    public void loadMoreRepositories(List<Repository> repositories) {

    }
}
