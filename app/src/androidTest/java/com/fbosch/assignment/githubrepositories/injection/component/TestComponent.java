package com.fbosch.assignment.githubrepositories.injection.component;

import com.fbosch.assignment.githubrepositories.injection.module.ApplicationTestModule;
import com.fbosch.assignment.githubrepositories.injection.module.DatabaseModule;
import com.fbosch.assignment.githubrepositories.injection.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationTestModule.class})
public interface TestComponent extends UiComponent {

}
