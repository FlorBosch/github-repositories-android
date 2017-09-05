package com.fbosch.assignment.githubrepositories.util;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NetworkUtil {

    public static <T> Flowable<T> call(Flowable<T> flowable) {
        return flowable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
