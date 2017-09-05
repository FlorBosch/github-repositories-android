package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.fbosch.assignment.githubrepositories.R;
import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.ui.BaseActivity;
import com.fbosch.assignment.githubrepositories.ui.widget.DataContainerLayout;
import com.fbosch.assignment.githubrepositories.ui.widget.EndlessScrollRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class RepositoryListActivity extends BaseActivity implements RepositoryListMvpView {

    @Inject
    RepositoryListPresenter presenter;

    @BindView(R.id.swipe_list)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.repository_list)
    EndlessScrollRecyclerView listView;

    @BindView(R.id.repository_list_container)
    DataContainerLayout dataContainer;

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
        listView.addItemDecoration(new DividerItemDecoration(listView.getContext(), VERTICAL));
        listView.setUp(presenter::loadMoreItems);
        dataContainer.setUp(presenter::loadRepositoryList);
        swipeRefresh.setOnRefreshListener(presenter::loadRepositoryList);
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
        dataContainer.stopLoading();
    }

    @Override
    public void loadMoreRepositories(List<Repository> repositories) {
        adapter.addItems(repositories);
        listView.onNewItemsLoaded();
    }

    @Override
    public void onNetworkError() {
        swipeRefresh.setRefreshing(false);
        dataContainer.displayError();
    }

    @Override
    public void onError(String errorMessage) {
        swipeRefresh.setRefreshing(false);
        dataContainer.stopLoading();
        new AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(errorMessage)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, (dialog, i) -> dialog.dismiss())
                .create()
                .show();
    }

}
