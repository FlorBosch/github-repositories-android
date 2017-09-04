package com.fbosch.assignment.githubrepositories.injection;

import android.content.Context;

import com.fbosch.assignment.githubrepositories.RepositoryApplication;
import com.fbosch.assignment.githubrepositories.injection.component.DaggerTestComponent;
import com.fbosch.assignment.githubrepositories.injection.component.TestComponent;
import com.fbosch.assignment.githubrepositories.injection.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public class TestComponentRule implements TestRule {

    private final RxAndroidSchedulersHook schedulersHook = new RxAndroidSchedulersHook() {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    private final Func1<Scheduler, Scheduler> scheduler = scheduler -> Schedulers.immediate();

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
                RxAndroidPlugins.getInstance().reset();
                RxAndroidPlugins.getInstance().registerSchedulersHook(schedulersHook);

                RxJavaHooks.reset();
                RxJavaHooks.setOnIOScheduler(scheduler);
                RxJavaHooks.setOnNewThreadScheduler(scheduler);

                RepositoryApplication application = RepositoryApplication.get(context);
                application.setComponent(testComponent);
                base.evaluate();
                application.setComponent(null);

                RxAndroidPlugins.getInstance().reset();
                RxJavaHooks.reset();
            }
        };
    }
}
