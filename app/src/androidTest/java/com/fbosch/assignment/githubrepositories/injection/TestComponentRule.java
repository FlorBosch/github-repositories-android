package com.fbosch.assignment.githubrepositories.injection;

import android.content.Context;

import com.fbosch.assignment.githubrepositories.RepositoryApplication;
import com.fbosch.assignment.githubrepositories.injection.component.DaggerTestComponent;
import com.fbosch.assignment.githubrepositories.injection.component.TestComponent;
import com.fbosch.assignment.githubrepositories.injection.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class TestComponentRule implements TestRule {

    private io.reactivex.Scheduler immediate = new io.reactivex.Scheduler() {
        @Override
        public Disposable scheduleDirect(@NonNull Runnable run, long delay,
                                         @NonNull TimeUnit unit) {
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    private final TestComponent testComponent;
    private final Context context;

    public TestComponentRule(Context context) {
        this.context = context;
        testComponent = DaggerTestComponent.builder()
                .applicationTestModule(
                        new ApplicationTestModule(RepositoryApplication.get(context)))
                .build();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
                RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);

                try {
                    RepositoryApplication application = RepositoryApplication.get(context);
                    application.setComponent(testComponent);
                    base.evaluate();
                    application.setComponent(null);
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }

}
