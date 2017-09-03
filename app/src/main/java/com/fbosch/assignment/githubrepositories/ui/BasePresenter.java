package com.fbosch.assignment.githubrepositories.ui;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T view;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        subscriptions.clear();
    }

    public T getView() {
        return this.view;
    }

    protected void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    protected void assertViewAttached() {
        if (!this.isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Call Presenter.attachView(MvpView)");
        }
    }
}
