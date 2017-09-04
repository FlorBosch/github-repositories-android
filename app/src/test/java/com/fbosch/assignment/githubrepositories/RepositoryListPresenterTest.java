package com.fbosch.assignment.githubrepositories;

import android.os.Bundle;

import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.network.GithubService;
import com.fbosch.assignment.githubrepositories.ui.repositorylist.RepositoryListMvpView;
import com.fbosch.assignment.githubrepositories.ui.repositorylist.RepositoryListPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryListPresenterTest {

    @Mock
    RepositoryListMvpView mockMvpView;

    @Mock
    GithubService mockService;

    @Mock
    Bundle bundle;

    private RepositoryListPresenter presenter;

    @Rule
    public final RxSchedulersRule rule = new RxSchedulersRule();

    @Before
    public void setUp() {
        presenter = new RepositoryListPresenter(mockService);
        presenter.attachView(mockMvpView);
    }

    @After
    public void tearDown() {
        presenter.detachView();
    }

    @Test
    public void loadRepositoryList() {
        List<Repository> repositories = getRepositories();
        when(mockService.getJakeWhartonRepositories(1, 15))
                .thenReturn(Observable.just(repositories));

        presenter.loadRepositoryList();
        verify(mockMvpView).displayRepositories(repositories);
        verify(mockMvpView, never()).onError(anyString());
    }

    @Test
    public void loadGifListFails() {
        when(mockService.getJakeWhartonRepositories(1, 15))
                .thenReturn(Observable.error(new Throwable("Error message")));

        presenter.loadRepositoryList();
        verify(mockMvpView).onError("Error message");
        verify(mockMvpView, never()).displayRepositories(new ArrayList<>());
    }

    @Test
    public void restoreState() {
        List<Repository> repositories = getRepositories();
        when(mockService.getJakeWhartonRepositories(1, 15))
                .thenReturn(Observable.just(repositories));
        when(bundle.containsKey(RepositoryListPresenter.KEY_REPOSITORY_LIST)).thenReturn(true);
        when(bundle.getParcelableArrayList(RepositoryListPresenter.KEY_REPOSITORY_LIST))
                .thenReturn(new ArrayList<>(repositories));

        presenter.loadRepositoryList();
        presenter.saveState(bundle);
        presenter.restoreState(bundle);
        verify(mockMvpView, new Times(2)).displayRepositories(repositories);
    }

    private List<Repository> getRepositories() {
        List<Repository> response = new ArrayList<>();
        for (long i = 0; i < 30; i++) {
            response.add(new Repository(i, "Name " + i, "Description", "Java", "08/07/2017", 2, 3));
        }
        return response;
    }
}

