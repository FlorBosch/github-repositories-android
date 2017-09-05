package com.fbosch.assignment.githubrepositories.data.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.fbosch.assignment.githubrepositories.data.model.Repository;

@Database(entities = {Repository.class}, version = 1, exportSchema = false)
public abstract class RepositoryDatabase extends RoomDatabase {

    public abstract RepositoryDao repositoryDao();
}