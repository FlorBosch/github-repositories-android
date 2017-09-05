package com.fbosch.assignment.githubrepositories.data;

import com.fbosch.assignment.githubrepositories.data.model.Repository;
import java.util.List;

import io.reactivex.Flowable;

public interface RepositoryDataSource {

  Flowable<List<Repository>> getRepositories(boolean forceRemote, int page);

  void saveRepository(Repository repository);
}
