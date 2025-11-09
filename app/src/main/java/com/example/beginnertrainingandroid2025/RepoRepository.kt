package com.example.beginnertrainingandroid2025

class RepoRepository(
    private val remoteDataSource: RepoRemoteDataSource,
    ) {
    suspend fun getRepoList(): List<Repo> = remoteDataSource.getRepoList()
}