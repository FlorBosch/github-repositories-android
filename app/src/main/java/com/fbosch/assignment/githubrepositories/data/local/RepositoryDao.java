package com.fbosch.assignment.githubrepositories.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.fbosch.assignment.githubrepositories.data.model.Repository;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    Flowable<List<Repository>> getAllRepositories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRepository(Repository repository);

}
