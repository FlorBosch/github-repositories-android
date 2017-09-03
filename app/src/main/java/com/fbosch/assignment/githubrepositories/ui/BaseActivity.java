package com.fbosch.assignment.githubrepositories.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fbosch.assignment.githubrepositories.RepositoryApplication;
import com.fbosch.assignment.githubrepositories.injection.component.UiComponent;


public abstract class BaseActivity extends AppCompatActivity {

    private UiComponent uiComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiComponent = RepositoryApplication.get(this).getComponent();
    }

    public UiComponent activityComponent() {
        return uiComponent;
    }

}
