package com.fbosch.assignment.githubrepositories;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;


public class RxSchedulersRule implements TestRule {

    private final RxAndroidSchedulersHook schedulersHook = new RxAndroidSchedulersHook() {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    private final Func1<Scheduler, Scheduler> scheduler = scheduler -> Schedulers.immediate();

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

                base.evaluate();

                RxAndroidPlugins.getInstance().reset();
                RxJavaHooks.reset();
            }
        };
    }
}
