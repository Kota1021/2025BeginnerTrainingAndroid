package com.example.beginnertrainingandroid2025

class RepoRepository(
    private val localDataSource: RepoLocalDataSource,
    private val remoteDataSource: RepoRemoteDataSource,
    ): GithubRepoRepository {
    override suspend fun getRepoList(): List<Repo> {
        return localDataSource.getRepoList().ifEmpty {
            val repoList = remoteDataSource.getRepoList()
            localDataSource.saveRepoList(repoList)
            repoList
        }
    }

    override suspend fun saveAsBookmark(repo: Repo) {
        localDataSource.saveAsBookmark(repo)
    }

    override suspend fun saveAsUnBookmark(repo: Repo) {
        localDataSource.saveAsUnBookmark(repo)
    }

    override suspend fun getBookmarkedRepoList(): List<Repo> {
        return localDataSource.getBookmarkRepoList()
    }
}