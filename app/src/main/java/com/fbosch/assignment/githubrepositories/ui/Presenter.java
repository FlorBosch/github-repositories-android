package com.fbosch.assignment.githubrepositories.ui;

public interface Presenter<T extends MvpView> {

    void attachView(T view);

    void detachView();
}
