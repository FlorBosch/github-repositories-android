package com.fbosch.assignment.githubrepositories.data.network;

import com.fbosch.assignment.githubrepositories.data.RepositoryDataSource;
import com.fbosch.assignment.githubrepositories.data.model.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

import static com.fbosch.assignment.githubrepositories.util.Constants.ITEMS_PER_PAGE;

public class RepositoryRemoteDataSource implements RepositoryDataSource {

  private GithubService githubService;

  @Inject
  public RepositoryRemoteDataSource(GithubService githubService) {
    this.githubService = githubService;
  }

  @Override
  public Flowable<List<Repository>> getRepositories(boolean forceRemote, int page) {
    return githubService.getJakeWhartonRepositories(page, ITEMS_PER_PAGE);
  }

  @Override
  public void saveRepository(Repository repository) {
    throw new UnsupportedOperationException("Unsupported operation");
  }

}
